/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.CustomServiceApi;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

import frame.kit.WeixinKit;
import frame.plugin.collerbind.Coller;
import module.model.ConfModel;

/**
 * @author hadong
 */
@Coller(value = { "/wx/msg" }, path = "wechat")
public class WxMsgCtrl extends MsgControllerAdapter {

	private static final String MODEL_CACHE_NAME = "ehcache-model";

	private final Logger logger = Logger.getLogger(WxMsgCtrl.class);

	@Override
	public ApiConfig getApiConfig() {
		return WeixinKit.getApiConfig(PropKit.get("wx.token"), PropKit.get("wx.appId"), PropKit.get("wx.appSecret"), PropKit.getBoolean("wx.encryptMessage", false), PropKit.get("wx.encodingAesKey"));
	}

	@Override
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {

		OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
		// outMsg.setContent(ConfModel.dao.findCfgValueByKey("wx.tip.subscribe"));
		// 如果为取消关注事件，将无法接收到传回的信息

		String customerOpenId = inFollowEvent.getFromUserName().trim();

		// 清空缓存
		CacheKit.remove(MODEL_CACHE_NAME, customerOpenId);
		CacheKit.remove(MODEL_CACHE_NAME, "step_" + customerOpenId);

		List<String> msgRecords = new ArrayList<String>();

		CacheKit.put(MODEL_CACHE_NAME, "step_" + customerOpenId, 0);
		CacheKit.put(MODEL_CACHE_NAME, customerOpenId, msgRecords);
		String question = ConfModel.dao.findCfgValueByKey("wx.cst.step.0");
		msgRecords.add(ConfModel.dao.findCfgValueByKey("wx.cst.step.recordname.0"));
		outMsg.setContent(question);
		render(outMsg);
	}

	@Override
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String customerMsg = inTextMsg.getContent().trim();
		String customerOpenId = inTextMsg.getFromUserName().trim();

		List<String> msgRecords = CacheKit.get(MODEL_CACHE_NAME, customerOpenId);
		Integer step = CacheKit.get(MODEL_CACHE_NAME, "step_" + customerOpenId);
		if (step == null) {
			step = 0;
			CacheKit.put(MODEL_CACHE_NAME, "step_" + customerOpenId, 0);
		}
		if (msgRecords == null) {
			msgRecords = new ArrayList<String>();

			String question = ConfModel.dao.findCfgValueByKey("wx.cst.step.0");
			msgRecords.add(ConfModel.dao.findCfgValueByKey("wx.cst.step.recordname.0"));
			CacheKit.put(MODEL_CACHE_NAME, "step_" + customerOpenId, 0);
			CacheKit.put(MODEL_CACHE_NAME, customerOpenId, msgRecords);

			render(new OutTextMsg(inTextMsg).setContent(question));
			return;
		} else {
			msgRecords.add(customerMsg);
		}

		Integer lastStep = new Integer(ConfModel.dao.findCfgValueByKey("wx.cst.step.last.number"));
		if (step >= lastStep) {
			// 汇总消息
			StringBuffer sb = new StringBuffer();
			if (msgRecords != null) {
				int i = 0;
				for (String s : msgRecords) {
					i++;
					sb.append(s);
					if (i % 2 == 0) {
						sb.append("\n");
					}

				}
				
				// 通知客服
				CustomServiceApi.sendText("oNSykwgRjAHQ9tcqcJn9LLdawtcI", "有一条新数据咨询：\n" + sb.toString());
				CustomServiceApi.sendText(ConfModel.dao.findCfgValueByKey("wx.cst.serviceaccount.openid"), "有一条新数据咨询：\n" + sb.toString());// TODO for test kf
				
				sb.append(" \n我现在去查询，有结果马上联系您，稍安勿躁 \n");
				CustomServiceApi.sendText(customerOpenId, "这是您的数据需求：\n" + sb.toString());

				// 清空缓存
				CacheKit.remove(MODEL_CACHE_NAME, customerOpenId);
				CacheKit.remove(MODEL_CACHE_NAME, "step_" + customerOpenId);
				
				// 转发到客服系统
				OutTextMsg outMsg = new OutTextMsg(inTextMsg);
				outMsg.setContent("Wait for you");
				outMsg.setMsgType("transfer_customer_service");
				render(outMsg); 
				return;
			} else {
				// TODO
			}
		} else {
			step++;
			String question = ConfModel.dao.findCfgValueByKey("wx.cst.step." + step);
			msgRecords.add(ConfModel.dao.findCfgValueByKey("wx.cst.step.recordname." + step));
			CacheKit.put(MODEL_CACHE_NAME, "step_" + customerOpenId, step);
			render(new OutTextMsg(inTextMsg).setContent(question));
			return;

		}

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
			return;
		}
		if (InQrCodeEvent.EVENT_INQRCODE_SCAN.equals(inQrCodeEvent.getEvent())) {
			String key = inQrCodeEvent.getEventKey();
			renderOutTextMsg(key);
		}
	}

	@Override
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
		outMsg.setContent("地理位置是：\n" + inLocationEvent.getLatitude() + "\n" + inLocationEvent.getLongitude());
		render(outMsg);
	}

}
