package top.jsonlu.app.frg;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.jsonlu.app.R;

/**
 * Author:JsonLu
 * DateTime:2020/1/10 16:57
 * Email:jsonlu@qq.com
 * Desc:
 **/
public abstract class BaseDialogFragment extends DialogFragment {
    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullDialog);
    }

    @Nullable
    protected <T> T gett(@NonNull String key) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Object d = bundle.getSerializable(key);
            if (d != null) {
                return (T) d;
            }
        }
        return null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            getDialog().getWindow().setGravity(Gravity.BOTTOM);
            getDialog().getWindow().setWindowAnimations(R.style.AnimBottom); // 设置窗口弹出动画
            getDialog().setCanceledOnTouchOutside(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRootView = inflater.inflate(getLayout(), container, false);
        initViews(mRootView, savedInstanceState);
        return mRootView;
    }

    protected abstract void initViews(View view, Bundle savedInstanceState);

    protected abstract int getLayout();

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(this, tag).addToBackStack(null);
        return transaction.commitAllowingStateLoss();

    }

    public void show(FragmentManager manager) {
        show(manager, this.getClass().getName());
    }

    protected void addFragment(int id, Fragment fragment) {
        try {
            FragmentManager fragmentmanager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentmanager.beginTransaction();
            fragmentTransaction.replace(id, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (null != getView()) {
            getView().setOnTouchListener((v, event) -> true);
        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
