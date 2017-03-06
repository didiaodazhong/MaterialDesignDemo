package com.peixing.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peixing.materialdesigndemo.R;

public class CollaspingActivity extends AppCompatActivity {

    private static final String TAG = "CollaspingActivity";
    private CoordinatorLayout activityCollasping;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout collapsingtoolbarlayout;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private NestedScrollView nestedScrollview;
    private TextView tvWord;
    private ImageView ivBenz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collasping);
        ivBenz = (ImageView) findViewById(R.id.iv_benz);
        activityCollasping = (CoordinatorLayout) findViewById(R.id.activity_collasping);
        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingtoolbarlayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        nestedScrollview = (NestedScrollView) findViewById(R.id.nested_scrollview);
        tvWord = (TextView) findViewById(R.id.tv_word);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //自定义文字选中状态选项
        tvWord.setCustomSelectionActionModeCallback(new MyCallBack());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + toolbar.getVisibility());
                if (toolbar.getVisibility() == View.VISIBLE) {
                    appBar.setExpanded(false);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyCallBack implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }
}
