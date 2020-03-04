package top.jsonlu.app.p.impl;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import top.jsonlu.app.p.IMainPresenter;
import top.jsonlu.app.v.IMainView;
import top.jsonlu.baselibrary.fetch.biz.MobileBiz;
import top.jsonlu.baselibrary.fetch.biz.d.TypeClassData;
import top.jsonlu.baselibrary.fetch.biz.net.Call;
import top.jsonlu.baselibrary.fetch.biz.p.BaseParam;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 13:31
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class MainPresenterImpl implements IMainPresenter, Call {

    protected IMainView view;
    protected MobileBiz biz;

    public MainPresenterImpl(IMainView view, FragmentActivity app) {
        biz = new MobileBiz(app);
        biz.setCall(this);
        this.view = view;
    }

    @Override
    public void getData() {
        biz.getTypeClassData(new BaseParam());
    }

    @Override
    public void route() {
        if (false) {
            view.toBuyTip();
        } else {
            view.goNext();
        }
    }

    @Override
    public void getData(int code, Object data) {
        TypeClassData d = (TypeClassData) data;
        view.loadData(d.getData());
    }
}
