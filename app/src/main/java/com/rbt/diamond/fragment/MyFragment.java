package com.rbt.diamond.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.address.AddressManagerActivity;
import com.rbt.diamond.activity.center.CertificateActivity;
import com.rbt.diamond.activity.center.InviteFriendsActivity;
import com.rbt.diamond.activity.my.MyTeamActivity;
import com.rbt.diamond.activity.my.SettingActivity;
import com.rbt.diamond.activity.order.OrderActivity;
import com.rbt.diamond.activity.passport.LoginActivity;
import com.rbt.diamond.activity.points.MyPointsActivity;
import com.rbt.diamond.databinding.FragmentMyBinding;
import com.rbt.diamond.public_bean.MyViewItemBean;
import com.rbt.diamond.public_bean.UserInfoBean;
import com.rbt.diamond.util.UpdateAppHttpUtil;
import com.rbt.diamond.util.Util;
import com.vector.update_app.UpdateAppManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class MyFragment extends Fragment {
    protected List<MyViewItemBean.DataBean> list = new ArrayList<>();
    protected FragmentMyBinding binding;
    protected UserInfoBean userInfoBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_my, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);

        // test code
//        viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);
//        binding.setData(viewModel);
//        binding.setLifecycleOwner(this);

        // 检测是否登陆

        initLoadUserInfo();

        return binding.getRoot();
    }

    protected void initLoadUserInfo() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        if (TextUtils.isEmpty(token)) {
            binding.nickName.setText("点击登录");
            binding.nickName.setTextColor(Color.parseColor("#ff0000"));
            binding.nickName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent loginIntent = new Intent(requireActivity(), LoginActivity.class);
                    startActivity(loginIntent);
                }
            });
        } else {
            String url = Util.url + "?s=/api/user/userInfo";
            OkHttpUtils
                    .get()
                    .url(url)
                    .addParams("token", token)
                    .addParams("wxapp_id", "10001")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
//                            Util.showToastError(requireActivity(), e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.println(response);
                            Gson gson = new Gson();
                            userInfoBean = gson.fromJson(response, UserInfoBean.class);
//                        Glide.with(requireActivity()).load(userInfoBean.getData().getAvatarUrl()).into(avatar);
                            if(userInfoBean.getCode() == -1){
                                binding.nickName.setText("点击登录");
                                Util.showToastError(requireActivity(), "请先登录");
                                binding.nickName.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent loginIntent = new Intent(requireActivity(), LoginActivity.class);
                                        startActivity(loginIntent);
                                    }
                                });
                            } else if(userInfoBean.getCode() == 1){
                                initLayoutManager();
                                binding.nickName.setText(userInfoBean.getData().getUsername());
//                                margin_money.setText(userInfoBean.getData().getMargin_money());
                                // 暂时显示积分
                            } else {
                                Util.showToastError(requireActivity(), userInfoBean.getMsg());
                            }
                        }
                    });
        }
    }

    protected void initLayoutManager(){

        list.clear();

//        MyViewItemBean.DataBean c2c_market = new MyViewItemBean.DataBean();
//        c2c_market.setEng_name("c2c_market");
//        c2c_market.setName("我的交易市场");
//        list.add(c2c_market);

        MyViewItemBean.DataBean address = new MyViewItemBean.DataBean();
        address.setEng_name("address");
        address.setName("地址管理");
        list.add(address);

//        MyViewItemBean.DataBean market_cart = new MyViewItemBean.DataBean();
//        market_cart.setEng_name("market_cart");
//        market_cart.setName("购物车");
//        list.add(market_cart);

        MyViewItemBean.DataBean contact_customer_service = new MyViewItemBean.DataBean();
        contact_customer_service.setEng_name("contact_customer_service");
        contact_customer_service.setName("联系客服");
        list.add(contact_customer_service);

        MyViewItemBean.DataBean my_bank_card = new MyViewItemBean.DataBean();
        my_bank_card.setEng_name("my_bank_card");
        my_bank_card.setName("我的银行卡");
        list.add(my_bank_card);

        MyViewItemBean.DataBean certificate = new MyViewItemBean.DataBean();
        certificate.setEng_name("certificate");
        certificate.setName("实名认证");
        list.add(certificate);

        MyViewItemBean.DataBean invite = new MyViewItemBean.DataBean();
        invite.setEng_name("invite");
        invite.setName("邀请好友");
        list.add(invite);

        MyViewItemBean.DataBean logout = new MyViewItemBean.DataBean();
        logout.setEng_name("logout");
        logout.setName("退出登录");
        list.add(logout);

        binding.points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pointsIntent = new Intent(requireActivity(), MyPointsActivity.class);
                startActivity(pointsIntent);
            }
        });

        binding.margin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent teamIntent = new Intent(requireActivity(), MyTeamActivity.class);
                startActivity(teamIntent);
            }
        });

        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingIntent = new Intent(requireActivity(), SettingActivity.class);
                settingIntent.putExtra("mobile_phone", userInfoBean.getData().getUsername());
                startActivity(settingIntent);
            }
        });

        initOrderClick();

        binding.viewRecycler.setLayoutManager(new GridLayoutManager(requireActivity(), 4));

        binding.viewRecycler.setAdapter(new EasyAdapter<MyViewItemBean.DataBean>(list, R.layout.member_center_item) {

            @Override
            protected void bind(@NonNull ViewHolder viewHolder, MyViewItemBean.DataBean dataBean, int i) {
                ImageView image = viewHolder.getView(R.id.image);
                TextView name = viewHolder.getView(R.id.name);

                switch (dataBean.getEng_name()){
                    case "c2c_market":
                        image.setImageResource(R.mipmap.c2c_market);
                        name.setText(dataBean.getName());
                        break;
                    case "address":
                        image.setImageResource(R.mipmap.address);
                        name.setText(dataBean.getName());
                        break;
//                    case "market_cart":
//                        image.setImageResource(R.mipmap.market_cart);
//                        name.setText(dataBean.getName());
//                        break;
                    case "contact_customer_service":
                        image.setImageResource(R.mipmap.contact_customer_service);
                        name.setText(dataBean.getName());
                        break;
                    case "my_bank_card":
                        image.setImageResource(R.mipmap.my_bank_card);
                        name.setText(dataBean.getName());
                        break;
                    case "certificate":
                        image.setImageResource(R.mipmap.certificate);
                        name.setText(dataBean.getName());
                        break;
                    case "invite":
                        image.setImageResource(R.mipmap.invite);
                        name.setText(dataBean.getName());
                        break;
                    case "logout":
                        image.setImageResource(R.mipmap.logout);
                        name.setText(dataBean.getName());
                        break;
                }

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(dataBean.getEng_name());
                        if(dataBean.getEng_name() == "address") {
                            Intent intent = new Intent(requireActivity(), AddressManagerActivity.class);
                            startActivity(intent);
                        }
                        if(dataBean.getEng_name() == "invite") {
                            Intent intent = new Intent(requireActivity(), InviteFriendsActivity.class);
                            intent.putExtra("invite_code", userInfoBean.getData().getActive_code());
                            intent.putExtra("nickName", userInfoBean.getData().getUsername());
                            startActivity(intent);
                        }
                        if(dataBean.getEng_name() == "logout") {
                            new XPopup.Builder(requireActivity())
                                    .asConfirm("警告", "您确认要退出吗？", new OnConfirmListener() {
                                        @Override
                                        public void onConfirm() {
                                            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.clear();
                                            editor.commit();
                                            initLoadUserInfo();
                                        }
                                    }).show();
                        }
                        if(dataBean.getEng_name() == "certificate"){
                            Intent certificateIntent = new Intent(requireActivity(), CertificateActivity.class);
                            startActivity(certificateIntent);
                        }
                    }
                });

            }
        });
    }

    protected void initOrderClick(){
        binding.allOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderIntent = new Intent(requireActivity(), OrderActivity.class);
                orderIntent.putExtra("position", 0);
                startActivity(orderIntent);
            }
        });
        binding.payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(requireActivity(), OrderActivity.class);
                paymentIntent.putExtra("position", 1);
                startActivity(paymentIntent);
            }
        });
        binding.delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deliveryIntent = new Intent(requireActivity(), OrderActivity.class);
                deliveryIntent.putExtra("position", 2);
                startActivity(deliveryIntent);
            }
        });
        binding.received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent receivedIntent = new Intent(requireActivity(), OrderActivity.class);
                receivedIntent.putExtra("position", 3);
                startActivity(receivedIntent);
            }
        });
        binding.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent commentIntent = new Intent(requireActivity(), OrderActivity.class);
                commentIntent.putExtra("position", 4);
                startActivity(commentIntent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        initLoadUserInfo();

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