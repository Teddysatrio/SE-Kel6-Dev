package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    TextView userinfo;
    Table<String,String,String> history;
    Intent intent;
    String name,age,gender,weight,height;
    private int size=0;
    private final static int REQUEST_CODE_1 = 1,REQUEST_CODE_2=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        history = HashBasedTable.create();
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();
        userinfo=findViewById(R.id.usrinfo);
        name=intent.getStringExtra("name");
        age=intent.getStringExtra("age");
        gender=intent.getStringExtra("gender");
        weight=intent.getStringExtra("weight");
        height=intent.getStringExtra("height");
        userinfo.setText("Welcome,\n"+name);
    }

    public void Stopwatch(View view) {
        Intent sw = new Intent(Main2Activity.this,Main3Activity.class);
        sw.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(sw,REQUEST_CODE_1);
    }

    public void Timer(View view) {
        Intent timer = new Intent(Main2Activity.this,CountDownTimer.class);
        timer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(timer,REQUEST_CODE_2);
    }

    public void sejarah(View view) {
        Intent ahistory = new Intent(Main2Activity.this,history.class);
        ahistory.putExtra("name",name);
        ahistory.putExtra("age",age);
        ahistory.putExtra("gender",gender);
        ahistory.putExtra("weight",weight);
        ahistory.putExtra("height",height);
        ahistory.putExtra("size",Integer.toString(history.column("type").size()));
        ahistory.putStringArrayListExtra("type",new ArrayList<>(history.column("type").values()));
        ahistory.putStringArrayListExtra("calorie",new ArrayList<>(history.column("calorie").values()));
        ahistory.putStringArrayListExtra("time",new ArrayList<>(history.column("time").values()));
        startActivity(ahistory);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Bundle bundle;
        String tipe,kalorie,waktu;
        switch(requestCode){
            case REQUEST_CODE_1:
                if(resultCode==RESULT_OK){
                        bundle = data.getBundleExtra("stopwatchdata");
                        for(int i=0;i<bundle.getStringArrayList("type").size();i++) {
                            tipe = bundle.getStringArrayList("type").get(i);
                            kalorie = bundle.getStringArrayList("calorie").get(i);
                            waktu = bundle.getStringArrayList("time").get(i);
                            history.put(Integer.toString(size),"name", name);
                            history.put(Integer.toString(size),"age", age);
                            history.put(Integer.toString(size),"gender", gender);
                            history.put(Integer.toString(size),"weight", weight);
                            history.put(Integer.toString(size),"height", height);
                            history.put(Integer.toString(size), "type", tipe);
                            history.put(Integer.toString(size), "calorie", kalorie);
                            history.put(Integer.toString(size), "time", waktu);
                            size++;
                        }
                }
                break;
            case REQUEST_CODE_2:
                history.put(Integer.toString(size),"name", name);
                history.put(Integer.toString(size),"age", age);
                history.put(Integer.toString(size),"gender", gender);
                history.put(Integer.toString(size),"weight", weight);
                history.put(Integer.toString(size),"height", height);
                if(resultCode==RESULT_OK){
                        bundle = data.getBundleExtra("timerdata");
                        tipe=bundle.getString("type");
                        kalorie = bundle.getString("calorie");
                        waktu = bundle.getString("time");
                        history.put(Integer.toString(size), "type", tipe);
                        history.put(Integer.toString(size), "calorie", kalorie);
                        history.put(Integer.toString(size), "time", waktu);
                }
                size++;
                break;
        }
    }

}
