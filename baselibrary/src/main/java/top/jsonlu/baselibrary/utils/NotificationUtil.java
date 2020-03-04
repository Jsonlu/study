package top.jsonlu.baselibrary.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import top.jsonlu.baselibrary.R;

/**
 * Author:JsonLu
 * DateTime:2020/1/9 15:11
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class NotificationUtil {
    private Context mContext;
    private Dialog dialog;

    public NotificationUtil(Context context) {
        this.mContext = context;
    }

    public void showNotification() {
        if (dialog == null) {
            dialog = getDialog(mContext);
            dialog.show();
        }
    }

    /**
     * 取消通知操作
     *
     * @description：
     * @author ldm
     * @date 2016-5-3 上午9:32:47
     */
    public void cancel() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void updateProgress(int progress) {
        if (dialog != null) {
            ProgressBar bar = dialog.findViewById(R.id.pb_g);
            TextView tv = dialog.findViewById(R.id.tv_progress);
            bar.setProgress(progress);
            tv.setText(progress + "%");
        }
    }

    public Dialog getDialog(Context context) {
        Dialog dialog = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (null != inflater) {
            View view = inflater.from(context).inflate(R.layout.progress, null);
            dialog = new Dialog(context, R.style.dialog);
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);// 不可以用“返回键”取消
        }
        return dialog;
    }
}