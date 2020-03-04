package top.jsonlu.app.p;

import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;

/**
 * Author:JsonLu
 * DateTime:2020/1/10 15:43
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface IDateChosePresenter extends IMainPresenter {

    void setTypeItem(TypeItem item);

    void download();

}
