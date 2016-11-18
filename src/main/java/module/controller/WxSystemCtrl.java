/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import frame.plugin.collerbind.Coller;
import module.validator.SigninValidator;

/**
 * @author hadong
 */
@Coller(value = { "/wechat/system" }, path = "system")
public class WxSystemCtrl extends Controller {
	public static String LOGIN_PAGE = "/wechat/system/login";
	public static String LOGIN_SUCCESS = "/wechat/config";
	public static String SYSTEM_NOT_FOUND = "/wechat/system/err404";
	public static String SYSTEM_ERROR = "/wechat/system/err500";

	// 默认登录页面
	public void index() {
		render("login.htm");
	}

	// 登录页面
	public void login() {
		index();
	}

	// 登录Action
	@Before(SigninValidator.class)
	public void signin() {
		if ("GET".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
			forwardAction(LOGIN_PAGE);
		} else if ("POST".equalsIgnoreCase(this.getRequest().getMethod()
				.toUpperCase())) {
			String username = getPara("username");
			String password = getPara("password");
			String rememberMe = getPara("rememberMe");
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password, "on".equalsIgnoreCase(rememberMe));
			try {
				currentUser.login(token);
				redirect(getCookie("_redrictUrl", LOGIN_SUCCESS));
			} catch (Exception e) {
				// 登录失败
				String esn = e.getClass().getSimpleName();
				if ("IncorrectCredentialsException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户名或者密码不正确！");
				} else if ("UnknownAccountException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户名错误！");
				} else if ("LockedAccountException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户已锁定！");
				} else if ("AuthenticationException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户认证失败！");
				} else if ("ExcessiveAttemptsException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户登录错误次数过多，10分钟内禁止登录！");
				} else if ("DisabledAccountException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户已禁用！");
				} else if ("ExpiredCredentialsException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户已过期！");
				} else {
					setAttr("errorMsg", "未知错误！");
				}
				setAttr("username", username);
				setAttr("rememberMe", rememberMe);
				forwardAction(LOGIN_PAGE);
			}
		}

	}

	// 登出Action
	public void signout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		redirect(getCookie("_redrictUrl", LOGIN_PAGE));
	}

	public void err401() {
		setAttr("msg", "401 Unauthorized");
		setAttr("success", false);
		renderJson();
	}

	public void err403() {
		setAttr("msg", "403 Forbidden");
		setAttr("success", false);
		renderJson();
	}

	public void err404() {
		render("error/404.htm");
	}

	public void err500() {
		render("error/500.htm");
	}
}
