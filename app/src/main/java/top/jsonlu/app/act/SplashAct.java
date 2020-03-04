package top.jsonlu.app.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import top.jsonlu.app.R;
import top.jsonlu.app.p.ISplashPresent;
import top.jsonlu.app.p.impl.SplashPresentImpl;
import top.jsonlu.app.v.ISplashView;
import top.jsonlu.baselibrary.ui.DialogUtils;
import top.jsonlu.baselibrary.ui.GlideUtils;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 17:23
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class SplashAct extends Ba implements ISplashView {

    private ImageView iv_splash_pic;
    private TextView tv_count;
    private RelativeLayout rl_splash_view;
    private ISplashPresent presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        initView();
        presenter = new SplashPresentImpl(this, this);
        setAnimation(1200);
        presenter.getWelcome();
    }

    private void initView() {
        iv_splash_pic = findViewById(R.id.iv_splash_pic);
        tv_count = findViewById(R.id.tv_count);
        rl_splash_view = findViewById(R.id.rl_splash_view);
    }

    private void setAnimation(int time) {
        View imageView = findViewById(R.id.iv_splash_logo);
        AlphaAnimation b = new AlphaAnimation(0.2f, 1);
        b.setDuration(time);
        b.setAnimationListener(this);
        imageView.startAnimation(b);
    }

    @Override
    public void loadPic(String url, final String jumpUrl, boolean needWait) {
        GlideUtils utils = new GlideUtils() {
            @Override
            protected void call(int type) {
                if (type == 0x1) {
                    rl_splash_view.setVisibility(View.VISIBLE);
                    presenter.start(5, 1);
                    if (needWait) {
                        presenter.cancel();
                    }
                    if (!TextUtils.isEmpty(jumpUrl)) {
                        iv_splash_pic.setOnClickListener(v -> {
                            jump(WebAct.class, jumpUrl);
                        });
                    }
                } else {
                    goNext();
                }
            }
        };
        utils.load(this, iv_splash_pic, url);
    }

    @Override
    public void setTime(long minute, long second) {
        tv_count.setText(second + " s");
    }

    @Override
    public void onFinish() {
        goNext();
    }


    @Override
    public void goNext() {
        jump(MainActivity.class, null);
        finish();
    }

    @Override
    public void showUpdate(int type, String msg) {
        if (type == 2) {
            DialogUtils.showDialog(this, msg, (w, l) -> {
                presenter.downloadApk();
            });
        } else {
            DialogUtils.showDialog(this, msg, null);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
//        goNext();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
