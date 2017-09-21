package com.chen.config;

import com.chen.tools.DebugInfo;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 实现访问方法进行拦截，并且写入日志文件
 * @author Simple
 *
 */
public class LogInterceptors implements Interceptor{

	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Controller invController = inv.getController();
		String actionKey = inv.getActionKey();
		String controllerKey = inv.getControllerKey();
		String methodName = inv.getMethodName();
		/**
		 * 把这些信息写入到日志文件中
		 */
		DebugInfo.log(methodName, "正在访问...");

		inv.invoke();
		
		System.out.println("|访问成功!");
	}

}
