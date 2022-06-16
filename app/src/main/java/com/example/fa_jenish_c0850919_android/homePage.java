package com.example.fa_jenish_c0850919_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {


    RecyclerView recycleRV;
    Button insertBtn;


    //declaring database helper
    DatabaseHelper myDbhelper;
    ArrayList<String> place_id,place_name;

    customeAdapter cusAdap;

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


        myDbhelper = new DatabaseHelper(homePage.this);
        place_id = new ArrayList<>();
        place_name = new ArrayList<>();


        storeDataInArray();


        cusAdap = new customeAdapter(homePage.this, this, place_id, place_name);
        recycleRV.setAdapter(cusAdap);

        recycleRV.setLayoutManager(new LinearLayoutManager(homePage.this));

    }



    void  storeDataInArray()
    {
        Cursor cursor = myDbhelper.readAllData();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data Available inside the datbase", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                place_id.add(cursor.getString(0));
                place_name.add(cursor.getString(1));
            }
        }
    }




}