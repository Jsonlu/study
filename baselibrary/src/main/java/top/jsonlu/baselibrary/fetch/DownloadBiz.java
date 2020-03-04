package top.jsonlu.baselibrary.fetch;

import android.os.Environment;
import android.support.v4.app.FragmentActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.jsonlu.baselibrary.bus.LiveDataBus;
import top.jsonlu.baselibrary.utils.FileUtils;
import top.jsonlu.baselibrary.utils.NotificationUtil;

/**
 * Author:JsonLu
 * DateTime:2020/1/9 14:31
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class DownloadBiz {

    NotificationUtil util;

    public void notification(FragmentActivity context) {
        if (util == null) {
            util = new NotificationUtil(context);
        }
        util.showNotification();
        LiveDataBus.get().with("download", Integer.class).observe(context, i -> {
            if (i == -1) {
                util.cancel();
            } else if (i == 100) {
                util.cancel();
            } else {
                util.updateProgress(i);
            }
        });
    }

    public void downloadFile(final String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Connection", "close")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                LiveDataBus.get().with("download").postValue(-1);
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = Constant.localSavePath;
                if (!FileUtils.isFolderExists(savePath)) {
                    FileUtils.makeDirectory(savePath);
                }
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath, url.substring(url.lastIndexOf("/") + 1));
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        LiveDataBus.get().with("download").postValue(progress);
                    }
                    fos.flush();
                } catch (Exception e) {
                    LiveDataBus.get().with("download").postValue(-1);
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }


}
