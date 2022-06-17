package com.example.fa_jenish_c0850919_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateActivity extends AppCompatActivity {

    EditText placeNameUP;
    Button updateBtn;


    String id1,name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        placeNameUP = findViewById(R.id.placeETUdatePage);
        updateBtn = findViewById(R.id.updateBtnUpdatePage);



        //setting up on Click listener
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });
        getAndSetIntentData();
    }


    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name"))
        {
            //getting the intent data from one page to another
            id1 = getIntent().getStringExtra("id");
            name1 = getIntent().getStringExtra("name");


            //Setting the data from intent
            placeNameUP.setText(name1);



        }
        else
        {
            Toast.makeText(this, "No data Available.", Toast.LENGTH_SHORT).show();
        }
    }

}