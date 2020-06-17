package com.finaltest.myapplication;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class DDLSQL {

    private static DDLDBHelper helper;

        static void initTable(Context context){
            helper = new DDLDBHelper(context);
            helper.getReadableDatabase();
        }

        /**【插入数据】**/
        static void insert(Context context, DDLshiti ddlshiti){
            String[] canshu=new String[]{ddlshiti.getshijianshuju(),ddlshiti.getDidianshuju(),ddlshiti.getshixiangshuju(),ddlshiti.getTasktimeshuju(),"0"};
            Log.i("TAG:","插入数据到数据库表：person中:"+ Arrays.toString(canshu));
            DDLDBManager sqlManager = new DDLDBManager(context);
            helper = new DDLDBHelper(context);
            helper.getWritableDatabase();
            String sql = "insert into person ( shijian , didian, shixiang,tasktime,previouscheck ) values ( ? , ?, ?,?,?)";
            sqlManager.updateSQLite( sql , canshu);
        }


        /**【模糊查询】**/
        static ArrayList<HashMap<String ,String>>query(Context context, String where1){
            DDLDBManager sqlManager = new DDLDBManager(context);
            ArrayList<HashMap<String ,String>> list = new ArrayList<>();

            String sql = "select * from person where shijian like ?";
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
            DDLDBManager sqlmanager = new DDLDBManager(context);
            String sql = "delete from person where _id=?";
            Log.i("TAG:","开始删除");
            sqlmanager.updateSQLite(sql , s);
        }

        /**【更新数据】**/
        static void update(Context context, DDLshiti ddlshiti){
            Object[] canshu=new Object[]{ddlshiti.getDidianshuju(),ddlshiti.getshixiangshuju(),ddlshiti.getshijianshuju(),ddlshiti.getTasktimeshuju(),Integer.parseInt(ddlshiti.getidshuju())};
            helper = new DDLDBHelper(context);
            helper.getReadableDatabase();
            DDLDBManager sqlManager = new DDLDBManager(context);
            String sql = "update person set didian=? , shixiang=? ,shijian=?, tasktime=? where _id=?";
            sqlManager.updateSQLite(sql,canshu);
        }


}
