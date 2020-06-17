package com.finaltest.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ddlxiugaiActivity extends AppCompatActivity implements View.OnClickListener{

    EditText shijianview;
    EditText didianview;
    EditText shixiangview;
    Button btn_xiugai;
    Intent intent =  new Intent();
    EditText dateview;
    EditText timeview;
    EditText tianshuview;
    EditText xiaoshiview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddlxiugai);

        btn_xiugai=(Button) findViewById(R.id.buttonxiugai);
        btn_xiugai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dateview=findViewById(R.id.editText_xiugaidate);
        timeview=findViewById(R.id.editText_xiugaitime);
        didianview=findViewById(R.id.textView5_xiugai);
        shixiangview=findViewById(R.id.textView7_xiugai);
        tianshuview=findViewById(R.id.editText_ddltianshu_xiugai );
        xiaoshiview=findViewById(R.id.editText_ddlxiaoshishu_xiugai);

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
            this.setResult(3,intent);
            this.finish();
        }
        this.finish();
    }
}
