package com.rbt.diamond.activity.passport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityLoginBinding;
import com.rbt.diamond.public_bean.LoginUserBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity {
    protected ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

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


        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_text = binding.mobilePhone.getText().toString();
                String password_text = binding.password.getText().toString();
                if(TextUtils.isEmpty(username_text)){
                    Util.showToastWarning(LoginActivity.this, "请填写账号");
                } else if(TextUtils.isEmpty(password_text)){
                    Util.showToastWarning(LoginActivity.this, "请填写密码");
                } else {
                    String url = Util.url + "?s=/api/passport/login";
                    // 开始登录操作
                    OkHttpUtils
                            .get()
                            .url(url)
                            .addParams("username", username_text)
                            .addParams("password", password_text)
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
                                        Util.showToastSuccess(LoginActivity.this, bean.getMsg());
                                        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("token", bean.getData().getToken());
                                        editor.putInt("user_id", bean.getData().getUser_id());
                                        editor.apply();
                                        finish();
                                    } else if(bean.getCode() == 0){
                                        // 登录错误
                                        Util.showToastError(LoginActivity.this, bean.getMsg());
                                    }
                                }
                            });
                }
            }
        });
    }
}