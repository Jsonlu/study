package top.jsonlu.baselibrary.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;

/**
 * Author:JsonLu
 * DateTime:2020/1/9 10:04
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class FileUtils {

    private static FileUtils instance;
    private static final int SUCCESS = 1;
    private static final int FAILED = 0;
    private Context context;
    private FileOperateCallback callback;
    private volatile boolean isSuccess;
    private String errorStr;

    public static FileUtils getInstance(Context context) {
        if (instance == null)
            instance = new FileUtils(context);
        return instance;
    }

    private FileUtils(Context context) {
        this.context = context;
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (callback != null) {
                if (msg.what == SUCCESS) {
                    callback.onSuccess();
                }
                if (msg.what == FAILED) {
                    callback.onFailed(msg.obj.toString());
                }
            }
        }
    };

    public FileUtils copyAssetsToSD(final String srcPath, final String sdPath) {
        new Thread(() -> {
            copyAssetsToDst(context, srcPath, sdPath);
            if (isSuccess)
                handler.obtainMessage(SUCCESS).sendToTarget();
            else
                handler.obtainMessage(FAILED, errorStr).sendToTarget();
        }).start();
        return this;
    }

    public void setFileOperateCallback(FileOperateCallback callback) {
        this.callback = callback;
    }

    private void copyAssetsToDst(Context context, String srcPath, String dstPath) {
        try {
            String fileNames[] = context.getAssets().list(srcPath);
            if (fileNames.length > 0) {
                File file = new File(Environment.getExternalStorageDirectory(), dstPath);
                if (!file.exists()) file.mkdirs();
                for (String fileName : fileNames) {
                    if (!srcPath.equals("")) { // assets 文件夹下的目录
                        copyAssetsToDst(context, srcPath + File.separator + fileName, dstPath + File.separator + fileName);
                    } else { // assets 文件夹
                        copyAssetsToDst(context, fileName, dstPath + File.separator + fileName);
                    }
                }
            } else {
                File outFile = new File(Environment.getExternalStorageDirectory(), dstPath);
                InputStream is = context.getAssets().open(srcPath);
                FileOutputStream fos = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024];
                int byteCount;
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
            }
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            errorStr = e.getMessage();
            isSuccess = false;
        }
    }

    public static boolean isFolderExists(String savePath) {
        File file = new File(savePath);
        return file.exists();
    }

    public static void makeDirectory(String savePath) {
        File file = new File(savePath);
        file.mkdirs();
    }

    public interface FileOperateCallback {
        void onSuccess();

        void onFailed(String error);
    }


    public static List<TypeItem> getFileList(String path) {
        File file = new File(path);
        List<TypeItem> l = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            List<File> list = Arrays.asList(files);
            Iterator<File> iterator = list.iterator();
            while (iterator.hasNext()) {
                File file1 = iterator.next();
                String name = file1.getName();
                if (name.endsWith(".db")) {
                    TypeItem item = new TypeItem();
                    item.setUrl(name);
                    item.setHash(getJdkFileMD5(file1));
                    l.add(item);
                }
            }
        }
        return l;
    }

    public static String getJdkFileMD5(File f) {
        try {
            BigInteger bi = null;
            byte[] buffer = new byte[1024 * 4];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
            return bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
}
