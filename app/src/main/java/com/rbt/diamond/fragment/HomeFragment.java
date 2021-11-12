package com.rbt.diamond.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.util.SmartGlideImageLoader;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.goods.GoodsDetailActivity;
import com.rbt.diamond.activity.home.MemberMallActivity;
import com.rbt.diamond.activity.home.PointsCategoryActivity;
import com.rbt.diamond.activity.home.SearchActivity;
import com.rbt.diamond.activity.passport.LoginActivity;
import com.rbt.diamond.activity.scan.QRCodeActivity;
import com.rbt.diamond.databinding.FragmentHomeBinding;
import com.rbt.diamond.public_adapter.HomeNetBannerAdapter;
import com.rbt.diamond.public_adapter.ImageNetAdapter;
import com.rbt.diamond.public_bean.HomePageBean;
import com.rbt.diamond.util.UpdateAppHttpUtil;
import com.rbt.diamond.util.Util;
import com.vector.update_app.UpdateAppManager;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


public class HomeFragment extends Fragment {
    protected View view;
    protected Banner banner;
    protected FragmentHomeBinding binding;
    public static ArrayList<Object> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        view = binding.getRoot();

        initView();

        initRequestHomeData();

        return view;
    }

    protected void initView() {
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
                intent.putExtra("category_id", "10001");
                startActivity(intent);
            }
        });

        binding.saleMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), MemberMallActivity.class);
                intent.putExtra("category_id", "10002");
                startActivity(intent);
            }
        });

        binding.searchGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        binding.pointsMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), PointsCategoryActivity.class);
                startActivity(intent);
            }
        });

        binding.bannItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), PointsCategoryActivity.class);
                startActivity(intent);
            }
        });

        binding.bannCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), PointsCategoryActivity.class);
                startActivity(intent);
            }
        });

        binding.memberRecycler.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));

    }

    protected void initRequestHomeData() {
        OkHttpUtils.
                post()
                .url(Util.url + "/api/page/index")
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
                        HomePageBean dataBean = gson.fromJson(response, HomePageBean.class);
                        if (dataBean.getCode() == 1) {
                            EasyAdapter<HomePageBean.DataBeanX.ItemsBean._$2Bean.GoodsDataBean> adapter
                                    = new EasyAdapter<HomePageBean.DataBeanX.ItemsBean._$2Bean.GoodsDataBean>(dataBean.getData().getItems().get_$2().getGoodsData(), R.layout.home_member_goods_item) {
                                @Override
                                protected void bind(@NonNull ViewHolder viewHolder, HomePageBean.DataBeanX.ItemsBean._$2Bean.GoodsDataBean goodsDataBean, int i) {
                                    TextView goods_price = viewHolder.getView(R.id.goods_price);
                                    TextView line_price = viewHolder.getView(R.id.line_price);
                                    ImageView goods_image = viewHolder.getView(R.id.goods_image);
                                    Glide.with(requireActivity()).load(goodsDataBean.getGoods_image()).into(goods_image);
                                    goods_price.setText(goodsDataBean.getGoods_price());
                                    line_price.setText(goodsDataBean.getLine_price());
                                    line_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                    line_price.getPaint().setAntiAlias(true);

                                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent goodsIntent = new Intent(requireActivity(), GoodsDetailActivity.class);
                                            goodsIntent.putExtra("goods_id", goodsDataBean.getGoods_id());
                                            startActivity(goodsIntent);
                                        }
                                    });
                                }
                            };
                            binding.memberRecycler.setAdapter(adapter);

                            binding.todo.setText(dataBean.getData().getItems().get_$1().getParams().getText());
                            Glide.with(requireActivity()).load(dataBean.getData().getItems().get_$1().getParams().getIcon()).into(binding.icon);

                            HomeNetBannerAdapter netBannerAdapter = new HomeNetBannerAdapter(dataBean.getData().getItems().get_$0().getData());
                            list.clear();
                            for (HomePageBean.DataBeanX.ItemsBean._$0Bean.DataBean bean : dataBean.getData().getItems().get_$0().getData()){
                                list.add(bean.getImgUrl());
                            }
                            binding.imageView.setAdapter(netBannerAdapter)
                                    .setOnBannerListener(new OnBannerListener() {
                                        @Override
                                        public void OnBannerClick(Object data, int position) {
                                            new XPopup.Builder(requireActivity()).asImageViewer(binding.viewImage, position, list, new OnSrcViewUpdateListener() {
                                                @Override
                                                public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
                                                    // 注意这里：根据position找到你的itemView。根据你的itemView找到你的ImageView。
                                                    // Demo中RecyclerView里面只有ImageView所以这样写，不要原样COPY。
                                                    // 作用是当Pager切换了图片，需要更新源View，
                                                }
                                            }, new SmartGlideImageLoader())
                                                    .show();
                                        }
                                    })
                                    .setIndicator(new CircleIndicator(requireActivity()));
                        }
                    }
                });
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