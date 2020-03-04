package com.jsonlu.plug;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author:JsonLu
 * DateTime:2019/12/26 13:25
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class UData {

    public void post(File file, String _api_key, String buildInstallType, String buildPassword) {

        try {
            OkHttpClient client = new OkHttpClient.Builder().build();
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);//将file转换成RequestBody文件
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("_api_key", _api_key)
                    .addFormDataPart("buildPassword", buildPassword)
                    .addFormDataPart("buildInstallType", buildInstallType)
                    .addFormDataPart("file", file.getName(), fileBody)
                    .setType(MultipartBody.FORM)
                    .build();

            Request request = new Request.Builder()
                    .url("http://www.pgyer.com/apiv2/app/upload")
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
