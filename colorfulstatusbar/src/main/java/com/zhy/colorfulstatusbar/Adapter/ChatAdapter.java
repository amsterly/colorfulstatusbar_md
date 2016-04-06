package com.zhy.colorfulstatusbar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhy.colorfulstatusbar.R;
import com.zhy.colorfulstatusbar.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class ChatAdapter extends BaseAdapter {

    private List<Student> list = new ArrayList<Student>();
    private final int MALE=0;
    private final int FEMALE=1;
    private Context mContext;

    public ChatAdapter(Context context) {
        this.mContext=context;
        for (int i = 0; i < 50; i++) {
            if (i % 3 == 0)
                list.add(new Student(MALE, "Student" + i));
            else
                list.add(new Student(FEMALE, "Student" + i));
        }
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      int  currentType = getItemViewType(position);

        if(currentType==MALE)
        {
            convertView= LayoutInflater.from(mContext).inflate(

                    R.layout.item_left, null);
        }
        else
        {
            convertView= LayoutInflater.from(mContext).inflate(

                    R.layout.item_right, null);
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {

        return list.get(position).getSex();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
