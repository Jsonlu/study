package top.jsonlu.baselibrary.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import top.jsonlu.baselibrary.R;


/**
 * Author:JsonLu
 * DateTime:2018/9/13 下午5:27
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class PDialog {

    public static Dialog getDialog(Context context) {
        Dialog dialog = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (null != inflater) {
            View view = inflater.from(context).inflate(R.layout.jp_dialog_loading, null);
            dialog = new Dialog(context, R.style.dialog);
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);// 不可以用“返回键”取消
        }
        return dialog;
    }
}
