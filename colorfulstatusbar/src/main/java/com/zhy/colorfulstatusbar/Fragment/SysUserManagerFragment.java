package com.zhy.colorfulstatusbar.Fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.colorfulstatusbar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SysUserManagerFragment extends Fragment {


    public SysUserManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sys_user_manager, container, false);
    }

}
