package com.finaltest.myapplication;


import android.util.Log;

import java.util.HashMap;

class Courseshiti {
    private String shijianshuju;
    private String didianshuju;
    private String idshuju;
    private String kechengmingshuju;
    private String zhoujishuju;

    Courseshiti(HashMap<String,String> items){
        shijianshuju=items.get("shijian");
        didianshuju=items.get("didian");
        zhoujishuju=items.get("zhouji");
        idshuju=items.get("_id");
        kechengmingshuju =items.get("kechengming");

    }
    String getshijianshuju(){
        return shijianshuju;
    }
    String getDidianshuju(){
        return didianshuju;
    }
    String getidshuju(){
        return idshuju;
    }
    String getkechengmingshuju(){
        return kechengmingshuju;}
    String getZhoujishuju(){
        return zhoujishuju;}
}