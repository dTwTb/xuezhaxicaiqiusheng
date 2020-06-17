package com.finaltest.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class course_xiugaiActivity extends AppCompatActivity implements View.OnClickListener{

    EditText shijianview;
    EditText didianview;
    EditText zhoujiview;
    EditText kechengmingview;
    Button btn_baocun;
    Intent intent =  new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_xiugai);

        btn_baocun=(Button) findViewById(R.id.button_course_xiugaiqueren);
        btn_baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        shijianview=findViewById(R.id.editText_course_xiugaishijian);
        didianview=findViewById(R.id.editText_course_xiugaididian);
        zhoujiview=findViewById(R.id.editText_course_xiugaizhouji);
        kechengmingview=findViewById(R.id.editText_course_xiugaikechengming);

        if(shijianview!=null&&didianview!=null&&zhoujiview!=null&&kechengmingview!=null){
            String shijian=shijianview.getText().toString();
            String didian=didianview.getText().toString();
            String zhouji=zhoujiview.getText().toString();
            String kechengming=kechengmingview.getText().toString();

            intent.putExtra("时间",shijian);
            intent.putExtra("地点",didian);
            intent.putExtra("周几",zhouji);
            intent.putExtra("课程名",kechengming);

            this.setResult(3,intent);
            this.finish();
        }
        this.finish();

    }
}

