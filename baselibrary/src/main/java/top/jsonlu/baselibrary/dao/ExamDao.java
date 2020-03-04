//package top.jsonlu.baselibrary.dao;
//
//import android.arch.lifecycle.LiveData;
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Query;
//
//import java.util.List;
//
//import top.jsonlu.baselibrary.fetch.biz.d.QuestionData;
//
///**
// * Author:JsonLu
// * DateTime:2020/1/9 09:51
// * Email:jsonlu@qq.com
// * Desc:
// **/
//@Dao
//public interface ExamDao {
//
//    @Query("SELECT * FROM soft")
//    LiveData<List<QuestionData>> loadAllQuestions();
//
//    @Query("SELECT * FROM soft WHERE id=:id")
//    LiveData<QuestionData> queryOne(int id);
//
//    @Query("SELECT count(*) FROM soft")
//    LiveData<Integer> count();
//}
