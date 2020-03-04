package top.jsonlu.baselibrary.fetch.biz.d;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 12:59
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class TypeItem implements Serializable {

    private String name;
    private String hash;
    private String date;
    private String url;
    private boolean buyed;
    private int id;
    private int type;

    public int getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isBuyed() {
        return buyed;
    }

    public void setBuyed(boolean buyed) {
        this.buyed = buyed;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeItem item = (TypeItem) o;
        String hash = item.hash;
        if (!TextUtils.isEmpty(hash) && hash.equals(getHash())) {
            setUrl(item.url);
            return true;
        }
        return false;
    }
}
