package com.rbt.diamond.activity.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.rbt.diamond.R;
import com.rbt.diamond.public_adapter.AddressAdapter;
import com.rbt.diamond.public_bean.AddressBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class AddressManagerActivity extends AppCompatActivity {
    protected TitleBar titleBar;
    protected RecyclerView address_recycler;
    protected AddressAdapter addressAdapter;
    protected ConstraintLayout add_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);

        initView();

    }

    protected void initView() {
        titleBar = findViewById(R.id.titleBar);

        address_recycler = findViewById(R.id.address_recycler);
        add_address = findViewById(R.id.add_address);

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

        address_recycler.setLayoutManager(new LinearLayoutManager(AddressManagerActivity.this, LinearLayoutManager.VERTICAL, false));

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAddressIntent = new Intent(AddressManagerActivity.this, AddAddressActivity.class);
                startActivity(addAddressIntent);
            }
        });
    }

    public void initRequestAddressList() {
        OkHttpUtils
                .post()
                .url(Util.url + "api/address/lists")
                .addParams("token", Util.getToken(AddressManagerActivity.this))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        AddressBean addressBean = gson.fromJson(response, AddressBean.class);
                        if (addressBean.getCode() == -1) {
                            Util.showToastError(AddressManagerActivity.this, "请先登录");
                        } else {
                            addressAdapter = new AddressAdapter(AddressManagerActivity.this, addressBean.getData().getList(), addressBean.getData().getDefault_id());
                            address_recycler.setAdapter(addressAdapter);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRequestAddressList();
    }
}