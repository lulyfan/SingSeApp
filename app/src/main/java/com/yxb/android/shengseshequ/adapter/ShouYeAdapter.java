package com.yxb.android.shengseshequ.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.bean.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 */
public class ShouYeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    private LayoutInflater mInflater;
    private List<Banner.VariablesBean.HotThreadListBean> mDatas;
    private static final int TYPE_ITEM = 0;     //普通Item View
    private static final int TYPE_FOOTER = 1;   //底部FootView
    Context context;
    public ShouYeAdapter(Context context, List<Banner.VariablesBean.HotThreadListBean> mbanners) {
        this.context = context;
        this.mDatas = mbanners;
    }

    public void addMoreItem(List<Banner.VariablesBean.HotThreadListBean> newDatas) {
        mDatas.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    //定义显示数据的hodler类
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_username;
        public TextView tv_time;
        public TextView tv_pingl;
        public TextView tv_reader;
        public TextView tv_summary;
        public TextView tv_title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_pingl = (TextView) itemView.findViewById(R.id.tv_pingl);
            tv_reader = (TextView) itemView.findViewById(R.id.tv_reader);
            tv_summary = (TextView) itemView.findViewById(R.id.tv_summary);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    //定义带进度hodler类
    public static class FootViewHolder extends RecyclerView.ViewHolder {
        public TextView foot_view_item_tv;
        public FootViewHolder(View itemView) {
            super(itemView);
            foot_view_item_tv = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //根据viewType来判断加载数据item,还是进度item
//        if (viewType == TYPE_ITEM) { //数据item
            View view = mInflater.inflate(R.layout.cardview_shouye, parent, false);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            return itemViewHolder;
//        } else if (viewType == TYPE_FOOTER) {//进度item
//            View foot_view = mInflater.inflate(R.layout.recycler_load_more_layout, parent, false);
//            FootViewHolder footViewHolder = new FootViewHolder(foot_view);
//            return footViewHolder;
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tv_username.setText(mDatas.get(position).getAuthor());
            ((ItemViewHolder) holder).tv_time.setText(mDatas.get(position).getDateline());
            ((ItemViewHolder) holder).tv_pingl.setText(mDatas.get(position).getReplies());
            ((ItemViewHolder) holder).tv_reader.setText(mDatas.get(position).getViews());
            ((ItemViewHolder) holder).tv_summary.setText(mDatas.get(position).getSummary());
            ((ItemViewHolder) holder).tv_title.setText(mDatas.get(position).getTitle());
            holder.itemView.setTag(position);//为当前item做个tag标记
//        } else if (holder instanceof FootViewHolder) {
//            FootViewHolder footViewHolder = (FootViewHolder) holder;
//            switch (load_more_status) {
//                case PULLUP_LOAD_MORE:
//                    footViewHolder.foot_view_item_tv.setText("上拉加载更多...");
//                    break;
//                case LOADING_MORE:
//                    footViewHolder.foot_view_item_tv.setText("正在加载...");
//                    break;
//            }
//        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();//数据item+进度item总和
    }

    public void addItem(List<Banner.VariablesBean.HotThreadListBean> newDatas) {
        newDatas.addAll(mDatas);
        mDatas.removeAll(mDatas);
        mDatas.addAll(newDatas);
        notifyDataSetChanged();
    }

    @Override //根据滑动的item的行索引来决定使用数据tiem/进度item
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
}
