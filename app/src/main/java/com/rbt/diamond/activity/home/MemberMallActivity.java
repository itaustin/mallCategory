package com.rbt.diamond.activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.passport.LoginActivity;
import com.rbt.diamond.adapter.GoodsListAdapter;
import com.rbt.diamond.databinding.ActivityMemberMallBinding;
import com.rbt.diamond.public_bean.GoodsListBean;
import com.rbt.diamond.public_bean.HomePageBean;
import com.rbt.diamond.util.Util;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MemberMallActivity extends AppCompatActivity {
    protected ActivityMemberMallBinding binding;

    protected List<GoodsListBean.DataBeanX.ListBean.DataBean> list = new ArrayList<>();
    protected GoodsListAdapter adapter;

    protected int page = 1;
    protected int total_page = 0;

    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_mall);
        binding = DataBindingUtil.setContentView(MemberMallActivity.this, R.layout.activity_member_mall);

        initView();

        binding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initRequestData(page);
            }
        });

        binding.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(page == total_page){
                    refreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    page++;
                    initRequestData(page);
                    refreshLayout.finishLoadMore();
                }
            }
        });

        binding.smartRefreshLayout.autoRefresh();
    }

    protected void initView() {
        intent = getIntent();
        if(intent.getStringExtra("category_id").equals("10001")){
            binding.titleBar.setTitle("会员商城");
        }
        if(intent.getStringExtra("category_id").equals("10002")){
            binding.titleBar.setTitle("消费商城");
        }
        if(intent.getStringExtra("category_id").equals("0")){
            binding.titleBar.setTitle("搜索结果");
        }
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
        binding.goodsRecycler.setLayoutManager(new GridLayoutManager(MemberMallActivity.this, 2));

        adapter = new GoodsListAdapter(list, MemberMallActivity.this);

        binding.goodsRecycler.setAdapter(adapter);
    }

    protected void initRequestData(int listPage) {
        PostFormBuilder builder = OkHttpUtils.post();
        if(!intent.getStringExtra("category_id").equals("0")){
            builder.addParams("category_id", intent.getStringExtra("category_id"));
        } else {
            builder.addParams("search", intent.getStringExtra("search_content"));
        }
        builder
            .url(Util.getCustomUrl("?s=/api/goods/lists"))
            .addParams("wxapp_id", "10001")
            .addParams("page", String.valueOf(listPage))
            .build()
            .execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    System.out.println(response);
                    Gson gson = new Gson();
                    GoodsListBean goodsListBean = gson.fromJson(response, GoodsListBean.class);
                    if (goodsListBean.getCode() == -1) {
                        // 去登录
                        Intent intent = new Intent(MemberMallActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else if (goodsListBean.getData().getList().getData().size() != 0) {
                        binding.smartRefreshLayout.finishRefresh();
                        if(page > 1){
                            adapter.addData(goodsListBean.getData().getList().getData());
                        } else {
                            adapter = new GoodsListAdapter(goodsListBean.getData().getList().getData(), MemberMallActivity.this);
                            binding.goodsRecycler.setAdapter(adapter);
                        }
                        total_page = goodsListBean.getData().getList().getLast_page();
                        System.out.println("adapter_count" + adapter.getItemCount());
                    } else {
                        adapter = new GoodsListAdapter(goodsListBean.getData().getList().getData(), MemberMallActivity.this);
                        binding.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                        binding.goodsRecycler.setAdapter(adapter);
                    }
                }
            });
    }
}