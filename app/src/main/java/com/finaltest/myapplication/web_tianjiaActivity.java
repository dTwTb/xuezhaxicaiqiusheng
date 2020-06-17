package com.finaltest.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.*;

public class web_tianjiaActivity extends AppCompatActivity  implements View.OnClickListener{

    EditText webmingview;
    EditText dizhiview;
    Button btn_baocun;

    Intent intent =  new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_tianjia);

        btn_baocun=(Button) findViewById(R.id.buttonbaocun_web);
        btn_baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        webmingview=findViewById(R.id.editText_tianjiawebming_web);
        dizhiview=findViewById(R.id.textView5_web);

        if(webmingview!=null&&dizhiview!=null){
            String webming=webmingview.getText().toString();
            String dizhi=dizhiview.getText().toString();

            intent.putExtra("web名",webming);
            intent.putExtra("地址",dizhi);

            this.setResult(1,intent);
            this.finish();
        }
        this.finish();
    }
}
