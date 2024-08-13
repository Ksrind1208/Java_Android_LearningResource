package com.example.demo;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ConstraintLayout ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn=(Button) findViewById(R.id.button);
        ct=(ConstraintLayout) findViewById(R.id.main);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMenu();
//            }
//        });

        registerForContextMenu(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContextMenu(btn);
            }
        });

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_demo,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_demo,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==R.id.settingMenu){
//            Toast.makeText(MainActivity.this,"Setting selected",Toast.LENGTH_SHORT).show();
//        }
//        if(item.getItemId()==R.id.searchMenu){
//            Toast.makeText(MainActivity.this,"Search selected",Toast.LENGTH_SHORT).show();
//        }
//        if(item.getItemId()==R.id.contactMenu){
//            Toast.makeText(MainActivity.this,"Contact selected",Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    private void showMenu(){
//        PopupMenu popupMenu=new PopupMenu(this,btn);
//        popupMenu.getMenuInflater().inflate(R.menu.menu_demo,popupMenu.getMenu());
//        popupMenu.show();
//    }
}