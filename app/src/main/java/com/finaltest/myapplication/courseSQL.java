package com.finaltest.myapplication;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class courseSQL {

    private static courseDBHelper helper;

    static void initTable(Context context){
        helper = new courseDBHelper(context);
        helper.getReadableDatabase();
    }

    /**【插入数据】**/
    static void insert(Context context, Courseshiti courseshiti){
        String[] canshu=new String[]{courseshiti.getshijianshuju(),courseshiti.getDidianshuju(),courseshiti.getkechengmingshuju(),courseshiti.getZhoujishuju(),"0"};
        Log.i("TAG:","插入数据到数据库表：person1中:"+ Arrays.toString(canshu));
        courseDBManager sqlManager = new courseDBManager(context);
        helper = new courseDBHelper(context);
        helper.getWritableDatabase();
        String sql = "insert into person1 ( shijian , didian, kechengming, zhouji,previouscheck ) values ( ? , ?, ?,?,?)";
        sqlManager.updateSQLite( sql , canshu);
    }


    /**【模糊查询】**/
    static ArrayList<HashMap<String ,String>>query(Context context, String where1){
        courseDBManager sqlManager = new courseDBManager(context);
        ArrayList<HashMap<String ,String>> list = new ArrayList<>();
        String sql = "select * from person1 where zhouji like ?";
        if(where1 == null){
            list = sqlManager.querySQLite(sql,new String[] {"%"});
        }else{
            list = sqlManager.querySQLite(sql,new String [] { "%"+where1+"%" } );
        }
        Log.i("TAG:","查询完毕，返回数据：" + list.size());
        return list;
    }

    /**【删除数据】**/
    static void delete(Context context, Object[] s){
        courseDBManager sqlmanager = new courseDBManager(context);
        String sql = "delete from person1 where _id=?";
        Log.i("TAG:","开始删除啦");
        sqlmanager.updateSQLite(sql , s);
    }

    /**【更新数据】**/
    static void update(Context context, Courseshiti courseshiti){
        Object[] canshu=new Object[]{courseshiti.getDidianshuju(),courseshiti.getshijianshuju(),courseshiti.getkechengmingshuju(),courseshiti.getZhoujishuju(),Integer.parseInt(courseshiti.getidshuju())};
        helper = new courseDBHelper(context);
        helper.getReadableDatabase();
        courseDBManager sqlManager = new courseDBManager(context);
        String sql = "update person1 set didian=? , shijian=?, kechengming=? ,zhouji=? where _id=?";
        sqlManager.updateSQLite(sql,canshu);
    }


}
