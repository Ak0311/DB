package com.example.dbinsertonly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static final String TABLE_NAME = "consumers";
    public static final String COL1 = "id";
    public static final String COL2 = "name";
    public static final String COL3 = "phoneNumber";
    public static final String COL4 = "accountNumber";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL2+" TEXT,"
                +COL3+" TEXT,"
                +COL4+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name,String phoneNumber,String accountNumber) {

        //The updated database is stored in SQLiteDatabase class.
        SQLiteDatabase db = this.getWritableDatabase();//Receiving current updated database.

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE name = ? ", new String[]{name});

        if (res.getCount() == 0) {

            ContentValues cv = new ContentValues();
            cv.put(COL2, name);
            cv.put(COL3, phoneNumber);
            cv.put(COL4, accountNumber);

            long verify = db.insert(TABLE_NAME, null, cv);
            db.close();

            if (verify == -1)
                return false;
            else
                return true;
        } else
            return false;
    }


    public ArrayList<Consumer> showData(){

        ArrayList<Consumer> consumerList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(res.moveToFirst()) {
            do {
                consumerList.add(new Consumer(
                        res.getString(res.getColumnIndex(COL2)),
                        res.getString(res.getColumnIndex(COL3)),
                        res.getString(res.getColumnIndex(COL4))));
            } while (res.moveToNext());
        }

        db.close();

        return consumerList;

    }

    public boolean updateData(String name,String phoneNumber,String accountNumber){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues conv = new ContentValues();

        conv.put(COL2,name);
        conv.put(COL3,phoneNumber);
        conv.put(COL4,accountNumber);

        db.update(TABLE_NAME,conv,"name = ?",new String[]{name});
        db.close();
        return true;
    }

    public void removeAll(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);

    }

    public void remove(String nameToDel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"name = ?",new String[]{nameToDel});
    }



}
