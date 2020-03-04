package top.jsonlu.baselibrary.ui;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Author:JsonLu
 * DateTime:2018/9/8 上午9:51
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Toast {
    /**
     * Show the sToast for a long period of time.
     *
     * @param resId The resource id for text.
     */
    public static void showLong(Context context, @StringRes final int resId) {
        if (context == null)
            return;
        ToastCustom toastCustom = ToastCustom.makeText(context, context.getString(resId), android.widget.Toast.LENGTH_LONG);
        toastCustom.show();
    }
    public static void showShort(Context context, @StringRes final int resId) {
        if (context == null)
            return;
        ToastCustom toastCustom = ToastCustom.makeText(context, context.getString(resId), android.widget.Toast.LENGTH_SHORT);
        toastCustom.show();
    }

    public static void showLong(Context context, String msg) {
        if (context == null)
            return;
        ToastCustom toastCustom = ToastCustom.makeText(context, msg, android.widget.Toast.LENGTH_LONG);
        toastCustom.show();
    }


}
