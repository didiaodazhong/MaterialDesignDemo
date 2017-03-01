package com.peixing.materialdesigndemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.peixing.materialdesigndemo.R;
import com.peixing.materialdesigndemo.view.BannerRotate;
import com.peixing.materialdesigndemo.view.MiClockView;

public class BannerActivity extends Activity {

    private SwipeRefreshLayout swipeContent;
    private RecyclerView recyclerContent;
    private BannerRotate banner;
    private MiClockView clock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);


        clock = (MiClockView) findViewById(R.id.clock);

        banner = (BannerRotate) findViewById(R.id.banner);
//        swipeContent = (SwipeRefreshLayout) findViewById(R.id.swipe_content);
//        recyclerContent = (RecyclerView) findViewById(R.id.recycler_content);
//        String[] urls = {"R.drawable.a001.jpg", "R.drawable.a005.jpg", "R.drawable.a020.jpg"};
        String[] urls = {"http://p1.so.qhmsg.com/t01514641c357a98c81.jpg", "http://p4.so.qhmsg.com/t01244e62a3f44edf24.jpg", "http://p4.so.qhmsg.com/t01f017b2c06cc1124e.jpg"};
        banner.setUrls(urls);
       /* banner.setOnItemClickListener(new BannerRotate().OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(BannerActivity.this, "第" + position + "被点击了！", Toast.LENGTH_SHORT).show();
            }
        });*/
        banner.setOnItemClickListener(new BannerRotate.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(BannerActivity.this, "第" + position + "被点击了！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
