package top.jsonlu.baselibrary.fetch.biz.d;

import java.util.List;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 16:17
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class TypeClassData extends BaseData {
    private List<TypeItem> data;

    public List<TypeItem> getData() {
        return data;
    }

    public void setData(List<TypeItem> data) {
        this.data = data;
    }
}
