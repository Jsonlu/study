package top.jsonlu.app.act;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 17:24
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Ba extends AppCompatActivity {

    final String CODE = "data";

    protected <T extends Serializable> void jump(Class act, T data) {
        Intent intent = new Intent(this, act);
        if (null != data)
            intent.putExtra(CODE, data);
        startActivity(intent);
    }

    @SuppressLint("ResourceType")
    protected void add(int id, Fragment fragment, String backStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(id, fragment);
        if (!TextUtils.isEmpty(backStack)) {
            transaction.addToBackStack(backStack);
        }
        transaction.commit();
    }

    @SuppressLint("ResourceType")
    protected void replace(int id, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }

}
