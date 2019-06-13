package com.example.databasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VARSION = 1;

    //TODO: Create public field for each column in your table.
    //SQL Statement to create a new database.

    static final String DATABASE_CREATE = "create table "+
            "LOGIN"+ "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME text," +
            "PASSWORD text); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    //Context of the application using the database.
    private final Context context;
    //Database open/upgrade helper
    private DBHelper dbHelper;
    public LoginDataBaseAdapter(Context _context){
        context = _context;
        dbHelper = new DBHelper(context,DATABASE_NAME,null,DATABASE_VARSION);
    }
    public LoginDataBaseAdapter open() throws SQLException{
        db = dbHelper.getWritableDatabase();
        return  this;
    }
    public void close()
    {
        db.close();
    }
    public void insertEntry(String userName,String password){
        ContentValues newValues = new ContentValues();
        //Assign values for each row.
        newValues.put("USERNAME",userName);
        newValues.put("PASSWORD",password);

        //Insert the row into the table
        db.insert("LOGIN",null,newValues);
    }
    public String getSingleEntry(String userName){
        Cursor cursor=db.query("LOGIN",null,"USERNAME=?",
                new String[]
                        {userName},null,null,null);
        if(cursor.getCount()<1) //UserName Not Exist
        {
            cursor.close();;
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password=cursor.getString(cursor.getColumnIndex
                ("PASSWORD"));
        cursor.close();
        return password;
    }

}
