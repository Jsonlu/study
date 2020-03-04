package top.jsonlu.baselibrary.fetch.biz.p;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 16:13
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class InitCheckOutParam extends BaseParam {

    private String versionName;
    private int versionCode;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}
