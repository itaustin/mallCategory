package com.rbt.diamond.activity.points;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(MyPointsActivity.this, R.layout.activity_my_points);

        initView();

        initRequestPoints();
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
                            // R.layout.points_record_item

                        }
                    }
                });
    }
}