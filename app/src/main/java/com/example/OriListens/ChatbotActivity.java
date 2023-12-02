package com.example.OriListens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatbotActivity extends AppCompatActivity {

    private RecyclerView chatsRV;
    private EditText userMsgEdt;
    private FloatingActionButton sendMsgFAB;
    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    private ArrayList<ChatsModal> chatsModalArrayList;
    private ChatRVAdapter chatRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_chatbot);
        chatsRV = findViewById(R.id.idRVChats);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        sendMsgFAB = findViewById(R.id.idFABSend);
        chatsModalArrayList = new ArrayList<>();

        chatRVAdapter = new ChatRVAdapter(chatsModalArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatRVAdapter);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userMsgEdt.getText().toString().isEmpty()){
                    Toast.makeText(ChatbotActivity.this, "Please enter your message", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());
                userMsgEdt.setText("");

            }
        });
        // navigationview start
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    // Handle the home action
                    Toast.makeText(ChatbotActivity.this,"HOME",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),homepage.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.nav_chatbot) {
                    // Handle the gallery action
                    Toast.makeText(ChatbotActivity.this,"Chatbot",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ChatbotActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (id == R.id.nav_videos) {
                    // Handle the gallery action
                    Toast.makeText(ChatbotActivity.this,"Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_contact) {
                    // Handle the gallery action
                    Toast.makeText(ChatbotActivity.this,"Contact",Toast.LENGTH_SHORT).show();

                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    private void getResponse(String message){
        chatsModalArrayList.add(new ChatsModal(message, "null", USER_KEY));
        chatRVAdapter.notifyDataSetChanged();
        String url="http://192.168.0.5:5000/chat/"+message;
        String BASE_URL = "http://192.168.0.5:5000";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MsgModal> call = retrofitAPI.getMessage(url);
       // Call<MsgModal> call = retrofitAPI.getChatBotReply(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if(response.isSuccessful())
                {
                    MsgModal modal = response.body();
                    chatsModalArrayList.add(new ChatsModal(modal.getChatBotReply(), modal.getVideoUrl(), BOT_KEY));
                    chatRVAdapter.notifyDataSetChanged();

                }
                else{
                    chatsModalArrayList.add(new ChatsModal("Sorry I don't understand. Can you try again?", "null", BOT_KEY));
                    chatRVAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatsModalArrayList.add(new ChatsModal("Sorry I don't understand. Can you try again?", "null", BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
                System.out.println("Exception: " + t.toString());
            }
        });
    }
}