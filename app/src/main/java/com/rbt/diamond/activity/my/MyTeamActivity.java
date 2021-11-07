package com.rbt.diamond.activity.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityMyTeamBinding;

public class MyTeamActivity extends AppCompatActivity {
    protected ActivityMyTeamBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MyTeamActivity.this, R.layout.activity_my_team);

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