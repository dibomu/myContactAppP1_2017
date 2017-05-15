package com.example.mukhopadhyayd0116.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mukhopadhyayd0116 on 5/11/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Contact.db";
    public static final String TABLE_NAME="contact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2="NAME";
    public static final String COL_3="AGE";
    public static final String COL_4="ADDRESS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String age, String address){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,address);

        long result= db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)return false;
        else return true;
    }
}
