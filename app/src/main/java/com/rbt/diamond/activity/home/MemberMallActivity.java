package com.rbt.diamond.activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityMemberMallBinding;
import com.rbt.diamond.public_bean.HomePageBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MemberMallActivity extends AppCompatActivity {
    protected ActivityMemberMallBinding binding;

    protected List<HomePageBean.DataListBean.GoodsBean.GoodsDataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_mall);
        binding = DataBindingUtil.setContentView(MemberMallActivity.this, R.layout.activity_member_mall);

        initView();
    }

    protected void initView(){
        // 返回功能
        binding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
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

        // 设置LayoutManager
        binding.goodsListRecycler.setLayoutManager(new GridLayoutManager(MemberMallActivity.this, 2));

        // 获取数据显示报单商城
        initRequestData();
    }

    protected void initRequestData(){
        OkHttpUtils
                .post()
                .url(Util.getCustomUrl("?s=/api/goods/lists"))
                .addParams("wxapp_id", "10001")
                .addParams("category_id", String.valueOf(10001))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
//                        Gson gson = new Gson();
//                        HomePageBean pageBean = gson.fromJson(response, HomePageBean.class);
//                        EasyAdapter adapter = new EasyAdapter<HomePageBean.DataListBean.GoodsBean.GoodsDataBean>(pageBean.getDataList().getGoods().getGoodsData(), R.layout.goods_item) {
//                            @Override
//                            protected void bind(@NonNull ViewHolder viewHolder, HomePageBean.DataListBean.GoodsBean.GoodsDataBean goodsDataBean, int i) {
//
//                            }
//                        };
//                        binding.goodsListRecycler.setAdapter(adapter);
                    }
                });
    }
}