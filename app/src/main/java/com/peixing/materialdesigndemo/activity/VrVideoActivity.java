package com.peixing.materialdesigndemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peixing.materialdesigndemo.R;


public class VrVideoActivity extends AppCompatActivity {
    private static final String TAG = "VrVideoActivity";
    private FrameLayout videoDetail;
    private LinearLayout controlBar;
    private TextView titleText;
    private ImageView detailImgView;
    private TextView videoType;
    private ImageButton playLink;
    private TextView detailText;
    String title;
    String img;
    String text;
    String play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_video);
        init();

    }

    private void init() {
        videoDetail = (FrameLayout) findViewById(R.id.video_detail);
        controlBar = (LinearLayout) findViewById(R.id.control_bar);
        titleText = (TextView) findViewById(R.id.title_text);
        detailImgView = (ImageView) findViewById(R.id.detail_img_view);
        videoType = (TextView) findViewById(R.id.video_type);
        playLink = (ImageButton) findViewById(R.id.play_link);
        detailText = (TextView) findViewById(R.id.detail_text);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        img = intent.getStringExtra("img");
        text = intent.getStringExtra("text");
        play = intent.getStringExtra("play");

        Log.i(TAG, "onCreate: " + title + "--" + img);
        titleText.setText(title);
        detailText.setText(text);
        Glide.with(getApplicationContext())
                .load(img)
                .asBitmap()
                .into(detailImgView);
        playLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VrVideoActivity.this, VrPlayerActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("play", play);
                Log.i(TAG, "onClick: " + play);
                startActivity(intent);
            }
        });
    }
}
