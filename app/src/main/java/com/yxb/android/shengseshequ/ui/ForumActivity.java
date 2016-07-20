package com.yxb.android.shengseshequ.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.yxb.android.shengseshequ.Fan_util.MySingleQueue;
import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.model.DetailForum;
import com.yxb.android.shengseshequ.model.Forum;
import com.yxb.android.shengseshequ.view.PostListAdapter;
import com.yxb.android.shengseshequ.view.PostTopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForumActivity extends AppCompatActivity {
    @Bind(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.title)
    TextView tv_title;
    @Bind(R.id.comment)
    ImageView comment;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
//    @Bind(R.id.rl_top)
//    RelativeLayout rlTop;
//    @Bind(R.id.rv_top)
//    RecyclerView rvTop;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
//    @Bind(R.id.linearLayout)
//    LinearLayout linearLayout;
    PostListAdapter postListAdapter;
//    PostTopAdapter postTopAdapter;
    RequestQueue queue;
    private final String TAG = "POST";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.forum_activity);
        setContentView(R.layout.forum_activity2);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String fid = intent.getStringExtra("fid");
        url = "http://www.singse.com/api/mobile/?mobile=no&version=4&module=forumdisplay&page=1&fid="+fid;

        tv_title.setText(title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);


//        LinearLayoutManager topLinearLayoutManager = new LinearLayoutManager(this);
//        topLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        rvTop.setLayoutManager(topLinearLayoutManager);
//        postTopAdapter = new PostTopAdapter(this, null);
//        rvTop.setAdapter(postTopAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(linearLayoutManager);
        postListAdapter = new PostListAdapter(this, null);
        rvList.setAdapter(postListAdapter);

        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        loadData();
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
//
//    @OnClick(R.id.rl_top)
//    public void onClick() {
//    }

    public boolean loadData() {
        if (queue == null)
            queue = MySingleQueue.getInstance(this.getApplicationContext()).getRequestQueue();
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DetailForum detailForum = gson.fromJson(response, DetailForum.class);
                        List<DetailForum.VariablesBean.ForumThreadlistBean> list = detailForum.getVariables().getForum_threadlist();
                        List<String> subjects = new ArrayList<>();
                        for (int i=0;i<list.size();i++)
                        {
                            String str = list.get(i).getSubject();
                            subjects.add(str);
                        }
                        postListAdapter.setHeadData(subjects);
//                        postTopAdapter.setData(subjects);
//                        postTopAdapter.notifyDataSetChanged();

                        List<DetailForum.PostBean> postBeanlist = DetailForum.PostBean.parseData(response);
                        postListAdapter.setData(postBeanlist);
                        postListAdapter.notifyDataSetChanged();
                        showView();

                        refreshLayout.setRefreshing(false);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForumActivity.this, "load Failed" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                });
        request.setTag(TAG);
        queue.add(request);
        return true;
    }

    public void showView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        queue.cancelAll(TAG);
    }
}
