package com.yxb.android.shengseshequ.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.model.Forum;
import com.yxb.android.shengseshequ.ui.ForumActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/7/17.
 */
public class MoreForumAdapter extends RecyclerView.Adapter<MoreForumAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    List<Forum.VariablesBean.ForumlistBean> list;
    private OnItemClikListener onItemClikListener;

    public MoreForumAdapter(final Context context, List<Forum.VariablesBean.ForumlistBean> list) {
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
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        Forum.VariablesBean.ForumlistBean forumlistBean = list.get(position);
        holder.forumName.setText(forumlistBean.getName());
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
        @Bind(R.id.forumName)
        TextView forumName;
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
