package com.yxb.android.shengseshequ.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.yxb.android.shengseshequ.Fan_util.MySingleQueue;
import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.model.Forum;
import com.yxb.android.shengseshequ.ui.ForumActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/7/17.
 */
public class HotForumAdapter extends RecyclerView.Adapter<HotForumAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Forum.VariablesBean.HotForumListBean> list;
    private OnItemClikListener onItemClikListener;

    public HotForumAdapter(final Context context, final List<Forum.VariablesBean.HotForumListBean> list) {
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
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);

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

    @Override
    public void onClick(View v) {
        if (onItemClikListener == null)
        {
            onItemClikListener = new OnItemClikListener() {
                @Override
                public void onItemClik(View view, int posizition) {
                    String fid = list.get(posizition).getFid();
                    TextView textView = (TextView) view.findViewById(R.id.forumName);
                    String title = textView.getText().toString();
                    Intent intent = new Intent(context, ForumActivity.class);
                    intent.putExtra("title",title);
                    intent.putExtra("fid",fid);
                    System.out.println("startActivity");
                    context.startActivity(intent);
                }
            };
        }
        int posizition = (int)(v.getTag());
        onItemClikListener.onItemClik(v, posizition);
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

    public void setOnItemClikListener(OnItemClikListener listener) {
        this.onItemClikListener = listener;
    }

    interface OnItemClikListener {
        void onItemClik(View view, int posizition);
    }
}
