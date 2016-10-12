package com.kucheng.ydtweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ENDY on 2016/10/10.
 */
public class YdtWeatherOpenHelper extends SQLiteOpenHelper {


    public  static  final  String  CREATE_PROVINCE="create table  Province ("
            + " id integer primary autoincrement,"
            + " province_name  text,"
            + " province_code text )";

    public  static  final  String  CREATE_CITY="create table  City ("
            + " id integer primary autoincrement,"
            + " city_name  text,"
            + " city_code text,"
            + " province_id integer )";
    public  static  final  String  CREATE_COUNTY="create table  County ("
            + " id integer primary autoincrement,"
            + " county_name  text,"
            + " county_code text,"
            + " city_id integer )";


    public YdtWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
