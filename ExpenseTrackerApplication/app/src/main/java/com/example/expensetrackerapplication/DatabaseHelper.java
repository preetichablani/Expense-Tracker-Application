package com.example.expensetrackerapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DatabaseHelper  extends SQLiteOpenHelper {
    public static final String DB_NAME = "exense_db";
    public static final int VERSION=1;
    public static final String TABLE = "expense";
    public static final String COL_ID="id";
    public static final String COL_TITLE="title";
    public static final String COL_AMOUNT = "amount";
    public static  final String COL_DATE="date";
    public static final String COL_CATEGORY = "category";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DB_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE+"("
                +COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_TITLE +" TEXT,"
                +COL_AMOUNT +" TEXT,"
                +COL_CATEGORY +" TEXT,"
                +COL_DATE +" TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertExpenese(String title, String amount , String category, String date){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_AMOUNT, amount);
        values.put(COL_CATEGORY , category);
        values.put(COL_DATE , date);
        long result = db.insert(TABLE,null , values);
        return result!=-1;
    }

    public Cursor getAllExpense(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE,null);
    }

    public void deleteExpense(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public Cursor getTotalAmount() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT SUM(amount) FROM "+TABLE,null);
    }

}