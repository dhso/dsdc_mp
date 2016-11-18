package resource;

import java.io.Serializable;

public class RestMsg implements Serializable {

	private static final long serialVersionUID = -4590203165906906571L;
	private Integer code = 200;
	private String msg = "success";

	public RestMsg() {
	}

	public RestMsg(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
