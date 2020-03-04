package top.jsonlu.app.p.impl;

import android.support.v4.app.FragmentActivity;

import top.jsonlu.app.BuildConfig;
import top.jsonlu.app.p.ISplashPresent;
import top.jsonlu.app.v.ISplashView;
import top.jsonlu.baselibrary.fetch.biz.MobileBiz;
import top.jsonlu.baselibrary.fetch.biz.d.InitCheckOutData;
import top.jsonlu.baselibrary.fetch.biz.net.Call;
import top.jsonlu.baselibrary.fetch.biz.p.InitCheckOutParam;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 17:52
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class SplashPresentImpl extends CountTimePresentImpl implements ISplashPresent, Call {

    MobileBiz biz;
    ISplashView view;

    String downloadUrl;

    public SplashPresentImpl(ISplashView view, FragmentActivity app) {
        super(view, app);
        this.view = view;
        biz = new MobileBiz(app);
    }

    @Override
    public void getWelcome() {
        biz.setCall(this);
        InitCheckOutParam p = new InitCheckOutParam();
        p.setVersionCode(BuildConfig.VERSION_CODE);
        p.setVersionName(BuildConfig.VERSION_NAME);
        biz.getData(p);
    }

    @Override
    public void downloadApk() {

    }

    @Override
    public void getData(int code, Object data) {
        InitCheckOutData d = (InitCheckOutData) data;
        int type = d.getType();
        view.loadPic(d.getPicUrl(), d.getWebUrl(), type != 0);
        if (type != 0) {
            view.showUpdate(type, d.getDesc());
            downloadUrl = d.getApkUrl();
        }
    }
}
