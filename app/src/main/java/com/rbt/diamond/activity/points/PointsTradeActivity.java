package com.rbt.diamond.activity.points;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityPointsTradeBinding;

public class PointsTradeActivity extends AppCompatActivity {
    protected ActivityPointsTradeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_points_trade);

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
    }
}