package com.finaltest.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.*;

public class ddlTianjiaActivity extends AppCompatActivity implements View.OnClickListener{

    EditText dateview;
    EditText timeview;
    EditText didianview;
    EditText shixiangview;
    EditText tianshuview;
    EditText xiaoshiview;
    Button btn_baocun;

    Intent intent =  new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddl_tianjia);

        btn_baocun=(Button) findViewById(R.id.buttonbaocun);
        btn_baocun.setOnClickListener(this);

    }

    //放入数据
    @Override
    public void onClick(View v) {
        dateview=findViewById(R.id.editText_tianjiadate);
        timeview=findViewById(R.id.editText_tianjiatime);
        didianview=findViewById(R.id.textView5);
        shixiangview=findViewById(R.id.textView7);
        tianshuview=findViewById(R.id.editText_ddltianshu);
        xiaoshiview=findViewById(R.id.editText_ddlxiaoshishu);

        if(dateview!=null&&didianview!=null&&shixiangview!=null&&timeview!=null&&tianshuview!=null&&xiaoshiview!=null){
        String shijian=dateview.getText().toString()+" "+timeview.getText().toString();
        String didian=didianview.getText().toString();
        String shixiang=shixiangview.getText().toString();
        int tianshu=Integer.parseInt(tianshuview.getEditableText().toString());
        float xiaoshi=Float.parseFloat(xiaoshiview.getEditableText().toString());
        double xiaoshishu=tianshu*24.0+xiaoshi;

        intent.putExtra("时间",shijian);
        intent.putExtra("地点",didian);
        intent.putExtra("事项",shixiang);
        intent.putExtra("小时数",xiaoshishu);

        this.setResult(1,intent);
        this.finish();
    }
        this.finish();
    }
}
