package com.example.OriListens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    ArrayList<ContactRV_item> contactRV_items;
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_contact);
        recyclerView = findViewById(R.id.ContactRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       contactRV_items = new ArrayList<ContactRV_item>();

       contactRV_items.add(new ContactRV_item(R.drawable.contact1,getString(R.string.c1Name),getString(R.string.c1Location),getString(R.string.c1Contact)));
        contactRV_items.add(new ContactRV_item(R.drawable.contact2,getString(R.string.c2Name),getString(R.string.c2Location),getString(R.string.c2Contact)));
        contactRV_items.add(new ContactRV_item(R.drawable.contact3,getString(R.string.c3Name),getString(R.string.c3Location),getString(R.string.c3Contact)));
        contactRV_items.add(new ContactRV_item(R.drawable.contact4,getString(R.string.c4Name),getString(R.string.c4Location),getString(R.string.c4Contact)));
        contactRV_items.add(new ContactRV_item(R.drawable.contact5,getString(R.string.c5Name),getString(R.string.c5Location),getString(R.string.c5Contact)));
        contactRV_items.add(new ContactRV_item(R.drawable.contact6,getString(R.string.c6Name),getString(R.string.c6Location),getString(R.string.c6Contact)));
        contactRV_items.add(new ContactRV_item(R.drawable.contact7,getString(R.string.c7Name),getString(R.string.c7Location),getString(R.string.c7Contact)));
        contactRV_items.add(new ContactRV_item(R.drawable.contact8,getString(R.string.c8Name),getString(R.string.c8Location),getString(R.string.c8Contact)));



        ContactRV_adapter contactRV_adapter = new ContactRV_adapter(contactRV_items, this);
        recyclerView.setAdapter(contactRV_adapter);


        // navigationview start
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    // Handle the home action
                    Toast.makeText(ContactActivity.this,"HOME",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),homepage.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.nav_chatbot) {
                    // Handle the gallery action
                    Toast.makeText(ContactActivity.this,"Chatbot",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ChatbotActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (id == R.id.nav_videos) {
                    // Handle the gallery action
                    Toast.makeText(ContactActivity.this,"Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_contact) {
                    // Handle the gallery action
                    Toast.makeText(ContactActivity.this,"Contact",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ContactActivity.class);
                    startActivity(intent);
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        // navview end

    }
}