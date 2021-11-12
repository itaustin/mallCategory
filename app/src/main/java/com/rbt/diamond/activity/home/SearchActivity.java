package com.rbt.diamond.activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.ActivitySearchBinding;
import com.rbt.diamond.public_bean.RecentSearchBean;
import com.rbt.diamond.util.Util;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    protected ActivitySearchBinding binding;
    protected List<RecentSearchBean.DataBean> list = new ArrayList<>();
    protected EasyAdapter<RecentSearchBean.DataBean> adapter;
    protected RecentSearchBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SearchActivity.this, R.layout.activity_search);

        initView();

        initViewRecycler();

    }

    protected void initView(){
        SharedPreferences sharedPreferences = getSharedPreferences("search", MODE_PRIVATE);
        String json = sharedPreferences.getString("search_content", "");
        Gson gson = new Gson();
        String jsonString;
        if(TextUtils.isEmpty(json)){
            jsonString = "{code:0,msg:'success',data:[]}";
        } else {
            jsonString = "{code:0,msg:'success',data:" + json + "}";
        }
        bean = gson.fromJson(jsonString, RecentSearchBean.class);
        list = bean.getData();
        binding.recentSearch.setLayoutManager(new GridLayoutManager(SearchActivity.this, 4));
        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences1 = getSharedPreferences("search", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.clear();
                editor.apply();
                list.clear();
                initViewRecycler();
            }
        });
        binding.searchContent.setFocusable(true);
        binding.searchContent.setFocusableInTouchMode(true);
        binding.searchContent.requestFocus();
        binding.searchContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    if(!TextUtils.isEmpty(binding.searchContent.getText().toString())){
                        RecentSearchBean.DataBean bean = new RecentSearchBean.DataBean();
                        bean.setName(binding.searchContent.getText().toString());
                        list.add(bean);
                        // 存放搜索结果到Shared
                        Gson gson = new Gson();
                        SharedPreferences sharedPreferences = getSharedPreferences("search", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("search_content", gson.toJson(list));
                        editor.apply();
                        Intent intent = new Intent(SearchActivity.this, MemberMallActivity.class);
                        intent.putExtra("search_content", binding.searchContent.getText().toString());
                        intent.putExtra("category_id", String.valueOf(0));
                        startActivity(intent);
                    } else {
                        Util.showToastError(SearchActivity.this, "请先输入搜索关键词");
                    }
                } else if(i == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    return false;
                } else if(i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    finish();
                }
                if (!keyEvent.isShiftPressed()) {
                    return true;
                }
                return false;
            }
        });
    }

    protected void initViewRecycler(){
        adapter = new EasyAdapter<RecentSearchBean.DataBean>(bean.getData(), R.layout.search_item) {
            @Override
            protected void bind(@NonNull ViewHolder viewHolder, RecentSearchBean.DataBean dataBean, int i) {
                TextView textView = viewHolder.getView(R.id.text);
                textView.setText(dataBean.getName());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SearchActivity.this, MemberMallActivity.class);
                        intent.putExtra("search_content", dataBean.getName());
                        intent.putExtra("category_id", String.valueOf(0));
                        startActivity(intent);
                    }
                });
            }
        };
        binding.recentSearch.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewRecycler();
    }

}