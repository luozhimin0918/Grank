package com.hhl.flowlayoutdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hhl.flowlayoutdemo.R;
import com.hhl.flowlayoutdemo.adapter.RecyclePinglunAdapter;
import com.hhl.flowlayoutdemo.model.CommentListEntity;
import com.hhl.flowlayoutdemo.model.KXTcommentListEntity;
import com.hhl.flowlayoutdemo.model.PaginationJsonEntity;
import com.hhl.flowlayoutdemo.utill.TextTypeUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.luo.luolib.base.BaseBestActivity;
import com.luo.luolib.dp.FastJsonRequest;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luozhimin on 2016/6/1.17:33
 */
public class MyActivity extends BaseBestActivity {


    String proId;
    RecyclePinglunAdapter mAdapter;
    @Override
    public void dosetPromo() {
        addViewLay();//添加布局

        TextTypeUtil.applyFont(mContext, getWindow().getDecorView(),"fonts/simsun.ttc");


        Intent intent = getIntent();
        if (intent != null) {
            proId = "22765";


        }
    }

    @Override
    public void initDataAbs() {
        centerTitle.setText("选择支付配送方式");
        initData(proId);
    }

    View view;
    private void addViewLay() {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_layout_quedin, null);
        Ba________addViewToallLinear(view);
//        Ba________addViewXuanFuLinear(view);
    }

    List<KXTcommentListEntity.DataBean> promotePostDateList = new ArrayList<KXTcommentListEntity.DataBean>();//本类帖子 分类里所有数据
    public int page = 1;
    int loadingTag = 2;//刷新flag   2 默认   1 下拉刷新  -1是上拉更多

    private void initData(final String id) {
        String url = "http://appapi.kxt.com/data/dianping?num=10";//

        Map<String, String> map = new HashMap<String, String>();


//        String sessStr = "";
//        if (SharedPreUtil.isLogin()) {
//            String sessionString = SharedPreferences.getInstance().getString("session", "");
//            if (sessionString != null && !sessionString.equals("")) {
//                sessStr = ",\"session\":" + sessionString;
//            }
//        }

        if (loadingTag == -1) {
            map = new HashMap<String, String>();
            PaginationJsonEntity paginationJson = new PaginationJsonEntity();
            paginationJson.setCount("10");
            paginationJson.setPage((++page) + "");
            String string = JSON.toJSONString(paginationJson);
            String d = "{\"id\":\"" + id + "\",\"pagination\":" + string + " ,\"type\":\"2\"" + "}";
            map.put("json", d);
           KLog.d( d + "》》》》");
        }
        if (loadingTag == 2 || loadingTag == 1) {//第一次加载数据
            map = new HashMap<String, String>();
            String oneString = "{\"type\":\"2\",\"id\":\"" + id + "\"" + "}";
            map.put("json", oneString);
           KLog.d( oneString + "》》》》");
        }


        FastJsonRequest<KXTcommentListEntity> fastJsonCommunity = new FastJsonRequest<KXTcommentListEntity>(Request.Method.GET, url, KXTcommentListEntity.class, null, new Response.Listener<KXTcommentListEntity>() {
            @Override
            public void onResponse(KXTcommentListEntity commentListEntity) {

                if (commentListEntity.getRet() == 0) {


                    Ba________progresNetworkGone();


                    if (loadingTag == -1) {


                        List<KXTcommentListEntity.DataBean> p = commentListEntity.getData();
                       KLog.d("" + promotePostDateList.size() + "1111++++promotePostDateList");
                        for (int i = 0; i < p.size(); i++) {
                            promotePostDateList.add(p.get(i));
                        }
                       KLog.d( "" + promotePostDateList.size() + "2222++++promotePostDateList");


                        Ba________recycyeNetworkCompetleMore();
                    }


                    if (loadingTag == 1 || loadingTag == 2) {
                        promotePostDateList.clear();
                        promotePostDateList.addAll(commentListEntity.getData());

                        if (promotePostDateList != null && promotePostDateList.size() > 0) {

                            if (loadingTag == 2) {
                                intData();//初始界面
                            }
                            if (loadingTag == 1) {
                                MyRecycleNotifi();
                            }


                           KLog.d( "2lslsls");
                        } else {
                            Ba________nullNetworkData("暂无评论");
                        }

                    }


                } else {


                    Ba________shibaiNetworkData();
                    // 请求失败
                   KLog.d( ""  + "++++success=0》》》》");

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {


                Ba________weizhiErrorNetworkData();
               KLog.d( "errror" + volleyError.toString() + "++++》》》》");
            }
        });

        fastJsonCommunity.setParams(map);

        mQueue.add(fastJsonCommunity);

    }


    public void MyRecycleNotifi() {
        mAdapter.notifyDataSetChanged();//TODO
        Ba________recycyeNetworkCompetle();
    }


    private void intData() {


        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
//        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);


        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadingTag = 1;//重新加载
                page = 1;
                initData(proId);
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//
//                        mRecyclerView.refreshComplete();
//
//
//                    }
//
//                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {

//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
                loadingTag = -1;
                KLog.d("initial    more");
                initData(proId);
//                        mRecyclerView.loadMoreComplete();
//                    }
//                }, 2000);

            }
        });


        if (promotePostDateList != null && promotePostDateList.size() > 0) {
            mAdapter = new RecyclePinglunAdapter(promotePostDateList, mContext);

            mRecyclerView.setAdapter(mAdapter);
        }

    }


}
