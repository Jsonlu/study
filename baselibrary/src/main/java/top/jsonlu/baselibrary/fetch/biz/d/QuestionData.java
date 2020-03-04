package top.jsonlu.baselibrary.fetch.biz.d;

/**
 * Author:JsonLu
 * DateTime:2020/1/7 16:17
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class QuestionData extends BaseData {

    private String data;
    private String question;
    private String option;
    private String answer;
    private String analysis;

    private int id;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

