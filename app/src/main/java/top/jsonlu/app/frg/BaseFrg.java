package top.jsonlu.app.frg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import top.jsonlu.app.R;
import top.jsonlu.app.act.MainActivity;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 14:25
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class BaseFrg extends Fragment {

    protected void setTitle(String title) {
        if (getActivity() instanceof MainActivity) {
            TextView tv = getActivity().findViewById(R.id.title);
            if (tv != null) {
                tv.setText(title);
            }
        }
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (null != getView()) {
            getView().setOnTouchListener((v, event) -> true);
        }

    }

    protected void add(int id, Fragment fragment, String backStack) {
        FrgUtils.add(id, fragment, backStack, this);
    }

    protected void add(int id, Fragment fragment) {
        FrgUtils.add(id, fragment, "default", this);
    }

    protected void replace(int id, Fragment fragment, String backStack) {
        FrgUtils.replace(id, fragment, backStack, this);
    }


    protected void back(Class<? extends BaseFrg> fragment) {
        FrgUtils.back(fragment, this);
    }

}
