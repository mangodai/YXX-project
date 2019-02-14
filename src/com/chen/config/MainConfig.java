package com.chen.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.chen.domain._MappingKit;
import com.chen.tools.DebugInfo;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.JspRender;
import com.jfinal.render.ViewType;

public class MainConfig extends JFinalConfig {

	/**
	 * 配置jetty启动所需的参数
	 */
	private static final String webAppDir = "WebRoot";
	private static final int port = 8080;
	private static final String context = "/";
	private static final int scanIntervalSeconds = 0;

	/**
	 * jetty启动的main方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFinal.start(webAppDir, port, context, scanIntervalSeconds);
	}

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		PropKit.use("db.txt");
		me.setEncoding("UTF-8");
		me.setViewType(ViewType.JSP);
		// 设置是否是开发模式、调试模式
		me.setDevMode(PropKit.getBoolean("devMode", true));
		// 设置默认
		me.setBaseViewPath(Constant.baseViewPath);
		// 设置404错误页面
		me.setError404View(Constant.error404PagePath);
		// 设置500错误页面
		me.setError500View(Constant.error500PagePath);
		// 设置默认的文件上传的路径
		me.setBaseUploadPath(Constant.uploadSaveDir);
		// 设置默认的下载的文件路径
		me.setBaseDownloadPath(Constant.downloadSaveDir);
		// 设置URL中间的分隔符【默认室“-”】
		me.setUrlParaSeparator(Constant.URLPARASEPARATOR);
		// 设置最大上传尺寸
		me.setMaxPostSize(Constant.MAXPOSTSIZE);

	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add(new com.chen.config.Routes());
	}

	/**
	 * 配置C3P0数据源
	 * 
	 * @return
	 */
	public static C3p0Plugin createC3p0Plugin() {

		return new C3p0Plugin(PropKit.get("mysql.jdbcUrl"),
				PropKit.get("mysql.user"), PropKit.get("mysql.password").trim());
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		DebugInfo.log("MainConfig", "初始化配置插件...");
		DebugInfo.log("MainConfig", "初始化配置数据源...");
		DruidPlugin dp = new DruidPlugin(PropKit.get("mysql.jdbcUrl"),
				PropKit.get("mysql.user"), PropKit.get("mysql.password").trim());
		StatFilter statFilter = new StatFilter();
		dp.addFilter(statFilter);
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		me.add(dp);
		DebugInfo.log("MainConfig", "初始化配置数据源数据表实体绑定...");
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		me.add(arp);
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new SessionInViewInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		me.add(new ContextPathHandler("ctx"));
		DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
		me.add(dvh); 

	}

}
