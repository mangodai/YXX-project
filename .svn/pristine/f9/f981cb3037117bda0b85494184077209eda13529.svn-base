package com.chen.config;

import com.chen.admin.controller.AdminController;
import com.chen.exam.controller.ExamController;
import com.chen.exam.controller.UserController;
import com.chen.home.controller.HomeController;
import com.chen.test.controller.Test_1Controller;

/**
 * 所有的路由配置文件
 * @author Simple
 *
 */
public class Routes extends com.jfinal.config.Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/exam", ExamController.class, "/Exam");
		add("/admin", AdminController.class, "/Admin");
		add("/", HomeController.class, "/Home");
		add("/userinfo", UserController.class);
		add("/test", Test_1Controller.class,"/Test" );
	}

}
