package com.finaltest.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ddlActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Button btn_ddl;
    Button btn_tianjia;
    ListView shixiang;
    int id;
    ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();
    AlarmManager am;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddl);

        DDLSQL.initTable(ddlActivity.this);
        //DocumentTool.verifyStoragePermissions(MainActivity.this);

        shuaxin(null);


    }



    //搜索DDL事项
    public void sousuo(View v){
        EditText k =  findViewById(R.id.sousuoneirong);
        String sousuoneirong= k.getText().toString();
Log.i("TAG","搜索内容值：  "+sousuoneirong);
        shuaxin(sousuoneirong);
    }

    //接收返回值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        HashMap<String,String> items=new HashMap<String,String>();
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==1){
            String shijian= data.getStringExtra("时间");
            String didian= data.getStringExtra("地点");
            String shixiang= data.getStringExtra("事项");
            double xiaoshishu=data.getDoubleExtra("小时数",0);

            Log.i("TAG","tasktime"+"  "+xiaoshishu);
            items.put("shijian",shijian);
            items.put("didian",didian);
            items.put("shixiang",shixiang);
            items.put("xiaoshishu", String.valueOf(xiaoshishu));

            DDLSQL function = new DDLSQL();
            DDLshiti shiti=new DDLshiti(items);
            DDLSQL.insert(ddlActivity.this,shiti);
        }
        if(requestCode==1&&resultCode==3){
            String shijian= data.getStringExtra("时间");
            String didian= data.getStringExtra("地点");
            String shixiang= data.getStringExtra("事项");
            double xiaoshishu=data.getDoubleExtra("小时数",0);
            items.put("shijian",shijian);
            items.put("didian",didian);
            items.put("shixiang",shixiang);
            items.put("_id",String.valueOf(id));
            items.put("xiaoshishu", String.valueOf(xiaoshishu));
            Log.i("TAG","tasktime"+"  "+xiaoshishu);
            DDLSQL function = new DDLSQL();
            DDLshiti shiti=new DDLshiti(items);
            DDLSQL.update(ddlActivity.this,shiti);
        }
        shuaxin(null);
    }

    //添加
    public void tianjia(View v) {
        //弹出一个东西,我觉得可以转到一个新页面，用于输入数据
        Intent tianjiayemian = new Intent(ddlActivity.this, ddlTianjiaActivity.class);
        startActivityForResult(tianjiayemian, 0);
    }

    //刷新按钮
    public void shuaxinbutton(View v){
        list = DDLSQL.query(ddlActivity.this, null);
        if(list.size()!=0) {
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                    list,//需要绑定的数据
                    R.layout.activity_ddl_view,//每一行的布局
                    new String[]{"shijian", "didian","shixiang","tasktime"},
//数组中的数据源的键对应到定义布局的View中
                    new int[]{ R.id.textView12_course,R.id.textView13_course,R.id.textView14_course,R.id.textView3_course});
            shixiang = (ListView) findViewById(R.id.ddlshixiang);
//为ListView绑定适配器
            shixiang.setAdapter(mSimpleAdapter);
        }
}

  //刷新
    public void shuaxin(String where1) {
        list = DDLSQL.query(ddlActivity.this, where1);
        Log.i("TAG", "list的值为" + String.valueOf(list));
        if(list.size()!=0) {
            list.get(0).get("_id");
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                    list,//需要绑定的数据
                    R.layout.activity_ddl_view,//每一行的布局
                    new String[]{"shijian", "didian","shixiang","tasktime"},
//数组中的数据源的键对应到定义布局的View中
                    new int[]{ R.id.textView12_course,R.id.textView13_course,R.id.textView14_course,R.id.textView3_course});
            shixiang = (ListView) findViewById(R.id.ddlshixiang);
//为ListView绑定适配器
            shixiang.setAdapter(mSimpleAdapter);

            shixiang.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long id) {
                    /*
                     * 点击列表项时触发onItemClick方法，四个参数含义分别为
                     * arg0：发生单击事件的AdapterView
                     * arg1：AdapterView中被点击的View
                     * position：当前点击的行在adapter的下标
                     * id：当前点击的行的id
                     */
                    Toast.makeText(ddlActivity.this,
                            "您选择的是" + list.get(position).get("name"),
                            Toast.LENGTH_SHORT).show();
                }
            });
            ItemOnLongClick1();
        }else{

            SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                    list,//需要绑定的数据
                    R.layout.activity_ddl_view,//每一行的布局
                    new String[]{"shijian", "didian","shixiang","tasktime"},
//数组中的数据源的键对应到定义布局的View中
                    new int[]{ R.id.textView12_course,R.id.textView13_course,R.id.textView14_course,R.id.textView3_course});
            shixiang = (ListView) findViewById(R.id.ddlshixiang);
//为ListView绑定适配器
            shixiang.setAdapter(mSimpleAdapter);

                Toast.makeText(ddlActivity.this,
                        "尚无数据",
                        Toast.LENGTH_SHORT).show();

        }
    }

//弹出菜单项
    private void ItemOnLongClick1() {
//注：setOnCreateContextMenuListener是与下面onContextItemSelected配套使用的
        shixiang
                .setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v,
                                                    ContextMenu.ContextMenuInfo menuInfo) {
                        menu.add(0, 0, 0, "删除");
                        menu.add(0, 1, 0, "修改");
                    }
                });
    }

    // 长按菜单响应函数。删除与修改
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int MID = (int) info.id;// 这里的info.id对应的不是数据库中_id的值
        id=Integer.parseInt(Objects.requireNonNull(list.get(MID).get("_id")));
        Log.i("TAG:","真正的id是"+id);
        switch (item.getItemId()) {
            case 0:
                // 删除操作
                Log.i("TAG:","通过id来删除数据！");
                Object[] did =new Object[] {id};
                DDLSQL function=new DDLSQL();
                DDLSQL.delete(ddlActivity.this,did);
                Log.i("TAG:","执行到此4");
                Toast.makeText(ddlActivity.this,
                        "已删除，请更新",
                        Toast.LENGTH_SHORT).show();
                shuaxin(null);
                break;

            case 1:
                // 更新操作
                Log.i("TAG:","通过id来更新数据！");
                Intent ddlxiugai=new Intent(ddlActivity.this,ddlxiugaiActivity.class);
                startActivityForResult(ddlxiugai,1);
                Toast.makeText(ddlActivity.this,
                        "已修改，请更新",
                        Toast.LENGTH_SHORT).show();
                shuaxin(null);
                break;


            default:
                break;
        }

        return super.onContextItemSelected(item);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

