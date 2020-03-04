package com.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.ByteBuffer;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author:JsonLu
 * DateTime:2019/12/26 10:14
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class UploadTest {

    public static void main(String... args) throws Exception {
        File file = new File("/Users/jsonlu/WorkSpace/android-cashier-sdk/logtest/build/outputs/apk/debug/logtest-debug.apk");
        UploadTest t = new UploadTest();
        byte[] d = t.getData(file);
        t.post(d);
//        t.ok();

    }

    void ok() {
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

        try {
            OkHttpClient client = new OkHttpClient.Builder().proxy(proxy).build();

            /**
             * 上传文件格式
             */
            File file = new File("/Users/jsonlu/WorkSpace/android-cashier-sdk/logtest/build/outputs/apk/debug/logtest-debug.apk");
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);//将file转换成RequestBody文件
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("_api_key", "4e8893b68de2f3a070c5bcec0a7695cb")
                    .addFormDataPart("buildPassword", "jpjpjp")
                    .addFormDataPart("buildInstallType", "2")
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


    final String mimeBoundary = "asdf$$";

    void post(byte[] bodyStr) {
        try {
            URL url = new URL("http://www.pgyer.com/apiv2/app/upload");
            InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5 * 60 * 1000);
            connection.setReadTimeout(5 * 60 * 1000);
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");

            connection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + mimeBoundary);
            //若需要向服务器请求数据，需要设定为true,默认为false
            connection.setDoOutput(true);
            //若提交为post方式，需要修改为false
            connection.setUseCaches(false);
            //向报务器连接
            connection.connect();
            OutputStream output = connection.getOutputStream();
            //向服务器传送post数据
            output.write(bodyStr);
            if (connection.getResponseCode() != 200) {
                throw new Exception("请求url失败");
            }
            InputStream readStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(readStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            String str = buf.toString();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getData(File file) {

        StringBuffer sb = new StringBuffer();
        //在boundary关需添加两个横线
        sb = sb.append("--").append(mimeBoundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"_api_key\"");
        //提交的数据前要有两个回车换行
        sb.append("\r\n\r\n");
        sb.append("4e8893b68de2f3a070c5bcec0a7695cb");
        sb.append("\r\n");
        //第二个提交的参数
        sb.append("--").append(mimeBoundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"buildInstallType\"");
        sb.append("\r\n\r\n");
        sb.append("2");
        sb.append("\r\n");
        //第3个提交的参数
        sb.append("--").append(mimeBoundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"buildPassword\"");
        sb.append("\r\n\r\n");
        sb.append("jpjpjp");
        sb.append("\r\n");
        sb.append("--").append(mimeBoundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"file\";filename=\"" + file.getName() + "\"");
        sb.append("\r\n");
        sb.append("Content-Type:application/octet-stream");
        sb.append("\r\n");
        sb.append("Content-Length: " + file.length());
//        sb.append("\r\n");
        sb.append("\r\n\r\n");
        byte[] bytes = File2byte(file);
//
        byte[] p = sb.toString().getBytes();
        ByteBuffer b = ByteBuffer.allocate(p.length + bytes.length + "\r\n".length());
        b.put(p);
        b.put(bytes);
        b.put("\r\n".getBytes());
        return p;
    }

    public static byte[] File2byte(File tradeFile) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
