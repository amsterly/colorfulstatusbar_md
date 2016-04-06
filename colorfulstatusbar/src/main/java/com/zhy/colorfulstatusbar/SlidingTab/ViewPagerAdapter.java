package com.zhy.colorfulstatusbar.SlidingTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhy.colorfulstatusbar.Fragment.SetupAlgorithmparameterFragment;
import com.zhy.colorfulstatusbar.Fragment.SetupHardwareParameterFragment;
import com.zhy.colorfulstatusbar.Fragment.SetupSysParameterFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT =3;
    private String titles[] ;

    private SetupSysParameterFragment setupSysParameterFragment;
    private SetupHardwareParameterFragment setupHardwareParameterFragment;
    private SetupAlgorithmparameterFragment setupAlgorithmparameterFragment;
    public ViewPagerAdapter(FragmentManager fm, String[] titles2) {
        super(fm);
        titles=titles2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
            case 0:
                if(null==setupSysParameterFragment)
                    setupSysParameterFragment=new SetupSysParameterFragment();
                return setupSysParameterFragment;
            case 1:
                if(null==setupHardwareParameterFragment)
                    setupHardwareParameterFragment = new SetupHardwareParameterFragment();
                return setupHardwareParameterFragment;
            case 2:
                if(null==setupAlgorithmparameterFragment)
                    setupAlgorithmparameterFragment=new SetupAlgorithmparameterFragment();
                return setupAlgorithmparameterFragment;
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}