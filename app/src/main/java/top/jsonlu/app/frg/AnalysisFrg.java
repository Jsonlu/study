package top.jsonlu.app.frg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.jsonlu.app.R;
import top.jsonlu.baselibrary.ui.Web;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 14:25
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class AnalysisFrg extends BaseFrg {

    private Web wb;
    private String data;
    private String title;

    public static AnalysisFrg newInstance(String data, String title) {
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        args.putSerializable("title", title);
        AnalysisFrg fragment = new AnalysisFrg();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_exam, container, false);
        data = gett("data");
        title = gett("title");
        setTitle("解析");
        initView(view);
        return view;
    }

    private void initView(View view) {
        wb = view.findViewById(R.id.wb);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        setTitle(title);
    }
}
