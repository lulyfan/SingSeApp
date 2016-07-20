package com.yxb.android.shengseshequ.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.yxb.android.shengseshequ.Fan_util.MySingleQueue;
import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.model.DetailForum;
import com.yxb.android.shengseshequ.model.Forum;
import com.yxb.android.shengseshequ.ui.ForumActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/7/19.
 */
public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<DetailForum.PostBean> list;
    private PostTopAdapter postTopAdapter; //recycleView的头部里的recycleView的adapter
    private static final int HEAD = 0;
    private static final int CONTEND = 1;

    public PostListAdapter(final Context context, List<DetailForum.PostBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<DetailForum.PostBean> list)
    {
        this.list = list;
    }

    //为recycleView的头部里的recycleView的adapter设置数据
    public void setHeadData(List<String> list) {
        if (postTopAdapter != null)
        {

            postTopAdapter.setData(list);
            postTopAdapter.notifyDataSetChanged();
        }
        else
            System.out.println("postTopAdapter == null");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        if (viewType == HEAD)
        {
            view = LayoutInflater.from(context).inflate(R.layout.post_recycle_header, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(view);
            LinearLayoutManager topLinearLayoutManager = new LinearLayoutManager(context);
            topLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            headerViewHolder.rv_top.setLayoutManager(topLinearLayoutManager);
            postTopAdapter = new PostTopAdapter(context, null);
            headerViewHolder.rv_top.setAdapter(postTopAdapter);
            viewHolder = headerViewHolder;
        }
        else
        {
            view = LayoutInflater.from(context).inflate(R.layout.postlist, parent, false);
            ViewHolder holder = new ViewHolder(view);


            viewHolder = holder;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder)
        {
            position = position - 1; //由于有一个头部，调整position
            ViewHolder viewHolder = (ViewHolder) holder;

            viewHolder.headPic.setDefaultImageResId(android.R.drawable.ic_delete);
            viewHolder.headPic.setErrorImageResId(android.R.drawable.stat_notify_error);
            for (int i=0;i<3;i++)
            {
                NetworkImageView imageView = (NetworkImageView) viewHolder.imageContainer.getChildAt(i);
                imageView.setDefaultImageResId(android.R.drawable.ic_delete);
                imageView.setErrorImageResId(android.R.drawable.stat_notify_error);
            }

            DetailForum.PostBean postBean = list.get(position);
            viewHolder.title.setText(postBean.getTitle());
            viewHolder.content.setText(postBean.getContent());
            viewHolder.userName.setText(postBean.getAuthor());
            viewHolder.date.setText(postBean.getDate());
            viewHolder.num_comment.setText(postBean.getNum_commnet());
            viewHolder.num_watch.setText(postBean.getNum_watch());

            MySingleQueue queue = MySingleQueue.getInstance(context.getApplicationContext());
            ImageLoader loader = queue.getImageLoader();
            List<String> list = postBean.getImageUrls();
            if (list != null && list.size() > 0)
            {
                int current = 0;
                while (current < list.size())
                {
                    if (current >= 3)
                        break;
                    NetworkImageView imageView = (NetworkImageView) viewHolder.imageContainer.getChildAt(current);
                    imageView.setVisibility(View.VISIBLE);
                    String url = list.get(current);
                    imageView.setImageUrl(url,loader);
//                    loader.get(url,ImageLoader.getImageListener(imageView,android.R.drawable.ic_delete,android.R.drawable.stat_notify_error));
                    current++;
                }
            }
            String headPicUrl = postBean.getHeadIconUrl();
            if (headPicUrl != null)
                viewHolder.headPic.setImageUrl(headPicUrl, loader);
//                loader.get(headPicUrl,ImageLoader.getImageListener(viewHolder.headPic,android.R.drawable.ic_delete,android.R.drawable.stat_notify_error));
        }
        else if (holder instanceof HeaderViewHolder)
        {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
        }



    }

    @Override
    public int getItemCount() {
        return list == null?1:list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEAD;
        else
            return CONTEND;
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rl_top)
        RelativeLayout relativeLayout;
        @Bind(R.id.arrow)
        ImageView arrow;
        @Bind(R.id.rv_top)
        RecyclerView rv_top;
        @Bind(R.id.tv_cat)
        TextView tv_cat;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.headPic)
        NetworkImageView headPic;
        @Bind(R.id.userName)
        TextView userName;
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.num_comment)
        TextView num_comment;
        @Bind(R.id.num_watch)
        TextView num_watch;
        @Bind(R.id.imageContainer)
        LinearLayout imageContainer;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
