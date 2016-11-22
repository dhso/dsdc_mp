package resource.qas.entity;

import java.io.Serializable;
import java.util.List;

public class QAMsg implements Serializable {

	private static final long serialVersionUID = -4590203165906906571L;
	private Long userId;
	private String userName;
	private String question;
	private List<Answer> answer;

	public QAMsg() {
	}

	public QAMsg(Long userId, String userName, String question, List<Answer> answer) {
		this.userId = userId;
		this.userName = userName;
		this.question = question;
		this.answer = answer;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

}
