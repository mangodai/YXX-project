package com.chen.exam.controller;

import javax.servlet.http.HttpServletRequest;

import com.chen.tools.IPUtil;
import com.jfinal.core.Controller;

public class UserController extends Controller{
	
	/**
	 * 根据当前计算机查询IP地址，获取用户相关信息
	 */
	public void index(){
		//根据当前计算机ip从服务器获取考生信息
		HttpServletRequest request = this.getRequest();
		String ipAddress = IPUtil.getRealIpAddr(request);
		System.out.println("||当前计算机IP:"+ipAddress);
		//用ip地址去数据库查询相关信息，返回给welcome.html页面

	}


}
