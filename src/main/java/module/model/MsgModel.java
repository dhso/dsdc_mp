package module.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.Table;


@Table("wx_question")
public class MsgModel extends Model<MsgModel>{
	public static final MsgModel dao = new MsgModel();
	
	/**
	 * 获取所有问题类型
	 * @return
	 */
	public List<MsgModel> findAllQuestionType() {
		return MsgModel.dao.find("select * from wx_question_type order by type_id asc");
	}
	
	public MsgModel findQuestion(int type, int step) {
		return MsgModel.dao.findFirst("select * from wx_question where wx_question_type = ? and wx_question_step = ?", type, step);
	}
	
	public MsgModel findQuestionCount(int type) {
		return MsgModel.dao.findFirst("select count(*) wx_count from wx_question where wx_question_type = ?", type);
	}

	public List<MsgModel> findAllQuestionsByType(int type) {
		return MsgModel.dao.find("select * from wx_question where wx_question_type = ? order by wx_question_step asc", type);
	}

	public MsgModel findQuestionByStep(int step) {
		return MsgModel.dao.findFirst("select * from wx_question where wx_question_step = ?", step);
	}

	public MsgModel findAllQuestionCount() {
		return MsgModel.dao.findFirst("select count(*) wx_count from wx_question");
	}

	public List<MsgModel> findAllQuestions() {
		return MsgModel.dao.find("select * from wx_question order by wx_question_step asc");
	}
}
