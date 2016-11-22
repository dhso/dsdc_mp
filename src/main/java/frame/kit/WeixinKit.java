package frame.kit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.jfinal.weixin.sdk.api.ApiConfig;

/**
 * 
 * @author Javen
 * @Email javenlife@126.com 公众平台通用接口工具类
 */
public class WeixinKit {

	/**
	 * 获取配置
	 */
	public static ApiConfig getApiConfig(String token, String appId, String appSecret, Boolean encryptMessage, String encodingAesKey) {
		ApiConfig ac = new ApiConfig();
		// 配置微信 API 相关常量
		ac.setToken(token);
		ac.setAppId(appId);
		ac.setAppSecret(appSecret);
		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey 2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(encryptMessage);
		ac.setEncodingAesKey(encodingAesKey);
		return ac;
	}

	public static ApiConfig getApiConfig(String token, String appId, String appSecret) {
		return getApiConfig(token, appId, appSecret, false, "ASDFGHJKL");
	}

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