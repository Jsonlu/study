package top.jsonlu.baselibrary.fetch.biz.d;

import java.io.Serializable;
import java.util.List;

/**
 * Author:JsonLu
 * DateTime:2020/1/15 13:51
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class TypeDateDataItem implements Serializable {


    private List<String> keys;
    private List<TypeItem> dates;

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<TypeItem> getDates() {
        return dates;
    }

    public void setDates(List<TypeItem> dates) {
        this.dates = dates;
    }
}
