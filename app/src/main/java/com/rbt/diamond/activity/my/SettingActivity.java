package com.rbt.diamond.activity.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.passport.ChangePasswordActivity;
import com.rbt.diamond.databinding.ActivitySettingBinding;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class SettingActivity extends AppCompatActivity {
    protected ActivitySettingBinding binding;
    protected BasePopupView editNickName;
    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SettingActivity.this, R.layout.activity_setting);

        initView();

        initClick();
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

        intent = getIntent();

        binding.username.setText(intent.getStringExtra("mobile_phone"));
    }

    protected void initClick(){
        binding.loginPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        binding.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editNickName = new XPopup.Builder(SettingActivity.this)
                        .hasStatusBarShadow(false)
                        .isDestroyOnDismiss(true)
                        .autoOpenSoftInput(true)
                        .isDarkTheme(false)
                        .asInputConfirm("昵称修改", null, null, "请输入您的昵称",
                                new OnInputConfirmListener() {
                                    @Override
                                    public void onConfirm(String text) {
                                        OkHttpUtils
                                                .post()
                                                .url(Util.url + "api/user/changeNickName")
                                                .addParams("token", Util.getToken(SettingActivity.this))
                                                .addParams("nickName", text)
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
                                                            Util.showToastError(SettingActivity.this, "请先登录");
                                                        } else if(resultMsgBean.getCode() == 1){
                                                            Util.showToastSuccess(SettingActivity.this, resultMsgBean.getMsg());
                                                            editNickName.smartDismiss();
                                                            finish();
                                                        } else if(resultMsgBean.getCode() == 0){
                                                            Util.showToastError(SettingActivity.this, resultMsgBean.getMsg());
                                                        }
                                                    }
                                                });
                                    }
                                })
                        .show();
            }
        });
    }
}