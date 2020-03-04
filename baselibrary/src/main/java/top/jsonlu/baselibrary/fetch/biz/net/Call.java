package top.jsonlu.baselibrary.fetch.biz.net;

/**
 * Author:JsonLu
 * DateTime:2019/4/1 10:48
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface Call<T> {

    void getData(int code, T data);
}
