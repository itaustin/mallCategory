package com.rbt.diamond.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.goods.GoodsDetailActivity;
import com.rbt.diamond.databinding.GoodsItemBinding;
import com.rbt.diamond.public_bean.GoodsListBean;

import java.util.ArrayList;
import java.util.List;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder>{
    protected List<GoodsListBean.DataBeanX.ListBean.DataBean> list = new ArrayList<>();
    protected Context context;

    public List<GoodsListBean.DataBeanX.ListBean.DataBean> getList() {
        return list;
    }

    public void setList(List<GoodsListBean.DataBeanX.ListBean.DataBean> list) {
        this.list = list;
    }

    public GoodsListAdapter(List<GoodsListBean.DataBeanX.ListBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void addData(List<GoodsListBean.DataBeanX.ListBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        GoodsItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.goods_item, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GoodsListAdapter.ViewHolder holder, int position) {
        GoodsListBean.DataBeanX.ListBean.DataBean data = list.get(position);

        if(data.getCategory_id() == 10006 || data.getCategory_id() == 10002) {
            holder.binding.goodsPrice.setText("积分：" + data.getGoods_min_price());
        } else {
            holder.binding.goodsPrice.setText(data.getGoods_min_price());
        }
        holder.binding.goodsName.setText(data.getGoods_name());
        Glide.with(context).load(data.getGoods_image()).into(holder.binding.goodsImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("goods_id", data.getGoods_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        protected GoodsItemBinding binding;

        public ViewHolder(GoodsItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
