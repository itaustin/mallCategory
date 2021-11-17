package com.rbt.diamond.activity.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.passport.LoginActivity;
import com.rbt.diamond.activity.passport.RegisterActivity;
import com.rbt.diamond.databinding.ActivityChangeHandlingFeePointsBinding;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class ChangeHandlingFeePointsActivity extends AppCompatActivity {
    protected ActivityChangeHandlingFeePointsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ChangeHandlingFeePointsActivity.this, R.layout.activity_change_handling_fee_points);

        initView();

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

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.mobilePhone.getText().toString())){
                    Util.showToastError(ChangeHandlingFeePointsActivity.this, "请输入接收者手机号码");
                } else if(TextUtils.isEmpty(binding.handlingFeePoints.getText().toString())) {
                    Util.showToastError(ChangeHandlingFeePointsActivity.this, "请输入手工积分数量");
                } else {
                    binding.save.setEnabled(false);
                    OkHttpUtils
                            .get()
                            .url(Util.url + "api/user/forwardPoints")
                            .addParams("token", Util.getToken(ChangeHandlingFeePointsActivity.this))
                            .addParams("wxapp_id", "10001")
                            .addParams("mobile_phone", binding.mobilePhone.getText().toString())
                            .addParams("points_num", binding.handlingFeePoints.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    binding.save.setEnabled(true);
                                    ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                    if(resultMsgBean.getCode() == -1) {
                                        Util.showToastError(ChangeHandlingFeePointsActivity.this, "请先登录");
                                    } else if(resultMsgBean.getCode() == 1) {
                                        Util.showToastSuccess(ChangeHandlingFeePointsActivity.this, resultMsgBean.getMsg());
                                    } else if(resultMsgBean.getCode() == 0) {
                                        Util.showToastError(ChangeHandlingFeePointsActivity.this, resultMsgBean.getMsg());
                                    }
                                }
                            });

                }
            }
        });
    }
}