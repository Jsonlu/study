package top.jsonlu.app.frg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.jsonlu.app.R;
import top.jsonlu.app.p.IExamPresenter;
import top.jsonlu.app.p.impl.ExamPresenterImpl;
import top.jsonlu.app.v.IExamView;
import top.jsonlu.baselibrary.fetch.biz.d.QuestionData;
import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;
import top.jsonlu.baselibrary.ui.Toast;
import top.jsonlu.baselibrary.ui.Web;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 14:25
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class ExamFrg extends BaseFrg implements IExamView {

    private TypeItem item;
    private Web wb;
    private View ll_bottom, tv_next, tv_prev;
    private IExamPresenter presenter;
    private String title;

    public static ExamFrg newInstance(TypeItem item, String title) {
        Bundle args = new Bundle();
        args.putSerializable("data", item);
        args.putSerializable("title", title);
        ExamFrg fragment = new ExamFrg();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_exam, container, false);
        item = gett("data");
        title = gett("title");
        if (item != null) {
            setTitle(item.getName());
            presenter = new ExamPresenterImpl(this, getActivity(), item.getId() + "");
            initView(view);
            wb.setCallBack(presenter.getWebCall());
            presenter.getData();
        }
        return view;
    }

    private void initView(View view) {
        wb = view.findViewById(R.id.wb);
        ll_bottom = view.findViewById(R.id.ll_bottom);
        tv_next = view.findViewById(R.id.tv_next);
        tv_next.setOnClickListener(v -> {
            String d = presenter.getFormatData(0x0);
            Toast.showLong(getContext(), d);
        });
        tv_prev = view.findViewById(R.id.tv_prev);
        tv_prev.setOnClickListener(v -> {
            String d = presenter.getFormatData(0x1);
            add(R.id.contents, AnalysisFrg.newInstance(d, item.getName()));
        });
        wb.getSettings().setJavaScriptEnabled(true);
        wb.addJavascriptInterface(new JSBrige(), "native");
    }

    @Override
    public void loadData(QuestionData data) {
        wb.loadDataWithBaseURL(null, presenter.getFormatData(data), "text/html", "utf-8", null);
    }

    @Override
    public void goNext() {

    }

    @Override
    public void toBuyTip() {

    }

    @Override
    public void showBottom(boolean s) {
        ll_bottom.setVisibility(s ? View.VISIBLE : View.GONE);
    }

    class JSBrige {
        @android.webkit.JavascriptInterface
        public void call(String data) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        setTitle(title);
    }
}
