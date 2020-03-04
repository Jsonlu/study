package top.jsonlu.baselibrary.fetch.biz.d;

/**
 * Author:JsonLu
 * DateTime:2020/1/10 18:25
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class PayData extends BaseData {

    private String data;
    private String tradeNo;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
