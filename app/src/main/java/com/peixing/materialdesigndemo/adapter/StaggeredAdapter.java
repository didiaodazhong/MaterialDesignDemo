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

import java.util.List;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {
    private static final String TAG = "Adapter";
    private Context context;
    List<String> message;
    private OnRecyclerViewItemClickListener mListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }


    public StaggeredAdapter(Context context, List<String> message) {
        this.context = context;
        this.message = message;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_stagger, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder itemViewHolder, final int position) {

        //Here you can fill your row view

        Glide.with(context)
                .load(message.get(position))
                .asBitmap()
//                .placeholder(R.mipmap.ic_launcher)
                .into(itemViewHolder.ivStagger);
        itemViewHolder.tvPosition.setText("this is " + position + "position");

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
        return message.size();
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivStagger;
        private TextView tvPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            ivStagger = (ImageView) itemView.findViewById(R.id.iv_stagger);
            tvPosition = (TextView) itemView.findViewById(R.id.tv_position);

        }
    }
}
