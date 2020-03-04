package top.jsonlu.app.p.impl;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import java.util.HashMap;

import top.jsonlu.app.p.IPayPresent;
import top.jsonlu.app.v.IBuyView;
import top.jsonlu.baselibrary.fetch.biz.MobileBiz;
import top.jsonlu.baselibrary.fetch.biz.d.GoodsData;
import top.jsonlu.baselibrary.fetch.biz.d.PayData;
import top.jsonlu.baselibrary.fetch.biz.d.TakeOrderData;
import top.jsonlu.baselibrary.fetch.biz.d.TradeState;
import top.jsonlu.baselibrary.fetch.biz.net.Call;
import top.jsonlu.baselibrary.fetch.biz.p.GetBaseParam;
import top.jsonlu.baselibrary.pay.Payment;

/**
 * Author:JsonLu
 * DateTime:2020/1/10 17:47
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class PayPresentImpl implements IPayPresent, Call {

    private final FragmentActivity app;
    private final IBuyView view;
    private final MobileBiz mobileBiz;
    String goodId = "";
    String orderId = "";
    String tradeId = "";

    public PayPresentImpl(FragmentActivity app, IBuyView view) {
        this.app = app;
        this.view = view;
        this.mobileBiz = new MobileBiz(app);
        mobileBiz.setCall(this);
    }

    @Override
    public void getOrder(String goodId) {
        this.goodId = goodId;
        GetBaseParam param = new GetBaseParam();
        param.setId(goodId);
        mobileBiz.getGoodsInfo(param);
    }

    private void goPayment() {
        GetBaseParam param = new GetBaseParam();
        param.setId(orderId);
        mobileBiz.getPaymentInfo(param);
    }

    private void takeOrder() {
        GetBaseParam param = new GetBaseParam();
        param.setId(goodId);
        mobileBiz.takeOrder(param);
    }

    @Override
    public void getData(int code, Object data) {
        if (code == mobileBiz.GoodsInfo) {
            view.showGoodInfo((GoodsData) data);
            new Handler().postDelayed(() -> takeOrder(), 1000);
            view.showStates(0x0);
        } else if (code == mobileBiz.PaymentInfo) {
            PayData data1 = (PayData) data;
            tradeId = data1.getTradeNo();
            openCashier(data1);
            view.showStates(0x2);
        } else if (code == mobileBiz.TakeOrder) {
            view.showStates(0x1);
            TakeOrderData data1 = (TakeOrderData) data;
            orderId = data1.getOrderId();
            new Handler().postDelayed(() -> goPayment(), 1000);
        } else {
            TradeState state = (TradeState) data;
            if ("S".equals(state)) {

            } else if ("P".equals(state)) {

            } else {

            }
        }
    }

    private void openCashier(PayData data) {
        view.close();
        Payment payment = new Payment();
        payment.payV2(data.getData(), app, 0x0FF, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //result、resultStatus
                HashMap<String, String> map = (HashMap) msg.obj;
                String resultStatus = map.get("resultStatus");
                if ("9000".equals(resultStatus) || "8000".equals(resultStatus) || "6004".equals(resultStatus)) {
                    checkState();
                }
            }
        });
    }


    private void checkState() {
        GetBaseParam param = new GetBaseParam();
        param.setId(tradeId);
        mobileBiz.getOrder(param);
    }
}
