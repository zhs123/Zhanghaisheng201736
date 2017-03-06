package com.bwei.zhanghaisheng201736;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    private RecyclerView re_1;
    private RecyclerView re_2;
    private RecyclerView re_3;
    private Recyclerview1Adapter r1Adapter;
    private Recyclerview2Adapter r2Adapter;
    private Recyclerview3Adapter r3Adapter;
    private TextView mR2_t;
    private List<Bean.RsBean> rs;
    private TextView mR3_t;
    private List<Bean.RsBean.ChildrenBeanX> children;
    private List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children1;
    private List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHeader();
        initWidget();
        setWidgetState();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUrl();
    }

    private void initHeader() {

    }

    private void initWidget() {
        //第一个RecyclerView
        re_1 = (RecyclerView) findViewById(R.id.re_1);
        re_1.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        re_1.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
        //创建适配器
        r1Adapter = new Recyclerview1Adapter(MainActivity.this);
        //布局管理器
        re_1.setLayoutManager(new LinearLayoutManager(this));
        //添加适配器
        re_1.setAdapter(r1Adapter);
        //第二个RecyclerView
        re_2 = (RecyclerView) findViewById(R.id.re_2);
        re_2.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        re_2.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
        //创建适配器
        r2Adapter = new Recyclerview2Adapter(this);
        //设置布局管理器的样式
        re_2.setLayoutManager(new GridLayoutManager(this, 3));
        //设置数据
        re_2.setAdapter(r2Adapter);
        //第三个RecyclerView
        re_3 = (RecyclerView) findViewById(R.id.re_3);
        re_3.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        re_3.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
        //创建适配器
        r3Adapter = new Recyclerview3Adapter(this);
        //设置布局管理器的样式
        re_3.setLayoutManager(new GridLayoutManager(this, 3));
        //设置数据
        re_3.setAdapter(r3Adapter);

        mR2_t = (TextView) findViewById(R.id.re_2_tv);
        mR3_t = (TextView) findViewById(R.id.re_3_tv);


    }

    private void setWidgetState() {

        r1Adapter.setOnRVitemClickListener(new Recyclerview1Adapter.OnRVitemClickListener() {

            @Override
            public void onClickListener(int position) {

                children = rs.get(position).getChildren();

                mR2_t.setText(children.get(0).getDirName());
                children0 = MainActivity.this.children.get(0).getChildren();
                r2Adapter.addList(children0);

                if (rs.get(position).getDirName().equals("图书天地")) {
                    mR3_t.setText("图书天地,近在咫尺!");
                    r3Adapter.addList(new ArrayList<Bean.RsBean.ChildrenBeanX.ChildrenBean>());

                }else {

                    children1 = MainActivity.this.children.get(1).getChildren();
                    mR3_t.setText(MainActivity.this.children.get(1).getDirName());
                    r3Adapter.addList(children1);

                }
            }
        });

        r2Adapter.setOnRVitemClickListener(new Recyclerview2Adapter.OnRVitemClickListener() {
            @Override
            public void onClickListener(int position) {

                Toast.makeText(MainActivity.this, children0.get(position).getDirName(), Toast.LENGTH_SHORT).show();

            }
        });

        r3Adapter.setOnRVitemClickListener(new Recyclerview3Adapter.OnRVitemClickListener() {
            @Override
            public void onClickListener(int position) {

                Toast.makeText(MainActivity.this, children1.get(position).getDirName(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getUrl() {

        String url = "http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";
        OkHttpManager.getAsync(url, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

                Gson gson = new Gson();

                Bean bean = gson.fromJson(result, Bean.class);

                rs = bean.getRs();

                Log.d("zhulei", rs.get(0).getDirName() + "-------");

                r1Adapter.addList(rs);

            }
        });
    }
}
