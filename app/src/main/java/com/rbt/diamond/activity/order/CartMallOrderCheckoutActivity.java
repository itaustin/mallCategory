package com.rbt.diamond.activity.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.address.AddressManagerActivity;
import com.rbt.diamond.public_bean.OrderCheckoutBean;
import com.rbt.diamond.public_bean.PayBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.qinsong.lib.pay.PAY_TYPE;
import org.qinsong.lib.pay.PayAPI;
import org.qinsong.lib.pay.PayCallback;
import org.qinsong.lib.pay.ali.AliPayInfo;

import okhttp3.Call;

public class CartMallOrderCheckoutActivity extends AppCompatActivity {

    protected TitleBar titleBar;
    protected RecyclerView goods_list_recycler;

    protected Intent intent;

    protected EasyAdapter<OrderCheckoutBean.DataBean.GoodsListBean> goodsListBeanEasyAdapter;

    protected TextView freight, total, address_name, phone, address;

    protected ConstraintLayout address_manager;

    protected Button pay_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_mall_order_checkout);


        initView();

        initRequestOrderInfo();
    }

    protected void initView(){
        intent = getIntent();
        titleBar = findViewById(R.id.titleBar);
        freight = findViewById(R.id.freight);
        total = findViewById(R.id.total);
        address_manager = findViewById(R.id.address_manager);
        pay_now = findViewById(R.id.pay_now);

        address_name = findViewById(R.id.address_name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);

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

        goods_list_recycler = findViewById(R.id.goods_list_recycler);

        goods_list_recycler.setLayoutManager(new LinearLayoutManager(CartMallOrderCheckoutActivity.this, LinearLayoutManager.VERTICAL, false));

        address_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressIntent = new Intent(CartMallOrderCheckoutActivity.this, AddressManagerActivity.class);
                startActivity(addressIntent);
            }
        });
    }

    protected void initRequestOrderInfo(){
        OkHttpUtils
                .get()
                .url(Util.url + "?s=/api/order/cartForAndroid")
                .addParams("delivery", String.valueOf(intent.getIntExtra("delivery", 0)))
                .addParams("shop_id", String.valueOf(intent.getIntExtra("shop_id", 0)))
                .addParams("coupon_id", String.valueOf(intent.getIntExtra("coupon_id", 0)))
                .addParams("is_use_points", String.valueOf(intent.getIntExtra("is_use_points", 0)))
                .addParams("cart_ids", intent.getStringExtra("cart_ids"))
                .addParams("wxapp_id", String.valueOf(intent.getIntExtra("wxapp_id", 0)))
                .addParams("token", Util.getToken(CartMallOrderCheckoutActivity.this))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        OrderCheckoutBean bean = gson.fromJson(response, OrderCheckoutBean.class);
                        freight.setText(bean.getData().getExpress_price());
                        total.setText("小计：" + bean.getData().getOrder_pay_price());
                        address_name.setText(bean.getData().getAddress().getName());
                        phone.setText(bean.getData().getAddress().getPhone());
                        address.setText(bean.getData().getAddress().getRegion().getProvince() + "-" + bean.getData().getAddress().getRegion().getCity() + "-" + bean.getData().getAddress().getRegion().getRegion() + bean.getData().getAddress().getDetail());
                        goodsListBeanEasyAdapter = new EasyAdapter<OrderCheckoutBean.DataBean.GoodsListBean>(bean.getData().getGoods_list(), R.layout.order_checkout_goods_item) {
                            @Override
                            protected void bind(@NonNull ViewHolder viewHolder, OrderCheckoutBean.DataBean.GoodsListBean goodsListBean, int i) {
                                Glide.with(CartMallOrderCheckoutActivity.this).load(goodsListBean.getGoods_image()).into((ImageView) viewHolder.getView(R.id.goods_image));
                                viewHolder.setText(R.id.goods_name, goodsListBean.getGoods_name());
                                viewHolder.setText(R.id.goods_price, "" + goodsListBean.getGoods_price());
                                viewHolder.setText(R.id.sku_info, goodsListBean.getGoods_sku().getGoods_attr());
                            }
                        };

                        goods_list_recycler.setAdapter(goodsListBeanEasyAdapter);
                    }
                });

        pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils
                        .post()
                        .url(Util.url + "?s=/api/order/cartForAndroid")
                        .addParams("delivery", String.valueOf(intent.getIntExtra("delivery", 0)))
                        .addParams("shop_id", String.valueOf(intent.getIntExtra("shop_id", 0)))
                        .addParams("coupon_id", String.valueOf(intent.getIntExtra("coupon_id", 0)))
                        .addParams("is_use_points", String.valueOf(intent.getIntExtra("is_use_points", 0)))
                        .addParams("cart_ids", intent.getStringExtra("cart_ids"))
                        .addParams("wxapp_id", String.valueOf(intent.getIntExtra("wxapp_id", 0)))
                        .addParams("token", Util.getToken(CartMallOrderCheckoutActivity.this))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                System.out.println(e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                System.out.println(response);
                                Gson gson = new Gson();
                                PayBean payBean = gson.fromJson(response, PayBean.class);
                                AliPayInfo aliPayInfo = new AliPayInfo();
                                aliPayInfo.payParam = payBean.getData();
                                PayAPI.get(CartMallOrderCheckoutActivity.this, PAY_TYPE.ALIPAY).pay(aliPayInfo, new PayCallback() {
                                    @Override
                                    public void onComplete(PAY_TYPE payType, String result) {
                                        //同步支付结果成功
                                        Util.showToastSuccess(CartMallOrderCheckoutActivity.this, "支付成功");
                                    }

                                    @Override
                                    public void onFail(PAY_TYPE payType, String msg) {
                                        finish();
                                        System.out.println("支付失败");
                                    }

                                    @Override
                                    public void onCancel(PAY_TYPE payType) {
                                        finish();
                                        System.out.println("支付取消");
                                    }
                                });
                            }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        initRequestOrderInfo();
    }
}