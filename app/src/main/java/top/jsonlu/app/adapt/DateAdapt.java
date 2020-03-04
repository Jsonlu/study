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
public class DateAdapt extends BaseAdapter {

    private List<TypeItem> list;
    private LayoutInflater inflater;

    public DateAdapt(Context context, @NonNull List<TypeItem> list) {
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
            convertView = inflater.inflate(R.layout.date_type, null);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.text);
            holder.tv_tag = convertView.findViewById(R.id.tag);
            holder.tv_date = convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TypeItem item = list.get(position);
        if (item != null) {
            holder.tv_name.setText(item.getName());
            int type = item.getType();
            if (type != 0 && list.indexOf(item) % type == 0) {
                holder.tv_date.setText(item.getDate());
                holder.tv_date.setVisibility(View.VISIBLE);
            }
            holder.tv_tag.setVisibility(item.isBuyed() ? View.VISIBLE : View.GONE);
        }

        return convertView;

    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_tag;
        TextView tv_date;
    }
}
