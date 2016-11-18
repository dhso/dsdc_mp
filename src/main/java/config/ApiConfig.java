package config;

import java.sql.Timestamp;

import cn.dreampie.orm.ActiveRecordPlugin;
import cn.dreampie.orm.provider.druid.DruidDataSourceProvider;
import cn.dreampie.route.cache.CacheInterceptor;
import cn.dreampie.route.config.Config;
import cn.dreampie.route.config.ConstantLoader;
import cn.dreampie.route.config.HandlerLoader;
import cn.dreampie.route.config.InterceptorLoader;
import cn.dreampie.route.config.PluginLoader;
import cn.dreampie.route.config.ResourceLoader;
import cn.dreampie.route.handler.cors.CORSHandler;
import cn.dreampie.route.interceptor.transaction.TransactionInterceptor;
import frame.jsonSerializer.JsonTimestampSerializer;

/**
 * Created by hadong on 15-1-16.
 */
public class ApiConfig extends Config {
	public void configConstant(ConstantLoader constantLoader) {
		constantLoader.addJsonSerializer(Timestamp.class, new JsonTimestampSerializer());
		// constantLoader.addJsonSerializerFeature(SerializerFeature.WriteMapNullValue);
	}

	public void configResource(ResourceLoader resourceLoader) {
		// 设置resource的目录 减少启动扫描目录
		resourceLoader.addIncludePackages("resource");
	}

	public void configPlugin(PluginLoader pluginLoader) {
		DruidDataSourceProvider ddsp = new DruidDataSourceProvider("default");
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(ddsp);
		activeRecordPlugin.addIncludePackages("resource");
		pluginLoader.add(activeRecordPlugin);
		// pluginLoader.add(new QuartzPlugin());
	}

	public void configInterceptor(InterceptorLoader interceptorLoader) {
		// 缓存
		interceptorLoader.add(new CacheInterceptor());
		// 事务的拦截器 @Transaction
		interceptorLoader.add(new TransactionInterceptor());
	}

	public void configHandler(HandlerLoader handlerLoader) {
		// 跨域
		handlerLoader.add(new CORSHandler());
	}
}
