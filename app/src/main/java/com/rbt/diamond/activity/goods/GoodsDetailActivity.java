package com.rbt.diamond.activity.goods;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.util.SmartGlideImageLoader;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.customPopupView.SelectSkuPopupView;
import com.rbt.diamond.databinding.ActivityGoodsDetailBinding;
import com.rbt.diamond.public_adapter.ImageNetAdapter;
import com.rbt.diamond.public_bean.GoodsDetailBean;
import com.rbt.diamond.public_bean.GoodsDetailCommentBean;
import com.rbt.diamond.util.Util;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

public class GoodsDetailActivity extends AppCompatActivity implements View.OnClickListener{
    protected ActivityGoodsDetailBinding binding;

    protected EasyAdapter<GoodsDetailCommentBean.CommentBean.ListBean.DataBean> adapter;

    protected Intent intent;

    protected int goods_id = 0;

    public static ArrayList<Object> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(GoodsDetailActivity.this, R.layout.activity_goods_detail);

        initView();

    }

    protected void initView(){
        intent = getIntent();

        goods_id = intent.getIntExtra("goods_id", 0);

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

        initRequestGoodsDetail();

        binding.commentRecycler.setLayoutManager(new LinearLayoutManager(GoodsDetailActivity.this, LinearLayoutManager.VERTICAL, false));

        binding.addCart.setOnClickListener(this);
        binding.buyNow.setOnClickListener(this);
        binding.startCart.setOnClickListener(this);
    }

    protected void initRequestGoodsDetail(){
        System.out.println("goods_id" + goods_id);
        OkHttpUtils
                .get()
                .url(Util.url + "?s=/api/goods/detail")
                .addParams("goods_id", String.valueOf(goods_id))
                .addParams("wxapp_id", String.valueOf(10001))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        GoodsDetailBean bean = gson.fromJson(response, GoodsDetailBean.class);
                        ImageNetAdapter adapter = new ImageNetAdapter(bean.getData().getDetail().getImage());

                        list.clear();

                        for (GoodsDetailBean.DataBean.DetailBean.ImageBean beans : bean.getData().getDetail().getImage()){
                            list.add(beans.getFile_path());
                        }

                        binding.banner.setAdapter(adapter)
                                .setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(Object data, int position) {
                                        new XPopup.Builder(GoodsDetailActivity.this).asImageViewer(binding.imageView, position, list, new OnSrcViewUpdateListener() {
                                            @Override
                                            public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
                                                // 注意这里：根据position找到你的itemView。根据你的itemView找到你的ImageView。
                                                // Demo中RecyclerView里面只有ImageView所以这样写，不要原样COPY。
                                                // 作用是当Pager切换了图片，需要更新源View，
                                            }
                                        }, new SmartGlideImageLoader())
                                                .show();
                                    }
                                })
                                .setIndicator(new CircleIndicator(GoodsDetailActivity.this));

//                        goods_price, line_price, goods_name, sale_num, comment_count, view_more
                        binding.goodsName.setText(bean.getData().getDetail().getGoods_name());

                        if(bean.getData().getDetail().getCategory_id() == 10006){
                            binding.goodsPrice.setText("积分：" + bean.getData().getDetail().getGoods_sku().getGoods_price());
                            binding.linePrice.setVisibility(View.GONE);
//                            sale_num.setVisibility(View.GONE);
                            binding.addCart.setVisibility(View.GONE);
                            binding.saleNum.setText("已售" + bean.getData().getDetail().getGoods_sales() + "件");
                        } else {
                            binding.goodsPrice.setText("" + bean.getData().getDetail().getGoods_sku().getGoods_price());
                            binding.linePrice.setText("原价：￥" + bean.getData().getDetail().getGoods_sku().getLine_price());
                            binding.saleNum.setText("已售" + bean.getData().getDetail().getGoods_sales() + "件");
                        }

                        String content = bean.getData().getDetail().getContent();
                        content = content.replace("<img", "<img style='max-width:100%;height:auto'");

                        binding.webView.loadData(content, "text/html", "utf-8");

                        binding.buyNow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SelectSkuPopupView popupView = new SelectSkuPopupView(GoodsDetailActivity.this, bean.getData().getSpecData(), bean.getData().getDetail().getGoods_image(), bean.getData().getDetail().getGoods_id(), bean.getData(), bean.getData().getDetail().getCategory_id());
                                new XPopup.Builder(GoodsDetailActivity.this)
                                        .asCustom(popupView)
                                        .show();
                            }
                        });

                        binding.addCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SelectSkuPopupView popupView = new SelectSkuPopupView(GoodsDetailActivity.this, bean.getData().getSpecData(), bean.getData().getDetail().getGoods_image(), bean.getData().getDetail().getGoods_id(), bean.getData(), bean.getData().getDetail().getCategory_id());
                                new XPopup.Builder(GoodsDetailActivity.this)
                                        .asCustom(popupView)
                                        .show();
                            }
                        });
                    }
                });

        OkHttpUtils
                .post()
                .url(Util.url + "?s=/api/comment/listsForAndroid")
                .addParams("page_id", String.valueOf(0))
                .addParams("goods_id", String.valueOf(goods_id))
                .addParams("page", String.valueOf(1))
                .addParams("wxapp_id", String.valueOf(10001))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        GoodsDetailCommentBean bean = gson.fromJson(response, GoodsDetailCommentBean.class);
                        adapter = new EasyAdapter<GoodsDetailCommentBean.CommentBean.ListBean.DataBean>(bean.getComment().getList().getData(), R.layout.goods_detail_comment_item_layout) {
                            @Override
                            protected void bind(@NonNull ViewHolder viewHolder, GoodsDetailCommentBean.CommentBean.ListBean.DataBean listBean, int i) {
                                viewHolder.setText(R.id.nickName, listBean.getUser().getNickName());
                                if(!TextUtils.isEmpty(listBean.getUser().getAvatarUrl())) {
                                    Glide.with(GoodsDetailActivity.this).load(listBean.getUser().getAvatarUrl()).into((ImageView) viewHolder.getView(R.id.user_avatar));
                                }
                                viewHolder.setText(R.id.content, listBean.getContent());
                            }
                        };

                        binding.commentRecycler.setAdapter(adapter);

                        binding.commentCount.setText("买家评价(" + bean.getComment().getList().getData().size() + ")");
                    }
                });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_cart:
//                Intent cartIntent = new Intent(GoodsDetailActivity.this, ShoppingCartFragment.class);
//                startActivity(cartIntent);
                break;
            case R.id.add_cart:

                break;
        }
    }
}