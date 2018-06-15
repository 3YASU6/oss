package com.example.kimhy.open_source_project_naver_price_compare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.kimhy.open_source_project_naver_price_compare.MyIntentService_kt;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class backGroundTask {


    /**
     * スケジュールを設定します。
     * @param context
     */
    public static void setSchedule(Context context, String ItemID, String ItemName) {
        // create instance
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        long time = System.currentTimeMillis();
        if(!isSetPending(context)) {
            Intent intent = new Intent(context, MyIntentService_kt.class);
            intent.putExtra("productID",ItemID);
            intent.putExtra("Title",ItemName);

            PendingIntent p = PendingIntent.getService(
                    context,
                    -1,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            //long delay = 24 * 60 * 60 * 1000; // 24 시간 간격으로 정기적으로 처리를 수행
            //am.setRepeating(AlarmManager.RTC, time, delay, p);

            Calendar calendar = Calendar.getInstance(); // Calendar取得
            calendar.setTimeInMillis(System.currentTimeMillis()); // 現在時刻を取得
            calendar.add(Calendar.SECOND, 5); // 現時刻より15秒後を設定
            System.out.println("=======================AlarmManager==========================");
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), p); // AlramManagerにPendingIntentを登録
        }
    }

    /**
     * PendingIntent로부터 intent를 받는 클래스
     * */
    public class AlarmBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("=======================Received==========================");

            Toast.makeText(context, "Received ", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * スケジュールが設定されているかどうかを返します。
     * @param context
     * ↑ backGround Thread 구별 ID 같은거
     */
    public static boolean isSetPending(Context context) {
        Intent intent = new Intent(context, MyIntentService_kt.class);
        PendingIntent p = PendingIntent.getService(
                context,
                -1,
                intent,
                PendingIntent.FLAG_NO_CREATE);
        if(p == null) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * スケジュールをキャンセルします。
     * @param context
     */
    public static void cancelSchedule(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, MyIntentService_kt.class);
        PendingIntent p = PendingIntent.getService(
                context,
                -1,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(p);
        p.cancel(); // これを行わないとisSetPendingで意図した値が返ってこない
    }
}
