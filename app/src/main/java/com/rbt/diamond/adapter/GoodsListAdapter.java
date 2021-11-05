package com.rbt.diamond.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbt.diamond.R;
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
        View view = inflater.inflate(R.layout.goods_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsListAdapter.ViewHolder holder, int position) {
        GoodsListBean.DataBeanX.ListBean.DataBean data = list.get(position);

        System.out.println(data.getGoods_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
