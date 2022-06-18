package com.example.fa_jenish_c0850919_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {


    RecyclerView recycleRV;
    Button insertBtn;
    ImageView image;
    TextView tv;

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


        image = findViewById(R.id.imageView1);
        tv = findViewById(R.id.textViewhp);




        //setting up on click listner
        insertBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(homePage.this, insertpage.class);
                startActivity(intent);

                recreate();
            }
        });


        myDbhelper = new DatabaseHelper(homePage.this);
        place_id = new ArrayList<>();
        place_name = new ArrayList<>();





        cusAdap = new customeAdapter(homePage.this, this, place_id, place_name);
        recycleRV.setAdapter(cusAdap);


        recycleRV.setLayoutManager(new LinearLayoutManager(homePage.this));
        storeDataInArray();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)
        {
            recreate();
            storeDataInArray();
        }
    }

    void  storeDataInArray()
    {
        Cursor cursor = myDbhelper.readAllData();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data Available inside the datbase", Toast.LENGTH_SHORT).show();
            image.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
        }
        else
        {
            if(cursor.moveToFirst())
            {
                do {
                    place_id.add(cursor.getString(0));
                    place_name.add(cursor.getString(1));
                }while (cursor.moveToNext());
            }
            image.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menue, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all)
        {
            Toast.makeText(this, "Would You want to delete All?", Toast.LENGTH_SHORT).show();
            confirmDialog();
        }

        return super.onOptionsItemSelected(item);
    }



    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are you sure you want to delete All Data ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper mydb = new DatabaseHelper(homePage.this);
                mydb.deleteAllData();
                Intent intent = new Intent(homePage.this, homePage.class);
                startActivity(intent);
                finish();
                recreate();
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