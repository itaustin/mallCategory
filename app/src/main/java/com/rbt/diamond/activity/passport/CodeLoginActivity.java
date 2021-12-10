package com.rbt.diamond.activity.passport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.MainActivity;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityCodeLoginBinding;
import com.rbt.diamond.public_bean.LoginUserBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class CodeLoginActivity extends AppCompatActivity {
    protected ActivityCodeLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_code_login);

        initView();
    }

    protected void initView(){
        binding.passwordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        binding.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile_phone = binding.mobilePhone.getText().toString();
                if(TextUtils.isEmpty(mobile_phone)){
                    Util.showToastError(CodeLoginActivity.this, "请填写手机号");
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

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_text = binding.mobilePhone.getText().toString();
                String password_text = binding.activeCode.getText().toString();
                if(TextUtils.isEmpty(username_text)){
                    Util.showToastWarning(CodeLoginActivity.this, "请填写账号");
                } else if(TextUtils.isEmpty(password_text)){
                    Util.showToastWarning(CodeLoginActivity.this, "请填写4位数手机验证码");
                } else {
                    String url = Util.url + "?s=/api/passport/login";
                    // 开始登录操作
                    OkHttpUtils
                            .get()
                            .url(url)
                            .addParams("username", username_text)
                            .addParams("password", password_text)
                            .addParams("is_code", String.valueOf(1))
                            .addParams("code", binding.activeCode.getText().toString())
                            .addParams("wxapp_id", "10001")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
//                                    Util.showToastError(LoginActivity.this, e.getMessage());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    System.out.println(response);
                                    Gson gson = new Gson();
                                    LoginUserBean bean = gson.fromJson(response, LoginUserBean.class);
                                    if(bean.getCode() == 1){
                                        // 登录成功
                                        Util.showToastSuccess(CodeLoginActivity.this, bean.getMsg());
                                        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("token", bean.getData().getToken());
                                        editor.putInt("user_id", bean.getData().getUser_id());
                                        editor.apply();
                                        Intent intent = new Intent(CodeLoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else if(bean.getCode() == 0){
                                        // 登录错误
                                        Util.showToastError(CodeLoginActivity.this, bean.getMsg());
                                    }
                                }
                            });
                }
            }
        });
    }
}