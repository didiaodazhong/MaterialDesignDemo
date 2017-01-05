package com.peixing.materialdesigndemo.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.peixing.materialdesigndemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peixing on 2016/12/28.
 */

public class BannerRotate extends FrameLayout {

    private Context mContext;
    private ViewPager viewPager;
    private TextView tvTitle;
    private LinearLayout pointGroup;
    private List<ImageView> lists;
    private Handler myHandler;
    private int pointsize = 20;
    //间距
    private int pointMargin = 20;


    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    public OnItemClickListener mListener;

    public BannerRotate(Context context) {
        super(context);
    }

    public BannerRotate(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        myHandler = new Handler();
        lists = new ArrayList<>();
        initView(mContext);
    }

    private void initView(Context mContext) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.banner_rotate, this, true);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
//        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        pointGroup = (LinearLayout) view.findViewById(R.id.point_group);

    }


    public void setUrls(String[] urls) {
        if (urls == null || urls.length == 0) {
            this.setVisibility(GONE);
            return;
        }
        for (int i = 0; i < urls.length; i++) {
            ImageView iv = new ImageView(mContext);
            Glide.with(mContext)
                    .load(urls[i])
                    .asBitmap()
//                    .placeholder(R.drawable.a005)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv);
            lists.add(iv);

            makePoint(i);
        }
        //设置adapter
        setUpWithAdapter();
        //定时任务
        timerTask();
    }

    /**
     * 定时任务
     */
    private void timerTask() {
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem == viewPager.getAdapter().getCount() - 1) {
                    viewPager.setCurrentItem(1);
                } else {
                    viewPager.setCurrentItem(currentItem + 1);
                }
                myHandler.postDelayed(this, 3000);

            }
        }, 3000);
    }

    /**
     * 与adapter关联
     */
    private void setUpWithAdapter() {
        viewPager.setAdapter(new CycleAdapter());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int lastPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % lists.size();
                pointGroup.getChildAt(position).setSelected(true);
                //设置当前远点选中
                pointGroup.getChildAt(lastPosition).setSelected(false);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 创建小圆点
     *
     * @param i
     */
    private void makePoint(int i) {
        ImageView point = new ImageView(mContext);
        point.setImageResource(R.drawable.shape_point_selector);
        //小圆点布局参数
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pointsize, pointsize);
        if (i > 0) {
            params.leftMargin = pointMargin;
            point.setSelected(false);
        } else {
            point.setSelected(true);
        }
        point.setLayoutParams(params);
        pointGroup.addView(point);

    }


    class CycleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % lists.size();
            final View view = lists.get(position);
            if (view.getParent() != null) {
                container.removeView(view);
            }
            //点击事件
            if (mListener != null) {
                final int finalPOsition = position;
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.OnItemClick(view, finalPOsition);
                    }
                });
            }
            container.addView(lists.get(position));
            return lists.get(position);

        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * 设置小圆点的大小
     * 默认为20dp，如果需要修改，则在 setImages() 方法前调用
     *
     * @param size：小圆点大小
     */
    public void setPointSize(int size) {
        this.pointsize = dp2px(size);
    }

    /**
     * 设置小圆点的左边距
     * 默认为20dp，如果需要修改，则在 setImages() 方法前调用
     *
     * @param margin：距离
     */
    public void setPointMargin(int margin) {
        this.pointMargin = dp2px(margin);
    }

    /**
     * 将 dp 转换成 px
     *
     * @param dp
     * @return
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

}