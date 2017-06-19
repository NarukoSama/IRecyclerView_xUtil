package com.bwie.day0618_oldweekexam_aaa;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSON;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class TwoActivity extends AppCompatActivity  {

    private IRecyclerView recyclerView;

    int page = 1;

//    List<Bean.ResultBean.DataBean> list = new ArrayList<>();

    List<String> list = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;
    private IAdapter iAdapter;
    private LoadMoreFooterView loadMoreFooterView;
    private IIAdapter iiAdapter;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    recyclerView.setRefreshing(false);
                    list.clear();

                    execute(true);

                    break;

                case 2:
                    execute(false);
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);

                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        recyclerView = (IRecyclerView) findViewById(R.id.recycleview_id);

        //        LinearLayoutManager.VERTICAL 表示水平垂直滑动
//                false true     layouts from end to start. else

//

        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

//添加分割线
//        recyclerView.addItemDecoration
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.RED)
//                        .sizeResId(R.dimen.divider)
//                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                        .build());

        // 为 item add remove 时增加动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());


        execute(true);

        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                recyclerView.setRefreshing(true);

                handler.sendEmptyMessageDelayed(1,1000);

            }
        });


   recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {



                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);

                handler.sendEmptyMessageDelayed(2,1000);

            }
        });

    }


    public void setAdapter(final boolean flag) {


        if (flag) {

//            iAdapter = new IAdapter(TwoActivity.this, list);
            iiAdapter = new IIAdapter(this, list);

            recyclerView.setIAdapter(iiAdapter);

        } else {

            iiAdapter.notifyDataSetChanged();

        }


    }

    public void execute(final boolean flag) {


        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }



        setAdapter(flag);


        RequestParams params = new RequestParams("http://japi.juhe.cn/joke/content/list.from?key=94fbc7ec2262160140d71e1418322f34%20&page=" + page + "&pagesize=10&sort=asc&time=1418745237");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Bean bean = JSON.parseObject(result, Bean.class);

//                list.addAll(bean.getResult().getData());


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

}
