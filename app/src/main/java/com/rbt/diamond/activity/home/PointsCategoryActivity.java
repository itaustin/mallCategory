package com.rbt.diamond.activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.passport.LoginActivity;
import com.rbt.diamond.databinding.ActivityPointsCategoryBinding;
import com.rbt.diamond.public_adapter.CategoryGoodsAdapter;
import com.rbt.diamond.public_bean.CategoryBean;
import com.rbt.diamond.public_bean.GoodsListBean;
import com.rbt.diamond.util.Util;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class PointsCategoryActivity extends AppCompatActivity {
    protected ActivityPointsCategoryBinding binding;
    protected EasyAdapter<CategoryBean.DataBean> categoryAdapter;
    protected CategoryGoodsAdapter goodsListAdapter = null;

    protected int mainPosition = 0;

    protected int page = 1;
    protected int total_page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(PointsCategoryActivity.this, R.layout.activity_points_category);

        initView();

        initRequestCategory();
    }

    protected void initView(){
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
        binding.leftNameRecycler.setLayoutManager(new LinearLayoutManager(PointsCategoryActivity.this, LinearLayoutManager.VERTICAL, false));
        binding.goodsRecycler.setLayoutManager(new GridLayoutManager(PointsCategoryActivity.this, 2));
    }

    protected void initRequestCategory() {
        OkHttpUtils
                .post()
                .url(Util.url + "api/points_category/getList")
                .addParams("token", Util.getToken(PointsCategoryActivity.this))
                .addParams("wxapp_id", "10001")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        CategoryBean bean = gson.fromJson(response, CategoryBean.class);
                        categoryAdapter = new EasyAdapter<CategoryBean.DataBean>(bean.getData(), R.layout.category_item) {
                            @Override
                            protected void bind(@NonNull ViewHolder viewHolder, CategoryBean.DataBean dataBean, int i) {
                                LinearLayout linearLayout = viewHolder.getView(R.id.bg);
                                TextView name = viewHolder.getView(R.id.name);
                                TextPaint textPaint = name.getPaint();
                                viewHolder.setText(R.id.name, dataBean.getName());
                                if(goodsListAdapter == null && i == 0){
                                    binding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                                        @Override
                                        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                                            page = 1;
                                            initRequestGoods(page, dataBean.getCategory_id());
                                        }
                                    });

                                    binding.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                                        @Override
                                        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                                            if(page == total_page){
                                                refreshLayout.finishLoadMoreWithNoMoreData();
                                            } else {
                                                page++;
                                                initRequestGoods(page, dataBean.getCategory_id());
                                                refreshLayout.finishLoadMore();
                                            }
                                        }
                                    });

                                    binding.smartRefreshLayout.autoRefresh();
                                }
                                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        setMainPosition(i);
                                        notifyDataSetChanged();
                                        try {
                                            System.out.println(dataBean.getName());
                                            System.out.println(dataBean.getCategory_id());
                                        } catch (Exception e){
                                            System.out.println(e.getMessage());
                                        }
                                        binding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                                            @Override
                                            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                                                page = 1;
                                                initRequestGoods(page, dataBean.getCategory_id());
                                            }
                                        });

                                        binding.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                                            @Override
                                            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                                                if(page == total_page){
                                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                                } else {
                                                    page++;
                                                    initRequestGoods(page, dataBean.getCategory_id());
                                                    refreshLayout.finishLoadMore();
                                                }
                                            }
                                        });

                                        binding.smartRefreshLayout.autoRefresh();

                                    }
                                });

                                if (i == getMainPosition()) {
                                    textPaint.setFakeBoldText(true);
                                    textPaint.setTextSize(35);
                                    textPaint.setColor(Color.parseColor("#000000"));
                                    linearLayout.setVisibility(View.VISIBLE);
                                } else {
                                    textPaint.setFakeBoldText(false);
                                    textPaint.setTextSize(33);
                                    textPaint.setColor(Color.parseColor("#ff999999"));
                                    linearLayout.setVisibility(View.GONE);
                                }
                            }
                        };
                        binding.leftNameRecycler.setAdapter(categoryAdapter);
                    }
                });
    }

    protected void initRequestGoods(int page, int category_id) {
        OkHttpUtils
                .post()
                .url(Util.url + "api/goods/lists")
                .addParams("token", Util.getToken(PointsCategoryActivity.this))
                .addParams("wxapp_id", "10001")
                .addParams("page", String.valueOf(page))
                .addParams("points_category_id", String.valueOf(category_id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        GoodsListBean bean = gson.fromJson(response, GoodsListBean.class);
                        if (bean.getCode() == -1) {
                            // ?????????
                            Intent intent = new Intent(PointsCategoryActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else if (bean.getData().getList().getData().size() != 0) {
                            binding.smartRefreshLayout.finishRefresh();
                            if(page > 1){
                                goodsListAdapter.addData(bean.getData().getList().getData());
                            } else {
                                goodsListAdapter = new CategoryGoodsAdapter(bean.getData().getList().getData(), PointsCategoryActivity.this, 1);
                                binding.goodsRecycler.setAdapter(goodsListAdapter);
                            }
                            total_page = bean.getData().getList().getLast_page();
                            System.out.println("adapter_count" + goodsListAdapter.getItemCount());
                        } else {
                            goodsListAdapter = new CategoryGoodsAdapter(bean.getData().getList().getData(), PointsCategoryActivity.this, 1);
                            binding.smartRefreshLayout.finishLoadMoreWithNoMoreData();
                            binding.goodsRecycler.setAdapter(goodsListAdapter);
                        }
                    }
                });
    }

    public int getMainPosition() {
        return mainPosition;
    }

    public void setMainPosition(int mainPosition) {
        this.mainPosition = mainPosition;
    }
}