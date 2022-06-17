package com.example.fa_jenish_c0850919_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateActivity extends AppCompatActivity {

    EditText placeNameUP;
    Button updateBtn,deleteBtn;


    String id1,name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        placeNameUP = findViewById(R.id.placeETUdatePage);
        updateBtn = findViewById(R.id.updateBtnUpdatePage);
        deleteBtn = findViewById(R.id.deleteBtnUpdatePagte);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null)
        {

            ab.setTitle(name1);

        }

        //setting up on Click listener
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DatabaseHelper myDBHelper = new DatabaseHelper(updateActivity.this);

                name1 = placeNameUP.getText().toString().trim();

                myDBHelper.updateData(id1, name1);
                finish();

            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });



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



    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name1 + " ?");
        builder.setMessage("Are you sure you want to delete " + name1 + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(updateActivity.this);
                myDB.deleteData(id1);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }

}