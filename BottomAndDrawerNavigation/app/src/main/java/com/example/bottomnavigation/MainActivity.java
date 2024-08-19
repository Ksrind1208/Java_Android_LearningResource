package com.example.bottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton buttonMenu;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,systemBars.bottom);
            return insets;
        });
        drawerLayout=findViewById(R.id.main);
        buttonMenu=findViewById(R.id.buttonMenu);
        navigationView=findViewById(R.id.navigationView);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemid=item.getItemId();
                if(itemid==R.id.navMenu){
                    Toast.makeText(MainActivity.this,"Menu selected",Toast.LENGTH_SHORT).show();
                }
                if(itemid==R.id.navCart){
                    Toast.makeText(MainActivity.this,"Cart selected",Toast.LENGTH_SHORT).show();
                }
                if(itemid==R.id.navContact){
                    Toast.makeText(MainActivity.this,"Contact selected",Toast.LENGTH_SHORT).show();
                }
                if(itemid==R.id.navFavourite){
                    Toast.makeText(MainActivity.this,"Favourite selected",Toast.LENGTH_SHORT).show();
                }
                if(itemid==R.id.navOrders){
                    Toast.makeText(MainActivity.this,"Orders selected",Toast.LENGTH_SHORT).show();
                }
                if(itemid==R.id.navFeedback){
                    Toast.makeText(MainActivity.this,"Feedback selected",Toast.LENGTH_SHORT).show();
                }
                if(itemid==R.id.navHistory){
                    Toast.makeText(MainActivity.this,"History selected",Toast.LENGTH_SHORT).show();
                }
                if(itemid==R.id.navTerms){
                    Toast.makeText(MainActivity.this,"Terms selected",Toast.LENGTH_SHORT).show();
                }
                if(itemid==R.id.navShare){
                    Toast.makeText(MainActivity.this,"Share selected",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
}