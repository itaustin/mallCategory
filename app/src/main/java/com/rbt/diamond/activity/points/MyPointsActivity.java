package com.rbt.diamond.activity.points;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.home.MemberMallActivity;
import com.rbt.diamond.activity.my.RedeemActivity;
import com.rbt.diamond.activity.passport.LoginActivity;
import com.rbt.diamond.databinding.ActivityMyPointsBinding;
import com.rbt.diamond.public_bean.UserInfoBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MyPointsActivity extends AppCompatActivity {
    protected ActivityMyPointsBinding binding;
    protected UserInfoBean userInfoBean;
    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(MyPointsActivity.this, R.layout.activity_my_points);

        initView();

        initRequestPoints();
    }

    protected void initView(){
        intent = getIntent();
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

        binding.pointsRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPointsActivity.this, CanUserPointsActivity.class);
                startActivity(intent);
            }
        });

        binding.withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 提现操作
                Intent intent = new Intent(MyPointsActivity.this, MemberMallActivity.class);
                intent.putExtra("category_id", "10002");
                startActivity(intent);
            }
        });

        binding.canUsePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPointsActivity.this, CanUserPointsActivity.class);
                startActivity(intent);
            }
        });

        binding.redeemGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPointsActivity.this, RedeemActivity.class);
                intent.putExtra("points", userInfoBean.getData().getFreeze_points());
                intent.putExtra("handling_fee_points", userInfoBean.getData().getHandling_fee_points());
                intent.putExtra("g_exchange_price", userInfoBean.getData().getG_price());
                intent.putExtra("mul", userInfoBean.getData().getG_exchange_price());
                startActivity(intent);
            }
        });
    }

    protected void initRequestPoints(){
        OkHttpUtils
                .get()
                .url(Util.url + "?s=/api/user/userInfo")
                .addParams("token", Util.getToken(MyPointsActivity.this))
                .addParams("wxapp_id", "10001")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                            Util.showToastError(requireActivity(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        userInfoBean = gson.fromJson(response, UserInfoBean.class);
                        if(userInfoBean.getCode() == -1){
                            Util.showToastError(MyPointsActivity.this, "请先登录！");
                        } else if(userInfoBean.getCode() == 1){
                            binding.points.setText(userInfoBean.getData().getPoints());
                            binding.freezePoints.setText(userInfoBean.getData().getFreeze_points());
                            binding.handPoints.setText(userInfoBean.getData().getHandling_fee_points());
                            // R.layout.points_record_item

                        }
                    }
                });
    }
}