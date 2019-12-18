package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText inputname,inputage,inputgender,inputweight,inputheight;
    Intent baru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        inputname = findViewById(R.id.inputname);
        inputage = findViewById(R.id.inputage);
        inputgender = findViewById(R.id.inputgender);
        inputweight = findViewById(R.id.inputweight);
        inputheight = findViewById(R.id.inputheight);
        baru = new Intent(MainActivity.this,Main2Activity.class);
    }

    public void pindah(View view) {
        if(verification()){
            baru.putExtra("name",inputname.getText().toString());
            baru.putExtra("age",inputage.getText().toString());
            baru.putExtra("gender",inputgender.getText().toString());
            baru.putExtra("weight",inputweight.getText().toString());
            baru.putExtra("height",inputheight.getText().toString());
            startActivity(baru);
            finish();
        }else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            alertDialogBuilder.setTitle("Error");

            alertDialogBuilder
                    .setMessage("Input Form Dengan Benar!")
                    .setCancelable(false)
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();
        }
    }

    public boolean verification() {
        if (inputname.getText().toString().isEmpty()
                ||(inputname.getText().toString().length() < 1)
                ||(inputname.getText().toString().length() > 25)) {
            return false;
        } else if ((inputgender.getText().toString().isEmpty())
                ||
                (
                        (inputgender.getText().toString().compareTo("Male") != 0)
                                &&
                                (inputgender.getText().toString().compareTo("Female") != 0))
        ) {
            return false;
        } else if (Integer.parseInt(inputage.getText().toString()) < 0
        ||
        inputage.getText().toString().isEmpty())return false;
        else if(Integer.parseInt(inputweight.getText().toString()) < 0
                ||
                inputweight.getText().toString().isEmpty())return false;
        else if(Integer.parseInt(inputheight.getText().toString()) < 0
                ||
                inputheight.getText().toString().isEmpty())return false;
        return true;
    }
}
