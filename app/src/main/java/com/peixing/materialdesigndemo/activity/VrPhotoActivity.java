package com.peixing.materialdesigndemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.peixing.materialdesigndemo.R;

import okhttp3.Call;
import okhttp3.Response;


public class VrPhotoActivity extends AppCompatActivity {
    private RelativeLayout activityVrPhoto;
    private VrPanoramaView imagePanorama;
    private ProgressBar pb;
    private ActionBar actionBar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_photo);
        init();


    }

    private void init() {
//        initActionBar();
        pb = (ProgressBar) findViewById(R.id.pb);
        activityVrPhoto = (RelativeLayout) findViewById(R.id.activity_vr_photo);
        imagePanorama = (VrPanoramaView) findViewById(R.id.image_panorama);
        imagePanorama.setInfoButtonEnabled(false);

        initPanorama();
    }

    /**
     * 初始化加载全景图片
     */
    private void initPanorama() {
        Intent intent =   getIntent();

        url = intent.getStringExtra("url");


        OkGo.get(url)
                .cacheKey(url)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Bitmap bitmap, Call call, Response response) {
                        pb.setVisibility(View.GONE);
                        VrPanoramaView.Options options = new VrPanoramaView.Options();
                        options.inputType = VrPanoramaView.Options.TYPE_MONO;
                        imagePanorama.loadImageFromBitmap(bitmap, options);
                    }
                });

    }

    private void initActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
