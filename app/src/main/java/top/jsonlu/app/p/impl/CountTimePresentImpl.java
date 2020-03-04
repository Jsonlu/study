package top.jsonlu.app.p.impl;

import android.arch.lifecycle.MutableLiveData;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;

import top.jsonlu.app.p.ICountTimePresent;
import top.jsonlu.app.v.ICountTimeView;


/**
 * Author:JsonLu
 * DateTime:2019/5/22 15:41
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class CountTimePresentImpl implements ICountTimePresent {

    private ICountTimeView view;
    private CountDownTimer timer;

    private MutableLiveData<Integer> liveData = new MutableLiveData<>();

    public CountTimePresentImpl(ICountTimeView view, FragmentActivity app) {
        this.view = view;
        liveData.observe(app, integer -> {
            if (0x1 == integer) {
                view.onFinish();
            } else if (0x2 == integer) {
                if (timer != null)
                    timer.cancel();
                timer = null;
            }
        });
    }

    @Override
    public void close() {
        liveData.setValue(0x2);
    }

    @Override
    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void start(int l, int s) {
        if (timer == null) {
            timer = new CountDownTimer(l * 1000, s * 1000) {
                @Override
                public void onTick(long l1) {
                    l1 = l1 / 1000L;
                    view.setTime(l1 / 60, l1 % 60);
                }

                @Override
                public void onFinish() {
                    liveData.setValue(0x1);
                }
            };
        }
        timer.start();
    }
}
