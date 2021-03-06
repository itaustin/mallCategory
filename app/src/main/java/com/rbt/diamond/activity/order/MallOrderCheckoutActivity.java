package com.rbt.diamond.activity.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
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
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rbt.diamond.MainActivity;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.address.AddressManagerActivity;
import com.rbt.diamond.public_bean.OrderCheckoutBean;
import com.rbt.diamond.public_bean.PayBean;
import com.rbt.diamond.public_bean.UploadImageBean;
import com.rbt.diamond.util.GlideEngine;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.qinsong.lib.pay.PAY_TYPE;
import org.qinsong.lib.pay.PayAPI;
import org.qinsong.lib.pay.PayCallback;
import org.qinsong.lib.pay.ali.AliPayInfo;

import java.io.File;
import java.util.List;

import okhttp3.Call;

public class MallOrderCheckoutActivity extends AppCompatActivity {
    protected TitleBar titleBar;
    protected RecyclerView goods_list_recycler;

    protected Intent intent;

    protected EasyAdapter<OrderCheckoutBean.DataBean.GoodsListBean> goodsListBeanEasyAdapter;

    protected TextView freight, total, address_name, phone, address, bottom_total;

    protected ConstraintLayout address_manager;

    protected Button pay_now;

    protected File pay_image = null;

