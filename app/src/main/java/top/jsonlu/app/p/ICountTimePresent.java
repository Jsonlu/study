package top.jsonlu.app.p;

/**
 * Author:JsonLu
 * DateTime:2019/5/22 15:38
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface ICountTimePresent {
    /**
     * 关闭计时
     */
    void close();

    /**
     * 取消计时
     */
    void cancel();

    /**
     * 开始计时
     *
     * @param l
     * @param s
     */
    void start(int l, int s);
}
