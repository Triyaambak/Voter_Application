package com.example.voterapplicationfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context) {
        super(context,"users",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table users(username text,email text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertValues(String username , String email , String password)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long res=database.insert("users",null,contentValues);
        if(res==-1)
            return false;
        else
            return true;
    }

    public Cursor fetchValues(String iusername)
    {
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where username = '"+iusername+"'",null);
        return cursor;
    }


}
