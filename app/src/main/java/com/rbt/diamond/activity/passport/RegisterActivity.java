package com.rbt.diamond.activity.passport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityRegisterBinding;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class RegisterActivity extends AppCompatActivity {
    protected ActivityRegisterBinding binding;
    protected Intent intent;
    protected String invite_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(RegisterActivity.this, R.layout.activity_register);

        intent = getIntent();
        invite_code = intent.getStringExtra("invite_code");
        if(!TextUtils.isEmpty(invite_code)) {
            binding.refereeCode.setText(invite_code);
            binding.refereeCode.setEnabled(false);
        } else {
            binding.refereeCode.setEnabled(true);
        }

        initView();

        clickOrder();

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

    protected void clickOrder(){
        binding.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile_phone = binding.mobilePhone.getText().toString();
                if(TextUtils.isEmpty(mobile_phone)){
                    Util.showToastError(RegisterActivity.this, "请填写手机号");
                } else {
                    OkHttpUtils
                            .post()
                            .url(Util.url + "api/passport/getGenerateCode")
                            .addParams("mobile_phone", mobile_phone)
                            .addParams("wxapp_id", "10001")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    System.out.println(response);
                                    CountDownTimer timer = new CountDownTimer(60000, 1000) {
                                        @SuppressLint("SetTextI18n")
                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                            binding.time.setEnabled(false);
                                            binding.time.setText(millisUntilFinished / 1000 + "重新获取");
                                        }

                                        @Override
                                        public void onFinish() {
                                            binding.time.setEnabled(true);
                                            binding.time.setText("重新获取验证码");
                                        }
                                    }.start();
                                }
                            });
                }
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.refereeCode.getText().toString())){
                    Util.showToastError(RegisterActivity.this, "请填写邀请码或扫码注册");
                } else if(TextUtils.isEmpty(binding.mobilePhone.getText().toString())){
                    Util.showToastError(RegisterActivity.this, "请填写手机号");
                } else if(TextUtils.isEmpty(binding.activeCode.getText().toString())){
                    Util.showToastError(RegisterActivity.this, "请填写手机验证码");
                } else if(TextUtils.isEmpty(binding.password.getText().toString())){
                    Util.showToastError(RegisterActivity.this, "请填写登录密码");
                } else if(TextUtils.isEmpty(binding.repeatPassword.getText().toString())){
                    Util.showToastError(RegisterActivity.this, "请重复填写登录密码");
                } else {
                    OkHttpUtils
                            .post()
                            .url(Util.url + "api/passport/register")
                            .addParams("token", Util.getToken(RegisterActivity.this))
                            .addParams("referee_code", binding.refereeCode.getText().toString())
                            .addParams("username", binding.mobilePhone.getText().toString())
                            .addParams("active_code", binding.activeCode.getText().toString())
                            .addParams("password", binding.password.getText().toString())
                            .addParams("repeat_password", binding.repeatPassword.getText().toString())
                            .addParams("wxapp_id", "10001")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    System.out.println(response);
                                    ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                    if(resultMsgBean.getCode() == -1){
                                        Util.showToastError(RegisterActivity.this, "请先登录");
                                    } else if(resultMsgBean.getCode() == 1){
                                        Util.showToastSuccess(RegisterActivity.this, resultMsgBean.getMsg());
                                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(loginIntent);
                                    } else if(resultMsgBean.getCode() == 0){
                                        Util.showToastError(RegisterActivity.this, resultMsgBean.getMsg());
                                    }
                                }
                            });
                }
            }
        });
    }
}