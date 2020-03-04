package top.jsonlu.baselibrary.fetch.biz;

import android.provider.Settings;
import android.support.v4.app.FragmentActivity;

import top.jsonlu.baselibrary.fetch.biz.net.CallData;
import top.jsonlu.baselibrary.fetch.biz.p.BaseParam;
import top.jsonlu.baselibrary.fetch.biz.p.InitCheckOutParam;


/**
 * Author:JsonLu
 * DateTime:2019/3/1 08:46
 * Email:jsonlu@qq.com
 * Desc:初始化业务类
 **/
public class MobileBiz extends CallData {

    public final int GoodsInfo = 0xAA;
    public final int PaymentInfo = 0xAB;
    public final int TakeOrder = 0xAC;
    public final int GetOrder = 0xAD;

    public MobileBiz(FragmentActivity app) {
        super(app);
    }


    public void getData(InitCheckOutParam param) {
        showProgress(app);
        param.setDevice(Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID));
        service.initCheckOut(param).observe(app, result -> common(result));
    }

    public void getExam(BaseParam param) {
        showProgress(app);
        param.setDevice(Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID));
        service.getExam(param).observe(app, result -> common(result));
    }

    public void getTypeClassData(BaseParam param) {
        showProgress(app);
        param.setDevice(Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID));
        service.getTypeClass(param).observe(app, result -> common(result));
    }

    public void getTypeDate(BaseParam param) {
        showProgress(app);
        param.setDevice(Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID));
        service.getTypeDate(param).observe(app, result -> common(result));
    }

    public void getGoodsInfo(BaseParam param) {
        showProgress(app);
        param.setDevice(Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID));
        service.getGoodsInfo(param).observe(app, result -> common(GoodsInfo, result));
    }

    public void getPaymentInfo(BaseParam param) {
        param.setDevice(Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID));
        service.getPaymentInfo(param).observe(app, result -> common(PaymentInfo, result));
    }

    public void takeOrder(BaseParam param) {
        param.setDevice(Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID));
        service.takeOrder(param).observe(app, result -> common(TakeOrder, result));
    }

    public void getOrder(BaseParam param) {
        param.setDevice(Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID));
        service.getOrder(param).observe(app, result -> common(GetOrder, result));
    }

}
