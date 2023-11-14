package com.example.OriListens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.OriListens.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
        setContentView(R.layout.activity_main);
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


        button = findViewById(R.id.idChatbot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

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