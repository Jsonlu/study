package top.jsonlu.app.adapt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import top.jsonlu.app.R;
import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 12:57
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class ClassAdapt extends BaseAdapter {

    private List<TypeItem> list;
    private LayoutInflater inflater;

    public ClassAdapt(Context context, @NonNull List<TypeItem> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_type, null);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.text);
            holder.tv_tag = convertView.findViewById(R.id.tag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TypeItem item = list.get(position);
        if (item != null) {
            holder.tv_name.setText(item.getName());
            holder.tv_tag.setVisibility(item.isBuyed() ? View.VISIBLE : View.GONE);
        }

        return convertView;

    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_tag;
    }
}
