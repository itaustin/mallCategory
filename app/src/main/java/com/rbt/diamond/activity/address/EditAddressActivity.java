package com.rbt.diamond.activity.address;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.rbt.diamond.R;
import com.rbt.diamond.public_bean.AddressDetailBean;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class EditAddressActivity extends AppCompatActivity {
    protected TitleBar titleBar;
    protected EditText name, phone, detail;
    protected TextView select_address;
    protected LinearLayout set_default;
    protected ConstraintLayout submit;
    protected Intent intent;
    protected int address_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        initView();
        initRequestDetail();
    }

    protected void initView(){
        intent = getIntent();
        address_id = intent.getIntExtra("address_id", 0);

        titleBar = findViewById(R.id.titleBar);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        detail = findViewById(R.id.detail);

        submit = findViewById(R.id.submit);

        select_address = findViewById(R.id.select_address);
        set_default = findViewById(R.id.set_default);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
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

        select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JDCityPicker cityPicker = new JDCityPicker();
                JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
                cityPicker.init(EditAddressActivity.this);
                cityPicker.setConfig(jdCityConfig);
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        select_address.setText(province.getName() + "," + city.getName() + "," + district.getName());
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                cityPicker.showCityPicker();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString())){
                    Util.showToastError(EditAddressActivity.this, "请填写收件人姓名");
                } else if(TextUtils.isEmpty(phone.getText().toString())){
                    Util.showToastError(EditAddressActivity.this, "请填写收件人手机号");
                } else if (TextUtils.isEmpty(select_address.getText().toString())){
                    Util.showToastError(EditAddressActivity.this, "请选择省市区");
                } else if(TextUtils.isEmpty(detail.getText().toString())){
                    Util.showToastError(EditAddressActivity.this, "请填写详细地址");
                } else {
                    OkHttpUtils
                            .post()
                            .url(Util.url + "api/address/edit")
                            .addParams("token", Util.getToken(EditAddressActivity.this))
                            .addParams("address_id", String.valueOf(address_id))
                            .addParams("name", name.getText().toString())
                            .addParams("phone", phone.getText().toString())
                            .addParams("region", select_address.getText().toString())
                            .addParams("detail", detail.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                    if(resultMsgBean.getCode() == -1){
                                        Util.showToastError(EditAddressActivity.this, "请先登录");
                                    } else if(resultMsgBean.getCode() == 1){
                                        Util.showToastSuccess(EditAddressActivity.this, resultMsgBean.getMsg());
                                    } else if(resultMsgBean.getCode() == 0){
                                        Util.showToastError(EditAddressActivity.this, resultMsgBean.getMsg());
                                    }
                                }
                            });
                }
            }
        });
    }

    protected void initRequestDetail(){
        OkHttpUtils
                .post()
                .url(Util.url + "api/address/detail")
                .addParams("token", Util.getToken(EditAddressActivity.this))
                .addParams("address_id", String.valueOf(address_id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        AddressDetailBean bean = gson.fromJson(response, AddressDetailBean.class);
                        select_address.setText(bean.getData().getRegion().get(0)+","+bean.getData().getRegion().get(1)+","+bean.getData().getRegion().get(2));
                        name.setText(bean.getData().getDetail().getName());
                        phone.setText(bean.getData().getDetail().getPhone());
                        detail.setText(bean.getData().getDetail().getDetail());
                    }
                });
    }
}