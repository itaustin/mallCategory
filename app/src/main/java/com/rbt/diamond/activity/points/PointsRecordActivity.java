package com.rbt.diamond.activity.points;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityPointsRecordBinding;
import com.rbt.diamond.public_bean.PointsBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class PointsRecordActivity extends AppCompatActivity {
    protected ActivityPointsRecordBinding binding;
    protected EasyAdapter<PointsBean.DataBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(PointsRecordActivity.this, R.layout.activity_points_record);

        initRecycler();

        initRequestPointsRecord();
    }

    protected void initRecycler(){
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
        binding.pointsRecordRecycler.setLayoutManager(new LinearLayoutManager(PointsRecordActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    protected void initRequestPointsRecord(){
        OkHttpUtils
                .get()
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
                        Gson gson = new Gson();
                        PointsBean pointsBean = gson.fromJson(response, PointsBean.class);
                        if(pointsBean.getCode() == 1){
                            adapter = new EasyAdapter<PointsBean.DataBean>(pointsBean.getData(), R.layout.points_record_item) {
                                @SuppressLint("SetTextI18n")
                                @Override
                                protected void bind(@NonNull ViewHolder viewHolder, PointsBean.DataBean dataBean, int i) {
                                    TextView title = viewHolder.getView(R.id.title);
                                    TextView description = viewHolder.getView(R.id.description);
                                    TextView points_number = viewHolder.getView(R.id.points_number);
                                    if(dataBean.getType() == 10){
                                        // 收入
                                        title.setText("收入");
                                        description.setText(dataBean.getDescription());
                                        points_number.setText("+" + dataBean.getPoints());
                                    } else {
                                        // 支出
                                        title.setText("支出");
                                        description.setText(dataBean.getDescription());
                                        points_number.setText("-" + dataBean.getPoints());
                                    }
                                }
                            };
                            binding.pointsRecordRecycler.setAdapter(adapter);
                        }
                    }
                });
    }
}