package com.example.sunil.sqlliteeg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sunil on 10/31/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="Person.db";
    public static final String TABLE_NAME="person_table";

    public static final String COL_1="ID";
    public static final String COL_2="name";
    public static final String COL_3="age";
    public static final String COL_4="checkbox";

    public DatabaseHandler(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+ TABLE_NAME +
                "(" +COL_1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " TEXT,"
                + COL_3 + " INTEGER,"
                + COL_4 +" INTEGER DEFAULT '0'"+")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(Model md){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value=new ContentValues();
        value.put(COL_2,md.getName());
        value.put(COL_3,md.getAge());
        value.put(COL_4,md.isSelected());

        long result=db.insert(TABLE_NAME,null,value);
        if(result == -1)                //In case of ERROR it return -1.
            return false;
        else
            return true;
    }

    public boolean updateData(Model md){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value=new ContentValues();
        value.put(COL_2,md.getName());
        value.put(COL_3,md.getAge());
        value.put(COL_4,md.isSelected());

        long result=db.update(TABLE_NAME,value,"ID=?",new String[] {md.id});
        if(result == 0)
            return false;
        else
            return true;
    }

    public boolean checkboxUpdater(Model md){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value=new ContentValues();
        value.put(COL_4,md.isSelected());

        long result=db.update(TABLE_NAME,value,"ID=?",new String[] {md.id});
        if(result == 0)
            return false;
        else
            return true;
    }

    public Integer deleteData(Model md){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID=?",new String[] {md.id});
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public ArrayList<Model> getArrayData() {
        Cursor res = getAllData();

        ArrayList<Model> mlist = new ArrayList<>();


        if (res != null) {
            while (res.moveToNext()) {
                int id = res.getInt(res.getColumnIndex(COL_1));
                String name = res.getString(res.getColumnIndex(COL_2));
                int age = res.getInt(res.getColumnIndex(COL_3));
                Log.d("TAG", "Before : " + res.getString(res.getColumnIndex(COL_4)));
                boolean cb = false;
                if (res.getString(res.getColumnIndex(COL_4)) != null) {
                    int isSelected = Integer.valueOf(res.getString(res.getColumnIndex(COL_4)));

                    if (isSelected == 1) {
                        cb = true;
                    } else {
                        cb = false;
                    }
                }

                mlist.add(new Model(String.valueOf(id),name,String.valueOf(age),cb));
            }
        }
        return mlist;
    }


}