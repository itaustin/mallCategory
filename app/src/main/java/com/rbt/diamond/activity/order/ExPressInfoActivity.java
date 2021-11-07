package com.rbt.diamond.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.public_bean.ExpressListBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class ExPressInfoActivity extends AppCompatActivity {
    protected TitleBar titleBar;

    protected RecyclerView express_recycler;

    protected int order_id = 0;

    protected Intent intent;

    protected EasyAdapter<ExpressListBean.DataBean.ExpressBean.ListBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_press_info);

        initView();

        initRequestExpress();
    }

    protected void initView() {
        intent = getIntent();

        order_id = intent.getIntExtra("order_id", 0);

        titleBar = findViewById(R.id.titleBar);
        express_recycler = findViewById(R.id.express_recycler);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View view) {
                finish();
            }

            @Override
            public void onTitleClick(View view) {

            }

            @Override
            public void onRightClick(View view) {

            }
        });

        express_recycler.setLayoutManager(new LinearLayoutManager(ExPressInfoActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    protected void initRequestExpress(){
        OkHttpUtils
                .get()
                .url(Util.url + "?s=/api/user.order/express")
                .addParams("order_id", String.valueOf(order_id))
                .addParams("wxapp_id", "10001")
                .addParams("token", Util.getToken(ExPressInfoActivity.this))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        ExpressListBean expressListBean = gson.fromJson(response, ExpressListBean.class);
                        for (ExpressListBean.DataBean.ExpressBean.ListBean bean : expressListBean.getData().getExpress().getList()){
                            System.out.println(bean.getContext() + bean.getFtime());
                        }
                        adapter = new EasyAdapter<ExpressListBean.DataBean.ExpressBean.ListBean>(expressListBean.getData().getExpress().getList(), R.layout.express_item_layout) {
                            @Override
                            protected void bind(@NonNull ViewHolder viewHolder, ExpressListBean.DataBean.ExpressBean.ListBean listBean, int i) {
                                viewHolder.setText(R.id.content, listBean.getContext());
                                viewHolder.setText(R.id.time, listBean.getFtime());

                                if(i == 0) {
                                    ImageView icon = viewHolder.getView(R.id.lineIcon);
                                    LinearLayout line = viewHolder.getView(R.id.line_color);
                                    icon.setImageResource(R.mipmap.checked);
                                    line.setBackgroundResource(R.drawable.express_line);
                                }
                            }
                        };

                        express_recycler.setAdapter(adapter);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

        initRequestExpress();
    }
}