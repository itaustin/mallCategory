package com.rbt.diamond.activity.points;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityPointsRecordBinding;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class PointsRecordActivity extends AppCompatActivity {
    protected ActivityPointsRecordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_record);
        binding = DataBindingUtil.setContentView(PointsRecordActivity.this, R.layout.activity_points_record);

        initRecycler();

        initRequestPointsRecord();
    }

    protected void initRecycler(){
        binding.pointsRecordRecycler.setLayoutManager(new LinearLayoutManager(PointsRecordActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    protected void initRequestPointsRecord(){
        OkHttpUtils
                .post()
                .url(Util.url + "api/points/getList")
                .addParams("token", Util.getToken(PointsRecordActivity.this))
                .addParams("wxapp_id", "10001")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                    }
                });
    }
}