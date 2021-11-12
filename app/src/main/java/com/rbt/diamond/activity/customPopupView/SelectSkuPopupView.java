package com.rbt.diamond.activity.customPopupView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.core.BottomPopupView;
import com.rbt.diamond.activity.order.MallOrderCheckoutActivity;
import com.rbt.diamond.public_bean.AddCartBean;
import com.rbt.diamond.public_bean.GoodsDetailBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.rbt.diamond.R;
import com.rbt.diamond.util.Util;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SelectSkuPopupView extends BottomPopupView {
    protected Context context;
    protected RecyclerView title_recycler;

    protected String goods_image;

    protected int goods_id;

    protected GoodsDetailBean.DataBean goodsDataBean;
    protected GoodsDetailBean.DataBean.SpecDataBean specSkuData;

    protected EasyAdapter<GoodsDetailBean.DataBean.SpecDataBean.SpecAttrBean> specAttrAdapter;

    protected List<GoodsDetailBean.DataBean.SpecDataBean.SpecAttrBean> specAttrData = new ArrayList<>();
    protected List<GoodsDetailBean.DataBean.DetailBean.GoodsMultiSpecBean.SpecAttrBean.SpecItemsBean> specItemsData = new ArrayList<>();

    protected String goods_sku_id;

    protected List<Integer> goods_sku_data = new ArrayList<>();

    protected Button add_cart, buy_now, sub, add;

    protected int sku_first_num = 0;

    protected TextView sku_price, stock, goods_num;

    protected int goods_number = 1;

    protected LinearLayout start_cart;

    protected int category_id = 0;

    public SelectSkuPopupView(@NonNull Context context, GoodsDetailBean.DataBean.SpecDataBean specSkuData, String goods_image, int goods_id, GoodsDetailBean.DataBean goodsDataBean, int category_id) {
        super(context);
        this.context = context;
        this.specSkuData = specSkuData;
        this.goods_image = goods_image;
        this.goods_id = goods_id;
        this.goodsDataBean = goodsDataBean;
        this.category_id = category_id;

        if(specSkuData != null) {
            this.sku_first_num = specSkuData.getSpec_attr().size();
            specAttrData = specSkuData.getSpec_attr();
        }
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.goods_sku_item_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        initView();

        ImageView goods_sku_image = findViewById(R.id.goods_sku_image);

        Glide.with(context).load(goods_image).into(goods_sku_image);

    }

    protected void initView(){
        title_recycler = findViewById(R.id.title_recycler);
        buy_now = findViewById(R.id.buy_now);
//        add_cart = findViewById(R.id.add_cart);
//        start_cart = findViewById(R.id.start_cart);

        sku_price = findViewById(R.id.sku_price);
        stock = findViewById(R.id.stock);
        sub = findViewById(R.id.sub);
        add = findViewById(R.id.add);
        goods_num = findViewById(R.id.goods_num);


        sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category_id == 10001){
                    Util.showToastSuccess(context, "报单商城最多值支持购买一个。");
                } else {
                    if (goods_number > 1) {
                        goods_number--;
                        goods_num.setText(String.valueOf(goods_number));
                    }
                }
            }
        });

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category_id == 10001){
                    Util.showToastSuccess(context, "报单商城最多值支持购买一个。");
                } else {
                    goods_number++;
                    goods_num.setText(String.valueOf(goods_number));
                }
            }
        });

