package com.yxb.android.shengseshequ.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.model.Forum;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/7/17.
 */
public class MoreForumAdapter extends RecyclerView.Adapter<MoreForumAdapter.ViewHolder>{

    Context context;
    List<Forum.VariablesBean.ForumlistBean> list;

    public MoreForumAdapter(Context context, List<Forum.VariablesBean.ForumlistBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<Forum.VariablesBean.ForumlistBean> list)
    {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.more_forum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forum.VariablesBean.ForumlistBean forumlistBean = list.get(position);
        holder.forumName.setText(forumlistBean.getName());
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.forumName)
        TextView forumName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
