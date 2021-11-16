package com.rbt.diamond.activity.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityGoldenCouponBinding;
import com.rbt.diamond.public_bean.GoldBean;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class GoldenCouponActivity extends AppCompatActivity {
    protected ActivityGoldenCouponBinding binding;
    protected EasyAdapter<GoldBean.DataBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(GoldenCouponActivity.this, R.layout.activity_golden_coupon);

        initView();

        initRequestGold();
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
        binding.goldRecycler.setLayoutManager(new LinearLayoutManager(GoldenCouponActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    protected void initRequestGold(){
        OkHttpUtils
                .get()
                .url(Util.url + "api/gold/getList")
                .addParams("token", Util.getToken(GoldenCouponActivity.this))
                .addParams("wxapp_id", "10001")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        GoldBean bean = gson.fromJson(response, GoldBean.class);
                        if(bean.getCode() == 1) {
                            adapter = new EasyAdapter<GoldBean.DataBean>(bean.getData(), R.layout.golden_coupon_item) {
                                @SuppressLint("SetTextI18n")
                                @Override
                                protected void bind(@NonNull ViewHolder viewHolder, GoldBean.DataBean dataBean, int i) {
                                    TextView gold_money = viewHolder.getView(R.id.gold_money);
                                    gold_money.setText(dataBean.getMoney() + "g");
                                    ImageView imageView = viewHolder.getView(R.id.imageView13);

                                    if(dataBean.getIs_clerk() == 1){
                                        viewHolder.setText(R.id.status, "【已核销】");
                                    } else {
                                        viewHolder.setText(R.id.status, "【待核销】");
                                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                new XPopup.Builder(GoldenCouponActivity.this)
                                                        .asConfirm("黄金券核销", "确认核销吗？", new OnConfirmListener() {
                                                            @Override
                                                            public void onConfirm() {
                                                                OkHttpUtils
                                                                        .get()
                                                                        .url(Util.url + "api/gold/clerk")
                                                                        .addParams("token", Util.getToken(GoldenCouponActivity.this))
                                                                        .addParams("wxapp_id", "10001")
                                                                        .addParams("golden_coupon_id", String.valueOf(dataBean.getGolden_coupon_id()))
                                                                        .build()
                                                                        .execute(new StringCallback() {
                                                                            @Override
                                                                            public void onError(Call call, Exception e, int id) {

                                                                            }

                                                                            @Override
                                                                            public void onResponse(String response, int id) {
                                                                                ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                                                                if(resultMsgBean.getCode() == -1){
                                                                                    Util.showToastError(GoldenCouponActivity.this, "请先登录");
                                                                                } else if(resultMsgBean.getCode() == 1){
                                                                                    initRequestGold();
                                                                                    Util.showToastSuccess(GoldenCouponActivity.this, resultMsgBean.getMsg());
                                                                                } else if(resultMsgBean.getCode() == 0){
                                                                                    Util.showToastError(GoldenCouponActivity.this, resultMsgBean.getMsg());
                                                                                }
                                                                            }
                                                                        });
                                                            }
                                                        }).show();
                                            }
                                        });
                                    }
                                }
                            };
                            binding.goldRecycler.setAdapter(adapter);
                        }
                    }
                });
    }
}