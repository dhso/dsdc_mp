/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package config;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.RedirectRender;
import com.jfinal.render.Render;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

import frame.handler.DruidStatViewHandler;
import frame.plugin.collerbind.RoutesScanner;
import frame.plugin.freemarker.BlockDirective;
import frame.plugin.freemarker.ExtendsDirective;
import frame.plugin.freemarker.OverrideDirective;
import frame.plugin.shiro.ShiroKit;
import frame.plugin.shiro.core.ShiroInterceptor;
import frame.plugin.shiro.core.ShiroPlugin;
import frame.plugin.shiro.freemarker.ShiroTags;
import frame.plugin.tablebind.TablesScanner;
import module.controller.WxSystemCtrl;

public class WechatConfig extends JFinalConfig {
	private Routes routes;

	public void configConstant(Constants me) {
		// 加载配置
		PropKit.use("config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		// 微信设置
		ApiConfigKit.setDevMode(me.getDevMode());
		// 设置错误模板
		me.setErrorView(401, WxSystemCtrl.LOGIN_PAGE);
		me.setErrorView(403, WxSystemCtrl.LOGIN_PAGE);
		me.setErrorView(404, WxSystemCtrl.SYSTEM_NOT_FOUND);
		me.setErrorView(500, WxSystemCtrl.SYSTEM_ERROR);
		me.setErrorRenderFactory(new IErrorRenderFactory() {
			@Override
			public Render getRender(int errorCode, String view) {
				return new RedirectRender(view, true);
			}
		});
	}

	public void configRoute(Routes me) {
		this.routes = me;
		// 自动路由绑定插件
		RoutesScanner rs = new RoutesScanner("module.controller");
		rs.start(me);
	}

	public void configPlugin(Plugins me) {
		// 添加shiro支持
		me.add(new ShiroPlugin(routes));
		// 添加缓存支持
		me.add(new EhCachePlugin(WechatConfig.class.getClassLoader()
				.getResource("ehcache-model.xml")));
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbc.url"),
				PropKit.get("jdbc.user"), PropKit.get("jdbc.password"),
				PropKit.get("jdbc.driver"));
		druidPlugin.setFilters("mergeStat,wall");
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		me.add(arp);
		// 自动表绑定插件
		TablesScanner ts = new TablesScanner("module.model");
		ts.start(arp);
	}

	public void configInterceptor(Interceptors me) {
		// shiro拦截器
		me.add(new ShiroInterceptor());
		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor(true));
	}

	public void configHandler(Handlers me) {
		me.add(new UrlSkipHandler(".*/static/.*", false));
		me.add(new ContextPathHandler("baseUrl"));
		me.add(new DruidStatViewHandler("druid", new IDruidStatViewAuth() {
			@Override
			public boolean isPermitted(HttpServletRequest request) {
				return ShiroKit.isAuthenticated();
			}
		}));

	}

	public void afterJFinalStart() {
		super.afterJFinalStart();
		FreeMarkerRender.getConfiguration().setClassicCompatible(true);
		FreeMarkerRender.getConfiguration().setSharedVariable("shiro",
				new ShiroTags());
		FreeMarkerRender.getConfiguration().setSharedVariable("block",
				new BlockDirective());
		FreeMarkerRender.getConfiguration().setSharedVariable("override",
				new OverrideDirective());
		FreeMarkerRender.getConfiguration().setSharedVariable("extends",
				new ExtendsDirective());
	}

}
