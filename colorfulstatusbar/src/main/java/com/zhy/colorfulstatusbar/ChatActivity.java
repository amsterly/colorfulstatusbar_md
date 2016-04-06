package com.zhy.colorfulstatusbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.colorfulstatusbar.Adapter.ChatAdapter;
import com.zhy.colorfulstatusbar.Bean.News;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

public class ChatActivity extends Activity implements View.OnClickListener {

    private ThreadLocal<String> local;
    private android.os.Handler handler;
    private Button id_request_btn;
    private TextView tv;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ListView listView = (ListView) findViewById(R.id.id_listView);
        listView.setAdapter(new ChatAdapter(this));
        tv = (TextView) findViewById(R.id.id_txt);
        Log.d("ChatActivity", "onCreate");
        id_request_btn = (Button) findViewById(R.id.id_request_btn);
        id_request_btn.setOnClickListener(this);
        gson = new Gson();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Log.d("ChatActivity", "Changed");
//                tv.setText("UI Changed");
//            }
//        }).start();
    }

    @Override
    protected void onResume() {
        Log.d("ChatActivity", "onResume");
        super.onResume();

    }

    public void getHtml(View view)
    {
        String url = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
        Map<String, String> headers = new HashMap<>();
        headers.put("apikey", "d7e569060ebb9021e8d8cc654df20505");

        OkHttpUtils
                .get()
                .url(url)
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
            setTitle("loading...");
        }

        @Override
        public void onAfter()
        {
            super.onAfter();
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e)
        {
            tv.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response)
        {
            News news=gson.fromJson(response,News.class);
            String str="";
            for (News.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean:news.getShowapi_res_body().getPagebean().getContentlist())
            {
                str+= contentlistBean.getTitle()+"\n";
            }
            tv.setText("onResponse:" + str);
        }

        @Override
        public void inProgress(float progress)
        {
            Log.e("ChatActivity", "inProgress:" + progress);
//            mProgressBar.setProgress((int) (100 * progress))
//            AsyncTask a=new AsyncTask() {
//                @Override
//                protected Object doInBackground(Object[] params) {
//                    return null;
//                }
//            };
//            a.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,)
        }
    }

    @Override
    public void onClick(View v) {
getHtml(v);
    }
}
