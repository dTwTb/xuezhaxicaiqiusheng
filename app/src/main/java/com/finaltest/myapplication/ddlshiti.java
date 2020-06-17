package com.finaltest.myapplication;


import java.util.HashMap;

class DDLshiti {
    private String shijianshuju;
    private String didianshuju;
    private String shixiangshuju;
    private String idshuju;
    private String tasktimeshuju;

    DDLshiti(HashMap<String,String> items){
         shijianshuju=items.get("shijian");
        didianshuju=items.get("didian");
        shixiangshuju=items.get("shixiang");
        idshuju=items.get("_id");
        tasktimeshuju =items.get("xiaoshishu");

    }
    String getshijianshuju(){
        return shijianshuju;
    }
    String getDidianshuju(){
    return didianshuju;
    }
    String getshixiangshuju(){ return shixiangshuju; }
    String getidshuju(){
        return idshuju;
    }
    String getTasktimeshuju(){return tasktimeshuju;}
}

