package com.finaltest.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
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

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class webActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Button btn_ddl;
    Button btn_tianjia;
    ListView shixiang;
    int id;
    ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        DDLSQL.initTable(webActivity.this);
        //DocumentTool.verifyStoragePermissions(MainActivity.this);

        shuaxin(null);
    }

    public void sousuo_web(View v){
        EditText k =  findViewById(R.id.sousuoneirong_web);
        String sousuoneirong= k.getText().toString();
        Log.i("TAG","搜索内容值：  "+sousuoneirong);
        shuaxin(sousuoneirong);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        HashMap<String,String> items=new HashMap<String,String>();
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==1){
            String webming= data.getStringExtra("web名");
            String dizhi= data.getStringExtra("地址");

            items.put("webming",webming);
            items.put("dizhi",dizhi);

            webSQL function = new webSQL();
            Webshiti shiti=new Webshiti(items);
            webSQL.insert(webActivity.this,shiti);
        }

        if(requestCode==1&&resultCode==3){
            String webming= data.getStringExtra("web名");
            String dizhi= data.getStringExtra("地址");

            items.put("webming",webming);
            items.put("dizhi",dizhi);
            items.put("_id",String.valueOf(id));

            webSQL function = new webSQL();
            Webshiti shiti=new Webshiti(items);
            webSQL.update(webActivity.this,shiti);
        }
        shuaxin(null);
    }

    public void tianjia_web(View v) {
        //弹出一个东西,我觉得可以转到一个新页面，用于输入数据
        Intent tianjiayemian = new Intent(webActivity.this, web_tianjiaActivity.class);
        startActivityForResult(tianjiayemian, 0);
    }

    public void shuaxin(String where1) {
        list = webSQL.query(webActivity.this, where1);
        Log.i("TAG", "list的值为" + String.valueOf(list));
        if(list.size()!=0) {
            list.get(0).get("_id");
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                    list,//需要绑定的数据
                    R.layout.activity_web_view,//每一行的布局
                    new String[]{"webming", "dizhi"},
//数组中的数据源的键对应到定义布局的View中
                    new int[]{R.id.textView12_web, R.id.textView13_web});
            shixiang = (ListView) findViewById(R.id.webshixiang_web);
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
                    Toast.makeText(webActivity.this,
                            "你将打开" + list.get(position).get("webming"),
                            Toast.LENGTH_SHORT).show();
                    String s=list.get(position).get("dizhi");
                    ItemOnClick1(s);

                }
            });
            ItemOnLongClick1();
        }
        else{
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                    list,//需要绑定的数据
                    R.layout.activity_web_view,//每一行的布局
                    new String[]{"webming", "dizhi"},
//数组中的数据源的键对应到定义布局的View中
                    new int[]{ R.id.textView12_web,R.id.textView13_web});
            shixiang = (ListView) findViewById(R.id.webshixiang_web);
//为ListView绑定适配器
            shixiang.setAdapter(mSimpleAdapter);

            Toast.makeText(webActivity.this,
                    "尚无数据",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void ItemOnClick1(String s){
        Log.i("TAG", "点击事件");
        Uri uri = Uri.parse((String) s);

        Intent intent =new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        Log.i("TAG", "即将调用浏览器");
        startActivity(intent);

    }

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
                webSQL function=new webSQL();
                webSQL.delete(webActivity.this,did);
                Log.i("TAG:","执行到此4");
                Toast.makeText(webActivity.this,
                        "已删除，请更新",
                        Toast.LENGTH_SHORT).show();
                shuaxin(null);
                break;

            case 1:
                // 更新操作
                Log.i("TAG:","通过id来更新数据！");
                Intent webxiugai=new Intent(webActivity.this,web_xiugaiActivity.class);
                startActivityForResult(webxiugai,1);
                Toast.makeText(webActivity.this,
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
