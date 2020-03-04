package top.jsonlu.app.frg;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import java.util.List;

/**
 * Author:JsonLu
 * DateTime:2019/12/11 11:44
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class FrgUtils {

    public static void add(int id, Fragment fragment, String backStack, Fragment container) {
        FragmentManager mFragmentManager = container.getFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(id, fragment);
        if (!TextUtils.isEmpty(backStack)) {
            transaction.addToBackStack(backStack);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    public static void replace(int id, Fragment fragment, String backStack, Fragment container) {
        FragmentManager mFragmentManager = container.getFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        if (!TextUtils.isEmpty(backStack)) {
            transaction.addToBackStack(backStack);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    public static void back(Class<? extends BaseFrg> fragment, Fragment container) {
        FragmentManager mFragmentManager = container.getFragmentManager();
        List<Fragment> list = mFragmentManager.getFragments();
        if (list != null) {
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i).getClass().equals(fragment)) {
                    break;
                } else {
                    mFragmentManager.popBackStack();
                }
            }
        }
    }
}
