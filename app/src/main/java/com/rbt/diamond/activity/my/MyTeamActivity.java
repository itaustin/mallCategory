package com.rbt.diamond.activity.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.my.fragment.FirstFragment;
import com.rbt.diamond.activity.order.fragment.AllOrderFragment;
import com.rbt.diamond.activity.order.fragment.CommentFragment;
import com.rbt.diamond.activity.order.fragment.DeliveryFragment;
import com.rbt.diamond.activity.order.fragment.PaymentFragment;
import com.rbt.diamond.activity.order.fragment.ReceivedFragment;
import com.rbt.diamond.custom_view.CustomRoundAngleImageView;
import com.rbt.diamond.databinding.ActivityMyTeamBinding;
import com.rbt.diamond.public_bean.TeamBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MyTeamActivity extends AppCompatActivity {
    protected ActivityMyTeamBinding binding;
    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MyTeamActivity.this, R.layout.activity_my_team);

        initView();

        initViewPager();

        initRequest();

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
    }

    protected void initViewPager() {
        binding.viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        binding.viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new FirstFragment(1);
                    case 1:
                        return new FirstFragment(2);
                }
                return null;
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });

//        viewPager2.setOffscreenPageLimit(1);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("直接粉丝");
                        break;
                    case 1:
                        tab.setText("间接粉丝");
                        break;
                }
            }
        }).attach();

    }

    protected void initRequest(){
        OkHttpUtils
                .get()
                .url(Util.url + "api/user/team")
                .addParams("token", Util.getToken(MyTeamActivity.this))
                .addParams("wxapp_id", "10001")
                .addParams("level", String.valueOf(1))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        TeamBean bean = gson.fromJson(response, TeamBean.class);
                        if(bean.getCode() == 1){
                            binding.topUsername.setText(intent.getStringExtra("username"));
                            binding.total.setText(String.valueOf(bean.getData().getCount().getTotal()));
                            binding.first.setText(String.valueOf(bean.getData().getCount().getFirst()));
                            binding.second.setText(String.valueOf(bean.getData().getCount().getSecond()));
                        }
                    }
                });
    }
}