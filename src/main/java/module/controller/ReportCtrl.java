/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.Controller;

import frame.plugin.collerbind.Coller;

/**
 * @author hadong
 */
@Coller(value = { "/report" }, path = "report")
public class ReportCtrl extends Controller {

	@RequiresAuthentication
	public void portal() {
		render("portal.htm");
	}
	
	@RequiresAuthentication
	public void job() {
		render("job.htm");
	}
	
	@RequiresAuthentication
	public void data_cleaning() {
		render("data-cleaning.htm");
	}

}
