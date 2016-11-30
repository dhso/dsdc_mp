package module.controller;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.ApiController;

import frame.kit.WeixinKit;

public class WxOauth extends ApiController {

	@Override
	public ApiConfig getApiConfig() {
		return WeixinKit.getApiConfig(PropKit.get("wx.token"), PropKit.get("wx.appId"), PropKit.get("wx.appSecret"), PropKit.getBoolean("wx.encryptMessage", false), PropKit.get("wx.encodingAesKey"));
	}

}
