package top.jsonlu.app.v;

import top.jsonlu.app.m.ResultData;
import top.jsonlu.baselibrary.fetch.biz.d.GoodsData;

/**
 * Author:JsonLu
 * DateTime:2020/1/10 17:44
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface IBuyView {

    void close();

    void showGoodInfo(GoodsData data);

    /**
     * @param type,=0,ing;=1,takeOrder ok;=2,openPay,=3,finishedPay;
     */
    void showStates(int type);

    void showResult(ResultData o);

}
