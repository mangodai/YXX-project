package com.chen.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.chen.domain.DbExamer;
import com.chen.domain.DbPc;
import com.chen.domain.Stxxb;
import com.chen.tools.IPUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class AdminService {
	/**
	 * 判断后台用户是否存在
	 * @param userName
	 * @param password
	 * @return
	 */
	public Boolean isLogin(String userName , String password){
		StringBuffer sb = new StringBuffer();
		//SELECT COUNT(*) FROM admin_table WHERE userName	= '张三' AND password = '123456'
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM admin_table ");
		sb.append(" WHERE username	= '"+userName+"' AND PASSWORD = '"+password+"' ");
		long result = Db.queryLong(sb.toString());
		if (result==1) {
			System.out.println("存在用户");
			return true;
		}else {
			System.out.println("不存在");
			return false;
		}		
	}
	
	/**
	 * 保存考生信息
	 * @param name
	 * @param idCardNum
	 * @param sex
	 * @param schoolName
	 * @param base64photo
	 * @return
	 */
	public Boolean savaExamer(String name,String idCardNum , String sex ,String schoolName ,String base64photo,String pcName){

		DbExamer dbExamer = new DbExamer();
//		dbExamer.setName(name);
//		dbExamer.setIdCard(idCardNum);
//		dbExamer.setSex(sex);
//		dbExamer.setSchool(schoolName);
//		dbExamer.setImg(base64photo);
//		dbExamer.setId(1);
////		dbExamer.setPcId(Integer.parseInt(pcName.toString()));
//		Boolean flage = dbExamer.save();
		dbExamer.set("name", name).set("id_card", idCardNum).set("sex", sex).set("school", schoolName).set("img", base64photo).set("pc_id", findPcIDBypcName(pcName)).save();
//		new Stxxb().findByIdLoadColumns(25, "id").set("name", "James").update();
		System.out.println("@@@@@@@@@@@@@@@@@@@"+findPcIDBypcName(pcName));
		new DbPc().findByIdLoadColumns(findPcIDBypcName(pcName), "id").set("state", 1).update();
		return true;		
	}
	
	public int findPcIDBypcName(String pcName){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT id FROM db_pc WHERE pcname='"+pcName+"'");
		return Db.queryInt(sb.toString());
		
	}
	
	public List<DbPc> getPCInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM db_pc WHERE state=0  ORDER BY RAND() LIMIT 1 ");
		List<DbPc> pc = DbPc.dao.find(sb.toString());
		return pc;
		
	}
	
	/**
	 * 随机分配pc给考生
	 * @return
	 */
	public List<DbPc> sendPC(String ip){
		
		//SELECT * FROM db_pc WHERE state=1 ORDER BY RAND() LIMIT 1
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT name,state FROM db_pc WHERE state=0  ORDER BY RAND() LIMIT 1 ");
		List<DbPc> pc = DbPc.dao.find(sb.toString());
		return pc;
		
	}
	

}
