package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class history extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private ArrayList<String> tipe,calorie,waktu;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();
        fetchdata();//fetch data here
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new HistoryAdapter(tipe,calorie,waktu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(history.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void fetchdata(){
        Intent intent = getIntent();
        String into;
        name = intent.getStringExtra("name");
        tipe=intent.getStringArrayListExtra("type");
        calorie=intent.getStringArrayListExtra("calorie");
        waktu=intent.getStringArrayListExtra("time");
    }

}
