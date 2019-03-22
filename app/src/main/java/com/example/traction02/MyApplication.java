package com.example.traction02;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {
    public static RequestQueue queue;
    public static Context appContext;

    public static RequestQueue conn;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        queue= Volley.newRequestQueue(getApplicationContext());//初始化

    }


}
