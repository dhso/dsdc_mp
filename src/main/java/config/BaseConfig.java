package config;

import net.dreamlu.event.EventPlugin;

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
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.FreeMarkerRender;

import frame.plugin.collerbind.RoutesScanner;
import frame.plugin.freemarker.BlockDirective;
import frame.plugin.freemarker.ExtendsDirective;
import frame.plugin.freemarker.OverrideDirective;
import frame.plugin.tablebind.TablesScanner;

public class BaseConfig extends JFinalConfig {

	@SuppressWarnings("unused")
	private Routes routes;

	public void configConstant(Constants me) {
		// 加载配置/国际化
		PropKit.use("config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}

	public void configRoute(Routes me) {
		this.routes = me;
		// 自动路由绑定插件
		RoutesScanner rs = new RoutesScanner("controller");
		rs.start(me);
	}

	public void configPlugin(Plugins me) {
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("web.jdbcUrl"),
				PropKit.get("web.jdbcUser"), PropKit.get("web.jdbcPassword"),
				PropKit.get("web.jdbcDriver"));
		druidPlugin.setFilters("mergeStat,wall");
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		me.add(arp);
		// 自动表绑定插件
		TablesScanner ts = new TablesScanner("model");
		//ts.start(arp);
		// 事件驱动插件
		EventPlugin ep = new EventPlugin();
		ep.async();
		ep.scanPackage("listener");
		ep.start();
	}

	public void configInterceptor(Interceptors me) {
		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor(true));
	}

	public void configHandler(Handlers me) {
		me.add(new UrlSkipHandler(".*/static/.*", false));
		me.add(new ContextPathHandler("baseUrl"));
		me.add(new DruidStatViewHandler("/druid"));
	}

	public void afterJFinalStart() {
		super.afterJFinalStart();
		FreeMarkerRender.getConfiguration().setClassicCompatible(true);
		FreeMarkerRender.getConfiguration().setSharedVariable("block", new BlockDirective());
		FreeMarkerRender.getConfiguration().setSharedVariable("override", new OverrideDirective());
		FreeMarkerRender.getConfiguration().setSharedVariable("extends", new ExtendsDirective());
	}
}