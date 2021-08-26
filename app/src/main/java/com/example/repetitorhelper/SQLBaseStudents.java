package com.example.repetitorhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLBaseStudents extends SQLiteOpenHelper {
    private static final String DB_NAME="dbStudents";
    private static final int DB_VER=1;
    private static final String DB_TABLE="students";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_SURNAME="surname";
    private static final String COLUMN_NUMBER="number";
    private static final String COLUMN_PRICE="price";
    private static final String COLUMN_COUNT="countClass";
    private static final String COLUMN_TYPE="type";
    private static final String KEY_ID="_id";
    String LOG_TAG = "SqlLog";
    public SQLBaseStudents(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=String.format("CREATE TABLE " + DB_TABLE + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_SURNAME + " TEXT," + COLUMN_TYPE + " TEXT," + COLUMN_PRICE + " TEXT,"+ COLUMN_COUNT + " TEXT," + COLUMN_NUMBER + " TEXT" + ");");
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion) {
            String query = String.format("DELETE TABLE IF EXISTS %s)", DB_TABLE);
            db.execSQL(query);
            onCreate(db);
        }
    }
    public void insertData(String name,String surname, String type, String price,String count, String number){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_SURNAME,surname);
        values.put(COLUMN_TYPE,type);
        values.put(COLUMN_PRICE,price);
        values.put(COLUMN_COUNT,count);
        values.put(COLUMN_NUMBER,number);
        db.insertWithOnConflict(DB_TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);
    }
    public ArrayList<String> getAllNames(){
        ArrayList<String> allNames=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_NAME},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_NAME);
            allNames.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return allNames;
    }
    public String getSurnames(){
        String surnames;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_SURNAME},null,null,null,null,null);
        cursor.moveToNext();
        int index = cursor.getColumnIndex(COLUMN_SURNAME);
        surnames = cursor.getString(index);

        cursor.close();
        db.close();
        return surnames;
    }
//   public String getAdress(){
//       String adress;
//       SQLiteDatabase db=this.getReadableDatabase();
//       Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_ADRESS},null,null,null,null,null);
//       cursor.moveToNext();
//       int index=cursor.getColumnIndex(COLUMN_ADRESS);
//       adress = cursor.getString(index);
//       cursor.close();
//       db.close();
//       return adress;
//   }
    public String getNumber(){
        String number;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_NUMBER},null,null,null,null,null);
        cursor.moveToNext();
        int index=cursor.getColumnIndex(COLUMN_NUMBER);
        number = cursor.getString(index);
        cursor.close();
        db.close();
        return number;
    }
    public String getType(){
        String type;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_TYPE},null,null,null,null,null);
        cursor.moveToNext();
        int index=cursor.getColumnIndex(COLUMN_TYPE);
        type = cursor.getString(index);
        cursor.close();
        db.close();
        return type;
    }
    public int checkTable(){
        int checkable;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM students", null);
        cursor.moveToFirst();
        checkable = cursor.getInt(0);
        Log.d(LOG_TAG,"my count = " + checkable);
        return checkable;
    }
    public void removeAll () {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DB_TABLE,null,null);
    }
}
