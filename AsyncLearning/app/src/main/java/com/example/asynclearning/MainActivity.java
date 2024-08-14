package com.example.asynclearning;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    ExecutorService executorService= Executors.newFixedThreadPool(2);

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

        btn=findViewById(R.id.button);
        txt=findViewById(R.id.textView);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(1000);
                                Log.e("Day la chu de","run: ");
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try{
                                Thread.sleep(1000);
                                Log.e("Day la chu de","task2");
                            }catch (InterruptedException e){
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText("hello 123");
                    }
                });
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đảm bảo ExecutorService được shutdown khi Activity bị hủy
        executorService.shutdown();
    }
}