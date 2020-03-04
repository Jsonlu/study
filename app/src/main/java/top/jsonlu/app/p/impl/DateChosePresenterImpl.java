package top.jsonlu.app.p.impl;

import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.jsonlu.app.p.IDateChosePresenter;
import top.jsonlu.app.v.IMainView;
import top.jsonlu.baselibrary.bus.LiveDataBus;
import top.jsonlu.baselibrary.fetch.DownloadBiz;
import top.jsonlu.baselibrary.fetch.biz.d.TypeClassData;
import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;
import top.jsonlu.baselibrary.fetch.biz.p.GetBaseParam;
import top.jsonlu.baselibrary.utils.FileUtils;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 13:31
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class DateChosePresenterImpl extends MainPresenterImpl implements IDateChosePresenter {


    final FragmentActivity app;
    private TypeItem item;

    public DateChosePresenterImpl(IMainView view, FragmentActivity app) {
        super(view, app);
        this.app = app;
    }


    @Override
    public void getData() {
        GetBaseParam p = new GetBaseParam();
        p.setId(item.getId() + "");
        biz.getTypeDate(p);
    }

    @Override
    public void route() {
        if (item == null)
            return;
        String url = item.getUrl();
//        view.toBuyTip();
        view.goNext();
//        if (!TextUtils.isEmpty(url)) {
//            if (calculate(item)) {
//                view.goNext();
//            } else {
//                view.toBuyTip();
//            }
//
//        }

    }

    private boolean calculate(TypeItem item) {
        boolean a = false;
        return a;
    }

    public void download() {
        DownloadBiz biz = new DownloadBiz();
        biz.notification(app);
        biz.downloadFile(item.getUrl());
        LiveDataBus.get().with("download", Integer.class).observe(app, i -> view.goNext());
    }

    @Override
    public void getData(int code, Object data) {
        TypeClassData d = (TypeClassData) data;
        List<TypeItem> list = d.getData();
        compare(list);
    }

    private void compare(List<TypeItem> list) {
        List<TypeItem> l = new ArrayList<>(list);
        File outFile = new File(Environment.getExternalStorageDirectory(), "app");
        List<TypeItem> local = FileUtils.getFileList(outFile.getAbsolutePath());
        if (local.size() != 0) {
            l.retainAll(local);
            local.retainAll(list);
            for (TypeItem item : l) {
                item.setBuyed(true);
            }
            l.addAll(local);
        }
        view.loadData(l);
    }

    @Override
    public void setTypeItem(TypeItem item) {
        this.item = item;
    }
}
