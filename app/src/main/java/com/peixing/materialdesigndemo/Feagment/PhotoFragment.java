package com.peixing.materialdesigndemo.Feagment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peixing.materialdesigndemo.R;
import com.peixing.materialdesigndemo.activity.VrPhotoActivity;
import com.peixing.materialdesigndemo.adapter.PhotoAdapter;
import com.peixing.materialdesigndemo.bean.ImageItem;
import com.peixing.materialdesigndemo.utils.ImageUrlGetter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {
    private RecyclerView photoRecycler;
    PhotoAdapter photoAdapter;

    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        initView(view);
        return view;


    }

    private void initView(View view) {
        photoRecycler = (RecyclerView) view.findViewById(R.id.photo_recycler);
        photoRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        final List<ImageItem> imageList = ImageUrlGetter.getImageItems();

        photoAdapter = new PhotoAdapter(getActivity(), imageList);
        photoRecycler.setAdapter(photoAdapter);

        photoAdapter.setOnItemClickListener(new PhotoAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), VrPhotoActivity.class);
                intent.putExtra("url", imageList.get(position).url);
                startActivity(intent);
            }
        });
    }
}
