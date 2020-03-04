package top.jsonlu.app.p.impl;

import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.TreeMap;

import top.jsonlu.app.p.IExamPresenter;
import top.jsonlu.app.v.IExamView;
import top.jsonlu.baselibrary.croypt.Aes;
import top.jsonlu.baselibrary.croypt.MD5Utils;
import top.jsonlu.baselibrary.fetch.biz.MobileBiz;
import top.jsonlu.baselibrary.fetch.biz.d.QuestionData;
import top.jsonlu.baselibrary.fetch.biz.net.Call;
import top.jsonlu.baselibrary.fetch.biz.p.GetBaseParam;
import top.jsonlu.baselibrary.ui.Web;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 16:01
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class ExamPresenterImpl implements IExamPresenter, Call {
    protected final IExamView view;
    private final FragmentActivity app;
    private int index = 0;
    private int max = 0;
    private List<QuestionData> list;
    private QuestionData questionData;
    private final MobileBiz mobileBiz;
    private String examId;


    public ExamPresenterImpl(IExamView view, FragmentActivity app, String examId) {
        this.view = view;
        this.app = app;
        mobileBiz = new MobileBiz(app);
        mobileBiz.setCall(this);
        this.examId = examId;
    }

    @Override
    public void getData() {
        GetBaseParam param = new GetBaseParam();
        param.setId(examId);
        mobileBiz.getExam(param);
    }

    @Override
    public void route() {

    }

    @Override
    public String getFormatData(QuestionData data) {
        StringBuilder b = new StringBuilder();
        if (data.getType() == 1) {
            b.append("<br>选项:<br>");
            JSONArray array = JSON.parseArray(data.getOption());
            for (int i = 0; i < array.size(); i++) {
                b.append("   (" + (i + 1) + ").<br>");
                b.append("<form style='border: 0.8px solid #ccc!important;padding:8px;border-radius: 8px!important;'>");
                JSONObject aaa = new JSONObject(new TreeMap<>());
                aaa.putAll(array.getJSONObject(i));
                for (String key : aaa.keySet()) {
                    String t = "<input name='answer' id='" + key + "' type=\"radio\" onclick=\"xclick(" + i + ",'" + key + "')\">" + key + "." + aaa.getString(key) + "<br />";
                    b.append(t);
                }
                b.append("</form>");
            }
        }
        b.append("<br><br><br>");
        String aa = b.toString();
        aa += "\n<script> function xclick(a,b) { native.call('{\"id\":'+a+',\"result\":'+b+'}')}</script>";
        aa = data.getId() + "." + data.getQuestion() + aa;
        return aa;
    }

    @Override
    public Web.CallBack getWebCall() {
        return new Web.CallBack() {
            @Override
            public void onBottom(boolean s) {
                if ((s && questionData != null) || !s)
                    view.showBottom(s);
            }

            @Override

            public void onLeft(boolean s) {
                view.showBottom(false);
                if (s) {
                    if (index == 0) {
                        if (list == null)
                            getData();
                        else
                            refresh();
                    } else if (index > 0) {
                        index--;
                        refresh();
                    }
                }
                if (!s) {
                    if (index == max - 1) {
                        refresh();
                    } else if (index < max) {
                        index++;
                        refresh();
                    }
                }
            }
        };
    }

    private void refresh() {
        questionData = list.get(index);
        view.loadData(questionData);
    }

    @Override
    public String getFormatData(int type) {
        if (questionData != null) {
            if (type == 0x0) {
                String a = questionData.getAnswer();
                if (!TextUtils.isEmpty(a)) {
                    return a.replaceAll("\\[|]", "");
                }
            } else {
                return questionData.getAnalysis();
            }
        }
        return "";
    }

    @Override
    public void getData(int code, Object data) {
        questionData = (QuestionData) data;
        String d = questionData.getData();
        if (TextUtils.isEmpty(d))
            return;
        try {
            String key = Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID) + "-" + examId;
            key = MD5Utils.stringToMD5(key);
            byte[] iv = key.substring(0, 16).getBytes();
            key = key.substring(16, 32);
            d = Aes.decrypt(d, key, iv);
            list = JSON.parseObject(d, new TypeReference<List<QuestionData>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            questionData = list.get(0);
            max = list.size();
            view.loadData(questionData);
        }

    }
}
