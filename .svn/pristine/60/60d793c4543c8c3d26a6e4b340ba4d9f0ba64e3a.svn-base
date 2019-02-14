package com.chen.config;
import java.io.File;

import com.jfinal.kit.PathKit;
/**
 * 定义基本的一些常量
 * @author Mr-Chen
 *
 */
public class Constant {
	/**
	 * 定义当前服务器环境是否为开发模式 默认是
	 */
	public static boolean devMode=true;
	/**
	 * url参数分隔符
	 */
	public static final String URLPARASEPARATOR="-";
	/**
	 * Views层根目录
	 */
	public static final String baseViewPath = "/Views/";
	/**
	 * 无权访问地址
	 */
	public static final String noAuthorityPagePath = "/Views/error/noauthority.html";
	/**
	 * 404页面地址
	 */
	public static final String error404PagePath = "/Views/error/404.jsp";
	/**
	 * 500页面地址
	 */
	public static final String error500PagePath = "/Views/error/500.jsp";
	/**
	 * 上传文件路径
	 */
	public static final String uploadSaveDir=PathKit.getWebRootPath() +File.separator+"upload";
	/**
	 * 下载文件路径
	 */
	public static final String downloadSaveDir=PathKit.getWebRootPath() +File.separator+"download";
	/**
	 * 定义用户sessionkey
	 */
	public static final String USER_SESSION_KEY="user";
	/**
	 * 最大上传尺寸
	 */
	public static final Integer MAXPOSTSIZE=1024*1024*5;
	/**
	 * 定义加密的的规则所需的字符串
	 */
	public static final String MD5KEY = "cyp_bysj";
	
	public static final String PATH = PathKit.getWebRootPath().replace("\\", "/")+baseViewPath;
	
	public static final String ViewsPath = "file:///"+PathKit.getWebRootPath().replace("\\", "/")+"/Views/";
	
	
	/**
	 * 定义考试返回常量
	 */
	//考试结束提示信息
	public static final String ERROR_INFO = "{\"Error\":{\"msg\":\"考试结束！\"}}";
	//考试时间到
	public static final String TIME_OUT = "{\"Error\":{\"msg\":\"考试时间到！\"}}";
	
}
