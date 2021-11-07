package com.rbt.diamond.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.order.fragment.AllOrderFragment;
import com.rbt.diamond.activity.order.fragment.CommentFragment;
import com.rbt.diamond.activity.order.fragment.DeliveryFragment;
import com.rbt.diamond.activity.order.fragment.PaymentFragment;
import com.rbt.diamond.activity.order.fragment.ReceivedFragment;


public class OrderActivity extends AppCompatActivity {
    protected TabLayout tab_layout;
    protected ViewPager2 viewPager2;
    protected TitleBar titleBar;

    protected Intent intent;

    protected int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        intent = getIntent();

        position = intent.getIntExtra("position", 0);

        initView();

        initViewPager();
    }

    protected void initView(){
        tab_layout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewPager2);
        titleBar = findViewById(R.id.titleBar);

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
    }

    protected void initViewPager() {
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new AllOrderFragment();
                    case 1:
                        return new PaymentFragment();
                    case 2:
                        return new DeliveryFragment();
                    case 3:
                        return new ReceivedFragment();
                    case 4:
                        return new CommentFragment();
                }
                return null;
            }

            @Override
            public int getItemCount() {
                return 5;
            }
        });

//        viewPager2.setOffscreenPageLimit(1);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        new TabLayoutMediator(tab_layout, viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("全部订单");
                        break;
                    case 1:
                        tab.setText("待付款");
                        break;
                    case 2:
                        tab.setText("待发货");
                        break;
                    case 3:
                        tab.setText("待收货");
                        break;
                    case 4:
                        tab.setText("待评价");
                        break;
                }
            }
        }).attach();

        tab_layout.getTabAt(position).select();
    }
}