package com.finaltest.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

class courseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "course.db";  //数据库名字
    private static final int DATABASE_VERSION = 1 ;         //数据库版本号

    public courseDBHelper(Context context){ super(context,DATABASE_NAME,null,DATABASE_VERSION); }

    public courseDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /**
     * 创建数据库表：person
     * _id为主键，自增
     * **/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("TAG:","创建person1数据库表！");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS person1(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " shijian VARCHAR,didian VARCHAR,kechengming Varchar,zhouji Varchar, previouscheck Varchar)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion,int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase){
        super.onOpen(sqLiteDatabase);
    }
}
