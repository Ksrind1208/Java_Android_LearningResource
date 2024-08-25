package com.example.androidble;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_BLUETOOTH_SCAN_PERMISSION = 2;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothGatt bluetoothGatt;
    private TextView textServiceUUID;
    private TextView textCharacteristicUUID;
    private TextView textContent;
    private Button buttonScan;
    private Button buttonDisconnect;
    private Button buttonConnect;
    private BluetoothDevice deviceToConnect;

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

        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        textServiceUUID = findViewById(R.id.textServiceUUID);
        textCharacteristicUUID = findViewById(R.id.textCharacteristicUUID);
        textContent = findViewById(R.id.textContent);
        buttonScan = findViewById(R.id.buttonScan);
        buttonDisconnect = findViewById(R.id.buttonDisconnect);
        buttonConnect = findViewById(R.id.buttonConnect);

        checkPermissions();

        buttonScan.setOnClickListener(v -> scanForDevices());
        buttonConnect.setOnClickListener(v -> connectToDevice());
        buttonDisconnect.setOnClickListener(v -> disconnectFromDevice());
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.BLUETOOTH_SCAN,
                            Manifest.permission.BLUETOOTH_CONNECT,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    REQUEST_CODE_BLUETOOTH_SCAN_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_BLUETOOTH_SCAN_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, tiếp tục với các chức năng cần thiết
            } else {
                Toast.makeText(this, "Permission denied. Unable to scan for BLE devices.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void scanForDevices() {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {

                bluetoothAdapter.getBluetoothLeScanner().startScan(new ScanCallback() {
                    @Override
                    public void onScanResult(int callbackType, ScanResult result) {
                        super.onScanResult(callbackType, result);
                        BluetoothDevice device = result.getDevice();
                        if (deviceToConnect == null) {
                            deviceToConnect = device;
                            Toast.makeText(MainActivity.this, "Device found: " + device.getAddress(), Toast.LENGTH_SHORT).show();
                            connectToDevice();
                        }
                    }

                    @Override
                    public void onBatchScanResults(List<ScanResult> results) {
                        super.onBatchScanResults(results);
                        // Xử lý khi có nhiều kết quả quét
                    }

                    @Override
                    public void onScanFailed(int errorCode) {
                        super.onScanFailed(errorCode);
                        Toast.makeText(MainActivity.this, "Scan failed with error code: " + errorCode, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Permissions required to scan for BLE devices.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth is not enabled", Toast.LENGTH_SHORT).show();
        }
    }

    private void connectToDevice() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            if (deviceToConnect != null) {
                bluetoothGatt = deviceToConnect.connectGatt(this, false, new BluetoothGattCallback() {
                    @Override
                    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                        super.onConnectionStateChange(gatt, status, newState);
                        if (newState == BluetoothProfile.STATE_CONNECTED) {
                            runOnUiThread(() -> {
                                Toast.makeText(MainActivity.this, "Connected to device", Toast.LENGTH_SHORT).show();
                                // Check permission before calling discoverServices
                                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                                    bluetoothGatt.discoverServices(); // Start discovering services
                                } else {
                                    Toast.makeText(MainActivity.this, "Bluetooth Connect permission required", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Disconnected from device", Toast.LENGTH_SHORT).show());
                        }
                    }

                    @Override
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                        super.onServicesDiscovered(gatt, status);
                        if (status == BluetoothGatt.GATT_SUCCESS) {
                            runOnUiThread(() -> {
                                List<BluetoothGattService> services = bluetoothGatt.getServices();
                                if (!services.isEmpty()) {
                                    for (BluetoothGattService service : services) {
                                        Log.e("BLE", "Service UUID: " + service.getUuid().toString());
                                    }

                                    BluetoothGattService service = services.get(2); // Select the first service
                                    textServiceUUID.setText("Service UUID: " + service.getUuid().toString());

                                    List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
                                    if (!characteristics.isEmpty()) {
                                        for (BluetoothGattCharacteristic characteristic : characteristics) {
                                            Log.e("BLE", "Characteristic UUID: " + characteristic.getUuid().toString());
                                        }

                                        BluetoothGattCharacteristic characteristic = characteristics.get(0); // Select the first characteristic
                                        textCharacteristicUUID.setText("Characteristic UUID: " + characteristic.getUuid().toString());

                                        // Check permission before calling readCharacteristic
                                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                                            bluetoothGatt.readCharacteristic(characteristic); // Read the value of the characteristic
                                        } else {
                                            Toast.makeText(MainActivity.this, "Bluetooth Connect permission required", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        textCharacteristicUUID.setText("No characteristics found");
                                    }
                                } else {
                                    textServiceUUID.setText("No services found");
                                    textCharacteristicUUID.setText("No characteristics found");
                                }
                            });
                        } else {
                            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to discover services", Toast.LENGTH_SHORT).show());
                        }
                    }

                    @Override
                    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                        super.onCharacteristicRead(gatt, characteristic, status);
                        if (status == BluetoothGatt.GATT_SUCCESS) {
                            runOnUiThread(() -> {
                                String content = new String(characteristic.getValue());
                                textContent.setText("Content: " + content);
                            });
                        } else {
                            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to read characteristic", Toast.LENGTH_SHORT).show());
                        }
                    }
                });
            } else {
                Toast.makeText(this, "No device to connect to", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth Connect permission required", Toast.LENGTH_SHORT).show();
        }
    }

    private void disconnectFromDevice() {
        try {
            // Check for Bluetooth permissions before performing operations
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                if (bluetoothGatt != null) {
                    bluetoothGatt.disconnect();
                    bluetoothGatt.close();
                    bluetoothGatt = null;
                    Toast.makeText(this, "Disconnected from device", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Not connected to any device", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Bluetooth Connect permission required", Toast.LENGTH_SHORT).show();
            }
        } catch (SecurityException e) {
            Toast.makeText(this, "Permission denied: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
