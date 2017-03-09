package com.peixing.materialdesigndemo.Feagment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.peixing.materialdesigndemo.R;
import com.peixing.materialdesigndemo.activity.MainActivity;
import com.peixing.materialdesigndemo.adapter.ApplicationAdapter;
import com.peixing.materialdesigndemo.bean.AppInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppInfoFragment extends BaseFragment {

    private RecyclerView recyclerAppInfo;
    private static Context context;
    private static List<AppInfo> applicationList = new ArrayList<AppInfo>();
    private static ApplicationAdapter adapter;

    static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    applicationList.clear();
                    //Query the applications
                    final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                    List<ResolveInfo> ril = context.getPackageManager().queryIntentActivities(mainIntent, 0);
                    for (ResolveInfo ri : ril) {
                        applicationList.add(new AppInfo(context, ri));
                    }
                    Collections.sort(applicationList);
                    for (AppInfo appInfo : applicationList)
                    {
                        //load icons before shown. so the list is smoother
                        appInfo.getIcon();
                    }
                    adapter.addApplications(applicationList);
                    break;
            }
        }
    };


    @Override
    protected void goBack() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        context = getActivity();
        handler.sendEmptyMessageDelayed(0, 500);
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_app_info, null);

        recyclerAppInfo = (RecyclerView) view.findViewById(R.id.recycler_app_info);
        recyclerAppInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ApplicationAdapter(applicationList, R.layout.row_application);
        recyclerAppInfo.setAdapter(adapter);
        adapter.setOnItemClickListener(new ApplicationAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(context, applicationList.get(position).getPackageName().toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = context.getPackageManager().getLaunchIntentForPackage(applicationList.get(position).getPackageName());
//                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}
