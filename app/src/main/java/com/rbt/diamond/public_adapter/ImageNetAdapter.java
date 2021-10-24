package com.rbt.diamond.public_adapter;

import android.os.Build;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;
import com.rbt.diamond.R;
import com.rbt.diamond.public_bean.GoodsDetailBean;
import com.rbt.diamond.public_holder.ImageHolder;

import java.util.List;

/**
 * 自定义布局，网络图片
 */
public class ImageNetAdapter extends BannerAdapter<GoodsDetailBean.DataBean.DetailBean.ImageBean, ImageHolder> {

    public ImageNetAdapter(List<GoodsDetailBean.DataBean.DetailBean.ImageBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);
        //通过裁剪实现圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView, 40);
        }
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, GoodsDetailBean.DataBean.DetailBean.ImageBean data, int position, int size) {
        holder.imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        Glide.with(holder.itemView)
             .load(data.getFile_path())
             .thumbnail(Glide.with(holder.itemView).load(R.drawable.loading))
             .skipMemoryCache(true)
             .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
             .into(holder.imageView);
    }

}
