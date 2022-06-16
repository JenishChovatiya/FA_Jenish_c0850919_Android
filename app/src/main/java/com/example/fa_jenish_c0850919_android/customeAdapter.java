package com.example.fa_jenish_c0850919_android;

import android.content.Context;
import android.media.session.PlaybackState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customeAdapter extends RecyclerView.Adapter<customeAdapter.myViewHolder> {


    private Context context;
    private ArrayList place_id, place_name;

    customeAdapter(homePage homePage, Context context, ArrayList place_id, ArrayList place_name)
    {
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
    public void onBindViewHolder(@NonNull myViewHolder holder, int position)
    {
        holder.place_id.setText(String.valueOf(place_id.get(position)));
        holder.place_name1.setText(String.valueOf(place_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return place_id.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView place_id, place_name1;

        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            place_id = itemView.findViewById(R.id.IdTV);
            place_name1 = itemView.findViewById(R.id.placeNameTV);
        }
    }
}
