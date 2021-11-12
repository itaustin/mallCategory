package com.rbt.diamond.activity.points;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.my.fragment.FirstFragment;
import com.rbt.diamond.activity.points.fragment.PointsRecordFragment;
import com.rbt.diamond.databinding.ActivityCanUserPointsBinding;

public class CanUserPointsActivity extends AppCompatActivity {
    protected ActivityCanUserPointsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(CanUserPointsActivity.this, R.layout.activity_can_user_points);

        initView();

        initViewPager();
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
    }

    protected void initViewPager() {
        binding.viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        binding.viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new PointsRecordFragment(30);
                    case 1:
                        return new PointsRecordFragment(40);
                    case 2:
                        return new PointsRecordFragment(50);
                }
                return null;
            }

            @Override
            public int getItemCount() {
                return 3;
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
                        tab.setText("每日释放");
                        break;
                    case 1:
                        tab.setText("分享加速释放");
                        break;
                    case 2:
                        tab.setText("团队加速释放");
                        break;
                }
            }
        }).attach();

    }
}