package com.example.fa_jenish_c0850919_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {


    RecyclerView recycleRV;
    Button insertBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        recycleRV = findViewById(R.id.recyclerView);
        insertBtn = findViewById(R.id.insertIntoDBBtn);

        //setting up on click listner
        insertBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(homePage.this, insertpage.class);
                startActivity(intent);
            }
        });

    }




}