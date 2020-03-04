package top.jsonlu.baselibrary.fetch;

import android.os.Environment;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 16:20
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface Constant {

    String LV_Auth = "";
//    String Host = "http://192.168.2.1:8080";
    String Host = "https://app.jsonlu.top";
    String BaseUrl = Host + "/app/api";
    String Static = Host + "/app/static";

    String localSavePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/app";
}
