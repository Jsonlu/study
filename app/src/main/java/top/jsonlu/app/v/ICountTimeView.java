package top.jsonlu.app.v;

/**
 * Author:JsonLu
 * DateTime:2019/5/22 15:43
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface ICountTimeView {

    /**
     * 计时触发
     *
     * @param minute
     * @param second
     */
    void setTime(long minute, long second);

    /**
     * 计时完成
     */
    void onFinish();


}
