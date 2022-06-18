package com.example.fa_jenish_c0850919_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.session.PlaybackState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customeAdapter extends RecyclerView.Adapter<customeAdapter.myViewHolder> {


    private Context context;
    Activity activity;
    private ArrayList place_id, place_name;


    customeAdapter(Activity activity, Context context, ArrayList place_id, ArrayList place_name)
    {
        this.activity = activity;
        this.context = context;
        this.place_id = place_id;
        this.place_name = place_name;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleviewcard, parent, false);
        return new myViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position)
    {

        holder.place_id_TV.setText(String.valueOf(place_id.get(position)));
        holder.place_name1_TV.setText(String.valueOf(place_name.get(position)));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updateActivity.class);

                intent.putExtra("id", String.valueOf(place_id.get(position)));
                intent.putExtra("name", String.valueOf(place_name.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return place_id.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView place_id_TV, place_name1_TV;
        LinearLayout mainlayout;



        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            place_id_TV = itemView.findViewById(R.id.IdTV);
            place_name1_TV = itemView.findViewById(R.id.placeNameTV);
            mainlayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
