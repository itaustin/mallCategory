package com.rbt.diamond.activity.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.util.SmartGlideImageLoader;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityInviteFriendsBinding;
import com.rbt.diamond.util.Util;

public class InviteFriendsActivity extends AppCompatActivity {
    protected ActivityInviteFriendsBinding binding;
    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(InviteFriendsActivity.this, R.layout.activity_invite_friends);


        intent = getIntent();

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
        String url = Util.url + "api/qrcode/view_android?invite_code=" + intent.getStringExtra("invite_code");
        Glide.with(InviteFriendsActivity.this).load(url).into(binding.imageCode);
        binding.imageCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(InviteFriendsActivity.this)
                        .asImageViewer(binding.imageCode, url, new SmartGlideImageLoader())
                        .show();
            }
        });

        binding.nickName.setText(intent.getStringExtra("nickName"));
    }
}