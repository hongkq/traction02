package com.example.traction02.SqlLitter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.traction02.activity.Activity1_1;


public class SQLiteDbHelpes extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyDatabase.db";
    private static final String TABLE_NAME = "ETCchonzhi";

    public SQLiteDbHelpes(Activity1_1 activity1_1, String s, Context context, int i) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+TABLE_NAME+"("
                +"id  integer peimary key autoincrement ,"
                +"username varchar(40) not null"
                +"money varchar(10) not null"
                +"userTime varchar(30) not null"
                +"carid varchar(10) not null"
                +");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库版本号变更会调用onUpgrde函数，在这里根据版本号进行升级数据库
        switch (oldVersion){
            case 1:
                break;
               default:
                   break;
        }

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen (db);
        if (! db.isReadOnly ()){
            db.execSQL("PRAGMA foreign_keys = 1;");

        }
    }
}
