package resource.qas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.DicAnalysis;

import cn.dreampie.common.util.properties.Proper;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.POST;
import frame.kit.ListKit;
import resource.ApiResource;
import resource.qas.entity.Answer;
import resource.qas.entity.QasMsg;
import resource.qas.kit.FilterRecognitions;
import resource.qas.model.QasWord;

/**
 * Created by hadong on 15-1-22.
 */
@API("/question")
public class QasResource extends ApiResource {

	@POST("/")
	public QasMsg Question() {
		QasMsg qasMsg = getModelParams(QasMsg.class);
		Result result = DicAnalysis.parse(qasMsg.getQuestion()).recognition(FilterRecognitions.preRecognition());
		System.out.println(result);
		System.out.println(result.toStringWithOutNature());
		List<QasWord> qasWords = QasWord.dao.getMultiKeys(Arrays.asList(result.toStringWithOutNature().split(",")));
		List<Answer> answerList = new ArrayList<Answer>();
		// not found
		if (qasWords.isEmpty()) {
			answerList.add(new Answer(0L, Proper.get("qas.str.notfound"), "text"));
		}
		// only one
		if (qasWords.size() == 1) {
			for (QasWord qasWord : qasWords) {
				Answer answer = new Answer(qasWord.get("wod_id", Long.class), qasWord.get("wod_answer", String.class), qasWord.get("wod_type", String.class));
				answerList.add(answer);
			}
		}
		// two and more
		if (qasWords.size() > 1) {

			for (QasWord qasWord : qasWords) {
				Answer answer = new Answer(qasWord.get("wod_id", Long.class), qasWord.get("wod_question", String.class), "link");
				answerList.add(answer);
			}
		}
		qasMsg.setAnswer(ListKit.subList(answerList, 0, 4));
		return qasMsg;
	}

	@POST("/id/:id")
	public QasMsg PostQuestionById(Long id) {
		QasMsg qasMsg = getModelParams(QasMsg.class);
		QasWord qasWord = QasWord.dao.getKeyById(id);
		Answer answer = new Answer(qasWord.get("wod_id", Long.class), qasWord.get("wod_answer", String.class), qasWord.get("wod_type", String.class));
		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(answer);
		qasMsg.setAnswer(answerList);
		return qasMsg;
	}

}
