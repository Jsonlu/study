package top.jsonlu.baselibrary.fetch.biz.d;

/**
 * Author:JsonLu
 * DateTime:2020/1/15 10:22
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class TradeState extends BaseData{
    private String trade_id;
    private String trade_state;

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }
}
