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
import top.jsonlu.app.adapt.ClassAdapt;
import top.jsonlu.app.p.IMainPresenter;
import top.jsonlu.app.p.impl.MainPresenterImpl;
import top.jsonlu.app.v.IMainView;
import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 14:24
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class MainFrg extends BaseFrg implements IMainView {

    private GridView grid_view;
    private IMainPresenter presenter;
    private TypeItem item;


    public static MainFrg newInstance() {
        Bundle args = new Bundle();
        MainFrg fragment = new MainFrg();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_main, container, false);
        grid_view = view.findViewById(R.id.grid_view);
        presenter = new MainPresenterImpl(this, getActivity());
        presenter.getData();
        return view;
    }

    @Override
    public void loadData(List<TypeItem> list) {
        BaseAdapter adapter = new ClassAdapt(getContext(), list);
        grid_view.setAdapter(adapter);
        grid_view.setOnItemClickListener((parent, view, position, id) -> {
            item = list.get(position);
            presenter.route();
        });
    }

    @Override
    public void goNext() {
        Fragment fragment = DateChoseFrg.newInstance(item);
        add(R.id.contents, fragment, "default");
    }

    @Override
    public void toBuyTip() {

    }
}
