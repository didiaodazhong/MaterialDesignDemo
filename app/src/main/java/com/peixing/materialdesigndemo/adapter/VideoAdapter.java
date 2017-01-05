package com.peixing.materialdesigndemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peixing.materialdesigndemo.R;
import com.peixing.materialdesigndemo.bean.VideoItem;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private static final String TAG = "VideoAdapter";
    private Context context;
    private List<VideoItem> videoItemList;
    private OnRecyclerViewItemClickListener mListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }



    public VideoAdapter(Context context, List<VideoItem> videoItemList) {
        this.context = context;
        this.videoItemList = videoItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_video, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder itemViewHolder, final int position) {

        //Here you can fill your row view
        Glide.with(context)
                .load(videoItemList.get(position).img)
                .asBitmap()
                .into(itemViewHolder.topicInitImg);
        itemViewHolder.topicInitTitle.setText(videoItemList.get(position).title);
        itemViewHolder.tag0.setText(videoItemList.get(position).tags[0]);
        itemViewHolder.dateText.setText(new SimpleDateFormat("yyyy/MM/dd").format(videoItemList.get(position).date));
        if (mListener != null) {
            itemViewHolder.videoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, position);
                Log.i(TAG, "onItemClick: " + videoItemList.get(position).title);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return videoItemList.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout videoLayout;
        private LinearLayout itemLayout;
        private ImageView topicInitImg;
        private TextView topicInitTitle;
        private TextView userName;
        private TextView channelName;
        private TextView tag0;
        private TextView tag1;
        private TextView tag2;
        private TextView dateText;


        public ViewHolder(View itemView) {
            super(itemView);
            videoLayout = (LinearLayout) itemView.findViewById(R.id.video_layout);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            topicInitImg = (ImageView) itemView.findViewById(R.id.topic_init_img);
            topicInitTitle = (TextView) itemView.findViewById(R.id.topic_init_title);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            channelName = (TextView) itemView.findViewById(R.id.channel_name);
            tag0 = (TextView) itemView.findViewById(R.id.tag0);
            tag1 = (TextView) itemView.findViewById(R.id.tag1);
            tag2 = (TextView) itemView.findViewById(R.id.tag2);
            dateText = (TextView) itemView.findViewById(R.id.date_text);

        }
    }
}
