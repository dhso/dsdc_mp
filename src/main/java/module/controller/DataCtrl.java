/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import com.jfinal.core.Controller;

import frame.plugin.collerbind.Coller;

/**
 * @author hadong
 */
@Coller(value = { "/metadata" }, path = "metadata")
public class DataCtrl extends Controller {
	public void datasource() {
		render("datasource.htm");
	}
	
	public void metadata() {
		render("metadata.htm");
	}
	
	public void mapping() {
		render("mapping.htm");
	}

	public void setup() {
		render("setup.htm");
	}

}
