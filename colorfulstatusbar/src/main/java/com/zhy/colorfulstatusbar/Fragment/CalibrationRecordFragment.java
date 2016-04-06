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
public class CalibrationRecordFragment extends Fragment {


    public CalibrationRecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calibration_record, container, false);
    }

}
