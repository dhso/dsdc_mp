package resource.qas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.DicAnalysis;

import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.POST;
import frame.kit.ListKit;
import module.model.ConfModel;
import resource.ApiResource;
import resource.qas.entity.Answer;
import resource.qas.entity.QAMsg;
import resource.qas.kit.FilterRecognitions;
import resource.qas.model.QA;

/**
 * Created by hadong on 15-1-22.
 */
@API("/question")
public class QAResource extends ApiResource {

	@SuppressWarnings("unchecked")
	@POST("/")
	public QAMsg Question() {
		QAMsg qaMsg = getModelParams(QAMsg.class);
		Result result = DicAnalysis.parse(qaMsg.getQuestion()).recognition(FilterRecognitions.preRecognition());
		List<QA> qas = QA.dao.getMultiKeys(Arrays.asList(result.toStringWithOutNature().split(",")));
		List<Answer> answerList = new ArrayList<Answer>();
		// not found
		if (qas.isEmpty()) {
			answerList.add(new Answer(0L, ConfModel.dao.findCfgValueByKey("qa.tip.notfound"), "text"));
		}
		// only one
		if (qas.size() == 1) {
			for (QA qa : qas) {
				Answer answer = new Answer(qa.get("qac_id", Long.class), qa.get("qac_answer", String.class), qa.get("qat_name", String.class));
				answerList.add(answer);
			}
		}
		// two and more
		if (qas.size() > 1) {

			for (QA qa : qas) {
				Answer answer = new Answer(qa.get("qac_id", Long.class), qa.get("qac_question", String.class), "link");
				answerList.add(answer);
			}
		}
		qaMsg.setAnswer(ListKit.subList(answerList, 0, 4));
		return qaMsg;
	}

	@POST("/id/:id")
	public QAMsg PostQuestionById(Long id) {
		QAMsg qasMsg = getModelParams(QAMsg.class);
		QA qa = QA.dao.getKeyById(id);
		Answer answer = new Answer(qa.get("qac_id", Long.class), qa.get("qac_answer", String.class), qa.get("qat_name", String.class));
		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(answer);
		qasMsg.setAnswer(answerList);
		return qasMsg;
	}

	@POST("/type/:type")
	public QAMsg PostQuestionByType(String type) {
		QAMsg qasMsg = getModelParams(QAMsg.class);
		Answer answer = new Answer(0L, ConfModel.dao.findCfgValueByKey(type), "text");
		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(answer);
		qasMsg.setAnswer(answerList);
		return qasMsg;
	}

}
