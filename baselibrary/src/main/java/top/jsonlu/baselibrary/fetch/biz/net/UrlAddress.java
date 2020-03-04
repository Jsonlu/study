package top.jsonlu.baselibrary.fetch.biz.net;

/**
 * Author:JsonLu
 * DateTime:2019/3/1 09:12
 * Email:jsonlu@qq.com
 * Desc:静态地址
 **/
public interface UrlAddress {

    String BasePath = "/app/api/";

    String initCheckOut = BasePath + "initCheckOut";

    String GetTypeClass = BasePath + "getTypeClass";

    String GetTypeDate = BasePath + "getTypeDate";

    String GetGoodsInfo = BasePath + "getGoodsInfo";

    String TakeOrder = BasePath + "takeOrder";

    String GetOrderStatus = BasePath + "getPaymentState";

    String GetPaymentInfo = BasePath + "getPaymentInfo";

    String GetExam = BasePath + "loadExam";


}
