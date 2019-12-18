package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CountDownTimer extends AppCompatActivity {// nama class diganti

    int jam, menit, detik, total = 0,akhir=0;
    private Runnable runnable;
    private Handler handler;
    TextView tvTime;
    Button btnStart, btnPause;
    EditText etCal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntimer); // layout sini diganti sesuai nama layout activity kalian

        getSupportActionBar().hide();
        tvTime = findViewById(R.id.tvTime);
        btnPause = findViewById(R.id.btnPause);
        btnStart = findViewById(R.id.btnStart);
        etCal = findViewById(R.id.etCal);
        btnPause.setEnabled(false);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (total <= 0) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CountDownTimer.this);

                    alertDialogBuilder.setTitle("Selesai");
                    alertDialogBuilder
                            .setMessage("Waktu telah habis")
                            .setCancelable(false)
                            .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    handler.removeCallbacks(runnable);
                }
                else{
                    akhir+=1;
                    total -= 1;
                    hitungWaktu();

                    //Toast.makeText(Main2Activity.this, "total ="+total +" "+jam+" "+menit+" "+detik, Toast.LENGTH_LONG).show();

                    tvTime.setText(""+String.format("%02d",jam)+":"+String.format("%02d",menit)+":"+String.format("%02d",detik));
                    handler.postDelayed(this, 1000);
                }

            }
        };


        etCal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //pause();
                String cal = etCal.getText().toString();
                if (TextUtils.isDigitsOnly(etCal.getText().toString()) && !cal.equals("")){
                    total = (Integer.parseInt(cal) * 10); // rumus kalori atur disini
                    hitungWaktu();
                    tvTime.setText(""+String.format("%02d",jam)+":"+String.format("%02d",menit)+":"+String.format("%02d",detik));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total == 0){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CountDownTimer.this);

                    alertDialogBuilder.setTitle("Selesai");
                    alertDialogBuilder
                            .setMessage("Waktu telah habis")
                            .setCancelable(false)
                            .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else {
                    start();
                }

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });


    }

    private void start() {
        btnStart.setEnabled(false);
        btnPause.setEnabled(true);
        etCal.setEnabled(false);
        handler.postDelayed(runnable, 0);
    }

    private void pause() {
        handler.removeCallbacks(runnable);
        btnPause.setEnabled(false);
        etCal.setEnabled(false);
        btnStart.setEnabled(true);
    }

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
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        pause();
        total=akhir;
        hitungWaktu();
        bundle.putString("type","timer");
        bundle.putString("calorie", etCal.getText().toString());
        bundle.putString("time",""+String.format("%02d",jam)+":"+String.format("%02d",menit)+":"+String.format("%02d",detik));
        intent.putExtra("timerdata",bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

}
