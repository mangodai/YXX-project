package com.chen.admin.validator;

import com.chen.admin.service.RandomCodeService;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator{
	
	private static final String msg = "message";
	
	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		setShortCircuit(true);
		validateRequired("username", msg, "请您输入用户名!");
		validateRequired("password", msg, "请您输入密码!");
        validateRequired("randomCode", msg, "请您输入验证码!");
         
        String inputRandomCode = c.getPara("randomCode");
        if (inputRandomCode!=null || inputRandomCode.length() !=0)
            inputRandomCode = inputRandomCode.toUpperCase();
         
        String systemRandomCode = c.getCookie("systemRandomCode");
        if (RandomCodeService.validate(inputRandomCode, systemRandomCode) == false) {
            addError(msg, "验证码输入不正确,请重新输入!");
        }
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		 c.keepPara();
	        c.render("login.html");
	}

}
