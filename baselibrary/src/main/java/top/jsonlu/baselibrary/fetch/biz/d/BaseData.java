package top.jsonlu.baselibrary.fetch.biz.d;

import java.io.Serializable;

/**
 * Author:JsonLu
 * DateTime:2019/2/27 17:02
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class BaseData implements Serializable {

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
