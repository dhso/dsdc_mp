/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import frame.plugin.collerbind.Coller;
import frame.plugin.shiro.ShiroKit;
import module.model.ShiroModel;

/**
 * @author hadong
 */
@Coller(value = { "/wechat/config" }, path = "config")
public class WxConfCtrl extends Controller {
	@RequiresAuthentication
	public void index() {
		setAttr("role", ShiroModel.dao.findRole(ShiroKit.who()).getStr("role_desc"));
		render("index.htm");
	}

	@RequiresAuthentication
	public void menus() {
		List<Record> menus = ShiroModel.dao.findUrls(ShiroKit.who());
		renderJson(menus);
	}
}
