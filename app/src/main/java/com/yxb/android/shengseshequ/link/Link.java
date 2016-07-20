package com.yxb.android.shengseshequ.link;

import com.yxb.android.shengseshequ.model.IGetData;
import com.yxb.android.shengseshequ.model.LoadData;
import com.yxb.android.shengseshequ.view.IView;

/**
 * Created by acer on 2016/7/19.
 */
public class Link {
    IView iView;
    IGetData iGetData;

    public Link(IView iView) {
        this.iView = iView;
        iGetData = new LoadData(iView.getContext());
    }

    public void load() {

    }
}
