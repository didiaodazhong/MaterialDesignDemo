package com.peixing.materialdesigndemo.Feagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.peixing.materialdesigndemo.bean.Image;
import com.peixing.materialdesigndemo.R;
import com.peixing.materialdesigndemo.adapter.GridAdapter;
import com.peixing.materialdesigndemo.view.MyDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridLayoutFragment extends BaseFragment {

    private SwipeRefreshLayout swipeRefreshGrid;
    private RecyclerView gridLayout;
    GridAdapter gridAdapter;
    private ArrayList<String> lists;

    @Override
    protected void goBack() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        lists = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            lists.add("李斯" + i);
        }
        gridAdapter = new GridAdapter(getActivity(), Image.imageThumbUrls, gridLayout);
        gridLayout.setAdapter(gridAdapter);
        gridLayout.addItemDecoration(new MyDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        gridAdapter.setItemHeight(250);

    }

    @Override
    protected View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_grid_layout, null);
        swipeRefreshGrid = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_grid);
        gridLayout = (RecyclerView) view.findViewById(R.id.grid_layout);
        gridLayout.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayout.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        gridAdapter.flushCache();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gridAdapter.cancelAllTasks();
    }
}
