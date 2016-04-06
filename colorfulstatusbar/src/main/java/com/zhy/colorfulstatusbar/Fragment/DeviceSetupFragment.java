package com.zhy.colorfulstatusbar.Fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.colorfulstatusbar.R;
import com.zhy.colorfulstatusbar.SlidingTab.SlidingTabLayout;
import com.zhy.colorfulstatusbar.SlidingTab.ViewPagerAdapter;



public class DeviceSetupFragment extends Fragment {
    private SlidingTabLayout slidingTabLayout;
    private TabLayout mTabLayout;
    private ViewPager pager;
    private int position;


//    private int postion;

    public DeviceSetupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        position = getArguments().getInt("position");
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_system_maintainace, container, false);
        pager = (ViewPager) inflate.findViewById(R.id.viewpager);

        slidingTabLayout = (SlidingTabLayout) inflate.findViewById(R.id.sliding_tabs);
        String titles[] = new String[]{getResources().getString(R.string.setupsysparameter),
                getResources().getString(R.string.setuphardwareparameter), getResources().getString(R.string.setupalgorithmparameter)};
        pager.setAdapter(new ViewPagerAdapter(getFragmentManager(), titles));
        slidingTabLayout.setViewPager(pager);
        slidingTabLayout.setSelectedIndicatorColors(Color.RED);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.BLACK;
            }

        });
        pager.setCurrentItem(position, true);
        return inflate;
    }
    public void ChangePageViewByPosition(int position)
    {
        pager.setCurrentItem(position,true);
    }

}
