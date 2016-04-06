package com.zhy.colorfulstatusbar.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.zhy.colorfulstatusbar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetectionStatisticsReportFragment extends Fragment {


    public DetectionStatisticsReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_detectiontatistics_report, container, false);
        ListView totallistview = (ListView) view.findViewById(R.id.id_total_listview);
        SimpleAdapter simpleAdapter1=new SimpleAdapter(getActivity(),getTotalData(),R.layout.simple_item,new String[] { "title",  "context" },
                new int[] { R.id.id_title_item, R.id.id_content_item });
        totallistview.setAdapter(simpleAdapter1);
        ListView currentlistview = (ListView) view.findViewById(R.id.id_current_listview);
        SimpleAdapter simpleAdapter2=new SimpleAdapter(getActivity(),getCurrentData(),R.layout.simple_item,new String[] { "title",  "context" },
                new int[] { R.id.id_title_item, R.id.id_content_item });
        currentlistview.setAdapter(simpleAdapter2);
        return view;
    }

    private List<Map<String, String>> getTotalData() {
        //map.put(参数名字,参数值)
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "设备固件信息");
        map.put("context", "1.0");
//        map.put("img", R.drawable.icon);
        list.add(map);

        map = new HashMap<String, String>();
        map.put("title", "编号");
        map.put("context", "1.0");
//        map.put("img", R.drawable.icon);
        list.add(map);

        map = new HashMap<String, String>();
        map.put("title", "生产时间");
        map.put("context", "2001.1.1");
//        map.put("img", R.drawable.icon);
        list.add(map);
        map = new HashMap<String, String>();
        map.put("title", "硬件版本");
        map.put("context", "1.1");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("title", "累计报警次数");
        map.put("context", "200");
//        map.put("img", R.drawable.icon);
        list.add(map);
        return list;
    }
    private List<Map<String, String>> getCurrentData() {
        //map.put(参数名字,参数值)
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "用户信息");
        map.put("context", "A");
//        map.put("img", R.drawable.icon);
        list.add(map);

        map = new HashMap<String, String>();
        map.put("title", "登陆时间");
        map.put("context", "11:00");
//        map.put("img", R.drawable.icon);
        list.add(map);

        map = new HashMap<String, String>();
        map.put("title", "当天总检测次数");
        map.put("context", "2001");
//        map.put("img", R.drawable.icon);
        list.add(map);
        map = new HashMap<String, String>();
        map.put("title", "当天总报警次数");
        map.put("context", "3333");
//        map.put("img", R.drawable.icon);
        list.add(map);
        return list;
    }
}
