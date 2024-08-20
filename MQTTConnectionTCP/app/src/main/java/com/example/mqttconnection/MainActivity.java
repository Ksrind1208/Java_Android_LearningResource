package com.example.mqttconnection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.MqttWebSocketConfig;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.UUID;



public class MainActivity extends AppCompatActivity {
    private Mqtt5AsyncClient mqtt5AsyncClient;
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
        connectToMQTT();
    }
    private void connectToMQTT() {
//        String serverHost = "mqtt.eclipseprojects.io";
//        int serverPort = 8883;
//        String username = "";
//        String password = "";
//        String clientId = "ClientID_" + System.currentTimeMillis();
//        Log.e("Current ID:", clientId);

        String serverHost = "broker.hivemq.com";
        int serverPort = 1883;
        String username = "";
        String password = "";
        String clientId = "ClientID_" + System.currentTimeMillis();
        Log.e("Current ID:", clientId);

        mqtt5AsyncClient = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(serverHost)
                .serverPort(serverPort)
//                .sslWithDefaultConfig()
                .buildAsync();

        mqtt5AsyncClient.connectWith()
                .simpleAuth()
                .username(username)
                .password(password.getBytes(StandardCharsets.UTF_8))
                .applySimpleAuth()
                .send()
                .whenComplete((connAck, throwable) -> {
                    if (throwable != null) {
                        Log.e("MQTT", "Connection failed", throwable);
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Connection failed", Toast.LENGTH_SHORT).show());
                    } else {
                        Log.i("MQTT", "Connected successfully");
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Connected successfully", Toast.LENGTH_SHORT).show());
                    }
                });
    }
}