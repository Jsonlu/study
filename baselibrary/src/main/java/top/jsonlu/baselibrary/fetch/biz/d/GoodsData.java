package top.jsonlu.baselibrary.fetch.biz.d;

/**
 * Author:JsonLu
 * DateTime:2020/1/10 18:24
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class GoodsData extends BaseData {

    private String perice;
    private String couponId;
    private String couponAmount;
    private String payAmount;

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPerice() {
        return perice;
    }

    public void setPerice(String perice) {
        this.perice = perice;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }
}
