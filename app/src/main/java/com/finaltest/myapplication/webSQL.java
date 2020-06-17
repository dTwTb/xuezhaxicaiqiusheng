package com.finaltest.myapplication;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class webSQL {

    private static webDBHelper helper;

    static void initTable(Context context){
        helper = new webDBHelper(context);
        helper.getReadableDatabase();
    }

    /**【插入数据】**/
    static void insert(Context context, Webshiti webshiti){
        String[] canshu=new String[]{webshiti.getwebmingshuju(),webshiti.getdizhishuju()};
        Log.i("TAG:","插入数据到数据库表：person2中:"+ Arrays.toString(canshu));
        webDBManager sqlManager = new webDBManager(context);
        helper = new webDBHelper(context);
        helper.getWritableDatabase();
        String sql = "insert into person2( webming , dizhi ) values ( ? , ?)";
        sqlManager.updateSQLite( sql , canshu);
    }


    /**【模糊查询】**/
    static ArrayList<HashMap<String ,String>>query(Context context, String where1){
        webDBManager sqlManager = new webDBManager(context);
        ArrayList<HashMap<String ,String>> list = new ArrayList<>();
        String sql = "select * from person2 where webming like ?";
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
        webDBManager sqlmanager = new webDBManager(context);
        String sql = "delete from person2 where _id=?";
        Log.i("TAG:","开始删除啦");
        sqlmanager.updateSQLite(sql , s);
    }

    /**【更新数据】**/
    static void update(Context context, Webshiti webshiti){
        Object[] canshu=new Object[]{webshiti.getdizhishuju(),webshiti.getwebmingshuju(),Integer.parseInt(webshiti.getidshuju())};
        helper = new webDBHelper(context);
        helper.getReadableDatabase();
        webDBManager sqlManager = new webDBManager(context);
        String sql = "update person2 set dizhi=? , webming=? where _id=?";
        sqlManager.updateSQLite(sql,canshu);
    }


}

