package com.peixing.materialdesigndemo.Feagment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import com.peixing.materialdesigndemo.bean.Image;
import com.peixing.materialdesigndemo.R;
import com.peixing.materialdesigndemo.adapter.StaggeredAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaggeredGridLayoutFragment extends BaseFragment {
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView linearLayout1;
    //    ArrayList<String> lists = new ArrayList<>();
    ArrayList<String> lists;
    private StaggeredAdapter staggeredAdapter;

    @Override
    protected void goBack() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        lists = new ArrayList<>();
//        lists.clear();
        for (int i = 0; i < Image.imageThumbUrls.length; i++) {
            lists.add(Image.imageThumbUrls[i]);
        }
        staggeredAdapter = new StaggeredAdapter(getActivity(), lists);
        linearLayout1.setAdapter(staggeredAdapter);
//        linearLayout1.addItemDecoration(new MyDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        staggeredAdapter.setOnItemClickListener(new StaggeredAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "第" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_stagger_layout, null);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_staggered);
        linearLayout1 = (RecyclerView) view.findViewById(R.id.staggergrid_layout);
        linearLayout1.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        linearLayout1.setLayoutManager(layoutManager);
        return view;
    }
}
