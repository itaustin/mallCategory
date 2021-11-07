package com.rbt.diamond.activity.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.passport.LoginActivity;
import com.rbt.diamond.databinding.ActivityCertificateBinding;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.public_bean.UserInfoBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class CertificateActivity extends AppCompatActivity {
    protected ActivityCertificateBinding binding;
    protected UserInfoBean userInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);
        binding = DataBindingUtil.setContentView(CertificateActivity.this, R.layout.activity_certificate);

        initView();

        initRequestUserInfo();

    }

    protected void initRequestUserInfo(){
        OkHttpUtils
                .get()
                .url(Util.url + "api/user/userInfo")
                .addParams("token", Util.getToken(CertificateActivity.this))
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
//                        Glide.with(requireActivity()).load(userInfoBean.getData().getAvatarUrl()).into(avatar);
                        if(userInfoBean.getCode() == -1){
                            Util.showToastError(CertificateActivity.this, "请先登录！");
                            finish();
                        } else if(userInfoBean.getCode() == 1){
                            binding.realName.setText(userInfoBean.getData().getReal_name());
                            binding.certificateNo.setText(userInfoBean.getData().getId_card());
                            // 暂时显示积分
                        } else {
                            Util.showToastError(CertificateActivity.this, userInfoBean.getMsg());
                        }
                    }
                });
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

        binding.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.realName.getText().toString())){
                    Util.showToastError(CertificateActivity.this, "请填写真实姓名");
                } else if(TextUtils.isEmpty(binding.certificateNo.getText().toString())) {
                    Util.showToastError(CertificateActivity.this, "请填写身份证号");
                } else {
                    OkHttpUtils
                            .post()
                            .url(Util.url + "api/user/certification")
                            .addParams("token", Util.getToken(CertificateActivity.this))
                            .addParams("real_name", binding.realName.getText().toString())
                            .addParams("id_card", binding.certificateNo.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                    if(resultMsgBean.getCode() == 1){
                                        Util.showToastSuccess(CertificateActivity.this, "认证成功");
                                        finish();
                                    } else if(resultMsgBean.getCode() == 0){
                                        Util.showToastError(CertificateActivity.this, resultMsgBean.getMsg());
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRequestUserInfo();
    }
}