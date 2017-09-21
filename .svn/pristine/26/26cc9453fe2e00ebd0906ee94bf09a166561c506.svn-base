package com.chen.test.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chen.domain.Test1;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.ICallback;
import com.jfinal.plugin.activerecord.Record;

public class Test_1Controller extends Controller{
	
	/**
	 * 实现jfinal-ICallback
	 * @author Mr-CHEN
	 *
	 */
	class TestCallBack implements ICallback{
		
		@Override
		public Object call(Connection conn) throws SQLException {
			CallableStatement state = null;
			state = (CallableStatement) conn.prepareCall("{call chen.test1()}");
			ResultSet rs = state.executeQuery();
			java.util.List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString("name"));
			}
			rs.close();
			conn.close();
			for (int i = 0; i <list.size(); i++) {
				System.out.println(list.get(i));
			}
			return null;
		}
	}
	/**
	 * 测试存储错过的调用
	 */
	public void test1(){
//		renderJson(Db.findFirst(" CALL chen.test1()"));
		renderJson(Db.execute(new TestCallBack()));
	}
	
	public void sendData(){
		renderJson(Db.find("select * from test_1"));
	}
	
	public void addUser(){
		String firstname = getPara("firstname");
		String lastname = getPara("lastname");
		String phone = getPara("phone");
		String email = getPara("email");
		
		Record record = new Record().set("firstname", firstname).set("lastname", lastname).set("phone", phone).set("email", email);
		Db.save("test_1", record );
		System.out.println("----"+"保存"+"----");
		redirect("/test1.html");
	}
	
	public void editUser(){
		int id = getParaToInt("id");
		String firstname = getPara("firstname");
		String lastname = getPara("lastname");
		String phone = getPara("phone");
		String email = getPara("email");
		new Test1().findByIdLoadColumns(id, "id").set("firstname", firstname).set("lastname", lastname).set("phone", phone).set("email", email).update();
		redirect("/test1.html");
	}
	
	public void delUser(){
		int id = getParaToInt("id");
		Db.deleteById("test_1", id);
		renderJson("删除成功");
	}
}
