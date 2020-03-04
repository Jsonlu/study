package top.jsonlu.baselibrary.fetch.biz.net;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.bumptech.glide.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import top.jsonlu.baselibrary.R;
import top.jsonlu.baselibrary.bus.LiveDataBus;
import top.jsonlu.baselibrary.fetch.Constant;
import top.jsonlu.baselibrary.fetch.adapter.FastJsonConverterFactory;
import top.jsonlu.baselibrary.fetch.adapter.LiveDataCallAdapterFactory;
import top.jsonlu.baselibrary.fetch.biz.Code;
import top.jsonlu.baselibrary.fetch.biz.d.BaseData;
import top.jsonlu.baselibrary.ui.DialogUtils;
import top.jsonlu.baselibrary.ui.PDialog;
import top.jsonlu.baselibrary.ui.Toast;

/**
 * Author:JsonLu
 * DateTime:2019/2/28 14:57
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class CallData implements Interceptor {

    public int DEFAULT_TIMEOUT_MS = 8;

    protected Dialog dialog;

    protected FragmentActivity app;

    protected Call call;

    public void setCall(Call call) {
        this.call = call;
    }

    public CallData(FragmentActivity app) {
        this.app = app;
    }



    protected final Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(FastJsonConverterFactory.create())
            .baseUrl(Constant.Host)
            .client(getClient())
            .build();

    protected OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(this)
                .connectTimeout(DEFAULT_TIMEOUT_MS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT_MS, TimeUnit.SECONDS)
                .build();
        return client;
    }

    final CacheControl cacheControl = new CacheControl.Builder().maxAge(10,TimeUnit.SECONDS).build();


    protected final SuperService service = retrofit.create(SuperService.class);

    protected void showProgress(Context context) {
        if (dialog == null) {
            dialog = PDialog.getDialog(context);
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog.show();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .addHeader("Client-Type", "1")
                .cacheControl(cacheControl)
                .addHeader("TimeStamp", String.valueOf(System.currentTimeMillis()));
        String userAgent = System.getProperty("http.agent");
        if (!TextUtils.isEmpty(userAgent)) {
            builder.addHeader("User-Agent", userAgent + "/" + BuildConfig.VERSION_NAME);
        }
        return chain.proceed(builder.build());
    }


    /**
     * 统一业务处理
     *
     * @param code
     */
    protected void error(int code, @Nullable BaseData result) {
        if (result != null) {
            if (Code.ErrorAuth.equals(result.getCode())) {
                LiveDataBus.get().with(Constant.LV_Auth).postValue(0x01);
            } else {
                String msg = result.getMsg();
                if (code < 0xFF)
                    Toast.showLong(app, msg);
                else
                    DialogUtils.showDialog(app, msg, null);
            }
        } else {
            Toast.showLong(app, app.getString(R.string.ew_t_112));
        }
    }

    protected void common(BaseData result) {
        common(0x1, result);
    }

    /**
     * @param code   请求回调识别，适用于多个回调
     * @param result
     */
    protected void common(int code, BaseData result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (result != null && Code.OK.equals(result.getCode())) {
            if (call != null) {
                call.getData(code, result);
            }
        } else {
            //提示错误信息
            error(code, result);
        }
    }


}
