package com.rbt.diamond.public_adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.goods.GoodsDetailActivity;
import com.rbt.diamond.databinding.CategoryGoodsItemBinding;
import com.rbt.diamond.public_bean.GoodsListBean;

import java.util.ArrayList;
import java.util.List;

public class CategoryGoodsAdapter extends RecyclerView.Adapter<CategoryGoodsAdapter.ViewHolder> {
    protected List<GoodsListBean.DataBeanX.ListBean.DataBean> list = new ArrayList<>();
    protected Context context;
    protected int type = 0;

    public CategoryGoodsAdapter(List<GoodsListBean.DataBeanX.ListBean.DataBean> list, Context context, int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    public void addData(List<GoodsListBean.DataBeanX.ListBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryGoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryGoodsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.category_goods_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryGoodsAdapter.ViewHolder holder, int position) {
        GoodsListBean.DataBeanX.ListBean.DataBean data = list.get(position);
        holder.binding.goodsName.setText(data.getGoods_name());
        Glide.with(context).load(data.getGoods_image()).into(holder.binding.goodsImage);
        holder.binding.goodsPrice.setText(data.getGoods_min_price());

        holder.binding.linePrice.setText(data.getGoods_sku().getLine_price());
        holder.binding.linePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.binding.linePrice.getPaint().setAntiAlias(true);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goodsDetailIntent = new Intent(context, GoodsDetailActivity.class);
                goodsDetailIntent.putExtra("goods_id", data.getGoods_id());
                context.startActivity(goodsDetailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        protected CategoryGoodsItemBinding binding;

        public ViewHolder(CategoryGoodsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
