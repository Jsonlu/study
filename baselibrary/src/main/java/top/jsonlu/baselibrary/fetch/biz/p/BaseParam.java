package top.jsonlu.baselibrary.fetch.biz.p;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Author:JsonLu
 * DateTime:2019/2/27 17:02
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class BaseParam implements Serializable {
    private byte[] iv;

    private String device;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @JSONField(serialize = false)
    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }
}
