package top.jsonlu.baselibrary.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

import top.jsonlu.baselibrary.R;

public class DialogUtils {

    public static void showDialog(final Context context, String str, DialogInterface.OnClickListener listener) {
        if (null == context) return;
        if (null == str) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.appalertdialog);
        builder.setMessage(str);
        builder.setPositiveButton(context.getResources().getString(R.string.ew_c_6), listener);

        if (context instanceof Activity) {
            if (!((Activity) context).isFinishing()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!((Activity) context).isDestroyed()) {
                        builder.setCancelable(false);// 不可以用“返回键”取消
                        builder.show();
                    }
                } else {
                    builder.setCancelable(false);// 不可以用“返回键”取消
                    builder.show();
                }
            }
        } else {
            builder.setCancelable(false);// 不可以用“返回键”取消
            builder.show();
        }
    }

    public static void showDialog(final Context context, DialogInterface.OnClickListener listener, int... ids) {
        if (null == context) return;

        if (ids == null || ids.length < 2) {
            return;
        }
        String title = context.getString(ids[0]);
        String left = context.getString(ids[1]);
        String right = context.getString(ids[2]);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.appalertdialog);
        builder.setMessage(title);
        builder.setPositiveButton(right, listener);
        builder.setNegativeButton(left, listener);

        if (context instanceof Activity) {
            if (!((Activity) context).isFinishing()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!((Activity) context).isDestroyed()) {
                        builder.setCancelable(false);// 不可以用“返回键”取消
                        builder.show();
                    }
                } else {
                    builder.setCancelable(false);// 不可以用“返回键”取消
                    builder.show();
                }
            }
        } else {
            builder.setCancelable(false);// 不可以用“返回键”取消
            builder.show();
        }
    }

}