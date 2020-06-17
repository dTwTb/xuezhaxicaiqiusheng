package com.finaltest.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.*;

public class web_xiugaiActivity extends AppCompatActivity  implements View.OnClickListener{

    EditText webmingview;
    EditText dizhiview;
    Button btn_baocun;

    Intent intent =  new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_xiugai);

        btn_baocun=(Button) findViewById(R.id.buttonbaocun_web_xiugai);
        btn_baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        webmingview=findViewById(R.id.editText_xiugaiwebming_web);
        dizhiview=findViewById(R.id.textView5_web_xiugai);

        if(webmingview!=null&&dizhiview!=null){
            String webming=webmingview.getText().toString();
            String dizhi=dizhiview.getText().toString();

            intent.putExtra("web名",webming);
            intent.putExtra("地址",dizhi);

            this.setResult(3,intent);
            this.finish();
        }
        this.finish();
    }
}
