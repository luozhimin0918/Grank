package com.hhl.flowlayoutdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.hhl.flowlayoutdemo.R;
import com.hhl.flowlayoutdemo.model.CommentListEntity;
import com.hhl.flowlayoutdemo.model.KXTcommentListEntity;
import com.hhl.flowlayoutdemo.utill.TextTypeUtil;
import com.luo.luolib.dp.AppContextApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/22.
 */
public class RecyclePinglunAdapter extends RecyclerView.Adapter<RecyclePinglunAdapter.ViewHolder>{





    // 数据集

    private List<KXTcommentListEntity.DataBean> pinglunList;
    RequestQueue mQueue;
    Context mContext;
    public RecyclePinglunAdapter(List<KXTcommentListEntity.DataBean> pinglunList, Context mContext) {
        super();
        mQueue =  AppContextApplication.getInstance().getmRequestQueue();
        this.pinglunList=pinglunList;
        this.mContext=mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.adapter_activity_invitation_pinglun_item, null);
//        TextTypeUtil.applyFont(mContext, view,"fonts/simsun.ttc");


        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        // 绑定数据到ViewHolder上
        KXTcommentListEntity.DataBean user=pinglunList.get(i);
        String UserimageUrl=user.getThumb();
//        viewHolder.imageTitle.setDefaultImageResId(R.drawable.loading);
//        viewHolder.imageTitle.setErrorImageResId(R.drawable.loading);
//        if(mQueue.getCache().get(UserimageUrl)==null){
//            viewHolder.imageTitle.startAnimation(ImagePagerAdapter.getInAlphaAnimation(2000));
//        }
        viewHolder.imageTitle.setImageUrl(UserimageUrl, AppContextApplication.getInstance().getmImageLoader());



        viewHolder.userName.setText(user.getTitle());
        viewHolder.AddTime.setText(pinglunList.get(i).getAddtime());

        viewHolder.pingContent.setText(pinglunList.get(i).getDescription());


    }







    @Override
    public int getItemCount() {
        return pinglunList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public NetworkImageView imageTitle;
        public TextView userName,AddTime,pingContent;
        public ViewHolder(View itemView) {
            super(itemView);
            imageTitle= (NetworkImageView) itemView.findViewById(R.id.imageTitle);
            userName= (TextView) itemView.findViewById(R.id.userName);
            AddTime= (TextView) itemView.findViewById(R.id.AddTime);
            pingContent= (TextView) itemView.findViewById(R.id.pingContent);
        }
    }
}