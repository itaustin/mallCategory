package com.rbt.diamond.public_adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rbt.diamond.R;
import com.rbt.diamond.activity.address.EditAddressActivity;
import com.rbt.diamond.public_bean.AddressBean;
import com.rbt.diamond.public_bean.ResultMsgBean;
import com.rbt.diamond.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    protected Context context;
    protected List<AddressBean.DataBean.ListBean> list;
    protected int lastSelectedPosition = -1;
    protected int default_id = 0;

    public AddressAdapter(Context context, List<AddressBean.DataBean.ListBean> list, int default_id) {
        this.context = context;
        this.list = list;
        this.default_id = default_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_address_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AddressBean.DataBean.ListBean data = list.get(position);
        holder.name.setText(data.getName());
        holder.phone.setText(data.getPhone());
        holder.address.setText(data.getRegion().getProvince() + "-" + data.getRegion().getCity() + "-" + data.getRegion().getRegion() + data.getDetail());

        if (data.getAddress_id() == default_id){
            holder.state.setImageResource(R.mipmap.checked);
            holder.set_default_text.setTextColor(Color.parseColor("#ff5a72b9"));
            lastSelectedPosition = position;
        } else {
            holder.state.setImageResource(R.mipmap.not_checked);
            holder.set_default_text.setTextColor(Color.parseColor("#999999"));
        }

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(context, EditAddressActivity.class);
                editIntent.putExtra("address_id", data.getAddress_id());
                context.startActivity(editIntent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new XPopup.Builder(context)
                        .dismissOnTouchOutside(false)
                        .dismissOnBackPressed(false)
                        .asConfirm("删除提示？", "确认要删除该地址吗？", new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                OkHttpUtils
                                        .post()
                                        .url(Util.url + "api/address/delete")
                                        .addParams("token", Util.getToken(context))
                                        .addParams("address_id", String.valueOf(data.getAddress_id()))
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {

                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                                if(resultMsgBean.getCode() == -1){
                                                    Util.showToastError(context, "请先登录");
                                                } else if(resultMsgBean.getCode() == 1){
                                                    Util.showToastSuccess(context, resultMsgBean.getMsg());
                                                } else if(resultMsgBean.getCode() == 0){
                                                    Util.showToastError(context, resultMsgBean.getMsg());
                                                }
                                            }
                                        });
                            }
                        }).show();
            }
        });
        
        holder.set_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new XPopup.Builder(context)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asConfirm("设置默认地址提示", "确认将改地址设为默认吗？", new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                OkHttpUtils
                                        .post()
                                        .url(Util.url + "api/address/setDefault")
                                        .addParams("token", Util.getToken(context))
                                        .addParams("address_id", String.valueOf(data.getAddress_id()))
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                ResultMsgBean resultMsgBean = Util.ResultFunction(response);
                                                if(resultMsgBean.getCode() == -1){
                                                    Util.showToastError(context, "请先登录");
                                                } else if(resultMsgBean.getCode() == 1){
                                                    Util.showToastSuccess(context, resultMsgBean.getMsg());
                                                    ((Activity) context).finish();
                                                } else if(resultMsgBean.getCode() == 0){
                                                    Util.showToastError(context, resultMsgBean.getMsg());
                                                }
                                            }
                                        });
                            }
                        }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected final static class ViewHolder extends RecyclerView.ViewHolder{
        protected LinearLayout set_default, edit, delete;
        protected ImageView state;
        protected TextView name, phone, address, set_default_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            set_default = itemView.findViewById(R.id.set_default);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            state = itemView.findViewById(R.id.state);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            set_default_text = itemView.findViewById(R.id.set_default_text);
        }
    }
}
