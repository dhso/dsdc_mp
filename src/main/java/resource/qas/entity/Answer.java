package resource.qas.entity;

import java.io.Serializable;

/**
 * Created by hadong on 15-1-19.
 */
public class Answer implements Serializable {

	private static final long serialVersionUID = -4590203165906906571L;
	private Long id = 0L;
	private String msgType;
	private String text;
	private String image;
	private String table;

	public Answer() {
	}

	public void setTextAnswer(Long id, String text) {
		this.id = id;
		this.msgType = "text";
		this.text = text;
	}
	
	public void setLinkAnswer(Long id, String text) {
		this.id = id;
		this.msgType = "link";
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

}
