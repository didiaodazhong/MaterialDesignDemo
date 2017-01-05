package com.peixing.materialdesigndemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peixing.materialdesigndemo.R;
import com.peixing.materialdesigndemo.bean.ImageItem;


import java.util.List;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private Context context;
    private List<ImageItem> imageItemList;
    private OnRecyclerViewItemClickListener mListener = null;
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public PhotoAdapter(Context context, List<ImageItem> imageItemList) {
        this.context = context;
        this.imageItemList = imageItemList;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_photo, viewGroup, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoHolder itemViewHolder, final int position) {

        //Here you can fill your row view
        Glide.with(context)
                .load(imageItemList.get(position).url)
                .asBitmap()
                .placeholder(R.mipmap.andes)
                .centerCrop()
                .into(itemViewHolder.ivPano);
        itemViewHolder.tvTitle.setText(imageItemList.get(position).title);
        if (mListener != null) {
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return imageItemList.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }


    public class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView ivPano;
        private TextView tvTitle;

        public PhotoHolder(View itemView) {
            super(itemView);
            ivPano = (ImageView) itemView.findViewById(R.id.iv_pano);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
