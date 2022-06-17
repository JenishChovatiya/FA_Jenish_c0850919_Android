package com.example.fa_jenish_c0850919_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {


    private  Context context;
    private static final String DATABASE_NAME = "bucket_List.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "myBucketList";
    private  static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "place_title";


    DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT); ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    //for Inserting the data inside the database
    void addPlaces(String title)
    {
        SQLiteDatabase sdatabase = this.getWritableDatabase();
        ContentValues cv =new ContentValues();


        cv.put(COLUMN_TITLE, title);

        long result = sdatabase.insert(TABLE_NAME, null, cv);
        if(result == -1)
        {
            Toast.makeText(context, "Failed to insert.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added Data into database Successfully.", Toast.LENGTH_SHORT).show();
        }


    }



    //reading the data from database
    Cursor readAllData()
    {
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();



        Cursor cursor = null;
        if (sqLiteDatabase != null)
        {
            cursor = sqLiteDatabase.rawQuery(query, null);
        }

        return  cursor;
    }



    //for updating the data
    void updateData(String row_id, String title)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);

        long result = sqLiteDatabase.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "No Data Available.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }


    //for deleting the data
    void deleteData(String row_id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME,"id=?", new String[]{row_id});

        if(result == -1)
        {
            Toast.makeText(context, "Error to Delete", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }


    //for deleting all the row
    void deleteAllData()
    {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        myDatabase.execSQL(" DELETE FROM " + TABLE_NAME);
    }

}
