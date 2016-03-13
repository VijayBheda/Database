package com.example.vij.one;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vij on 27-12-2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="one.db";
    public static final String TABLE_NAME="one_table";
    public static final String _ID="ID";
    public static final String _FNAME="FNAME";
    public static final String _LNAME="LNAME";
    public static final String _EMAIL="EMAIL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER,FNAME TEXT,LNAME TEXT,EMAIL TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id,String fname,String lname,String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(_ID,id);
        contentValues.put(_FNAME,fname);
        contentValues.put(_LNAME,lname);
        contentValues.put(_EMAIL, email);
        Long result=db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME,null);
       return res;
    }
    public boolean updateData(String fname,String lname,String email,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(_FNAME,fname);
        contentValues.put(_LNAME,lname);
        contentValues.put(_EMAIL,email);
        contentValues.put(_ID, id);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id });
        return true;
    }

    public Integer DeleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }
}
