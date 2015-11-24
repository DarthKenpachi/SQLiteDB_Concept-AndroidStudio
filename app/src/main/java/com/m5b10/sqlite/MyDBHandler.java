package com.m5b10.sqlite;

//import all needed libraries
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


public class MyDBHandler extends SQLiteOpenHelper {  //class responsible for handling ALL DB functions

    private static final int DATABASE_VERSION = 1;  //DB VERSION
    private static final String DATABASE_NAME = "products.db"; //include .db for Android to recognize DB file
    private static final String TABLE_PRODUCTS = "products";    //TABLE NAME
    private static final String COLUMN_ID = "_id";  //COLUMN NAME
    private static final String COLUMN_PRODUCTNAME = "_productname";    //COLUMN NAME

    //ALT+INSERT - CONSTRUCTOR
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }   //CONSTRUCTOR

    //ALT+INSERT - OVERRIDE onCreate and onUpgrade


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +  //properties for COLUMN_ID
                COLUMN_PRODUCTNAME + " TEXT " +  //properties for COLUMN_PRODUCTNAME
                ");";
        db.execSQL(query);  //how to execute SQL query

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);   //delete current table
        onCreate(db);   //remake table passing db into onCreate()
    }


    public void addProduct(Products product){   //type Products product obj
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    } //add a new row to database

    public void deleteProduct(String productname){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + "WHERE " + COLUMN_PRODUCTNAME + "=\"" + productname + "\";");
    }//delete a product from database

    //Print out the database as a string
    public String databaseToString(){
        String dbString ="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + "WHERE 1";

        //cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //move to first row in results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("productname"))!=null){
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;


    }

}   //END OF MyDBHandler class
