package com.rbt.diamond.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.rbt.diamond.R;
import com.rbt.diamond.databinding.FragmentMyBinding;
import com.rbt.diamond.public_bean.MyViewItemBean;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends Fragment {
    protected List<MyViewItemBean.DataBean> list = new ArrayList<>();
    protected ViewModel viewModel;
    protected FragmentMyBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_my, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);

        // test code
        viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);

        initLayoutManager();

        return binding.getRoot();
    }

    protected void initLayoutManager(){

        MyViewItemBean.DataBean c2c_market = new MyViewItemBean.DataBean();
        c2c_market.setEng_name("c2c_market");
        c2c_market.setName("我的交易市场");
        list.add(c2c_market);

        MyViewItemBean.DataBean address = new MyViewItemBean.DataBean();
        address.setEng_name("address");
        address.setName("地址管理");
        list.add(address);

        MyViewItemBean.DataBean market_cart = new MyViewItemBean.DataBean();
        market_cart.setEng_name("market_cart");
        market_cart.setName("购物车");
        list.add(market_cart);

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
                    case "market_cart":
                        image.setImageResource(R.mipmap.market_cart);
                        name.setText(dataBean.getName());
                        break;
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
                }

            }
        });
    }
}