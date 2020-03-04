package top.jsonlu.baselibrary.fetch.biz.net;

import android.arch.lifecycle.LiveData;

import retrofit2.http.Body;
import retrofit2.http.POST;
import top.jsonlu.baselibrary.fetch.biz.d.GoodsData;
import top.jsonlu.baselibrary.fetch.biz.d.InitCheckOutData;
import top.jsonlu.baselibrary.fetch.biz.d.PayData;
import top.jsonlu.baselibrary.fetch.biz.d.QuestionData;
import top.jsonlu.baselibrary.fetch.biz.d.TakeOrderData;
import top.jsonlu.baselibrary.fetch.biz.d.TradeState;
import top.jsonlu.baselibrary.fetch.biz.d.TypeClassData;
import top.jsonlu.baselibrary.fetch.biz.d.TypeDateData;

/**
 * Author:JsonLu
 * DateTime:2019/2/28 14:58
 * Email:jsonlu@qq.com
 * Desc:请求方式
 **/
public interface SuperService {

    /**
     * 初始化路径
     *
     * @param param
     * @return
     */
    @POST(UrlAddress.initCheckOut)
    LiveData<InitCheckOutData> initCheckOut(@Body Object param);

    /**
     * 获取考试科目
     *
     * @param param
     * @return
     */
    @POST(UrlAddress.GetTypeClass)
    LiveData<TypeClassData> getTypeClass(@Body Object param);

    /**
     * 获取科目的历年信息
     *
     * @param param
     * @return
     */
    @POST(UrlAddress.GetTypeDate)
    LiveData<TypeDateData> getTypeDate(@Body Object param);

    /**
     * 获取商品信息（价格，减免券）
     *
     * @param param
     * @return
     */
    @POST(UrlAddress.GetGoodsInfo)
    LiveData<GoodsData> getGoodsInfo(@Body Object param);

    /**
     * 获取拉起收银台支付参数
     *
     * @param param
     * @return
     */
    @POST(UrlAddress.GetPaymentInfo)
    LiveData<PayData> getPaymentInfo(@Body Object param);

    /**
     * 获取订单号
     *
     * @param param
     * @return
     */
    @POST(UrlAddress.TakeOrder)
    LiveData<TakeOrderData> takeOrder(@Body Object param);

    @POST(UrlAddress.GetOrderStatus)
    LiveData<TradeState> getOrder(@Body Object param);

    @POST(UrlAddress.GetExam)
    LiveData<QuestionData> getExam(@Body Object param);


}
