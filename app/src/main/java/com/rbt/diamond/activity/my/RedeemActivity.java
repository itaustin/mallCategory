package com.rbt.diamond.activity.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hjq.bar.OnTitleBarListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityReddemBinding;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class RedeemActivity extends AppCompatActivity {
    protected ActivityReddemBinding binding;
    protected Intent intent;

    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    protected int g = 0;
    protected int total_points = 0;
    protected int need_points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(RedeemActivity.this, R.layout.activity_reddem);

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
        binding.points.setText(intent.getStringExtra("points"));

        data_list = new ArrayList<>();
        data_list.add("5g");
        data_list.add("10g");
        data_list.add("30g");
        data_list.add("50g");
        data_list.add("100g");

        arr_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_list);

        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinner.setAdapter(arr_adapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(data_list.get(i).equals("5g")){
                    g = 5;
                }
                if(data_list.get(i).equals("10g")){
                    g = 10;
                }
                if(data_list.get(i).equals("30g")){
                    g = 30;
                }
                if(data_list.get(i).equals("50g")){
                    g = 50;
                }
                if(data_list.get(i).equals("100g")){
                    g = 100;
                }
                int g_exchange_price = intent.getIntExtra("g_exchange_price", 0);
                int mul = intent.getIntExtra("mul", 0);
                total_points = g_exchange_price * g;
                need_points = mul * g;
                binding.points.setText(intent.getStringExtra("points"));
                binding.handlingFeePoints.setText(String.valueOf(total_points));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(RedeemActivity.this)
                        .asConfirm("兑换提示", "您确定将可用积分兑换为" + g + "g黄金吗", new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                OkHttpUtils
                                        .get()
                                        .url(Util.url + "api/user/redeem_gold")
                                        .addParams("token", Util.getToken(RedeemActivity.this))
                                        .addParams("wxapp_id", "10001")
                                        .addParams("handling_fee_points", String.valueOf(need_points))
                                        .addParams("g", String.valueOf(g))
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
                                                    Util.showToastError(RedeemActivity.this, "请先登录");
                                                } else if(resultMsgBean.getCode() == 1){
                                                    Util.showToastSuccess(RedeemActivity.this, resultMsgBean.getMsg());
                                                    finish();
                                                    finish();
                                                } else if(resultMsgBean.getCode() == 0){
                                                    Util.showToastError(RedeemActivity.this, resultMsgBean.getMsg());
                                                }
                                            }
                                        });
                            }
                        }).show();
            }
        });
    }
}