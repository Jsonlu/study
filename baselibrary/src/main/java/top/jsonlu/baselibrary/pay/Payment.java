package top.jsonlu.baselibrary.pay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * Author:JsonLu
 * DateTime:2020/1/10 16:45
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Payment {


    /**
     * 支付宝支付业务示例
     */
    public void payV2(String orderInfo, Activity activity, final int SDK_PAY_FLAG, final Handler mHandler) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(activity);
            Map<String, String> result = alipay.payV2(orderInfo, true);
            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
