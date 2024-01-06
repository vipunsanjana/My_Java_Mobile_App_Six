package com.example.myappsix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Button btn_map;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_map = findViewById(R.id.btn_map);
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        nav_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.nav_map){

                    Intent intent = new Intent(getApplicationContext(),GooglleMapsActivity.class);
                    startActivity(intent);
                    drawer_layout.closeDrawer(GravityCompat.START);

                } else if (id == R.id.nav_home) {

                    Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                    drawer_layout.closeDrawer(GravityCompat.START);

                }else if (id == R.id.nav_one) {

                    Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();
                    drawer_layout.closeDrawer(GravityCompat.START);

                }else if (id == R.id.nav_two) {

                    Toast.makeText(getApplicationContext(), "Users", Toast.LENGTH_SHORT).show();
                    drawer_layout.closeDrawer(GravityCompat.START);

                }

                return true;
            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), GooglleMapsActivity.class);
                startActivity(intent);

            }
        });
    }
}