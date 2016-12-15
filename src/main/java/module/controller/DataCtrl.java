/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.Controller;

import frame.plugin.collerbind.Coller;
import frame.plugin.shiro.ShiroKit;
import module.model.ShiroModel;

/**
 * @author hadong
 */
@Coller(value = { "/data" }, path = "data")
public class DataCtrl extends Controller {
	@RequiresAuthentication
	public void index() {
		setAttr("role", ShiroModel.dao.findRole(ShiroKit.who()).getStr("role_desc"));
		render("index.htm");
	}

	@RequiresAuthentication
	public void report() {
		render("report.htm");
	}
	
	@RequiresAuthentication
	public void setup() {
		render("setup.htm");
	}

}
