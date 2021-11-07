package com.rbt.diamond.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.public_bean.CommentBean;
import com.rbt.diamond.public_bean.CommentSubmitBean;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class CommentActivity extends AppCompatActivity {
    protected TitleBar titleBar;

    protected int order_id = 0;

    protected Intent intent;

    protected RecyclerView comment_recycler;

    protected CommentBean commentBean;

    protected EasyAdapter<CommentBean.DataBean.GoodsListBean> adapter;

    protected CommentSubmitBean bean;

    protected List<CommentSubmitBean.FormDataBean> formDataBeans = new ArrayList<>();

    protected ConstraintLayout submit_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        initView();

        initRequestCommentList();
    }

    protected void initView(){
        intent = getIntent();
        titleBar = findViewById(R.id.titleBar);
        comment_recycler = findViewById(R.id.comment_recycler);
        submit_comment = findViewById(R.id.submit_comment);

        order_id = intent.getIntExtra("order_id", 0);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
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

        comment_recycler.setLayoutManager(new LinearLayoutManager(CommentActivity.this, LinearLayoutManager.VERTICAL, false));

        submit_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = "";
                boolean isEmpty = false;
                for (CommentSubmitBean.FormDataBean bean : formDataBeans) {
                    if(TextUtils.isEmpty(bean.getContent())){
                        isEmpty = true;
                        break;
                    }
                    if(bean.getScore() == 0){
                        isEmpty = true;
                    }
                }
                if(!isEmpty) {
                    json = gson.toJson(formDataBeans);
                    OkHttpUtils
                            .post()
                            .url(Util.url + "?s=/api/user.comment/order")
                            .addParams("order_id", String.valueOf(order_id))
                            .addParams("formData", json)
                            .addParams("wxapp_id", "10001")
                            .addParams("token", Util.getToken(CommentActivity.this))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    System.out.println(response);
                                    ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                    if(resultMsgBean.getCode() == -1){
                                        Util.showToastError(CommentActivity.this, "请先登录");
                                    } else if(resultMsgBean.getCode() == 1){
                                        Util.showToastSuccess(CommentActivity.this, resultMsgBean.getMsg());
                                        finish();
                                    } else if(resultMsgBean.getCode() == 0){
                                        Util.showToastError(CommentActivity.this, resultMsgBean.getMsg());
                                    }
                                }
                            });
                } else {
                    Util.showToastError(CommentActivity.this, "请填写完整信息");
                }

            }
        });
    }

    protected void initRequestCommentList(){
        OkHttpUtils
                .get()
                .url(Util.url + "?s=/api/user.comment/order")
                .addParams("order_id", String.valueOf(order_id))
                .addParams("wxapp_id", "10001")
                .addParams("token", Util.getToken(CommentActivity.this))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        commentBean = gson.fromJson(response, CommentBean.class);
                        adapter = new EasyAdapter<CommentBean.DataBean.GoodsListBean>(commentBean.getData().getGoodsList(), R.layout.comment_item_layout) {
                            CommentSubmitBean.FormDataBean dataBean;
                            CommentSubmitBean.FormDataBean editBean;

                            @Override
                            protected void bind(@NonNull ViewHolder viewHolder, CommentBean.DataBean.GoodsListBean goodsListBean, int i) {
//                                dataBean = new CommentSubmitBean.FormDataBean();
                                EditText content = viewHolder.getView(R.id.content);
                                content.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        try {
                                            editBean = formDataBeans.get(i);
                                            editBean.setContent(content.getText().toString());
                                        } catch (Exception e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                });

                                RadioGroup radioGroup = viewHolder.getView(R.id.radioGroup);
                                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        RadioButton rb = viewHolder.getView(checkedId);
                                        editBean = formDataBeans.get(i);
                                        if(rb.getText().toString().equals("好评")) {
                                            editBean.setScore(10);
                                        } else if(rb.getText().toString().equals("中评")) {
                                            editBean.setScore(20);
                                        } else if(rb.getText().toString().equals("差评")) {
                                            editBean.setScore(30);
                                        }
                                    }
                                });

                                try {
                                    editBean.setImage_list(new ArrayList<>());
                                    editBean.setGoods_id(goodsListBean.getGoods_id());
                                    editBean.setUploaded(new ArrayList<>());
                                    editBean.setOrder_goods_id(goodsListBean.getOrder_goods_id());
                                    formDataBeans.set(i, editBean);
                                } catch (Exception e){
                                    CommentSubmitBean.FormDataBean dataBean = new CommentSubmitBean.FormDataBean();
                                    dataBean.setImage_list(new ArrayList<>());
                                    dataBean.setGoods_id(goodsListBean.getGoods_id());
                                    dataBean.setUploaded(new ArrayList<>());
                                    dataBean.setContent(content.getText().toString());
                                    dataBean.setOrder_goods_id(goodsListBean.getOrder_goods_id());
                                    formDataBeans.add(i, dataBean);
                                }
                            }
                        };

                        comment_recycler.setAdapter(adapter);
                    }
                });
    }
}