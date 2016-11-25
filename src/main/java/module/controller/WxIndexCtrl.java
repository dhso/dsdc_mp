/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;

import frame.plugin.collerbind.Coller;

/**
 * @author hadong
 */
@Coller(value = { "/" }, path = "wechat")
public class WxIndexCtrl extends Controller {
	public void index() {
		render("index.htm");
	}

	public void put() {
		CacheKit.put("ehcache-model", "key", getPara("val", "0"));
		renderText(CacheKit.get("ehcache-model", "key").toString());
	}

	public void get() {
		renderText(CacheKit.get("ehcache-model", "key").toString());
	}
}
