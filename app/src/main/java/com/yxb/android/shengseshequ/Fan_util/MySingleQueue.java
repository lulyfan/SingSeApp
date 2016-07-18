package com.yxb.android.shengseshequ.Fan_util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by acer on 2016/7/7.
 */
public class MySingleQueue {
    static private MySingleQueue mySingleQueue;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private MySingleQueue(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            int appMemory = (int) Runtime.getRuntime().maxMemory();
            int lruSize = appMemory/8;
            private final LruCache<String, Bitmap> lruCache = new LruCache(lruSize);
            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url,bitmap);
            }
        });
    }

    public <T> Request<T> addRequest(Request<T> request) {
        return requestQueue.add(request);
    }

    public static MySingleQueue getInstance(Context context) {
        if (mySingleQueue == null)
            mySingleQueue = new MySingleQueue(context);
        return mySingleQueue;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
