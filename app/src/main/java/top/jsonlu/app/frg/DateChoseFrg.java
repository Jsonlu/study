package top.jsonlu.app.frg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.List;

import top.jsonlu.app.R;
import top.jsonlu.app.adapt.DateAdapt;
import top.jsonlu.app.p.IDateChosePresenter;
import top.jsonlu.app.p.impl.DateChosePresenterImpl;
import top.jsonlu.app.v.IMainView;
import top.jsonlu.baselibrary.bus.LiveDataBus;
import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 14:25
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class DateChoseFrg extends BaseFrg implements IMainView {

    private GridView grid_view;
    private IDateChosePresenter presenter;
    private TypeItem item;
    private String title;


    public static DateChoseFrg newInstance(TypeItem item) {
        Bundle args = new Bundle();
        args.putSerializable("data", item);
        DateChoseFrg fragment = new DateChoseFrg();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_main, container, false);
        grid_view = view.findViewById(R.id.grid_view);
        item = gett("data");
        if (item != null) {
            title = item.getName();
            setTitle(title);
            presenter = new DateChosePresenterImpl(this, getActivity());
            presenter.setTypeItem(item);
            presenter.getData();
        }
        return view;
    }

    @Override
    public void loadData(List<TypeItem> list) {
        BaseAdapter adapter = new DateAdapt(getContext(), list);
        if (list == null) {

        } else if (list.size() > 0) {
            TypeItem ii = list.get(0);
            grid_view.setNumColumns(ii.getType());
            grid_view.setAdapter(adapter);
            grid_view.setOnItemClickListener((parent, view, position, id) -> {
                item = list.get(position);
                presenter.setTypeItem(item);
                presenter.route();
            });
        }

    }

    @Override
    public void goNext() {
        Fragment fragment = ExamFrg.newInstance(item, title);
        add(R.id.contents, fragment, "default");
    }

    @Override
    public void toBuyTip() {
        LiveDataBus.get().with("downloadDb", Integer.class).observe(getActivity(), i -> {
            presenter.download();
        });
        goPay();
    }

    private void goPay() {
        Fragment fragment = BuyFrg.newInstance(item);
        add(R.id.contents, fragment, "default");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        setTitle("真题");
    }
}
