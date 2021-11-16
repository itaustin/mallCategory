package com.rbt.diamond.activity.passport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityChangePasswordBinding;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class ChangePasswordActivity extends AppCompatActivity {
    protected ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        binding = DataBindingUtil.setContentView(ChangePasswordActivity.this, R.layout.activity_change_password);

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

        changePassword();
    }

    protected void changePassword(){
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.sourcePassword.getText().toString())) {
                    Util.showToastError(ChangePasswordActivity.this, "请输入原密码");
                }else if(TextUtils.isEmpty(binding.password.getText().toString())) {
                    Util.showToastError(ChangePasswordActivity.this, "请输入新密码");
                }else if(TextUtils.isEmpty(binding.repeatPassword.getText().toString())) {
                    Util.showToastError(ChangePasswordActivity.this, "请重复输入原密码");
                } else {
                    new XPopup.Builder(ChangePasswordActivity.this)
                            .asConfirm("警告提示", "确认修改密码吗？", new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    OkHttpUtils
                                            .get()
                                            .url(Util.url + "api/passport/changePassword")
                                            .addParams("password", binding.sourcePassword.getText().toString())
                                            .addParams("new_password", binding.password.getText().toString())
                                            .addParams("repeat_new_password", binding.repeatPassword.getText().toString())
                                            .addParams("token", Util.getToken(ChangePasswordActivity.this))
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
                                                        Util.showToastError(ChangePasswordActivity.this, "请先登录");
                                                    } else if(resultMsgBean.getCode() == 1){
                                                        Util.showToastSuccess(ChangePasswordActivity.this, resultMsgBean.getMsg());
                                                        finish();
                                                    } else if(resultMsgBean.getCode() == 0){
                                                        Util.showToastError(ChangePasswordActivity.this, resultMsgBean.getMsg());
                                                    }
                                                }
                                            });
                                }
                            }).show();
                }
            }
        });
    }
}