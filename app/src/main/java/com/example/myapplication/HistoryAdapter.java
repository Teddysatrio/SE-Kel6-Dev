package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewAdapter> {

    private ArrayList<String> tipe,calorie,waktu;

    public HistoryAdapter(ArrayList<String> tipe, ArrayList<String> calorie, ArrayList<String> waktu){
        this.tipe =tipe;
        this.calorie =calorie;
        this.waktu = waktu;
    }

    @Override
    public HistoryViewAdapter onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_history,parent,false);
        return new HistoryViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewAdapter holder,int position){
        holder.tvCalorie.setText(calorie.get(position));
        holder.tvTime.setText(waktu.get(position));

        if (tipe.get(position).equals("stopwatch"))
            holder.ivType.setImageResource(R.drawable.mustopwatchbutton);
        else
            holder.ivType.setImageResource(R.drawable.mutimerbutton);
    }

    @Override
    public int getItemCount(){
        return (tipe!=null)?tipe.size():0;
    }

    public class HistoryViewAdapter extends RecyclerView.ViewHolder{

        private TextView tvCalorie, tvTime;
        private ImageView ivType;

        public HistoryViewAdapter(View itemView){
            super(itemView);
            tvCalorie = itemView.findViewById(R.id.tvCalories);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivType = itemView.findViewById(R.id.ivType);

        }

    }
}
