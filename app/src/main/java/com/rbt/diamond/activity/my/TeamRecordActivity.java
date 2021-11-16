package com.rbt.diamond.activity.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivityTeamRecordBinding;
import com.rbt.diamond.public_bean.TeamRecordBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class TeamRecordActivity extends AppCompatActivity {
    protected ActivityTeamRecordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(TeamRecordActivity.this, R.layout.activity_team_record);

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

        binding.recycler.setLayoutManager(new LinearLayoutManager(TeamRecordActivity.this, LinearLayoutManager.VERTICAL, false));

        initRequestData();
    }

    protected void initRequestData(){
        OkHttpUtils
                .get()
                .url(Util.url + "api/user/teamList")
                .addParams("token", Util.getToken(TeamRecordActivity.this))
                .addParams("wxapp_id", "10001")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        TeamRecordBean bean = gson.fromJson(response, TeamRecordBean.class);
                        EasyAdapter<TeamRecordBean.DataBean> adapter = new EasyAdapter<TeamRecordBean.DataBean>(bean.getData(), R.layout.team_list_item) {
                            @Override
                            protected void bind(@NonNull ViewHolder viewHolder, TeamRecordBean.DataBean dataBean, int i) {
                                viewHolder.setText(R.id.username, dataBean.getUsername());
                                viewHolder.setText(R.id.time, "总业绩：" + dataBean.getTotal_price());
                            }
                        };
                        binding.recycler.setAdapter(adapter);
                    }
                });
    }

}