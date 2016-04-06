package com.zhy.colorfulstatusbar.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.zhy.colorfulstatusbar.Bean.InternationalNews;
import com.zhy.colorfulstatusbar.Interface.RecycleViewItemOnClickListener;
import com.zhy.colorfulstatusbar.R;
import com.zhy.colorfulstatusbar.utils.GlideCircleTransform;
import com.zhy.colorfulstatusbar.utils.GlideRoundTransform;

import java.util.ArrayList;


/**
 * Created by jianghejie on 15/11/26.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public ArrayList<InternationalNews.NewslistBean> datas = null;
    private Context mContext;



    private RecycleViewItemOnClickListener mListener;
    public void setOnItemClickListener(RecycleViewItemOnClickListener listener)
    {
        this.mListener=listener;
    }


    public MyAdapter(ArrayList<InternationalNews.NewslistBean> datas) {
        this.datas = datas;
    }
    public MyAdapter(ArrayList<InternationalNews.NewslistBean> datas,Context context) {
        this.datas = datas;mContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        final ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final  int position) {
        viewHolder.mTextView.setText(datas.get(position).getTitle());
        String url = "http://avatar.csdn.net/3/D/3/1_lvwenbo0107.jpg";
        if(!datas.get(position).getPicUrl().equals(""))
            url=datas.get(position).getPicUrl();
        Glide
                .with(mContext)
                .load(url)
//                .transform(new GlideRoundTransform(mContext, 28))
                .transform(new GlideCircleTransform(mContext))
//                .centerCrop() //这个会影响transform
                .placeholder(R.drawable.ic_done)
                .thumbnail(0.1f)
                .error(R.drawable.ic_discuss)
                .crossFade()
                .into(viewHolder.mImageView);
        //Gif
//        Glide
//                .with(mContext)
//                .load(R.drawable.yellow) .into(viewHolder.mImageView);

        if(mListener!=null) {

            viewHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int AdapterPosition = viewHolder.getAdapterPosition();
                    int LayoutPosition = viewHolder.getLayoutPosition();
                    Log.d("MyAdapter", "AdapterPosition:" + AdapterPosition);
                    Log.d("LayoutPosition", "LayoutPosition:" + LayoutPosition);
                    Log.d("position", "position:" + position);
                    mListener.onItemClick(viewHolder.mLinearLayout, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView mTextView;
        public LinearLayout mLinearLayout;
        public ImageView mImageView;

        public ViewHolder(View view) {
            super(view);

            mLinearLayout = (LinearLayout) view.findViewById(R.id.id_linear);
            mImageView= (ImageView) view.findViewById(R.id.id_imageview);
            mLinearLayout.setClickable(true);
            mTextView = (TextView) view.findViewById(R.id.text);
        }
    }
}
