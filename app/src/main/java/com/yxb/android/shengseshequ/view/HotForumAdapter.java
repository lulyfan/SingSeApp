package com.yxb.android.shengseshequ.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.yxb.android.shengseshequ.Fan_util.MySingleQueue;
import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.model.Forum;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/7/17.
 */
public class HotForumAdapter extends RecyclerView.Adapter<HotForumAdapter.ViewHolder> {

    Context context;
    List<Forum.VariablesBean.HotForumListBean> list;

    public HotForumAdapter(Context context, List<Forum.VariablesBean.HotForumListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<Forum.VariablesBean.HotForumListBean> list)
    {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_forum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forum.VariablesBean.HotForumListBean hotForumListBean = list.get(position);
        holder.forumName.setText(hotForumListBean.getTitle());
        holder.postNum.setText(hotForumListBean.getPosts());

        MySingleQueue queue = MySingleQueue.getInstance(context.getApplicationContext());
        ImageLoader loader = queue.getImageLoader();
        loader.get(list.get(position).getIcon(),ImageLoader.getImageListener(holder.forumIcon,android.R.drawable.ic_delete,android.R.drawable.stat_notify_error));
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView)
        ImageView forumIcon;
        @Bind(R.id.forumName)
        TextView forumName;
        @Bind(R.id.postNum)
        TextView postNum;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
