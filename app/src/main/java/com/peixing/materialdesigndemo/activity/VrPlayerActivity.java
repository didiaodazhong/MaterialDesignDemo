package com.peixing.materialdesigndemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.peixing.materialdesigndemo.R;


import java.io.IOException;

public class VrPlayerActivity extends AppCompatActivity {
    private LinearLayout activityVrPlayer;
    private VrVideoView videoPlayer;
    private SeekBar seekBar;
    private TextView tvProgress;
    private TextView titlePlay;
    String play;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_player);
        init();
    }

    /**
     * 初始化界面
     */
    private void init() {

        activityVrPlayer = (LinearLayout) findViewById(R.id.activity_vr_player);
        videoPlayer = (VrVideoView) findViewById(R.id.video_player);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        tvProgress = (TextView) findViewById(R.id.tv_progress);

        videoPlayer.setInfoButtonEnabled(false);
        titlePlay = (TextView) findViewById(R.id.title_play);

        Intent intent = getIntent();

        play = intent.getStringExtra("play");

        title = intent.getStringExtra("title");

        titlePlay.setText(title);
        VideoLoaderTask task = new VideoLoaderTask();
        task.execute(play);
        videoPlayer.setEventListener(new VrVideoEventListener() {
            @Override
            public void onLoadSuccess() {
                super.onLoadSuccess();
                seekBar.setMax((int) videoPlayer.getDuration());
            }

            @Override
            public void onNewFrame() {
                super.onNewFrame();
                seekBar.setProgress((int) videoPlayer.getCurrentPosition());
                tvProgress.setText("当前进度：" + String.format("%.2f", videoPlayer.getCurrentPosition() / 1000f));

            }
        });

    }


    private class VideoLoaderTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String play = params[0];
            VrVideoView.Options options = new VrVideoView.Options();
            options.inputType = VrVideoView.Options.TYPE_MONO;
            options.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;
            try {
                videoPlayer.loadVideo(Uri.parse(play), options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.resumeRendering();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.pauseRendering();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.shutdown();
    }
}
