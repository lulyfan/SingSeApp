package com.yxb.android.shengseshequ.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yxb.android.shengseshequ.Fan_util.MySingleQueue;
import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.model.Forum;
import com.yxb.android.shengseshequ.view.HotForumAdapter;
import com.yxb.android.shengseshequ.view.MoreForumAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/15.
 */
public class BanKuaiFragment extends Fragment {
    RequestQueue queue;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.num_totalPost)
    TextView num_totalPost;
    @Bind(R.id.num_today)
    TextView num_Today;
    @Bind(R.id.num_member)
    TextView num_member;
    @Bind(R.id.hot)
    RecyclerView hot;
    @Bind(R.id.more)
    LinearLayout moreLayout;
    HotForumAdapter adapter;
    private final String TAG = "forum";
    private final String URL_FORUM = "http://www.singse.com/api/mobile/?mobile=no&version=5&module=forumindex";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bankuai_fragment, container, false);
        ButterKnife.bind(this, view);
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        hot.setLayoutManager(gridLayoutManager);
        adapter = new HotForumAdapter(getActivity(), null);
        hot.setAdapter(adapter);
        loadData();
        return view;
    }

    public boolean loadData() {
        if (queue == null)
            queue = MySingleQueue.getInstance(getActivity().getApplicationContext()).getRequestQueue();
        StringRequest request = new StringRequest(URL_FORUM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Forum forum = gson.fromJson(response, Forum.class);
                        showView(forum);
                        refreshLayout.setRefreshing(false);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "load Failed" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                });
        request.setTag(TAG);
        queue.add(request);
        return true;
    }

    public void showView(Forum forum) {
        Forum.VariablesBean.SummaryBean summaryBean = forum.getVariables().getSummary();
        num_totalPost.setText(summaryBean.getPosts());
        num_Today.setText(summaryBean.getBbsnewposts());
        num_member.setText(summaryBean.getMembers());

        List<Forum.VariablesBean.HotForumListBean> hot_forum_list = forum.getVariables().getHot_forum_list();
        adapter.setData(hot_forum_list);
        adapter.notifyDataSetChanged();

        List<Forum.VariablesBean.CatlistBean> catlist = forum.getVariables().getCatlist();
        List<Forum.VariablesBean.ForumlistBean> totalforumlist = forum.getVariables().getForumlist();
        List<String> totalFids = new ArrayList<>();
        for (Forum.VariablesBean.ForumlistBean bean:totalforumlist)
            totalFids.add(bean.getFid());

        for (int i=0;i<catlist.size();i++)
        {
            String catName = catlist.get(i).getName();
            List<String> fids = catlist.get(i).getForums();
            List<Forum.VariablesBean.ForumlistBean> forumlist = new ArrayList<>();

            for(String fid:fids)
            {
                int index = totalFids.indexOf(fid);
                forumlist.add(totalforumlist.get(index));
            }

            RecyclerView recyclerView = new RecyclerView(getActivity());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(new MoreForumAdapter(getActivity(),forumlist));

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.more_header,null);
            TextView cat = (TextView) view.findViewById(R.id.cat);
            cat.setText(catName);
            moreLayout.addView(view);
            moreLayout.addView(recyclerView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        queue.cancelAll(TAG);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