    protected OrderCheckoutBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_order_checkout);

        initView();

        initRequestOrderInfo();
    }

    protected void initView() {
        intent = getIntent();
        titleBar = findViewById(R.id.titleBar);
        freight = findViewById(R.id.freight);
        total = findViewById(R.id.total);
        bottom_total = findViewById(R.id.bottom_total);
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

        goods_list_recycler.setLayoutManager(new LinearLayoutManager(MallOrderCheckoutActivity.this, LinearLayoutManager.VERTICAL, false));

        address_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressIntent = new Intent(MallOrderCheckoutActivity.this, AddressManagerActivity.class);
                startActivity(addressIntent);
            }
        });
    }

    protected void initRequestOrderInfo() {
        OkHttpUtils
                .get()
                .url(Util.url + "?s=/api/order/buyNowAlipay")
                .addParams("delivery", String.valueOf(intent.getIntExtra("delivery", 0)))
                .addParams("shop_id", String.valueOf(intent.getIntExtra("shop_id", 0)))
                .addParams("coupon_id", String.valueOf(intent.getIntExtra("coupon_id", 0)))
                .addParams("is_use_points", String.valueOf(intent.getIntExtra("is_use_points", 0)))
                .addParams("goods_id", String.valueOf(intent.getIntExtra("goods_id", 0)))
                .addParams("goods_num", String.valueOf(intent.getIntExtra("goods_num", 0)))
                .addParams("goods_sku_id", String.valueOf(intent.getStringExtra("goods_sku_id")))
                .addParams("wxapp_id", String.valueOf(intent.getIntExtra("wxapp_id", 0)))
                .addParams("token", Util.getToken(MallOrderCheckoutActivity.this))
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
                        bean = gson.fromJson(response, OrderCheckoutBean.class);
                        if(bean.getCode() == -1) {
                            Util.showToastError(MallOrderCheckoutActivity.this, "????????????");
                            finish();
                        } else {
                            freight.setText(bean.getData().getExpress_price());
                            total.setText("?????????" + bean.getData().getOrder_pay_price());
                            bottom_total.setText("?????????" + bean.getData().getOrder_pay_price());
                            if(bean.getData().getAddress() != null){
                                address_name.setText(bean.getData().getAddress().getName());
                                phone.setText(bean.getData().getAddress().getPhone());
                                address.setText(bean.getData().getAddress().getRegion().getProvince() + "-" + bean.getData().getAddress().getRegion().getCity() + "-" + bean.getData().getAddress().getRegion().getRegion() + bean.getData().getAddress().getDetail());
                            } else {
                                address_name.setText("?????????????????????");
//                                phone.setText("?????????????????????");
//                                address.setText("?????????????????????");
                            }
                            goodsListBeanEasyAdapter = new EasyAdapter<OrderCheckoutBean.DataBean.GoodsListBean>(bean.getData().getGoods_list(), R.layout.order_checkout_goods_item) {
                                @Override
                                protected void bind(@NonNull ViewHolder viewHolder, OrderCheckoutBean.DataBean.GoodsListBean goodsListBean, int i) {
                                    Glide.with(MallOrderCheckoutActivity.this).load(goodsListBean.getGoods_image()).into((ImageView) viewHolder.getView(R.id.goods_image));
                                    viewHolder.setText(R.id.goods_name, goodsListBean.getGoods_name());
                                    if (goodsListBean.getCategory_id() == 10006 || goodsListBean.getCategory_id() == 10002) {
                                        viewHolder.setText(R.id.goods_price, "?????????" + goodsListBean.getGoods_price());
                                    } else {
                                        viewHolder.setText(R.id.goods_price, "" + goodsListBean.getGoods_price());
                                    }
                                    viewHolder.setText(R.id.sku_info, goodsListBean.getGoods_sku().getGoods_attr());
                                }
                            };

                            goods_list_recycler.setAdapter(goodsListBeanEasyAdapter);
                        }
                    }
                });

        pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean.getData().getGoods_list().get(0).getCategory_id() != 10001){
                    OkHttpUtils
                            .post()
                            .url(Util.url + "?s=/api/order/buyNowAlipayForAndroid")
                            .addParams("delivery", String.valueOf(intent.getIntExtra("delivery", 0)))
                            .addParams("shop_id", String.valueOf(intent.getIntExtra("shop_id", 0)))
                            .addParams("coupon_id", String.valueOf(intent.getIntExtra("coupon_id", 0)))
                            .addParams("is_use_points", String.valueOf(intent.getIntExtra("is_use_points", 0)))
                            .addParams("goods_id", String.valueOf(intent.getIntExtra("goods_id", 0)))
                            .addParams("goods_num", String.valueOf(intent.getIntExtra("goods_num", 0)))
                            .addParams("goods_sku_id", String.valueOf(intent.getStringExtra("goods_sku_id")))
                            .addParams("wxapp_id", String.valueOf(intent.getIntExtra("wxapp_id", 0)))
                            .addParams("token", Util.getToken(MallOrderCheckoutActivity.this))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    System.out.println(e.getMessage());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Gson gson = new Gson();
                                    PayBean payBean = gson.fromJson(response, PayBean.class);
                                    if(payBean.getCode() == 2){
                                        Util.showToastSuccess(MallOrderCheckoutActivity.this, "??????????????????");
                                        finish();
                                    } else if(payBean.getCode() == 3) {
                                        Util.showToastSuccess(MallOrderCheckoutActivity.this, payBean.getMsg());
                                        startActivity(new Intent(MallOrderCheckoutActivity.this, MainActivity.class));
                                    } else if(payBean.getCode() == 1) {
                                        Util.showToastSuccess(MallOrderCheckoutActivity.this, "?????????????????????????????????????????????");
                                    } else if(payBean.getCode() == 0){
                                        Util.showToastError(MallOrderCheckoutActivity.this, payBean.getMsg());
                                    } else {
                                        AliPayInfo aliPayInfo = new AliPayInfo();
                                        aliPayInfo.payParam = payBean.getData();
                                        PayAPI.get(MallOrderCheckoutActivity.this, PAY_TYPE.ALIPAY).pay(aliPayInfo, new PayCallback() {
                                            @Override
                                            public void onComplete(PAY_TYPE payType, String result) {
                                                //????????????????????????
                                                Util.showToastSuccess(MallOrderCheckoutActivity.this, "????????????");
                                                finish();
                                            }

                                            @Override
                                            public void onFail(PAY_TYPE payType, String msg) {
                                                finish();
                                                System.out.println("????????????");
                                            }

                                            @Override
                                            public void onCancel(PAY_TYPE payType) {
                                                finish();
                                                System.out.println("????????????");
                                            }
                                        });
                                    }
                                }
                            });
                } else {
                    new XPopup.Builder(MallOrderCheckoutActivity.this)
                            .asConfirm("????????????", "??????????????????????????????????????????/?????????", new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    PictureSelector.create(MallOrderCheckoutActivity.this)
                                            .openGallery(PictureMimeType.ofImage())
                                            .imageEngine(GlideEngine.createGlideEngine())// ??????????????????????????????????????????
                                            .isCamera(false)
                                            .isEnablePreviewAudio(false)
                                            .maxSelectNum(1)
                                            .minSelectNum(1)
                                            .hideBottomControls(false)// ????????????uCrop???????????????????????????
                                            .isEnableCrop(true)// ????????????
                                            .isOriginalImageControl(true)
                                            .isWeChatStyle(true)// ????????????????????????????????????
                                            .isUseCustomCamera(true)// ???????????????????????????
                                            .forResult(new OnResultCallbackListener<LocalMedia>() {
                                                @Override
                                                public void onResult(List<LocalMedia> result) {
                                                    System.out.println(result.toString());
                                                    for (LocalMedia media : result) {
                                                        BitmapFactory.Options options = new BitmapFactory.Options();
                                                        options.inJustDecodeBounds = true;
                                                        // Bitmap bitmap = BitmapFactory.decodeFile(media.isCut() ? media.getCutPath() : media.getRealPath());
                                                        pay_image = new File(media.isCut() ? media.getCutPath() : media.getRealPath());
                                                        BasePopupView basePopupView = new XPopup.Builder(MallOrderCheckoutActivity.this)
                                                                .dismissOnBackPressed(false)
                                                                .dismissOnTouchOutside(false)
                                                                .asLoading("?????????...")
                                                                .show();
                                                        System.out.println("start_image");
                                                        System.out.println(pay_image.getName());
                                                        System.out.println(pay_image.getPath());
                                                        OkHttpUtils
                                                                .post()
                                                                .addFile("iFile", pay_image.getName(), pay_image)
                                                                .url(Util.url + "api/upload/image")
                                                                .addParams("token", Util.getToken(MallOrderCheckoutActivity.this))
                                                                .addParams("wxapp_id", "10001")
                                                                .build()
                                                                .execute(new StringCallback() {
                                                                    @Override
                                                                    public void onError(Call call, Exception e, int id) {
                                                                        System.out.println("Exception???" + e.getMessage());
                                                                    }

                                                                    @Override
                                                                    public void onResponse(String response, int id) {
                                                                        System.out.println(response);
                                                                        Gson gson = new Gson();
                                                                        UploadImageBean imageBean = gson.fromJson(response, UploadImageBean.class);
                                                                        System.out.println("file_id???" + imageBean.getData().getFile_id());
                                                                        System.out.println(Util.url + "?s=/api/order/buyNowAlipayForAndroid");
                                                                        System.out.println("delivery" + " -- " + intent.getIntExtra("delivery", 0));
                                                                        System.out.println("shop_id" + " -- " + intent.getIntExtra("shop_id", 0));
                                                                        System.out.println("coupon_id" + " -- " + intent.getIntExtra("coupon_id", 0));
                                                                        System.out.println("is_use_points" + " -- " + intent.getIntExtra("is_use_points", 0));
                                                                        System.out.println("goods_id" + " -- " + intent.getIntExtra("goods_id", 0));
                                                                        System.out.println("goods_num" + " -- " + intent.getIntExtra("goods_num", 0));
                                                                        System.out.println("goods_sku_id" + " -- " + intent.getStringExtra("goods_sku_id"));
                                                                        System.out.println("audit_image_id" + " -- " + imageBean.getData().getFile_id());
                                                                        System.out.println("wxapp_id" + " -- " + intent.getIntExtra("wxapp_id", 0));
                                                                        System.out.println("token" + " -- " + Util.getToken(MallOrderCheckoutActivity.this));
                                                                        OkHttpUtils
                                                                                .post()
                                                                                .url(Util.url + "?s=/api/order/buyNowAlipayForAndroid")
                                                                                .addParams("delivery", String.valueOf(intent.getIntExtra("delivery", 0)))
                                                                                .addParams("shop_id", String.valueOf(intent.getIntExtra("shop_id", 0)))
                                                                                .addParams("coupon_id", String.valueOf(intent.getIntExtra("coupon_id", 0)))
                                                                                .addParams("is_use_points", String.valueOf(intent.getIntExtra("is_use_points", 0)))
                                                                                .addParams("goods_id", String.valueOf(intent.getIntExtra("goods_id", 0)))
                                                                                .addParams("goods_num", String.valueOf(intent.getIntExtra("goods_num", 0)))
                                                                                .addParams("goods_sku_id", String.valueOf(intent.getStringExtra("goods_sku_id")))
                                                                                .addParams("audit_image_id", String.valueOf(imageBean.getData().getFile_id()))
                                                                                .addParams("wxapp_id", String.valueOf(intent.getIntExtra("wxapp_id", 0)))
                                                                                .addParams("token", Util.getToken(MallOrderCheckoutActivity.this))
                                                                                .build()
                                                                                .execute(new StringCallback() {
                                                                                    @Override
                                                                                    public void onError(Call call, Exception e, int id) {
                                                                                        System.out.println(e.getMessage());
                                                                                    }

                                                                                    @Override
                                                                                    public void onResponse(String response, int id) {
                                                                                        System.out.println(response);
                                                                                        basePopupView.smartDismiss();
                                                                                        Gson gson = new Gson();
                                                                                        PayBean payBean = gson.fromJson(response, PayBean.class);
                                                                                        if (payBean.getCode() == 2) {
                                                                                            Util.showToastSuccess(MallOrderCheckoutActivity.this, "??????????????????");
                                                                                            finish();
                                                                                        } else if (payBean.getCode() == 3) {
                                                                                            Util.showToastSuccess(MallOrderCheckoutActivity.this, payBean.getMsg());
                                                                                            startActivity(new Intent(MallOrderCheckoutActivity.this, MainActivity.class));
                                                                                        } else if (payBean.getCode() == 1) {
                                                                                            Util.showToastSuccess(MallOrderCheckoutActivity.this, "?????????????????????????????????????????????");
                                                                                        } else if (payBean.getCode() == 0) {
                                                                                            Util.showToastError(MallOrderCheckoutActivity.this, payBean.getMsg());
                                                                                        } else {
                                                                                            AliPayInfo aliPayInfo = new AliPayInfo();
                                                                                            aliPayInfo.payParam = payBean.getData();
                                                                                            PayAPI.get(MallOrderCheckoutActivity.this, PAY_TYPE.ALIPAY).pay(aliPayInfo, new PayCallback() {
                                                                                                @Override
                                                                                                public void onComplete(PAY_TYPE payType, String result) {
                                                                                                    //????????????????????????
                                                                                                    Util.showToastSuccess(MallOrderCheckoutActivity.this, "????????????");
                                                                                                    finish();
                                                                                                }

                                                                                                @Override
                                                                                                public void onFail(PAY_TYPE payType, String msg) {
                                                                                                    finish();
                                                                                                    System.out.println("????????????");
                                                                                                }

                                                                                                @Override
                                                                                                public void onCancel(PAY_TYPE payType) {
                                                                                                    finish();
                                                                                                    System.out.println("????????????");
                                                                                                }
                                                                                            });
                                                                                        }
                                                                                    }
                                                                                });
                                                                    }
                                                                });

                                                    }
                                                }

                                                @Override
                                                public void onCancel() {

                                                }
                                            });
                                }
                            }).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        initRequestOrderInfo();
    }
}