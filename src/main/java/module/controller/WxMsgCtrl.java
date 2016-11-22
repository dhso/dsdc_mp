/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

import frame.kit.WeixinKit;
import frame.plugin.collerbind.Coller;

/**
 * @author hadong
 */
@Coller(value = { "/wx/msg" }, path = "wechat")
public class WxMsgCtrl extends MsgControllerAdapter {
	@Override
	public ApiConfig getApiConfig() {
		return WeixinKit.getApiConfig(PropKit.get("wx.token"), PropKit.get("wx.appId"), PropKit.get("wx.appSecret"), PropKit.getBoolean("wx.encryptMessage", false), PropKit.get("wx.encodingAesKey"));
	}

	@Override
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
		OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
		outMsg.setContent("Welcome to Acxiom Wechat!");
		// 如果为取消关注事件，将无法接收到传回的信息
		render(outMsg);
	}

	@Override
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String msgContent = inTextMsg.getContent().trim();
		OutTextMsg outMsg = new OutTextMsg(inTextMsg);
		outMsg.setContent(msgContent);
		render(outMsg);
	}

	@Override
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
		if (InQrCodeEvent.EVENT_INQRCODE_SUBSCRIBE.equals(inQrCodeEvent.getEvent())) {
			OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
			outMsg.setContent("感谢您的关注，二维码内容：" + inQrCodeEvent.getEventKey());
			render(outMsg);
		}
		if (InQrCodeEvent.EVENT_INQRCODE_SCAN.equals(inQrCodeEvent.getEvent())) {
			String key = inQrCodeEvent.getEventKey();
			renderOutTextMsg(key);
		}
	}
	
	@Override
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
		outMsg.setContent("地理位置是：\n" + inLocationEvent.getLatitude()+"\n"+inLocationEvent.getLongitude());
		render(outMsg);
	}
}
