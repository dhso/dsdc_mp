package module.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.Table;

@SuppressWarnings("serial")
@Table(value = "wx_question", pkName = "wq_id")
public class QuestionModel extends Model<QuestionModel> {
	public static final QuestionModel dao = new QuestionModel();

	public QuestionModel findQuestion(String qid) {
		return QuestionModel.dao.findById(qid);
	}

	public void addQusetion(String openid, String question) {
		new QuestionModel().set("wq_openid", openid).set("wq_question", question).set("wq_create_dt", new Date()).save();
	}
}
