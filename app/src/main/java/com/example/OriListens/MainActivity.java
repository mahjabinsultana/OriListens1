package com.example.OriListens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.OriListens.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainListener {
    ArrayList<MainRV_item> mainRV_items;
    FloatingActionButton button;
    RecyclerView recyclerView;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);
        recyclerView = findViewById(R.id.mainRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mainRV_items = new ArrayList<MainRV_item>();
        mainRV_items.add(new MainRV_item(R.drawable.depression2, "Anxiety"));
        mainRV_items.add(new MainRV_item(R.drawable.depression, "Depression"));
        mainRV_items.add(new MainRV_item(R.drawable.anxiety, "Panic Attack"));
        mainRV_items.add(new MainRV_item(R.drawable.selfcare, "Insomnia"));
        mainRV_items.add(new MainRV_item(R.drawable.selfcare, "Stress"));
        mainRV_items.add(new MainRV_item(R.drawable.meditation, "Meditation"));
        mainRV_items.add(new MainRV_item(R.drawable.selfcare, "Selfcare"));

        MainRV_adapter mainRV_adapter = new MainRV_adapter(mainRV_items, this, this );
        recyclerView.setAdapter(mainRV_adapter);

        //database = FirebaseDatabase.getInstance().getReference("Vidoes");



        // navigationview start
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    // Handle the home action
                    Toast.makeText(MainActivity.this,"HOME",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),homepage.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.nav_chatbot) {
                    // Handle the gallery action
                    Toast.makeText(MainActivity.this,"Chatbot",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ChatbotActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (id == R.id.nav_videos) {
                    // Handle the gallery action
                    Toast.makeText(MainActivity.this,"Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_contact) {
                    // Handle the gallery action
                    Toast.makeText(MainActivity.this,"Contact",Toast.LENGTH_SHORT).show();

                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        // navview end


    }
    public void openNewActivity(){
        Intent intent = new Intent(this, ChatbotActivity.class);
        startActivity(intent);

    }

    @Override
    public void onItemClicked(MainRV_item mainRV_item) {
        Intent intent = new Intent(this, SencondActivity.class);
        intent.putExtra("messageKey", mainRV_item.getText());
        startActivity(intent);
    }





}