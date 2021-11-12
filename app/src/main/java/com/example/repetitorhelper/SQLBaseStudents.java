package com.example.repetitorhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
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
    private static final String COLUMN_COUNT_CANCEL="countCancelClass";
    private static final String COLUMN_TYPE="type";
    private static final String COLUMN_COLOR="color";
    private static final String KEY_ID="_id";
    String LOG_TAG = "SqlLog";
    public SQLBaseStudents(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=String.format("CREATE TABLE " + DB_TABLE + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_SURNAME + " TEXT," + COLUMN_TYPE + " TEXT," + COLUMN_PRICE + " TEXT,"+ COLUMN_COUNT + " TEXT,"+ COLUMN_COUNT_CANCEL + " INTEGER,"+ COLUMN_COLOR + " INTEGER," + COLUMN_NUMBER + " TEXT" + ");");
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
    public void insertData(String name,String surname, String type, String price,String count, String number, int cancelCount){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_SURNAME,surname);
        values.put(COLUMN_TYPE,type);
        values.put(COLUMN_PRICE,price);
        values.put(COLUMN_COUNT,count);
        values.put(COLUMN_NUMBER,number);
        values.put(COLUMN_COUNT_CANCEL,cancelCount);
        values.put(COLUMN_COLOR, Color.WHITE);
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
    public ArrayList<String> getAllSurnames(){
        ArrayList<String> allNames=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_SURNAME},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_SURNAME);
            allNames.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return allNames;
    }
    public ArrayList<String> getAllClassCounts(){
        ArrayList<String> allCount=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_COUNT},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_COUNT);
            allCount.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return allCount;
    }
    public ArrayList<String> getAllPriceOfClass(){
        ArrayList<String> allPrice=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_PRICE},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_PRICE);
            allPrice.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return allPrice;
    }
    public ArrayList<Integer> getAllCancelClass(){
        ArrayList<Integer> allPrice=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_COUNT_CANCEL},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_COUNT_CANCEL);
            allPrice.add(cursor.getInt(index));
        }
        cursor.close();
        db.close();
        return allPrice;
    }
    public ArrayList<Integer> getColor(){
        ArrayList<Integer> allPrice=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_COLOR},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_COLOR);
            allPrice.add(cursor.getInt(index));
        }
        cursor.close();
        db.close();
        return allPrice;
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
    public String getPhoneNum(String surname){
        String phoneNum;
        String selection = COLUMN_SURNAME + "= ?";
        String [] selectionArgs = new String [] {surname};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_NUMBER},selection,selectionArgs,null,null,null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(COLUMN_NUMBER);
        phoneNum = cursor.getString(index);
        cursor.close();
        db.close();
        return phoneNum;
    }
    public String getCountClass(String surname){
        String countClass;
        String selection = COLUMN_SURNAME + "= ?";
        String [] selectionArgs = new String [] {surname};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_COUNT},selection,selectionArgs,null,null,null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(COLUMN_COUNT);
        countClass = cursor.getString(index);
        cursor.close();
        db.close();
        return countClass;
    }
    public String getPriceClass(String surname){
        String priceClass;
        String selection = COLUMN_SURNAME + "= ?";
        String [] selectionArgs = new String [] {surname};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_PRICE},selection,selectionArgs,null,null,null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(COLUMN_PRICE);
        priceClass = cursor.getString(index);
        cursor.close();
        db.close();
        return priceClass;
    }
    public String getTypeClass(String surname){
        String typeClass;
        String selection = COLUMN_SURNAME + "= ?";
        String [] selectionArgs = new String [] {surname};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_TYPE},selection,selectionArgs,null,null,null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(COLUMN_TYPE);
        typeClass = cursor.getString(index);
        cursor.close();
        db.close();
        return typeClass;
    }
    public ArrayList<String> getCountCancelClass(){
        ArrayList<String> alCount=new ArrayList<>();
        String selection = COLUMN_COUNT_CANCEL + ">?";
        String [] selectionArgs = new String [] {"0"};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_COUNT_CANCEL},selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_COUNT_CANCEL);
            alCount.add(String.valueOf(cursor.getInt(index)));
        }
        cursor.close();
        db.close();
        return alCount;
    }
    public ArrayList<String> getNameCancelClass(){
        ArrayList<String> alCount=new ArrayList<>();
        String selection = COLUMN_COUNT_CANCEL + ">?";
        String [] selectionArgs = new String [] {"0"};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_NAME},selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_NAME);
            alCount.add(String.valueOf(cursor.getString(index)));
        }
        cursor.close();
        db.close();
        return alCount;
    }
    public ArrayList<String> getSurnameCancelClass(){
        ArrayList<String> alCount=new ArrayList<>();
        //String query = "SELECT " + COLUMN_SURNAME + " FROM " + DB_TABLE + " WHERE " + COLUMN_COUNT_CANCEL + " >0;";
        String selection = COLUMN_COUNT_CANCEL + ">?";
        String [] selectionArgs = new String [] {"0"};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_SURNAME},selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_SURNAME);
            alCount.add(String.valueOf(cursor.getString(index)));
        }
        cursor.close();
        db.close();
        return alCount;
    }    public ArrayList<String> getSurnameWithoutCancelClass(){
        ArrayList<String> alCount=new ArrayList<>();
        String selection = COLUMN_COUNT_CANCEL + "=?";
        String [] selectionArgs = new String [] {"0"};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_SURNAME},selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_SURNAME);
            alCount.add(String.valueOf(cursor.getString(index)));
        }
        cursor.close();
        db.close();
        return alCount;
    }
    public ArrayList<String> getMoneyCancelClass(){
        ArrayList<String> alCount=new ArrayList<>();
        String selection = COLUMN_COUNT_CANCEL + ">?";
        String [] selectionArgs = new String [] {"0"};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_PRICE},selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_PRICE);
            alCount.add(String.valueOf(cursor.getString(index)));
        }
        cursor.close();
        db.close();
        return alCount;
    }
    public void upgradeCountCancelClass(String surname, int countCancel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COUNT_CANCEL,countCancel);
        String selection = COLUMN_SURNAME + "=?";
        String [] selectionArgs = {surname};
        db.update(DB_TABLE,contentValues,selection,selectionArgs);
    }
    public void upgradeColor(String surname, int color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COLOR,color);
        String selection = COLUMN_SURNAME + "=?";
        String [] selectionArgs = {surname};
        db.update(DB_TABLE,contentValues,selection,selectionArgs);
    }
    public void removeAll () {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DB_TABLE,null,null);
    }
}
