package com.rbt.diamond.activity.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityMyBankAccountBinding;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MyBankAccountActivity extends AppCompatActivity {
    protected ActivityMyBankAccountBinding binding;
    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MyBankAccountActivity.this, R.layout.activity_my_bank_account);

        initView();
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

        binding.bankRealName.setText(intent.getStringExtra("bank_real_name"));
        binding.bankName.setText(intent.getStringExtra("bank_name"));
        binding.bankCard.setText(intent.getStringExtra("bank_account"));

        binding.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSaveBankInfo();
            }
        });
    }

    protected void initSaveBankInfo(){
        if(TextUtils.isEmpty(binding.bankRealName.getText().toString())){
            Util.showToastError(MyBankAccountActivity.this, "请填写开户姓名");
        } else if(TextUtils.isEmpty(binding.bankName.getText().toString())){
            Util.showToastError(MyBankAccountActivity.this, "请填写银行名称");
        } else if(TextUtils.isEmpty(binding.bankCard.getText().toString())){
            Util.showToastError(MyBankAccountActivity.this, "请填写银行卡号");
        } else {
            OkHttpUtils
                    .post()
                    .url(Util.url + "api/user/authorization")
                    .addParams("token", Util.getToken(MyBankAccountActivity.this))
                    .addParams("wxapp_id", "10001")
                    .addParams("bank_realname", binding.bankRealName.getText().toString())
                    .addParams("bank_name", binding.bankName.getText().toString())
                    .addParams("bank_account", binding.bankCard.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                            if(resultMsgBean.getCode() == 1){
                                Util.showToastSuccess(MyBankAccountActivity.this, resultMsgBean.getMsg());
                                finish();
                            } else if(resultMsgBean.getCode() == 0){
                                Util.showToastError(MyBankAccountActivity.this, resultMsgBean.getMsg());
                            }
                        }
                    });
        }
    }
}