package top.jsonlu.app.p;

import top.jsonlu.baselibrary.fetch.biz.d.QuestionData;
import top.jsonlu.baselibrary.ui.Web;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 13:17
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface IExamPresenter {

    void getData();

    void route();

    String getFormatData(QuestionData data);

    Web.CallBack getWebCall();

    String getFormatData(int type);
}
