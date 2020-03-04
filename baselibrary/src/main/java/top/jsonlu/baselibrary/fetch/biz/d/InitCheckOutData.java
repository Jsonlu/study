package top.jsonlu.baselibrary.fetch.biz.d;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 16:17
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class InitCheckOutData extends BaseData {
    /**
     * @type == 0x0,默认，desc==null,apkUrl==null,picUrl!=null
     * @type == 0x1,建议升级，desc!=null,apkUrl!=null,picUrl!=null
     * @type == 0x2,强制升级，desc!=null,apkUrl!=null,picUrl!=null
     */
    private int type;
    private String desc;
    private String apkUrl;
    private String webUrl;
    private String picUrl;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
