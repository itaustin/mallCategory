package com.rbt.diamond.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rbt.diamond.R;
import com.rbt.diamond.util.Util;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


public class HomeFragment extends Fragment {
    protected View view;
    protected Banner banner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();

        return view;
    }

    protected void initView(){
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
}