package com.rbt.diamond.activity.points.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.points.PointsRecordActivity;
import com.rbt.diamond.databinding.FragmentPointsRecordBinding;
import com.rbt.diamond.public_bean.PointsBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class PointsRecordFragment extends Fragment {
    protected Intent intent;
    protected int type = 30; // 30 每日释放，40 分享加速，50团队释放
    protected FragmentPointsRecordBinding binding;
    protected EasyAdapter<PointsBean.DataBean> adapter;

    public PointsRecordFragment(int type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_points_record, container, false);

        initRecycler();

        initRequestPointsRecord();

        return binding.getRoot();
    }

    protected void initRecycler(){
        binding.pointsRecordRecycler.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
    }

    protected void initRequestPointsRecord(){
        OkHttpUtils
                .get()
                .url(Util.url + "api/points/getList")
                .addParams("token", Util.getToken(requireActivity()))
                .addParams("type", String.valueOf(type))
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
                                    } else if(dataBean.getType() == 20) {
                                        // 支出
                                        title.setText("支出");
                                        description.setText(dataBean.getDescription());
                                        points_number.setText("-" + dataBean.getPoints());
                                    } else if(dataBean.getType() == 30) {
                                        title.setText("每日释放");
                                        description.setText(dataBean.getDescription());
                                        points_number.setText("+" + dataBean.getPoints());
                                    } else if(dataBean.getType() == 40) {
                                        title.setText("分享释放");
                                        description.setText(dataBean.getDescription());
                                        points_number.setText("+" + dataBean.getPoints());
                                    } else if(dataBean.getType() == 50) {
                                        title.setText("团队释放");
                                        description.setText(dataBean.getDescription());
                                        points_number.setText("+" + dataBean.getPoints());
                                    }

                                    viewHolder.setText(R.id.create_time, dataBean.getCreate_time());
                                }
                            };
                            binding.pointsRecordRecycler.setAdapter(adapter);
                        }
                    }
                });
    }
}