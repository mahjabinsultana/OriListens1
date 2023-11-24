package com.example.OriListens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class homepage extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_homepage);

        // navigationview start
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    // Handle the home action
                    Toast.makeText(homepage.this,"HOME",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),homepage.class);
                    startActivity(intent);
                } else if (id == R.id.nav_chatbot) {
                    // Handle the gallery action
                    Toast.makeText(homepage.this,"Chatbot",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ChatbotActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_videos) {
                    // Handle the gallery action
                    Toast.makeText(homepage.this,"Videos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }
                else if (id == R.id.nav_contact) {
                    // Handle the gallery action
                    Toast.makeText(homepage.this,"Contact",Toast.LENGTH_SHORT).show();

                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        button = findViewById(R.id.gotochatbotbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChatbotActivity.class);
                startActivity(intent);
            }
        });
    }
}