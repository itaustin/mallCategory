package com.rbt.diamond.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rbt.diamond.R;
import com.rbt.diamond.activity.home.MemberMallActivity;
import com.rbt.diamond.activity.scan.QRCodeActivity;
import com.rbt.diamond.databinding.FragmentHomeBinding;
import com.rbt.diamond.util.UpdateAppHttpUtil;
import com.rbt.diamond.util.Util;
import com.vector.update_app.UpdateAppManager;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


public class HomeFragment extends Fragment {
    protected View view;
    protected Banner banner;
    protected FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        view = binding.getRoot();

        initView();

        return view;
    }

    protected void initView(){
        binding.scanQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scanQrcodeIntent = new Intent(requireActivity(), QRCodeActivity.class);
                startActivity(scanQrcodeIntent);
            }
        });

        binding.memberMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), MemberMallActivity.class);
                startActivity(intent);
            }
        });

        binding.saleMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        banner = view.findViewById(R.id.banner);
    }

    protected void initRequestHomeData(){
//        OkHttpUtils.
//                post()
//                .url(Util.url + "/api/home/index")
//                .addParams("token", Util.getToken(requireActivity()))
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        System.out.println(response);
//                    }
//                });
    }

    @Override
    public void onResume() {
        super.onResume();

        Map<String, String> params = new HashMap<String, String>();

        params.put("appVersion", Util.getAppVersionCode(requireActivity()));

        new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(requireActivity())
                .setPost(false)
                .setParams(params)
                .setTopPic(R.mipmap.banner)
                //更新地址
                .setUpdateUrl(Util.url + "/api/apk/checkUpdate")
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                .build()
                .update();
    }
}