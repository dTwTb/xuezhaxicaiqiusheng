package com.finaltest.myapplication;

import android.util.Log;

import java.util.HashMap;

class Webshiti {
    private String webmingshuju;
    private String dizhishuju;
    private String idshuju;


    Webshiti(HashMap<String,String> items){
        webmingshuju=items.get("webming");
        dizhishuju=items.get("dizhi");
        idshuju=items.get("_id");

    }
    String getwebmingshuju(){
        return webmingshuju;
    }
    String getdizhishuju(){
        return dizhishuju;
    }
    String getidshuju(){
        return idshuju;
    }

}
