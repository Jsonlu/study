package top.jsonlu.app.m;

public class ResultData {
    private ResultItem alipay_trade_app_pay_response;
    private String sign;
    private String sign_type;

    public ResultItem getAlipay_trade_app_pay_response() {
        return this.alipay_trade_app_pay_response;
    }

    public void setAlipay_trade_app_pay_response(ResultItem alipay_trade_app_pay_response) {
        this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return this.sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }
}