//        if(goodsDataBean.getDetail().getCategory_id() == 10006){
//            add_cart.setVisibility(GONE);
//        }

        title_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        specAttrAdapter = new EasyAdapter<GoodsDetailBean.DataBean.SpecDataBean.SpecAttrBean>(specAttrData, R.layout.sku_popup_title) {
            protected RecyclerView sku_item;

            protected EasyAdapter<GoodsDetailBean.DataBean.DetailBean.GoodsMultiSpecBean.SpecAttrBean.SpecItemsBean> sku_item_adapter;

            @Override
            protected void bind(@NonNull ViewHolder viewHolder, GoodsDetailBean.DataBean.SpecDataBean.SpecAttrBean specAttrBean, int i) {

                TextView title_name = viewHolder.getView(R.id.title_name);

                title_name.setText(specAttrBean.getGroup_name());

                sku_item = viewHolder.getView(R.id.sku_item_recycler);

                sku_item.setLayoutManager(new GridLayoutManager(context, 4));

                sku_item_adapter = new EasyAdapter<GoodsDetailBean.DataBean.DetailBean.GoodsMultiSpecBean.SpecAttrBean.SpecItemsBean>(specAttrData.get(i).getSpec_items(), R.layout.sku_item_layout) {
                    protected Button button;

                    @SuppressLint("SetTextI18n")
                    @Override
                    protected void bind(@NonNull ViewHolder viewHolder, GoodsDetailBean.DataBean.DetailBean.GoodsMultiSpecBean.SpecAttrBean.SpecItemsBean specItemsBean, int j) {

                        button = viewHolder.getView(R.id.button);

                        button.setText(specItemsBean.getSpec_value());

                        button.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setmPosition(j);
                                notifyDataSetChanged();
                            }
                        });

                        if(j == getmPosition()){
                            button.setBackgroundResource(R.drawable.sku_item_selected);
                            button.setTextColor(Color.parseColor("#ffffff"));
                            specItemsBean.setSelected(true);

                            try {
                                int sku_item_id = goods_sku_data.get(i);
                                goods_sku_data.set(i, specItemsBean.getItem_id());
                            } catch (IndexOutOfBoundsException e){
                                try {
                                    goods_sku_data.add(i, specItemsBean.getItem_id());
                                } catch (Exception exception){
                                    goods_sku_data.clear();
                                    button.setBackgroundResource(R.drawable.sku_item_not_selected);
                                    button.setTextColor(Color.parseColor("#333333"));
                                    Util.showToastError(context, "请按从上往下的顺序选择规格。");
                                }
                            }
                            goods_sku_id = "";
                            for (Integer ii : goods_sku_data) {
                                goods_sku_id += ii + "_";
                            }
                            if(specSkuData.getSpec_attr().size() == goods_sku_data.size()){
                                goods_sku_id = goods_sku_id.substring(0, goods_sku_id.length()-1);
                                for (GoodsDetailBean.DataBean.SpecDataBean.SpecListBean bean : specSkuData.getSpec_list()) {
                                    if(bean.getSpec_sku_id().equals(goods_sku_id)){
                                        String stock_str = String.valueOf(bean.getForm().getStock_num());
                                        stock.setText(stock_str);
                                        sku_price.setText(bean.getForm().getGoods_price());
                                    }
                                }
                            }
                        } else {
                            button.setBackgroundResource(R.drawable.sku_item_not_selected);
                            button.setTextColor(Color.parseColor("#333333"));
                            specItemsBean.setSelected(false);
                        }
                    }
                };

                sku_item.setAdapter(sku_item_adapter);

            }
        };

        title_recycler.setAdapter(specAttrAdapter);

        buy_now.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goods_sku_data.size() < sku_first_num){
                    Util.showToastError(context, "请选择规格");
                } else {
                    goods_sku_id = "";
                    for (Integer ii : goods_sku_data) {
                        goods_sku_id += ii + "_";
                    }
                    if(!TextUtils.isEmpty(goods_sku_id)) {
                        goods_sku_id = goods_sku_id.substring(0, goods_sku_id.length()-1);
                        if(specSkuData.getSpec_attr().size() == goods_sku_data.size()){
                            for (GoodsDetailBean.DataBean.SpecDataBean.SpecListBean bean : specSkuData.getSpec_list()) {
                                if(bean.getSpec_sku_id().equals(goods_sku_id)){
                                    if(bean.getForm().getStock_num() == 0){
                                        Util.showToastError(context, "库存不足，请选择其他规格。");
                                    } else {
                                        // 检测商品数量
                                        if(goods_number == 0){
                                            Util.showToastError(context, "请选择商品数量");
                                        } else {
                                            // 开始提交
                                            Intent orderCheckoutInfo = new Intent(context, MallOrderCheckoutActivity.class);
                                            orderCheckoutInfo.putExtra("delivery", 0);
                                            orderCheckoutInfo.putExtra("shop_id", 0);
                                            orderCheckoutInfo.putExtra("coupon_id", 0);
                                            orderCheckoutInfo.putExtra("is_use_points", 0);
                                            orderCheckoutInfo.putExtra("goods_id", goods_id);
                                            orderCheckoutInfo.putExtra("goods_num", goods_number);
                                            orderCheckoutInfo.putExtra("goods_sku_id", TextUtils.isEmpty(goods_sku_id) ? String.valueOf(0) : goods_sku_id);
                                            orderCheckoutInfo.putExtra("wxapp_id", 10001);
                                            orderCheckoutInfo.putExtra("token", Util.getToken(context));
                                            context.startActivity(orderCheckoutInfo);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        goods_sku_id = "";
                        if(goodsDataBean.getDetail().getGoods_sku().getStock_num() == 0){
                            Util.showToastError(context, "库存不足，请选择其他规格。");
                        } else {
                            // 检测商品数量
                            if(goods_number == 0){
                                Util.showToastError(context, "请选择商品数量");
                            } else {
                                // 开始提交
                                Intent orderCheckoutInfo = new Intent(context, MallOrderCheckoutActivity.class);
                                orderCheckoutInfo.putExtra("delivery", 0);
                                orderCheckoutInfo.putExtra("shop_id", 0);
                                orderCheckoutInfo.putExtra("coupon_id", 0);
                                orderCheckoutInfo.putExtra("is_use_points", 0);
                                orderCheckoutInfo.putExtra("goods_id", goods_id);
                                orderCheckoutInfo.putExtra("goods_num", goods_number);
                                orderCheckoutInfo.putExtra("goods_sku_id", TextUtils.isEmpty(goods_sku_id) ? String.valueOf(0) : goods_sku_id);
                                orderCheckoutInfo.putExtra("wxapp_id", 10001);
                                orderCheckoutInfo.putExtra("token", Util.getToken(context));
                                context.startActivity(orderCheckoutInfo);
                            }
                        }
                    }
                    // 记得判断库存

                }
            }
        });

//        add_cart.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(goods_sku_data.size() < sku_first_num){
//                    Util.showToastError(context, "请选择规格");
//                } else {
//                    goods_sku_id = "";
//                    for (Integer ii : goods_sku_data) {
//                        goods_sku_id += ii + "_";
//                    }
//                    if(!TextUtils.isEmpty(goods_sku_id)) {
//                        goods_sku_id = goods_sku_id.substring(0, goods_sku_id.length()-1);
//                        // 记得判断库存
//                        if(specSkuData.getSpec_attr().size() == goods_sku_data.size()){
//                            for (GoodsDetailBean.DataBean.SpecDataBean.SpecListBean bean : specSkuData.getSpec_list()) {
//                                if(bean.getSpec_sku_id().equals(goods_sku_id)){
//                                    if(bean.getForm().getStock_num() == 0){
//                                        Util.showToastError(context, "库存不足，请选择其他规格。");
//                                    } else {
//                                        // 检测商品数量
//                                        if(goods_number == 0){
//                                            Util.showToastError(context, "请选择商品数量");
//                                        } else {
//                                            // 开始加入购物车
//                                            OkHttpUtils
//                                                    .post()
//                                                    .url(Util.url + "?s=/api/cart/add")
//                                                    .addParams("goods_id", String.valueOf(goods_id))
//                                                    .addParams("goods_num", String.valueOf(goods_number))
//                                                    .addParams("goods_sku_id", TextUtils.isEmpty(goods_sku_id) ? String.valueOf(0) : goods_sku_id)
//                                                    .addParams("wxapp_id", String.valueOf(10001))
//                                                    .addParams("token", Util.getToken(context))
//                                                    .build()
//                                                    .execute(new StringCallback() {
//                                                        @Override
//                                                        public void onError(Call call, Exception e, int id) {
//
//                                                        }
//
//                                                        @Override
//                                                        public void onResponse(String response, int id) {
//                                                            Gson gson = new Gson();
//                                                            AddCartBean bean = gson.fromJson(response, AddCartBean.class);
//                                                            Util.showToastSuccess(context, bean.getMsg());
//                                                        }
//                                                    });
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    } else {
//                        goods_sku_id = "";
//                        if(goodsDataBean.getDetail().getGoods_sku().getStock_num() == 0) {
//                            Util.showToastError(context, "库存不足，请选择其他规格。");
//                        } else {
//                            // 检测商品数量
//                            if(goods_number == 0){
//                                Util.showToastError(context, "请选择商品数量");
//                            } else {
//                                // 开始加入购物车
//                                OkHttpUtils
//                                        .post()
//                                        .url(Util.url + "?s=/api/cart/add")
//                                        .addParams("goods_id", String.valueOf(goods_id))
//                                        .addParams("goods_num", String.valueOf(goods_number))
//                                        .addParams("goods_sku_id", TextUtils.isEmpty(goods_sku_id) ? String.valueOf(0) : goods_sku_id)
//                                        .addParams("wxapp_id", String.valueOf(10001))
//                                        .addParams("token", Util.getToken(context))
//                                        .build()
//                                        .execute(new StringCallback() {
//                                            @Override
//                                            public void onError(Call call, Exception e, int id) {
//
//                                            }
//
//                                            @Override
//                                            public void onResponse(String response, int id) {
//                                                Gson gson = new Gson();
//                                                AddCartBean bean = gson.fromJson(response, AddCartBean.class);
//                                                Util.showToastSuccess(context, bean.getMsg());
//                                            }
//                                        });
//                            }
//                        }
//                    }
//
//                }
//            }
//        });

//        start_cart.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cartIntent = new Intent();
//                context.startActivity(cartIntent);
//            }
//        });

    }

    private int mPosition = -1;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}
