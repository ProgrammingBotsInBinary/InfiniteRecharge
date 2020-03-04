package com.example.infiniterecharge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.infiniterecharge.Variables.*;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + THE_NAME + " (" + Col0 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + Col1 + " INTEGER, " + Col2 + " INTEGER, " + Col3 + " INTEGER, " + Col4 + " INTEGER, " + Col5 + " INTEGER, " +
            Col6 + " INTEGER, " + Col7 + " INTEGER, " + Col8 + " INTEGER, " + Col9 + " INTEGER, " + Col10 + " INTEGER, " + Col11 + " INTEGER, " +
            Col12 + " INTEGER, " + Col13 + " INTEGER, " + Col14 + " INTEGER, " + Col15 + " INTEGER, " + Col16 + " INTEGER, " + Col17 + " INTEGER, "
        + Col18 + " INTEGER, " + Col19 + " INTEGER, " + Col20 + " INTEGER, " + Col21 + " INTEGER, " + Col22 + " TEXT)";



    public static final int DB_VERSION = 1;
    private static final String SQL_DELETE = "DROP TABLE IF EXISTS " + THE_NAME;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
    public void deleteDb(Context context){
        context.deleteDatabase(DB_NAME);
    }
    public boolean insertData(int teamnumber, int matchnumber, int autonlvl1, int autonlvl2, int autonlvl3, int autonline, int autonposition, int
                              teleopsuccesslvl1, int teleopsuccesslvl2, int teleopsuccesslvl3, int teleopfaillvl1, int teleopfaillvl2,
                              int teleopfaillvl3, int rotationcontrol, int positioncontrol, int endgamee,int rotationtime, int positiontime, int cycles,
                               int defenseplayed, int defenseplayedon, String notess){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1, teamnumber);
        contentValues.put(Col2, matchnumber);
        contentValues.put(Col3, autonlvl1);
        contentValues.put(Col4, autonlvl2);
        contentValues.put(Col5, autonlvl3);
        contentValues.put(Col6, autonline);
        contentValues.put(Col7, autonposition);
        contentValues.put(Col8, teleopsuccesslvl1);
        contentValues.put(Col9, teleopsuccesslvl2);
        contentValues.put(Col10, teleopsuccesslvl3);
        contentValues.put(Col11, teleopfaillvl1);
        contentValues.put(Col12, teleopfaillvl2);
        contentValues.put(Col13, teleopfaillvl3);
        contentValues.put(Col14, rotationcontrol);
        contentValues.put(Col15, positioncontrol);
        contentValues.put(Col16, endgamee);
        contentValues.put(Col17, rotationtime);
        contentValues.put(Col18, positiontime);
        contentValues.put(Col19, cycles);
        contentValues.put(Col20, defenseplayed);
        contentValues.put(Col21, defenseplayedon);
        contentValues.put(Col22, notess);

        long result = db.insert(THE_NAME,null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + THE_NAME,null);
        return res;
    }
    public void deleteRow (int teamNum, int matchNum){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "delete" + " from " + THE_NAME +
                " where " + Col1 + " = " + teamNum + " and " + Col2 + " = " + matchNum;
        Cursor cur = db.rawQuery(query, null);
        Log.d("Query :", query);
        Log.d("Deleting Row", Integer.toString(cur.getCount()));
    }
}
