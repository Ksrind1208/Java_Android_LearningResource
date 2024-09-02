package com.example.androidfirebase;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView textContent;
    private DatabaseReference sensorRef;
    private DatabaseReference ledRef;
    private static final String TAG = "MainActivity";

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

        textContent=findViewById(R.id.textContent);

        // Initialize Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://esp32-first-6425d-default-rtdb.asia-southeast1.firebasedatabase.app/");
        sensorRef = database.getReference("Sensor/value");
        ledRef = database.getReference("Led/status");

        // Read from the database
        sensorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the value from the database
                String sensorValue = dataSnapshot.getValue().toString();
                textContent.setText(sensorValue);
                Log.d(TAG, "Sensor Value: " + sensorValue);
                Toast.makeText(MainActivity.this, "Sensor Value: " + sensorValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        // Write to the database
        findViewById(R.id.buttonOn).setOnClickListener(v -> {
            ledRef.setValue(true);
            Toast.makeText(MainActivity.this, "LED turned ON", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.buttonOff).setOnClickListener(v -> {
            ledRef.setValue(false);
            Toast.makeText(MainActivity.this, "LED turned OFF", Toast.LENGTH_SHORT).show();
        });
    }
}
