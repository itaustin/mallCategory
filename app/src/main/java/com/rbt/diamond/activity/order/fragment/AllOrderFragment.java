package com.rbt.diamond.activity.order.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.order.CommentActivity;
import com.rbt.diamond.activity.order.ExPressInfoActivity;
import com.rbt.diamond.public_bean.ExpressListBean;
import com.rbt.diamond.public_bean.OrderBean;
import com.rbt.diamond.public_bean.PayBean;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.qinsong.lib.pay.PAY_TYPE;
import org.qinsong.lib.pay.PayAPI;
import org.qinsong.lib.pay.PayCallback;
import org.qinsong.lib.pay.ali.AliPayInfo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class AllOrderFragment extends Fragment implements View.OnClickListener {
    protected View view;

    protected static SmartRefreshLayout smartRefreshLayout;
    protected RecyclerView my_buy_order;

    protected ConstraintLayout empty_data;

    protected int page = 1;
    protected int total_page;

    protected OrderBean bean;

    protected List<OrderBean.OrderListBean.DataBean> list = new ArrayList<>();

    protected EasyAdapter<OrderBean.OrderListBean.DataBean> adapter;

    protected EasyAdapter<OrderBean.OrderListBean.DataBean.GoodsBean> goodsAdapter;

    protected ExpressListBean expressListBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_order, container, false);

        initView();

        initSmartRefreshLayout();

        return view;
    }

    protected void initView() {
        my_buy_order = view.findViewById(R.id.my_buy_order);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        empty_data = view.findViewById(R.id.empty_data);

    }

    public static void refresh() {
        smartRefreshLayout.autoRefresh();
    }

    public void initSmartRefreshLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);

        my_buy_order.setLayoutManager(linearLayoutManager);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initOrderList(page);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (page < total_page) {
                    page++;
                    initOrderList(page);
                    refreshLayout.finishLoadMore();
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });

        smartRefreshLayout.autoRefresh();
    }


    @Override
    public void onClick(View v) {

    }

    protected void initOrderList(int page) {
//        BasePopupView basePopupView = new XPopup.Builder(requireActivity())
//                .dismissOnTouchOutside(false)
//                .dismissOnBackPressed(false)
//                .asLoading("?????????...")
//                .show();
        String url = Util.url + "?s=/api/user.order/listsForAndroid";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("page", String.valueOf(page))
                .addParams("dataType", "all")
                .addParams("wxapp_id", "10001")
                .addParams("token", Util.getToken(requireActivity()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
//                        basePopupView.smartDismiss();
                        Gson gson = new Gson();
                        OrderBean orderBean = gson.fromJson(response, OrderBean.class);
                        bean = orderBean;
                        if (orderBean.getCode() == -1) {
                            Util.showToastInfo(requireActivity(), "????????????");
                            smartRefreshLayout.finishRefresh();
                        } else if (orderBean.getOrderList().getTotal() != 0) {
                            my_buy_order.setAdapter(null);
                            smartRefreshLayout.finishRefresh();
                            my_buy_order.setVisibility(View.VISIBLE);
                            empty_data.setVisibility(View.GONE);
                            list = orderBean.getOrderList().getData();

                            adapter = new EasyAdapter<OrderBean.OrderListBean.DataBean>(list, R.layout.order_item_layout) {
                                @Override
                                protected void bind(@NonNull ViewHolder viewHolder, OrderBean.OrderListBean.DataBean dataBean, int i) {
                                    viewHolder.setText(R.id.order_no, "???????????????" + dataBean.getOrder_no());
                                    viewHolder.setText(R.id.order_status, dataBean.getOrder_status().getText());

                                    viewHolder.getView(R.id.cancel).setVisibility(View.GONE);

                                    viewHolder.setText(R.id.goods_total_num, "???" + dataBean.getGoods().size() + "?????????");
                                    viewHolder.setText(R.id.goods_total_price, "" + dataBean.getTotal_price());

                                    RecyclerView goods_list_recycler = viewHolder.getView(R.id.goods_list_recycler);

                                    goods_list_recycler.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));

                                    goodsAdapter = new EasyAdapter<OrderBean.OrderListBean.DataBean.GoodsBean>(dataBean.getGoods(), R.layout.order_goods_item_layout) {
                                        @Override
                                        protected void bind(@NonNull ViewHolder viewHolder, OrderBean.OrderListBean.DataBean.GoodsBean goodsBean, int i) {

                                            viewHolder.setText(R.id.goods_name, goodsBean.getGoods_name());
                                            viewHolder.setText(R.id.goods_price, "" + goodsBean.getGoods_price());
                                            viewHolder.setText(R.id.sku_attr, goodsBean.getGoods_attr());
                                            viewHolder.setText(R.id.goods_num, "x" + goodsBean.getTotal_num());

                                            Glide.with(requireActivity()).load(goodsBean.getImage().getFile_path()).into((ImageView) viewHolder.getView(R.id.goods_image));
                                        }
                                    };

                                    goods_list_recycler.setAdapter(goodsAdapter);

                                    if(dataBean.getOrder_status().getValue() == 21){
                                        viewHolder.getView(R.id.pay_now).setVisibility(View.GONE);
                                        viewHolder.getView(R.id.comment).setVisibility(View.GONE);
                                        viewHolder.getView(R.id.received).setVisibility(View.GONE);
                                        viewHolder.getView(R.id.view_express).setVisibility(View.GONE);
                                        viewHolder.getView(R.id.cancel).setVisibility(View.GONE);

                                        viewHolder.setText(R.id.order_status, dataBean.getOrder_status().getText());
                                    } else if(dataBean.getOrder_status().getValue() == 20){
                                        viewHolder.getView(R.id.pay_now).setVisibility(View.GONE);
                                        viewHolder.getView(R.id.comment).setVisibility(View.GONE);
                                        viewHolder.getView(R.id.received).setVisibility(View.GONE);
                                        viewHolder.getView(R.id.view_express).setVisibility(View.GONE);
                                        viewHolder.getView(R.id.cancel).setVisibility(View.GONE);
                                        viewHolder.setText(R.id.order_status, dataBean.getOrder_status().getText());
                                    } else {
                                        if(dataBean.getPay_status().getValue() == 20){
                                            // ?????????
                                            if(dataBean.getDelivery_status().getValue() == 10) {
                                                // ?????????
                                                viewHolder.getView(R.id.pay_now).setVisibility(View.GONE);
                                                viewHolder.getView(R.id.comment).setVisibility(View.GONE);
                                                viewHolder.getView(R.id.received).setVisibility(View.GONE);
                                                viewHolder.getView(R.id.view_express).setVisibility(View.GONE);
                                                viewHolder.getView(R.id.cancel).setVisibility(View.GONE);

                                                viewHolder.setText(R.id.order_status, "???????????????????????????...");

                                                if(dataBean.getGoods().get(0).getCategory_id().equals("10001")) {
                                                    viewHolder.getView(R.id.cancel).setVisibility(View.GONE);
                                                } else {
                                                    viewHolder.getView(R.id.cancel).setVisibility(View.VISIBLE);
                                                }
                                            } else {
                                                // ?????????
                                                if(dataBean.getReceipt_status().getValue() == 10){
                                                    viewHolder.getView(R.id.cancel).setVisibility(View.GONE);
                                                    viewHolder.getView(R.id.comment).setVisibility(View.GONE);
                                                    viewHolder.getView(R.id.received).setVisibility(View.VISIBLE);
                                                    viewHolder.getView(R.id.pay_now).setVisibility(View.GONE);
                                                    viewHolder.getView(R.id.view_express).setVisibility(View.VISIBLE);
                                                    viewHolder.setText(R.id.order_status, "???????????????");
                                                } else {
                                                    if(dataBean.getIs_comment() == 0) {
                                                        // ?????????
                                                        viewHolder.getView(R.id.pay_now).setVisibility(View.GONE);
                                                        viewHolder.getView(R.id.comment).setVisibility(View.VISIBLE);
                                                        viewHolder.getView(R.id.received).setVisibility(View.GONE);
                                                        viewHolder.getView(R.id.view_express).setVisibility(View.GONE);
                                                        viewHolder.getView(R.id.cancel).setVisibility(View.GONE);
                                                        viewHolder.setText(R.id.order_status, "???????????????????????????");
                                                    } else {
                                                        // ?????????
                                                        viewHolder.setText(R.id.order_status, "???????????????????????????");
                                                        viewHolder.getView(R.id.pay_now).setVisibility(View.GONE);
                                                        viewHolder.getView(R.id.comment).setVisibility(View.GONE);
                                                        viewHolder.getView(R.id.received).setVisibility(View.GONE);
                                                        viewHolder.getView(R.id.view_express).setVisibility(View.GONE);
                                                        viewHolder.getView(R.id.cancel).setVisibility(View.GONE);
                                                    }
                                                }
                                            }
                                        } else {
                                            // ?????????
                                            viewHolder.getView(R.id.pay_now).setVisibility(View.VISIBLE);
                                            viewHolder.getView(R.id.comment).setVisibility(View.GONE);
                                            viewHolder.getView(R.id.received).setVisibility(View.GONE);
                                            viewHolder.getView(R.id.view_express).setVisibility(View.GONE);
                                            viewHolder.getView(R.id.cancel).setVisibility(View.VISIBLE);
                                        }
                                    }

                                    if(dataBean.getIs_audit() == 0 && dataBean.getAudit_image_id() > 0){
                                        // ?????????
                                        Button button = viewHolder.getView(R.id.pay_now);
                                        button.setText("??????????????????");
                                        button.setEnabled(false);
                                    }

                                    viewHolder.getView(R.id.pay_now).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            OkHttpUtils
                                                    .get()
                                                    .url(Util.url + "?s=/api/user.order/payForAndroid")
                                                    .addParams("order_id", String.valueOf(dataBean.getOrder_id()))
                                                    .addParams("wxapp_id", "10001")
                                                    .addParams("token", Util.getToken(requireActivity()))
                                                    .build()
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onError(Call call, Exception e, int id) {

                                                        }

                                                        @Override
                                                        public void onResponse(String response, int id) {
                                                            System.out.println(response);
                                                            Gson gson = new Gson();
                                                            PayBean payBean = gson.fromJson(response, PayBean.class);
                                                            if(payBean.getCode() == 2){
                                                                Util.showToastSuccess(requireActivity(), "????????????");
                                                                initOrderList(1);
                                                            } else if(payBean.getCode() == 0){
                                                                Util.showToastError(requireActivity(), payBean.getMsg());
                                                            } else {
                                                                AliPayInfo aliPayInfo = new AliPayInfo();
                                                                aliPayInfo.payParam = payBean.getData();
                                                                PayAPI.get(requireActivity(), PAY_TYPE.ALIPAY).pay(aliPayInfo, new PayCallback() {
                                                                    @Override
                                                                    public void onComplete(PAY_TYPE payType, String result) {
                                                                        //????????????????????????
                                                                        Util.showToastSuccess(requireActivity(), "????????????");
                                                                        initOrderList(1);
                                                                    }

                                                                    @Override
                                                                    public void onFail(PAY_TYPE payType, String msg) {
                                                                        System.out.println("????????????");
                                                                    }

                                                                    @Override
                                                                    public void onCancel(PAY_TYPE payType) {
                                                                        System.out.println("????????????");
                                                                    }
                                                                });
                                                            }
                                                        }
                                                    });
                                        }
                                    });

                                    viewHolder.getView(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new XPopup.Builder(requireActivity())
                                                    .asConfirm("????????????", "?????????????????????????????????", new OnConfirmListener() {
                                                        @Override
                                                        public void onConfirm() {
                                                            System.out.println();
                                                            OkHttpUtils
                                                                    .post()
                                                                    .url(Util.url + "?s=/api/user.order/cancel")
                                                                    .addParams("order_id", String.valueOf(dataBean.getOrder_id()))
                                                                    .addParams("wxapp_id", "10001")
                                                                    .addParams("token", Util.getToken(requireActivity()))
                                                                    .build()
                                                                    .execute(new StringCallback() {
                                                                        @Override
                                                                        public void onError(Call call, Exception e, int id) {

                                                                        }

                                                                        @Override
                                                                        public void onResponse(String response, int id) {
                                                                            ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                                                            if(resultMsgBean.getCode() == 1){
                                                                                initOrderList(1);
                                                                                Util.showToastSuccess(requireActivity(), resultMsgBean.getMsg());
                                                                            } else if(resultMsgBean.getCode() == 0){
                                                                                Util.showToastError(requireActivity(), resultMsgBean.getMsg());
                                                                            }
                                                                        }
                                                                    });
                                                        }
                                                    }).show();
                                        }
                                    });

