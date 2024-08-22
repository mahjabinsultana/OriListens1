package com.example.OriListens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SencondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SecondRV_adapter secondRV_adapter;
    ArrayList<SecondRV_item> secondRV_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sencond);
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this);
        }
        TextView textView = findViewById(R.id.textView2);
        // Retrieve the Intent that started this activity
        Intent intent = getIntent();
        DatabaseReference database;
        // Retrieve the String using the key you used in the sender activity
        String receivedMessage = intent.getStringExtra("messageKey");
        textView.setText(receivedMessage);


        recyclerView = findViewById(R.id.ContactRecyclerView);
        database = FirebaseDatabase.getInstance().getReference().child("Video").child(receivedMessage);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        secondRV_items = new ArrayList<>();
        secondRV_adapter = new SecondRV_adapter(secondRV_items, this);
        recyclerView.setAdapter(secondRV_adapter);

        Log.d("Tag Nishaaaaaaaaa", receivedMessage);
        System.out.println("outside database code");
        /*database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                    String videoKey = videoSnapshot.getKey();
                    String title = videoSnapshot.child("title").getValue(String.class);
                    String link = videoSnapshot.child("link").getValue(String.class);

                    // Do something with the retrieved data (e.g., display in a list)
                    Log.d("FirebaseData", "VideoKey: " + videoKey + ", Title: " + title + ", Link: " + link);
                    VideoInfo videoInfo = new VideoInfo(link);
                    videoInfo.createThumbnailUrl();//"https://img.youtube.com/vi/BVJkf8IuRjE/hqdefault.jpg"
                    SecondRV_item secondRV_item = new SecondRV_item(videoInfo.getThumbnailUrl(), title);
                    secondRV_items.add(secondRV_item);
                }
                secondRV_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors here
                Log.e("FirebaseError", "Failed to read value.", databaseError.toException());
            }
        });*/
        database.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("Tag Nishaaaaaaaaa", "flag1");
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String url = dataSnapshot.child("link").getValue(String.class);
                    String title = dataSnapshot.child("title").getValue(String.class);
                    Log.d("Tag Nishaaaaaaaaa", url+"-----------"+title);
                    VideoInfo videoInfo = new VideoInfo(url);
                    videoInfo.createThumbnailUrl();//"https://img.youtube.com/vi/BVJkf8IuRjE/hqdefault.jpg"
                    videoInfo.createUrl();
                    SecondRV_item secondRV_item = new SecondRV_item(videoInfo.getThumbnailUrl(), title, videoInfo.getUrl());
                    secondRV_items.add(secondRV_item);
                }
                secondRV_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}