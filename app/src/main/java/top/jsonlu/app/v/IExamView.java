package top.jsonlu.app.v;

import top.jsonlu.baselibrary.fetch.biz.d.QuestionData;


/**
 * Author:JsonLu
 * DateTime:2020/1/8 13:17
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface IExamView {

    void loadData(QuestionData data);

    void goNext();

    void toBuyTip();

    void showBottom(boolean s);
}
