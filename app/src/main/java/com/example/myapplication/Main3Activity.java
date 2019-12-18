package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

//total = (Integer.parseInt(cal) / 100) * 60, rumus conversi calorie, konsultasi ke user untuk detail

public class Main3Activity extends AppCompatActivity {
    int detik,menit,jam,total=0;
    private Runnable runnable;
    private Handler handler;
    Button buttonPause,buttonPlay,buttonReset;
    TextView etWatch,etCal;
    ArrayList<String> stopwatchtype,waktu,cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().hide();
        buttonPause = findViewById(R.id.bpause);
        buttonPlay = findViewById(R.id.bmulai);
        buttonReset = findViewById(R.id.bulang);
        etWatch = findViewById(R.id.etWatch);
        etCal = findViewById(R.id.lap);
        buttonPause.setEnabled(false);
        buttonReset.setEnabled(false);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                   total+=1;
                   hitungWaktu();
                   //something stop here
                   etWatch.setText(""+String.format("%02d",jam)+":"+String.format("%02d",menit)+":"+String.format("%02d",detik));
                   etCal.setText(Integer.toString(total/10)+" calories burned");
                   handler.postDelayed(this,1000);
            }
        };
        waktu = new ArrayList<>();
        stopwatchtype= new ArrayList<>();
        cal=new ArrayList<>();
    }

    public void mulai(View view) {
        buttonPlay.setEnabled(false);
        buttonPause.setEnabled(true);
        buttonReset.setEnabled(false);
        start();
    }

    public void berhenti(View view) {
        handler.removeCallbacks(runnable);
        buttonReset.setEnabled(true);
        buttonPlay.setEnabled(true);
        buttonPause.setEnabled(false);
    }

    public void ulang(View view) {
        handler.removeCallbacks(runnable);
        buttonPause.setEnabled(false);
        buttonPlay.setEnabled(true);
        buttonReset.setEnabled(true);
        total=0;
        hitungWaktu();
        etWatch.setText(""+String.format("%02d",jam)+":"+String.format("%02d",menit)+":"+String.format("%02d",detik));
    }

    private void start(){handler.postDelayed(runnable,0);}

    private void hitungWaktu() {
        int waktu = total;
        if (waktu >= 3600 ){
            jam  = waktu/3600;
            waktu -= jam * 3600;
        }
        else
            jam = 0;
        if (waktu >= 60){
            menit = waktu/60;
            waktu -= menit * 60;
        }
        else
            menit = 0;
        detik = waktu;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        waktu.add(""+String.format("%02d",jam)+":"+String.format("%02d",menit)+":"+String.format("%02d",detik));
        stopwatchtype.add("stopwatch");
        cal.add(Integer.toString(total/10));
        bundle.putStringArrayList("type",stopwatchtype);
        bundle.putStringArrayList("time",waktu);
        bundle.putStringArrayList("calorie",cal);
        intent.putExtra("stopwatchdata",bundle);
        setResult(RESULT_OK,intent);
        handler.removeCallbacks(runnable);
        finish();
    }

}
