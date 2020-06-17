package com.finaltest.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    AlarmManager am1;
    AlarmManager am2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ddl;
        Button btn_course;

        btn_ddl = (Button) findViewById(R.id.zhuxuanxaing1);
        btn_ddl.setOnClickListener(this);

        tongzhi1();
        tongzhi2();

    }

    public void tongzhi1(){
        Log.i("TAG", "tongzhi1tiaozhuan");
        am1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        // AlarmManager.ELAPSED_REALTIME_WAKEUP表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟使用相对时间
        // SystemClock.elapsedRealtime()表示手机开始到现在经过的时间
        Intent intent1 = new Intent(this, courseReceiver.class);
        intent1.setAction("VIDEO_TIMER");
        // PendingIntent这个类用于处理即将发生的事情
        PendingIntent sender1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
        am1.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), 60 * 1000, sender1);
    }

    public void tongzhi2(){
        Log.i("TAG", "tongzhi2tiaozhuan");
        am2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        // AlarmManager.ELAPSED_REALTIME_WAKEUP表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟使用相对时间
        // SystemClock.elapsedRealtime()表示手机开始到现在经过的时间
        Intent intent2 = new Intent(this, Tongzhi_ddlActivity.class);
        intent2.setAction("VIDEO_TIMER");
        // PendingIntent这个类用于处理即将发生的事情
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent2, 0);
        am2.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), 60 * 1000, sender);
    }

    @Override
    //启动ddl主页面
    public void onClick(View v) {
        Intent intentddl = new Intent(MainActivity.this,ddlActivity.class);
        startActivity(intentddl);
    }
    //启动course主页面
    public void open_course(View v){
        Intent intentcourse= new Intent(MainActivity.this,courseActivity.class);
        startActivity(intentcourse);
    }
    //启动web主页面
    public void open_web(View v){
        Intent intentweb= new Intent(MainActivity.this,webActivity.class);
        startActivity(intentweb);
    }
}

