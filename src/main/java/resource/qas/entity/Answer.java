package resource.qas.entity;

import java.io.Serializable;

/**
 * Created by hadong on 15-1-19.
 */
public class Answer implements Serializable {

	private static final long serialVersionUID = -4590203165906906571L;
	private Long id = 0L;
	private String type;
	private String text;

	public Answer() {
	}

	public Answer(Long id, String text, String type) {
		this.id = id;
		this.type = type;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