//                                    viewHolder.getView(R.id.pay_now).setVisibility(View.VISIBLE);
//                                    viewHolder.getView(R.id.comment).setVisibility(View.GONE);
//                                    viewHolder.getView(R.id.received).setVisibility(View.GONE);
//                                    viewHolder.getView(R.id.view_express).setVisibility(View.GONE);
//                                    viewHolder.getView(R.id.cancel).setVisibility(View.VISIBLE);
                                    viewHolder.getView(R.id.received).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new XPopup.Builder(requireActivity())
                                                    .asConfirm("????????????", "????????????????????????", new OnConfirmListener() {
                                                        @Override
                                                        public void onConfirm() {
                                                            OkHttpUtils
                                                                    .post()
                                                                    .url(Util.url + "?s=/api/user.order/receipt")
                                                                    .addParams("order_id", String.valueOf(dataBean.getOrder_id()))
                                                                    .addParams("wxapp_id", "10001")
                                                                    .addParams("token", Util.getToken(requireActivity()))
                                                                    .build()
                                                                    .execute(new StringCallback() {
                                                                        @Override
                                                                        public void onError(Call call, Exception e, int id) {

                                                                        }

                                                                        @Override
                                                                        public void onResponse(String response, int id) {
                                                                            ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                                                            if(resultMsgBean.getCode() == 1){
                                                                                initOrderList(1);
                                                                                Util.showToastSuccess(requireActivity(), resultMsgBean.getMsg());
                                                                            } else if(resultMsgBean.getCode() == 0){
                                                                                Util.showToastError(requireActivity(), resultMsgBean.getMsg());
                                                                            }
                                                                        }
                                                                    });
                                                        }
                                                    }).show();
                                        }
                                    });

                                    viewHolder.getView(R.id.view_express).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // ??????????????????
                                             Intent expressIntent = new Intent(requireActivity(), ExPressInfoActivity.class);
                                             expressIntent.putExtra("order_id", dataBean.getOrder_id());
                                             startActivity(expressIntent);
                                        }
                                    });

                                    viewHolder.getView(R.id.comment).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // ?????????
                                            Intent commentIntent = new Intent(requireActivity(), CommentActivity.class);
                                            commentIntent.putExtra("order_id", dataBean.getOrder_id());
                                            startActivity(commentIntent);
                                        }
                                    });
                                }
                            };
                            my_buy_order.setAdapter(adapter);
                            total_page = orderBean.getOrderList().getLast_page();
                        } else {
                            if (page == 1) {
                                my_buy_order.setAdapter(null);
                            }
                            smartRefreshLayout.finishLoadMoreWithNoMoreData();
                            empty_data.setVisibility(View.VISIBLE);
                            my_buy_order.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        initOrderList(1);
    }
}