package com.peixing.materialdesigndemo.Feagment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.peixing.materialdesigndemo.R;
import com.peixing.materialdesigndemo.activity.VrVideoActivity;
import com.peixing.materialdesigndemo.adapter.VideoAdapter;
import com.peixing.materialdesigndemo.bean.VideoItem;
import com.peixing.materialdesigndemo.utils.ApiUrls;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    private static final String TAG = "VideoFragment";
    private static final int LOAD = 1;
    private RecyclerView videoRecycler;
    VideoAdapter videoAdapter;
    List<VideoItem> videoItemList;

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOAD:
                    initData();
                    break;
            }
        }
    };

    /**
     * 网络加载数据
     */
    private void initData() {
        OkGo.get(ApiUrls.URL_Query).cacheKey(ApiUrls.URL_Query)
                .cacheMode(CacheMode.DEFAULT)
                .connTimeOut(3000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
//                                    Log.i(TAG, "onSuccess: " + s);
                            String content = jsonObject.getString("content");
                            videoItemList = JSON.parseArray(content, VideoItem.class);
                            //设置设配器
                            videoAdapter = new VideoAdapter(getActivity(), videoItemList);
                            videoRecycler.setAdapter(videoAdapter);
                            videoAdapter.setOnItemClickListener(new VideoAdapter.OnRecyclerViewItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Log.i(TAG, "onItemClick: " + videoItemList.get(position).title);
                                    Intent intent = new Intent(getActivity(), VrVideoActivity.class);
                                    intent.putExtra("title", videoItemList.get(position).title);
                                    intent.putExtra("img", videoItemList.get(position).img);
                                    intent.putExtra("text", videoItemList.get(position).text);
                                    intent.putExtra("play", videoItemList.get(position).play);
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        videoRecycler = (RecyclerView) view.findViewById(R.id.video_recycler);
//        GridLayoutManager gridLayoutManager  = GridLayoutManager();
        videoRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        //发送网络请求，获取数据
        myHandler.sendEmptyMessage(LOAD);
    }
}
