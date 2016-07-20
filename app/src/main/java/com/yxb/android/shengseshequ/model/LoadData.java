package com.yxb.android.shengseshequ.model;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.yxb.android.shengseshequ.Fan_util.MySingleQueue;

/**
 * Created by acer on 2016/7/19.
 */
public class LoadData implements IGetData{
    Context context;
    public LoadData(Context context) {
        this.context = context;
    }
    @Override
    public void loadData(String url, final IListener listener) {
        RequestQueue queue = MySingleQueue.getInstance(context.getApplicationContext()).getRequestQueue();
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (listener != null)
                            listener.onSuccess();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null)
                            listener.onFailed();
                    }
                });
        queue.add(request);
    }

}
