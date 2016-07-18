package com.yxb.android.shengseshequ.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.adapter.ShouYeAdapter;
import com.yxb.android.shengseshequ.bean.Banner;
import com.yxb.android.shengseshequ.utils.GsonRequest;
import com.yxb.android.shengseshequ.utils.MySingleton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ShouYeFragment extends Fragment {
    @Bind(R.id.slider)
    SliderLayout slider;
    @Bind(R.id.custom_indicator)
    PagerIndicator customIndicator;
    @Bind(R.id.baoliao)
    LinearLayout baoliao;
    @Bind(R.id.meishi)
    LinearLayout meishi;
    @Bind(R.id.youhuiquan)
    LinearLayout youhuiquan;
    @Bind(R.id.weizhang)
    LinearLayout weizhang;
    @Bind(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView recyclerView;
    @Bind(R.id.SwipeRefreshLayout)
    android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;
    private List<Banner.VariablesBean.PicListBean> pic_List;
    private List<Banner.VariablesBean.HotThreadListBean> hot_thread_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye_fragment, container, false);
        ButterKnife.bind(this, view);
        loadImages();
        loadData();
        initRecyclerView();
        initSwipeRefresh();
        return view;
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
//1.设置进度圈颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
//2.设置进度条位置
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

    }
//创建服务器
//    private void requestImages() {
//        String url = "http://112.124.22.238:8081/course_api/banner/query?type=1";
//        httpHelper.get(url, new SpotsCallBack<List<Banner>>(getContext()) {
//
//            @Override
//            public void onSuccess(Response response, List<Banner> banners) {
//                pic_List = banners;
//                initSlider();
//            }
//
//            @Override
//            public void onError(Response response, int code, Exception e) {
//
//            }
//        });
//    }

    private void loadImages() {
        String url = "http://www.singse.com/api/mobile/?mobile=no&version=5&module=appindex";
        GsonRequest<Banner> gsonRequest = new GsonRequest<Banner>(url, Banner.class,
                new Response.Listener<Banner>() {
                    @Override
                    public void onResponse(Banner response) {
                        List<Banner.VariablesBean.PicListBean> picListBean = response.getVariables().getPic_list();
                        Banner.VariablesBean.PicListBean picBean = new Banner.VariablesBean.PicListBean();
//                        Banner banner = new Banner();
//                        List<Banner> banners = new ArrayList<>();
                        picListBean.add(picBean);
                        pic_List = picListBean;
                        initSlider();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        MySingleton.getInstance(this.getContext()).getRequestQueue().add(gsonRequest);

    }

    private void initSlider() {
        if (pic_List != null) {

            for (Banner.VariablesBean.PicListBean banner : pic_List) {
                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(banner.getImg());
                textSliderView.description(banner.getTitle());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                slider.addSlider(textSliderView);
            }
        }
        //使用自定义的指示器
//        slider.setCustomIndicator(customIndicator);
        //使用默认指示器
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        slider.setDuration(3000);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ShouYeAdapter shouYeAdapter = new ShouYeAdapter(getActivity(), hot_thread_list);
//        recyclerView.setAdapter(shouYeAdapter);

    }

    private void loadData() {
        String url = "http://www.singse.com/api/mobile/?mobile=no&version=5&module=appindex";
        GsonRequest<Banner> gsonRequest = new GsonRequest<Banner>(url, Banner.class,
                new Response.Listener<Banner>() {
                    @Override
                    public void onResponse(Banner response) {
                        hot_thread_list = response.getVariables().getHot_thread_list();
                        Banner.VariablesBean.HotThreadListBean hotThreadListBean = new Banner.VariablesBean.HotThreadListBean();
                        hot_thread_list.add(hotThreadListBean);
                        Log.e("jjjjj", "onCreateView: " + hot_thread_list.size());
//                        List<Banner.VariablesBean.HotThreadListBean> hotListBean = response.getVariables().getHot_thread_list();
////                        hot_thread_list =response.getVariables().getHot_thread_list();
//                        Banner.VariablesBean.HotThreadListBean hotThreadListBean = new Banner.VariablesBean.HotThreadListBean();
//                        hotListBean.add(hotThreadListBean);
//                        hot_thread_list=hotListBean;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        MySingleton.getInstance(this.getContext()).getRequestQueue().add(gsonRequest);
    }

    @OnClick({R.id.baoliao, R.id.meishi, R.id.youhuiquan, R.id.weizhang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.baoliao:
                break;
            case R.id.meishi:
                break;
            case R.id.youhuiquan:
                break;
            case R.id.weizhang:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (slider != null) {
            slider.stopAutoCycle();
        }

    }
}
