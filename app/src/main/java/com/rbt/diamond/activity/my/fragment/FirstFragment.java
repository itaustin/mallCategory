package com.rbt.diamond.activity.my.fragment;

import android.annotation.SuppressLint;
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
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.custom_view.CustomRoundAngleImageView;
import com.rbt.diamond.databinding.FragmentFirstBinding;
import com.rbt.diamond.public_bean.TeamBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class FirstFragment extends Fragment {
    protected int level;
    protected FragmentFirstBinding binding;
    protected EasyAdapter<TeamBean.DataBean.RefereeBean> adapter;

    public FirstFragment(int level) {
        this.level = level;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false);

        initView();

        initRequestTeam();

        return binding.getRoot();
    }

    protected void initView(){
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
    }

    protected void initRequestTeam(){
        // 请求粉丝总数
//        OkHttpUtils
//                .post()
//                .url(Util.url + "api/user/team_count")
//                .addParams()
        OkHttpUtils
                .get()
                .url(Util.url + "api/user/team")
                .addParams("token", Util.getToken(requireActivity()))
                .addParams("wxapp_id", "10001")
                .addParams("level", String.valueOf(level))
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
                            adapter = new EasyAdapter<TeamBean.DataBean.RefereeBean>(bean.getData().getReferee(), R.layout.team_item) {
                                @SuppressLint("SetTextI18n")
                                @Override
                                protected void bind(@NonNull ViewHolder viewHolder, TeamBean.DataBean.RefereeBean dataBean, int i) {
                                    CustomRoundAngleImageView imageView = viewHolder.getView(R.id.avatar);
                                    TextView username = viewHolder.getView(R.id.username);
                                    TextView time = viewHolder.getView(R.id.time);

                                    username.setText(dataBean.getUser().getUsername());
                                    time.setText("加入时间：" + dataBean.getCreate_time());
                                }
                            };
                            binding.recycler.setAdapter(adapter);
                        }
                    }
                });
    }
}