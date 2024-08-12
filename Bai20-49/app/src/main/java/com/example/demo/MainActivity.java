package com.example.demo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    ConstraintLayout cl;
//    Button btn;
//    Boolean isClicked=false;

//    ImageButton img;

//    SwitchCompat swt;

//    RadioButton rd1,rd2,rd3;

//    Button btn;
//    ProgressBar prg;

//    SeekBar sb;

//    ListView lv;
//    ArrayList<String> arr;
//    Button btn;
//    int indexCLicked=-1;

    ListView lvTraiCay;
    ArrayList<TraiCay> arrayTraiCay;
    TraiCayAdapter adapter;

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

//        cl=(ConstraintLayout) findViewById(R.id.main);
//        btn=(Button) findViewById(R.id.button);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isClicked=!isClicked;
//
//                if(isClicked){
//                    cl.setBackgroundResource(R.drawable.background2);
//                }else{
//                    cl.setBackgroundResource(R.drawable.background);
//                }
//            }
//        });

//        img=(ImageButton) findViewById(R.id.imageButton);
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Da bam", Toast.LENGTH_SHORT).show();
//            }
//        });

//        swt=(SwitchCompat) findViewById(R.id.switch1);
//        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    Toast.makeText(MainActivity.this,"Da bat wifi",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this,"Da tat wifi",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        rd1=(RadioButton) findViewById(R.id.radioButton4);
//        rd2=(RadioButton) findViewById(R.id.radioButton5);
//        rd3=(RadioButton) findViewById(R.id.radioButton6);
//
//        rd1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(rd1.isChecked()){
//                    Toast.makeText(MainActivity.this,"Chon 1 ",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(rd2.isChecked()){
//                    Toast.makeText(MainActivity.this,"Chon 2",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        rd3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(rd3.isChecked()){
//                    Toast.makeText(MainActivity.this,"Chon 3",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        btn=(Button) findViewById(R.id.button);
//        prg=(ProgressBar) findViewById(R.id.progressBar);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CountDownTimer countDownTimer=new CountDownTimer(20000,1000) {
//                    @Override
//                    public void onTick(long l) {
//                        int current = prg.getProgress();
//                        prg.setProgress(current + 5);
//                        if (prg.getProgress() == 100) {
//                            current = 0;
//                            prg.setProgress(current);
//                        }
//                    }
//                        @Override
//                        public void onFinish() {
//                            Toast.makeText(MainActivity.this,"Downloaded",Toast.LENGTH_SHORT).show();
//                        }
//                    };
//                countDownTimer.start();
//            }
//        });

//        sb=(SeekBar) findViewById(R.id.seekBar);
//
//        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                Log.d("AAA","Gia tri la: "+i);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                Log.d("AAA","START");
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("AAA","STOP");
//            }
//        });

//        //set listView
//        lv=(ListView) findViewById(R.id.listView);
//        btn=(Button) findViewById(R.id.button2);
//        //define ArrayList and add String
//        arr=new ArrayList<>();
//
//        arr.add("123");
//        arr.add("456");
//        arr.add("789");
//
//        //adapter
//        ArrayAdapter adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arr);
//
//        //setAdapter
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                indexCLicked=i;
//                Toast.makeText(MainActivity.this,arr.get(i),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                arr.set(indexCLicked,"Da bam");
//                adapter.notifyDataSetChanged();
//            }
//        });

        lvTraiCay=(ListView) findViewById(R.id.listviewTraiCay);
        arrayTraiCay=new ArrayList<>();
        arrayTraiCay.add(new TraiCay("Earth","Trái đất",R.drawable.earth));
        arrayTraiCay.add(new TraiCay("Jupiter","Sao mộc",R.drawable.jupiter));
        arrayTraiCay.add(new TraiCay("Mars","Sao hảo",R.drawable.mars));
        arrayTraiCay.add(new TraiCay("Neptune","Sao thủy",R.drawable.neptune));

        adapter=new TraiCayAdapter(this,R.layout.dong_trai_cay,arrayTraiCay);
        lvTraiCay.setAdapter(adapter);
    }
}