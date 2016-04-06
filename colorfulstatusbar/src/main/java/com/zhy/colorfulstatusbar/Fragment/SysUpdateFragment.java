package com.zhy.colorfulstatusbar.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.colorfulstatusbar.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SysUpdateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SysUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SysUpdateFragment extends Fragment {


    public SysUpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sys_update, container, false);
    }


}
