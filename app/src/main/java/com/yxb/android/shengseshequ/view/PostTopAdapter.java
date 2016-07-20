package com.yxb.android.shengseshequ.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.model.DetailForum;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/7/19.
 */
public class PostTopAdapter extends RecyclerView.Adapter<PostTopAdapter.ViewHolder>{
    private Context context;
    private List<String> list;
    private int showItemCount = 2;

    public PostTopAdapter(final Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<String> list)
    {
        this.list = list;
    }

    public void setShowItemCount(int showItemCount) {
        this.showItemCount = showItemCount;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_top, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String content = list.get(position);
        holder.content.setText(content);
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        else if (list.size() <= 2)
            return list.size();
        else
        {
            if (list.size() >= showItemCount)
                return showItemCount;
            else
                return list.size();
        }
//        return list == null?0:list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.content)
        TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
