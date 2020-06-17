package com.finaltest.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class courseActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Button btn_course_tianjia;
    ListView kecheng;
    int id;
    ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();
    AlarmManager am;
    Button btn_zhou1;
    Button btn_zhou2;
    Button btn_zhou3;
    Button btn_zhou4;
    Button btn_zhou5;
    Button btn_zhou6;
    Button btn_zhou7;
    List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        courseSQL.initTable(courseActivity.this);
        //DocumentTool.verifyStoragePermissions(MainActivity.this);

        btn_course_tianjia=(Button) findViewById(R.id.button_course_tianjia);

        btn_zhou1=(Button)  findViewById(R.id.button_course_zhou1);
        btn_zhou2=(Button)  findViewById(R.id.button_course_zhou2);
        btn_zhou3=(Button)  findViewById(R.id.button_course_zhou3);
        btn_zhou4=(Button)  findViewById(R.id.button_course_zhou4);
        btn_zhou5=(Button)  findViewById(R.id.button_course_zhou5);
        btn_zhou6=(Button)  findViewById(R.id.button_course_zhou6);
        btn_zhou7=(Button)  findViewById(R.id.button_course_zhou7);

        shuaxin(null);


    }

    public void dianjizhouji(View view){
        switch (view.getId()) {
            case R.id.button_course_zhou1:
                shuaxin("1");
                break;
            case R.id.button_course_zhou2:
                shuaxin("2");
                break;
            case R.id.button_course_zhou3:
                shuaxin("3");
                break;
            case R.id.button_course_zhou4:
                shuaxin("4");
                break;
            case R.id.button_course_zhou5:
                shuaxin("5");
                break;
            case R.id.button_course_zhou6:
                shuaxin("6");
                break;
            case R.id.button_course_zhou7:
                shuaxin("7");
                break;

            default:
                break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        HashMap<String,String> items=new HashMap<String,String>();
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==1){
            String shijian= data.getStringExtra("时间");
            String didian= data.getStringExtra("地点");
            String zhouji= data.getStringExtra("周几");
            String kechengming= data.getStringExtra("课程名");

            items.put("shijian",shijian);
            items.put("didian",didian);
            items.put("zhouji",zhouji);
            items.put("kechengming", kechengming);

            courseSQL function = new courseSQL();
            Courseshiti shiti=new Courseshiti(items);
            courseSQL.insert(courseActivity.this,shiti);
        }

        if(requestCode==1&&resultCode==3){
            String shijian= data.getStringExtra("时间");
            String didian= data.getStringExtra("地点");
            String zhouji= data.getStringExtra("周几");


            String kechengming= data.getStringExtra("课程名");
            items.put("shijian",shijian);
            items.put("didian",didian);
            items.put("zhouji",zhouji);
            items.put("kechengming", kechengming);
            items.put("_id",String.valueOf(id));

            courseSQL function = new courseSQL();
            Courseshiti shiti=new Courseshiti(items);
            courseSQL.update(courseActivity.this,shiti);
        }
        shuaxin(null);
    }

    public void tianjia(View v){
        Intent tianjiayemian = new Intent(courseActivity.this, course_tianjiaActivity.class);
        startActivityForResult(tianjiayemian, 0);
    }

    public void shuaxin(String where1) {
        list = courseSQL.query(courseActivity.this, where1);
        Log.i("TAG", "list的值为" + String.valueOf(list));
        if(list.size()!=0) {
            list.get(0).get("_id");
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                    list,//需要绑定的数据
                    R.layout.activity_course_view,//每一行的布局
                    new String[]{"shijian", "didian","zhouji","kechengming"},
//数组中的数据源的键对应到定义布局的View中
                    new int[]{ R.id.textView12_course,R.id.textView13_course,R.id.textView14_course,R.id.textView3_course});
            kecheng = (ListView) findViewById(R.id.kecheng);
//为ListView绑定适配器
            kecheng.setAdapter(mSimpleAdapter);

            kecheng.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
                    Toast.makeText(courseActivity.this,
                            "您选择的是" + list.get(position).get("name"),
                            Toast.LENGTH_SHORT).show();
                }
            });
            ItemOnLongClick1();
        }
        else{

            SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                    list,//需要绑定的数据
                    R.layout.activity_course_view,//每一行的布局
                    new String[]{"shijian", "didian","shixiang","tasktime"},
//数组中的数据源的键对应到定义布局的View中
                    new int[]{ R.id.textView12_course,R.id.textView13_course,R.id.textView14_course,R.id.textView3_course});
            Log.i("TAG", "到此为止15");
            kecheng = (ListView) findViewById(R.id.kecheng);
//为ListView绑定适配器
            kecheng.setAdapter(mSimpleAdapter);

            Toast.makeText(courseActivity.this,
                    "尚无数据",
                    Toast.LENGTH_SHORT).show();

        }
    }

    //弹出菜单项
    private void ItemOnLongClick1() {
//注：setOnCreateContextMenuListener是与下面onContextItemSelected配套使用的
        kecheng
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
                courseSQL function=new courseSQL();
                courseSQL.delete(courseActivity.this,did);
                Log.i("TAG:","执行到此4");
                Toast.makeText(courseActivity.this,
                        "已删除，请更新",
                        Toast.LENGTH_SHORT).show();
                shuaxin(null);
                break;

            case 1:
                // 更新操作
                Log.i("TAG:","通过id来更新数据！");
                Intent coursexiugai=new Intent(courseActivity.this,course_xiugaiActivity.class);
                startActivityForResult(coursexiugai,1);
                Toast.makeText(courseActivity.this,
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
