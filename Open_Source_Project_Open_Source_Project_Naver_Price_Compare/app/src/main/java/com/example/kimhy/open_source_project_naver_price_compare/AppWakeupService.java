package com.example.kimhy.open_source_project_naver_price_compare;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.os.SystemClock;
//https://gist.github.com/Antarix/9090171

public class AppWakeupService extends BroadcastReceiver
{
    Context context;
    AlarmManager am;
    long time = System.currentTimeMillis();//지금으로부터 2시간 뒤 기준

    public AppWakeupService(Context context)
    {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);//
        //9:00:00 시간 설정

        this.context = context;
         am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);


    }

    @Override
    public void onReceive(Context context,Intent intent)
    {
        this.context= context;
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String[] price = new String[20];
    String price1;
    String price2;
    String price3;
    String price4;
    //HashMap<String,String>;
    //for(;;)
    //price0 = 5, price1 = 5000

}
