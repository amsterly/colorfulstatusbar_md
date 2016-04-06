package com.zhy.colorfulstatusbar.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.colorfulstatusbar.Adapter.MyAdapter;
import com.zhy.colorfulstatusbar.Bean.InternationalNews;
import com.zhy.colorfulstatusbar.Bean.News;
import com.zhy.colorfulstatusbar.Interface.RecycleViewItemOnClickListener;
import com.zhy.colorfulstatusbar.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;


/**
 * Created by sunger on 2015/12/15.
 */
public class RecyclerLinearFragment extends Fragment implements RecycleViewItemOnClickListener {
    private XRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<InternationalNews.NewslistBean> listData;
    private int refreshTime = 0;
    private int times = 0;
    private View header;
    private Integer page=1;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        header = inflater.inflate(R.layout.recyclerview_header, container, false);
        gson=new Gson();
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (XRecyclerView) getView().findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.SemiCircleSpin);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        mRecyclerView.addHeaderView(header);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                page = 1;

                new Handler().post(new Runnable() {
                    public void run() {
                        listData.clear();
                        getInternationalNews();
                    }

                });            //refresh data here
            }

            @Override
            public void onLoadMore() {
                page++;
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        getInternationalNews();
                    }
                });
//                if (times < 2) {
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            mRecyclerView.loadMoreComplete();
//                            for (int i = 0; i < 15; i++) {
//                                listData.add("item" + (i + listData.size()));
//                            }
//                            mAdapter.notifyDataSetChanged();
//                            mRecyclerView.refreshComplete();
//                        }
//                    }, 1000);
//                } else {
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//
//                            mAdapter.notifyDataSetChanged();
//                            mRecyclerView.loadMoreComplete();
//                        }
//                    }, 1000);
//                }
//                times++;
//                page++;
            }
        });

        listData = new ArrayList<InternationalNews.NewslistBean>();
        page=1;
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                Log.d("RecyclerLinearFragment", "ThreadInfo:"+Thread.currentThread().getName());
                listData.clear();
                getInternationalNews();
            }
        }.start();
//        new Handler().post(new Runnable() {
//            public void run() {
//
//            }
//
//        });
        mAdapter = new MyAdapter(listData, getActivity());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        //header跟下拉刷新共两项

        Toast.makeText(getActivity(), "position:"+listData.get(position), Toast.LENGTH_SHORT).show();
        getIDInfo();
    }
    public void getNews()
    {
        String url = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
        Map<String, String> headers = new HashMap<>();
        headers.put("apikey", "d7e569060ebb9021e8d8cc654df20505");
        Log.d("RecyclerLinearFragment", "getNews_Page:" + page.toString());
        OkHttpUtils
                .get()
                .url(url)
                .addParams("page",page.toString())
                .headers(headers)
                .build()
                .execute(new MyStringCallback());
    }
    public void getInternationalNews()
    {
        String url = "http://apis.baidu.com/txapi/world/world";
        Map<String, String> headers = new HashMap<>();
        headers.put("apikey", "d7e569060ebb9021e8d8cc654df20505");
        Log.d("RecyclerLinearFragment", "getNews_Page:" + page.toString());
        OkHttpUtils
                .get()
                .url(url)
                .addParams("page", page.toString())
                .addParams("num", "10")
                .headers(headers)
                .build()
                .execute(new MyStringCallback());
    }
    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request)
        {
            super.onBefore(request);
            Log.d("MyStringCallback", "onBefore");
//            setTitle("loading...");
        }

        @Override
        public void onAfter()
        {
            super.onAfter();
            Log.d("MyStringCallback", "onAfter");
//            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e) {
            Log.d("MyStringCallback", "onError");
//            tv.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response) {
            Log.d("MyStringCallback", "onResponse");
            InternationalNews news = gson.fromJson(response, InternationalNews.class);
            String str = "";

            for ( InternationalNews.NewslistBean contentlistBean : news.getNewslist()) {
                listData.add(contentlistBean);
            }
            mAdapter.notifyDataSetChanged();
            mRecyclerView.refreshComplete();
            mRecyclerView.loadMoreComplete();
        }

        @Override
        public void inProgress(float progress)
        {
            Log.e("ChatActivity", "inProgress:" + progress);
//            mProgressBar.setProgress((int) (100 * progress));
        }
    }
    public void getIDInfo()
    {
        String url = "http://apis.baidu.com/netpopo/exchange/currency";
        Map<String, String> headers = new HashMap<>();
        headers.put("apikey", "d7e569060ebb9021e8d8cc654df20505");
//        Log.d("RecyclerLinearFragment", "getNews_Page:" + page.toString());
        OkHttpUtils
                .get()
                .url(url)
//                .addParams("id", "210102196308195612")
//                .addParams("num", "10")
                .headers(headers)
                .build()
                .execute(new MyIDCallback());
    }
    public class MyIDCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request)
        {
            super.onBefore(request);

        }

        @Override
        public void onAfter()
        {
            super.onAfter();

        }

        @Override
        public void onError(Call call, Exception e) {
            Log.d("MyIDCallback", "onError");

        }

        @Override
        public void onResponse(String response) {
            Log.d("MyIDCallback", "onResponse");
            Log.d("MyIDCallback", response);
        }

        @Override
        public void inProgress(float progress)
        {
            Log.e("ChatActivity", "inProgress:" + progress);
//            mProgressBar.setProgress((int) (100 * progress));
        }
    }
}
