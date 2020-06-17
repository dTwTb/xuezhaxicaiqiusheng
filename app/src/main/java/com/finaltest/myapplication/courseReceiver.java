package com.finaltest.myapplication;

import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class courseReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_FLAG = 1;
    NotificationManager notificationManager;
    ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();
    private static courseDBHelper helper;
    int tongzhiid;

    @Override
    public void onReceive(Context context, Intent intent) {
        tongzhiid = 1;
        Log.i("TAG", "成功跳转1");
        list = courseSQL.query(context, null);
        Log.i("TAG", "成功跳转1"+list.toString());
        long now=System.currentTimeMillis()+8*60*60*1000;//1970年来的毫秒数
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                HashMap<String,String> item= list.get(i);
                Log.i("TAG", "此次item为"+item);
                int id=Integer.parseInt(Objects.requireNonNull(item.get("_id")));
                String s=item.get("shijian");
                String zhouji=item.get("zhouji");
                long shangcitongzhi=Long.parseLong(Objects.requireNonNull(item.get("previouscheck")));
                long ddlshengyu=30*60*1000;
                String nowzhouji=haomiaozhuanzhouji(now);
                String nowdate=haomiaozhuandate(now);
                String s1=nowdate+" "+s;
                long future=shijianzhuanhaomiao(s1);
                if(future-now< ddlshengyu&& nowzhouji.equals(zhouji)) {
                    Log.i("TAG", "即将调用发起通知");
                    //每隔30分钟的实现
                    if (now - shangcitongzhi >  12* 60 * 1000 && future - now >= 4*60*1000) {
                        shangcitongzhi=1*now;
                        String previouscheck = String.valueOf(shangcitongzhi);
                        Object[] canshu = new Object[]{previouscheck, id};
                        helper = new courseDBHelper(context);
                        helper.getReadableDatabase();
                        courseDBManager sqlManager = new courseDBManager(context);
                        String sql = "update person1 set previouscheck=? where _id=?";
                        sqlManager.updateSQLite(sql, canshu);
                        faqitongzhi(context, intent, item);
                    }
                    else{
                        break;
                    }
                }
            }
        }
    }

    public void faqitongzhi(Context context, Intent intent,HashMap<String,String> item) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (intent.getAction().equals("VIDEO_TIMER")) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, ddlActivity.class), 0);
            NotificationChannel notificationChannel = new NotificationChannel("1", "name", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
            NotificationCompat.Builder mb = new NotificationCompat.Builder(context, "1");

            String tishitext=item.get("shijian")+"\n"+item.get("didian")+"\n"+item.get("zhouji")+"\n"+item.get("kechengming");
            long[] vibrate = new long[]{0, 500, 1000, 1500};
            mb.setContentTitle("课程提醒")
                    .setContentText(tishitext)
                    .setTicker("收到一条消息").setWhen(System.currentTimeMillis())
                    .setChannelId("1")
                    .setVibrate(vibrate);
            mb.setContentIntent(pendingIntent).setNumber(1).build();
            Notification notification = mb.build();
            notification .flags |= Notification.FLAG_INSISTENT;
            notificationManager.notify(tongzhiid, notification);
            tongzhiid+=1;
        }
    }

    public String haomiaozhuanzhouji(long b){
        Date date1 = new Date(b);//得到当前时间
        SimpleDateFormat format = new SimpleDateFormat("EEEE", Locale.getDefault());
        HashMap<String,String> xingqi=new HashMap<String,String>();
        xingqi.put("Monday","1");
        xingqi.put("Wednesday","3");
        xingqi.put("Tuesday","2");
        xingqi.put("Thursday","4");
        xingqi.put("Friday","5");
        xingqi.put("Saturday","6");
        xingqi.put("Sunday","7");
        return xingqi.get(format.format(date1));
    }

    public String haomiaozhuandate(long b){
        Date date1 = new Date(b);//得到当前时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date1);
    }

    public String haomiaozhuanshijian(long b){
        Date date1 = new Date(b);//得到当前时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Log.i("TAG", "format.format(date) =====" + format.format(date1));
        return format.format(date1);
    }

    public long shijianzhuanhaomiao(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("11111111", "date.getTime(); =====" + date.getTime());
        return date.getTime();

    }
}
