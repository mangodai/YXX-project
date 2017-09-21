package com.chen.exam.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.chen.domain.DbExamer;
import com.chen.domain.Stcwb;
import com.chen.domain.Stdlb;
import com.chen.domain.Stlbb;
import com.chen.domain.Stlxb;
import com.chen.domain.Stxxb;
import com.chen.tools.IPUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 考试service
 */
public class ExamService {

	/**
	 * 出题
	 * 
	 * @return
	 */
	public List<Stxxb> selectExam() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from stxxb order by rand() limit 100 ");
		List<Stxxb> stxxbs = Stxxb.dao.find(sb.toString());
		return stxxbs;
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteByid(int id) {
		Boolean flage = Stxxb.dao.deleteById(id);
		return flage;
	}

	public Stxxb findByid(int id) {
		Stxxb stxxb = Stxxb.dao.findById(id);
		return stxxb;

	}
	//获取全部
	public long count(){
		long count = Db.queryLong("select count(*) from stxxb");
		System.out.println("获取的总记录数："+count);
		return count ;

	}
	//获取全部,根据参数
		public long count(String name){
			long count = Db.queryLong("select count(*) from stxxb where STTM like '%"+name+"%'");
			System.out.println("获取的总记录数："+count);
			return count ;

		}
	/**
	 * 根据试题编号、用户答案判断是否正确
	 * 
	 * @param t_id
	 * @param t_answer
	 * @return
	 */
	public boolean isTrue(String t_id, String t_answer) {
		// 切换答案的状态
		switch (t_answer) {
		case "A":
			t_answer = "A";
			break;
		case "B":
			t_answer = "B";
			break;
		case "C":
			t_answer = "C";
			break;
		case "D":
			t_answer = "D";
			break;
		case "Y":
			t_answer = "Y";
			break;
		case "N":
			t_answer = "N";
			break;

		default:
			break;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(1) ");
		sb.append(" FROM stxxb ");
		sb.append(" WHERE id='" + t_id + "' AND ZQDA ='" + t_answer + "'; ");
		System.out.println("判断答案是否正确:" + sb.toString());

		long result = Db.queryLong(sb.toString());
	//	System.out.println(result);

		if (result==1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据试题的编号写入到错题信息表中
	 * 首先判断这个错误的题目编号是否已经存在错题信息表中;如果存在，则直接update数据;不存在的话，直接执行insert
	 * 
	 * @param t_id
	 */
	public boolean savaToSTCWB(String t_id) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" SELECT COUNT(1) FROM stcwb WHERE TMID='" + t_id
				+ "' ");
		long total = Db.queryLong(stringBuffer.toString());
		if (total==0) {
			Stcwb stcwb = new Stcwb();
			stcwb.setTMID(Integer.parseInt(t_id));
			stcwb.setTOTAL(1);
			stcwb.save();
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(" UPDATE stcwb SET TOTAL = TOTAL+1 WHERE TMID= '"
					+ Integer.parseInt(t_id) + "' ");
			System.out.println(sb.toString());
			Db.update(sb.toString());
		}
		return true;
	}
	
	public List<DbExamer> getUserToZKZ(String ip){
		//SELECT exam.`name`,exam.`sex`,exam.`id_card`,exam.`school`,exam.`img` FROM db_examer exam LEFT JOIN db_pc pc ON exam.`pc_id`=pc.`id` AND pc.`IP`='127.0.0.1'
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT exam.`name`,exam.`sex`,exam.`id_card`,exam.`school`,exam.`img`,pc.`pcname` FROM db_examer exam LEFT JOIN db_pc pc ON exam.`pc_id`=pc.`id` WHERE pc.`IP`='"+ip+"' ");
		System.out.println(sb.toString()+"<<<>>>");
		List<DbExamer> examers = DbExamer.dao.find(sb.toString());
		return examers;
	}
	
	/**
	 * 获取所有试题类型
	 * @return
	 */
	public List<Stlxb> getStlxbs(){
		StringBuffer sb = new StringBuffer();
		sb.append(" select name,id from stlxb ");
		List<Stlxb> stlxbs = Stlxb.dao.find(sb.toString());
		return stlxbs;
	}
	
	/**
	 * 获取所有试题大类
	 * @return
	 */
	public List<Stdlb> getStdlb(){
		StringBuffer sb = new StringBuffer();
		sb.append(" select name,id from stdlb ");
		List<Stdlb> Stdlbs = Stdlb.dao.find(sb.toString());
		return Stdlbs;
	}
	
	/*
	 * 获取试题类别
	 * @return
	 */
	public List<Stlbb> getStlbb(){
		StringBuffer sb = new StringBuffer();
		sb.append(" select name,id from stlbb ");
		List<Stlbb> Stlbbs = Stlbb.dao.find(sb.toString());
		return Stlbbs;
	}
	
	
	/**
	 * 根绝当前计算机ip获取考生的身份证编号
	 */
	public String getExam_idCard(String IP){
//		HttpServletRequest request = getRequest();
//		String IP = IPUtil.getRealIpAddr(request);
		String sql = "SELECT id_card FROM db_examer LEFT JOIN db_pc ON db_pc.`id` = db_examer.`pc_id` WHERE db_pc.`IP`='"+IP+"'";
		Object sfz_id = Db.queryColumn(sql);
		return sfz_id.toString();
//		renderJson(sfz_id.toString());
	}
	
	public Boolean saveTM(String tm,String stdl,String stlx,String stlb,String answer,String A_answer,String B_answer,String C_answer,String D_answer){
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO stxxb (STLB,STDL,STLX,STTM,IMAGE,XXA,XXB,XXC,XXD,ZQDA,STJX)VALUES( (SELECT ID FROM stlbb WHERE  NAME = '"+stlb+"' ) ,(SELECT ID FROM stdlb WHERE  NAME = '"+stdl+"') ,(SELECT ID FROM stlxb WHERE  NAME = '"+stlx+"' ) ,'"+tm+"','图片名称稍后处理','"+A_answer+"','"+B_answer+"','"+C_answer+"','"+D_answer+"','"+answer+"','提示信息')");
		int state = Db.update(sb.toString());
		System.out.println("---"+state);
		if (state==1) {
			return true;
		}else {
			return false;
		}
	}
}
