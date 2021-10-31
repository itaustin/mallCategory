package com.rbt.diamond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rbt.diamond.fragment.MyFragment;
import com.rbt.diamond.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().setStatusBarColor(0xff);

//        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//        startActivity(intent);

        mall();

    }

    protected void mall() {
        viewPager2 = findViewById(R.id.viewPage2);
        // 控制左右滑动切换
        // viewPager2.setUserInputEnabled(false);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.black),
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.black)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        bottomNavigationView.setItemTextColor(csl);
        bottomNavigationView.setItemIconTintList(csl);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new HomeFragment();
                    case 2:
                        return new HomeFragment();
                    case 3:
                        return new MyFragment();
                }
                return null;
            }

            @Override
            public int getItemCount() {
                return 4;
            }
        });

        bottomNavigationView.setItemIconTintList(null);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().getItem(position).setIcon(R.mipmap.mall_index_checked);
                        bottomNavigationView.getMenu().getItem(1).setIcon(R.mipmap.category);
                        bottomNavigationView.getMenu().getItem(2).setIcon(R.mipmap.order);
                        bottomNavigationView.getMenu().getItem(3).setIcon(R.mipmap.my);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().getItem(position).setIcon(R.mipmap.category_checked);
                        bottomNavigationView.getMenu().getItem(0).setIcon(R.mipmap.mall_index);
                        bottomNavigationView.getMenu().getItem(2).setIcon(R.mipmap.order);
                        bottomNavigationView.getMenu().getItem(3).setIcon(R.mipmap.my);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().getItem(position).setIcon(R.mipmap.order_checked);
                        bottomNavigationView.getMenu().getItem(0).setIcon(R.mipmap.mall_index);
                        bottomNavigationView.getMenu().getItem(1).setIcon(R.mipmap.category);
                        bottomNavigationView.getMenu().getItem(3).setIcon(R.mipmap.my);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().getItem(position).setIcon(R.mipmap.my_checked);
                        bottomNavigationView.getMenu().getItem(0).setIcon(R.mipmap.mall_index);
                        bottomNavigationView.getMenu().getItem(1).setIcon(R.mipmap.category);
                        bottomNavigationView.getMenu().getItem(2).setIcon(R.mipmap.order);
                        break;
                }
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        viewPager2.setCurrentItem(0, false);
                        menuItem.setIcon(R.mipmap.mall_index_checked);
                        bottomNavigationView.getMenu().getItem(1).setIcon(R.mipmap.category);
                        bottomNavigationView.getMenu().getItem(2).setIcon(R.mipmap.order);
                        bottomNavigationView.getMenu().getItem(3).setIcon(R.mipmap.my);
                        return true;
                    case R.id.category:
                        viewPager2.setCurrentItem(1, false);
                        menuItem.setIcon(R.mipmap.category_checked);
                        bottomNavigationView.getMenu().getItem(0).setIcon(R.mipmap.mall_index);
                        bottomNavigationView.getMenu().getItem(2).setIcon(R.mipmap.order);
                        bottomNavigationView.getMenu().getItem(3).setIcon(R.mipmap.my);
                        break;
                    case R.id.order:
                        viewPager2.setCurrentItem(2, false);
                        menuItem.setIcon(R.mipmap.order_checked);
                        bottomNavigationView.getMenu().getItem(0).setIcon(R.mipmap.mall_index);
                        bottomNavigationView.getMenu().getItem(1).setIcon(R.mipmap.category);
                        bottomNavigationView.getMenu().getItem(3).setIcon(R.mipmap.my);
                        return true;
                    case R.id.my:
                        viewPager2.setCurrentItem(3, false);
                        menuItem.setIcon(R.mipmap.my_checked);
                        bottomNavigationView.getMenu().getItem(0).setIcon(R.mipmap.mall_index);
                        bottomNavigationView.getMenu().getItem(1).setIcon(R.mipmap.category);
                        bottomNavigationView.getMenu().getItem(2).setIcon(R.mipmap.order);
                        return true;

                }
                return false;
            }
        });
    }
}