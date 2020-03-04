package top.jsonlu.baselibrary.fetch.biz.p;

/**
 * Author:JsonLu
 * DateTime:2020/1/10 18:27
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class GetPaymentParam extends BaseParam {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
