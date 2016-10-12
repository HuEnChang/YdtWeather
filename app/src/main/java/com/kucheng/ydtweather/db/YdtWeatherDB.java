package com.kucheng.ydtweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kucheng.ydtweather.model.City;
import com.kucheng.ydtweather.model.County;
import com.kucheng.ydtweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ENDY on 2016/10/10.
 */
public class YdtWeatherDB {
    public static final String DB_NAME="ydt_weather";
    public static final int VERSION=1;

    private static YdtWeatherDB ydtWeatherDB;
    private static SQLiteDatabase db;

    private YdtWeatherDB(Context context){
        YdtWeatherOpenHelper dbHelper=new YdtWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db=dbHelper.getWritableDatabase();
    }


    /**
     * 获取实例
     * @param context
     * @return
     */
    public synchronized static YdtWeatherDB getInstance(Context context){
        if(ydtWeatherDB==null){
            ydtWeatherDB=new YdtWeatherDB(context);
        }
        return  ydtWeatherDB;
    }

    /**
     * 保存省份
     * @param province
     */
    public void saveProvince(Province province){
        if(province!=null){
            ContentValues values=new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }


    /**
     * 读取省份信息
     * @return
     */
    public List<Province> loadProvinces(){
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do{
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        return  list;
    }

    /**
     * 保存城市
     * @param city
     */
    public void saveCity(City city){
        if(city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }

    /**
     * 读取城市列表
     * @return
     */
    public List<City> loadCities(){
        List<City> list=new ArrayList<City>();
        Cursor cursor=db.query("City",null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do{
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
                list.add(city);
            }while (cursor.moveToNext());
        }
        return  list;
    }

    /**
     * 保存县区
     * @param county
     */
    public void saveCounty(County county){
        if(county!=null){
            ContentValues values=new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert("County",null,values);
        }
    }

    /**
     * 读取县区数据
     * @return
     */
    public List<County> loadCounties(){
        List<County> list=new ArrayList<County>();
        Cursor cursor=db.query("County",null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do{
                County  county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
                list.add(county);
            }while (cursor.moveToNext());
        }
        return  list;
    }

}
