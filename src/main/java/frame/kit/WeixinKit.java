package frame.kit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 
 * @author Javen
 * @Email javenlife@126.com
 * 公众平台通用接口工具类
 */
public class WeixinKit {

	/**
	 * emoji表情转换(hex -> utf-16)
	 *
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * UTF-8编码
	 *
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		try {
			return URLEncoder.encode(source, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}