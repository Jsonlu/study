package top.jsonlu.app.v;

import android.view.animation.Animation;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 17:32
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface ISplashView extends Animation.AnimationListener, ICountTimeView {

    void loadPic(String url, String jumpUrl,boolean needWait);

    void goNext();

    void showUpdate(int type, String msg);

}
