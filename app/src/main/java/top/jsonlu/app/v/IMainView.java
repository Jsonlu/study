package top.jsonlu.app.v;

import java.util.List;

import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;


/**
 * Author:JsonLu
 * DateTime:2020/1/8 13:17
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface IMainView {

    void loadData(List<TypeItem> list);

    void goNext();

    void toBuyTip();
}
