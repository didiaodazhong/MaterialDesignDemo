package com.peixing.materialdesigndemo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.peixing.materialdesigndemo.Feagment.AppInfoFragment;
import com.peixing.materialdesigndemo.Feagment.GridLayoutFragment;
import com.peixing.materialdesigndemo.Feagment.LinearLayoutFragment;
import com.peixing.materialdesigndemo.Feagment.PhotoFragment;
import com.peixing.materialdesigndemo.Feagment.StaggeredGridLayoutFragment;
import com.peixing.materialdesigndemo.Feagment.VideoFragment;
import com.peixing.materialdesigndemo.PermissionListener;
import com.peixing.materialdesigndemo.R;

import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewpager;
    FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private String[] titles = new String[]{"一", "二", "三", "四", "五", "六"};
    private Toolbar toolbar;
    //, "五","六","七","八","九","十"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //设置状态栏透明
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        initViewGroup();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        //android6.0动态申请权限
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "你确定要申请权限!", Snackbar.LENGTH_LONG)
                        .setAction("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

//                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_LONG).show();
                                //用户申请权限
                                requestRuntimePermission(new String[]{
                                                Manifest.permission.CAMERA,
                                                Manifest.permission.READ_CONTACTS,
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        new PermissionListener() {

                                            @Override
                                            public void onGranted() {
                                                Toast.makeText(MainActivity.this, "所有权限都被允许！", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onDenied(List<String> deniedPermission) {
                                                for (String permission : deniedPermission) {
                                                    Toast.makeText(MainActivity.this, permission + "权限都被拒绝！", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                            }
                        }).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 初始化viewpager
     */
    private void initViewGroup() {
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new LinearLayoutFragment();
                } else if (position == 1) {
                    return new GridLayoutFragment();
                } else if (position == 2) {
                    return new StaggeredGridLayoutFragment();
                } else if (position == 3) {
                    return new PhotoFragment();
                } else if (position == 4) {
                    return new VideoFragment();
                } else {
                    return new AppInfoFragment();
                }
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

        });
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 填充右上角设置选项
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                Snackbar.make(fab, "设置选项", Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.action_more:
                Snackbar.make(fab, "更多选项", Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.action_night:
                if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO) {
                    getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                // 调用 recreate() 使设置生效
                recreate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(MainActivity.this, BannerActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(MainActivity.this, CollaspingActivity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
