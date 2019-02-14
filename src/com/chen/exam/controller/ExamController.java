package com.chen.exam.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.chen.bean.JsonResult;
import com.chen.domain.Cjd;
import com.chen.domain.DbExamer;
import com.chen.domain.Stcwb;
import com.chen.domain.Stdlb;
import com.chen.domain.Stlbb;
import com.chen.domain.Stlxb;
import com.chen.domain.Stxxb;
import com.chen.exam.service.ExamService;
import com.chen.tools.IPUtil;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

/**
 * 考试控制器
 * 
 * @author Simple
 *
 */
public class ExamController extends Controller {

	/**
	 * 定义一个接受错题数量的容器.此处用于演示，实际情况下，只能根据考生身份证号码+错题数目作为关键存储
	 */
	// public static final ArrayList<Object> total = new ArrayList<Object>();
	// public static final HashMap<String, Integer> map = new HashMap<String,
	// Integer>();
	// Map<List<String>, List<String>> mm = new HashMap<List<String>,
	// List<String>>();
	Map<String, List<Integer>> resMap = new HashMap<String, List<Integer>>();
	public static List<Integer> errorList = new ArrayList<Integer>();

	/**
	 * 默认首页方法
	 */
	public void index() {
		render("zhunkaozheng.html");
	}

	public void startExam() {
		render("index.html");
	}

	public void sendSTDL() {
		renderJson(Db.find("select * from stdlb"));
	}

	public void sendSTLB() {
		renderJson(Db.find("select * from stlbb"));
	}

	public void sendSTLX() {
		renderJson(Db.find("select * from stlxb"));
	}

	public void addSTDL() {
		String NAME = getPara("NAME");
		String NOTE = getPara("NOTE");

		Record record = new Record().set("NAME", NAME).set("NOTE", NOTE);
		Db.save("stdlb", record);
		System.out.println("----" + "保存" + "----");
		redirect("/stdl.html");
	}

	public void editSTDL() {
		int ID = getParaToInt("id");
		String NAME = getPara("NAME");
		String NOTE = getPara("NOTE");
		new Stdlb().findByIdLoadColumns(ID, "ID").set("NAME", NAME)
				.set("NOTE", NOTE).update();
		redirect("/stdl.html");
	}

	public void delSTDL() {
		int id = getParaToInt("id");
		Db.deleteById("stdlb", id);
		renderJson("删除成功");
	}

	/**
	 * 
	 */
	public void saveTM_pic() {
		// 获取上传的文件
		@SuppressWarnings("unused")
		UploadFile files = getFile(getPara("file"), "");
		renderJson("上传成功");
	}

	/**
	 * 新增试题页面
	 */
	public void saveTM() {
		// 获取上传的文件
		// UploadFile files = getFile(getPara("file"),"");
		// 组织数据
		String tm = getPara("tm");
		String stdl = getPara("stdl");
		String stlx = getPara("stlx");
		String stlb = getPara("stlb");
		String answer = getPara("answer");
		String A_answer = getPara("A_answer");
		String B_answer = getPara("B_answer");
		String C_answer = getPara("C_answer");
		String D_answer = getPara("D_answer");
		System.out.println(A_answer + "<--->" + B_answer + "<--->" + C_answer);
		Boolean flage = new ExamService().saveTM(tm, stdl, stlx, stlb, answer,
				A_answer, B_answer, C_answer, D_answer);
		if (flage) {
			renderJson(new JsonResult(true, "保存成功"));
		} else {
			renderJson("{\"msg\":\"保存失败，请稍后再试！\"}");
		}
	}

	/**
	 * 新增试题方法
	 */
	public void addpage() {
		List<Stdlb> stdlbs = new ExamService().getStdlb();
		List<Stlbb> stlbbs = new ExamService().getStlbb();
		List<Stlxb> stlxbs = new ExamService().getStlxbs();
		ArrayList<List<?>> data = new ArrayList<List<?>>();
		data.add(stdlbs);
		data.add(stlbbs);
		data.add(stlxbs);
		renderJson(data);

	}

	/**
	 * 删除试题
	 */
	public void delete_st() {
		int id = getParaToInt("id");
		boolean flage = new ExamService().deleteByid(id);
		if (flage) {
			renderJson(new JsonResult(true, "删除成功！"));
		} else {
			renderJson(new JsonResult(false, "系统错误，请稍后再试...！"));
		}
	}

	/**
	 * 查询试题信息
	 */
	public void allST() {
		String sql = null;
		if (getPara("name") != null) {
			String name = getPara("name");
			sql = "SELECT * FROM stxxb WHERE sttm LIKE '%" + name + "%'";
		} else {
			sql = "SELECT * FROM stxxb";
		}
		System.out.println("查询题目信息SQL:" + sql);
		renderJson(Stxxb.dao.find(sql));
	}

	public void pageST() {

		Map<String, Object> map = new HashMap<String, Object>();

		int page = getParaToInt("page");
		int rows = getParaToInt("rows");
		int currpage = (page - 1) * rows;
		System.out.println(currpage);
		String where = null;
		String sql = null;
		if (getPara("name") != null) {
			where = getPara("name");
			map.put("total", new ExamService().count(where));
			sql = " select stlbb.NAME as STLB, stdlb.NAME as STDL, stlxb.NAME as STLX,STTM,ZQDA " +
					"from stxxb, stdlb, stlbb, stlxb " +
					"where stxxb.STLB = stlbb.ID AND stxxb.STDL = stdlb.ID AND stxxb.STLX = stlxb.ID AND like '%"
					+ where + "%' limit " + currpage + ",10";

		} else {
			map.put("total", new ExamService().count());
			sql = " select stlbb.NAME as STLB, stdlb.NAME as STDL, stlxb.NAME as STLX,STTM,ZQDA " +
					"from stxxb, stdlb, stlbb, stlxb " +
					"where stxxb.STLB = stlbb.ID AND stxxb.STDL = stdlb.ID AND stxxb.STLX = stlxb.ID  limit "
					+ currpage + ",10 ";
		}
		// map.put("total", 914);
		map.put("rows", Db.find(sql));
		renderJson(map);
	}
	//该方法用于管理后台显示图标信息
	/**
	 * 返回统计出来各个试题类型总计做错的试题
	 */
	public void findError(){
		String sql = "SELECT stdlb.`NAME` AS name, SUM(stcwb.`TOTAL`) AS value FROM stcwb LEFT JOIN stxxb ON stcwb.`TMID` = stxxb.`ID` LEFT JOIN stdlb ON stdlb.`ID`=stxxb.`STDL` GROUP BY stxxb.`STDL` ";
		List<Record> list = Db.find(sql);
		renderJson(list);	
	}
	
	/**
	 * 返回统计出的试题类别信息，作为图表展示信息
	 */
	public void findSTLB(){
		String sql = "SELECT stlbb.`NAME` AS name,COUNT(*) AS value FROM stxxb LEFT JOIN stlbb ON stlbb.`ID`=stxxb.`STLB` GROUP BY stxxb.`STLB`;";
		List<Record> list = Db.find(sql);
		renderJson(list);
	}
	
	public void findSTLX(){
		String sql = "SELECT stlxb.`NAME` AS name,COUNT(*) AS value FROM stxxb LEFT JOIN stlxb ON stlxb.`ID`=stxxb.`STLX` GROUP BY stxxb.`STLX`;";
		List<Record> list = Db.find(sql);
		renderJson(list);
	}
	
	public void findSTDL(){
		String sql = "SELECT stdlb.`NAME` AS name,COUNT(*) AS value FROM stxxb LEFT JOIN stdlb ON stdlb.`ID`=stxxb.`STDL` GROUP BY stxxb.`STDL`;";
		List<Record> list = Db.find(sql);
		renderJson(list);
	}
	/**
	 * 根据试题编号查询试题
	 */
	public void findById() {
		// TODO 以后开发

	}

	/**
	 * 根据试题的类型查询
	 */
	public void findByLX() {
		// TODO 以后开发
	}

	/**
	 * 根据试题的考点查询试题
	 */
	public void findByZSD() {
		// TODO 以后开发
	}

	/**
	 * 根据试题的大类查询
	 */
	public void findBySTDL() {
		// TODO 以后开发
	}

	/**
	 * 考试随机出题，已经测试通过...
	 */
	public void sendST() {
		List<Stxxb> stxxbs = new ExamService().selectExam();
		renderJson(stxxbs);
	}

	/**
	 * 返回错误试题的解析
	 */
	public void sendInfo() {
		// TODO 以后开发
	}

	/**
	 * 根据试题编号查询正确答案---APP
	 */
	public void findByIdToAnswer() {
		String tid = getPara("tid");
		String answer = getPara("answer");
		String info = null;
		System.out.println("编号:" + Integer.parseInt(tid) + "   " + "答案:"
				+ answer);
		String sql = " SELECT count(*) FROM stxxb WHERE id= '"
				+ Integer.parseInt(tid) + "' AND ZQDA = '" + answer + "'";
		long num = Db.queryLong(sql);
		System.out.println(num == 1);
		if (num == 1) {
			info = "{\"msg\":\"恭喜您！回答正确。\",\"count\":\"Success\"}";
		} else {
			info = "{\"msg\":\"很遗憾！回答错误。\",\"count\":\"Error\"}";
		}
		renderJson(info);
	}

	/**
	 * 根据试题的id和用户先责的答案判断是否正确
	 */
	public void findByIdAndAnswerToMark() {
		// 返回给前台界面的通知结果
		String info = null;
		// 首先获取所需要的参数
		String t_id = getPara("t_id");
		String t_answer = getPara("t_answer");
		// 调用Service
		boolean result = new ExamService().isTrue(t_id, t_answer);

		// 核心代码处理
		/**
		 * ①如果提交的答案是对的，则不处理，没有任何逻辑 ②如果提交的答案是错误的： 如果错题的总是大于10，则通知前端考试结束
		 * 把错题的id写入到错题的数据表中
		 */
		if (result == true) {
			System.out.println("--->回答正确<---");
			info = "{\"msg\":\"回答正确！\"}";
		}
		if (result == false) {
			// 处理错误试题之写入到错题信息表中
			boolean flage = new ExamService().savaToSTCWB(t_id);
			if (flage) {
				System.out.println("--->写入到错误试题信息表成功<---");
			}
			// 接受现在已经回答错误试题的总数
			// map.put((String) getSessionAttr("ip")+(Math.random()*100+1),
			// Integer.parseInt(t_id));
			// int error = map.size();
			// String now_id = getSessionAttr("id_num");
			System.out.println("准备放入数组元素的错误试题编号:" + t_id);
			errorList.add(Integer.parseInt(t_id));
			// resMap.put(now_id, errorList );

			int error = errorList.size();
			System.out.println("现在已经回答错误题目的总数:" + error);
			/**
			 * 这里后台验证应该取消。在前台已经验证，我已经注释了
			 */
			// //判断是否错误超过10道题
			// if (error>11) {
			// info = "{\"msg\":\"您的成绩已经不合格了，结束考试！\"}";
			// System.out.println("^-^  结束考试   ^-^");
			// }else {
			// //记录错误信息
			// //返回给前台一个错误的提示信息
			// System.out.println("正在返回错误提示信息...");
			// info =
			// "{\"msg\":\"试题的提示信息，应该从数据库根据题目的编号获取显示信息。\",\"count\":\"测试\"}";
			// }
			// // //错误的数目≤10，成绩合格，考试结束
			// // renderJson("{\"Error\":{\"msg\":\"成绩合格，考试结束！\"}}");
			info = "{\"msg\":\"回答错误\",\"count\":\"测试\"}";
		}
		renderJson(info);
	}

	/**
	 * 获取最后的成绩
	 * 
	 * @return 最终分数
	 */
	public void SvaeToMark() {
		String id_card = getPara("id_card");
		String mark = getPara("mark");
		String photo = getPara("photo");
		System.out.println("---SvaeToMark---" + mark);
		// 定义时间格式化
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(photo);
		Cjd cjd = new Cjd();
		cjd.setIdCard(id_card);
		cjd.setMark(Integer.parseInt(mark));
		cjd.setPhoto1(photo);
		cjd.setRegtime(sm.format(new Date()));
		cjd.save();
		redirect("/Views/Home/index.html");
	}

	public void mark() {
		resMap.put((java.lang.String) getSessionAttr("id_num"), errorList);
		String info = null;
		// resMap.put("622726199304282052",errorList );
		// String mok = getSessionAttr("id_num");
		// System.out.println("@@123"+resMap.get("622726199304282052"));
		// System.out.println(mok+"当前分数考生的身份证编号");
		// // System.out.println(resMap.values()+"@@@");
		// // System.out.println("获取分数中得到Map需要的key:"+getSessionAttr("id_num"));
		// // System.out.println("OOOOOOOOO"+(List<Integer>)resMap.get(mok));
		// // System.out.println("!!!!"+ resMap.get(getSessionAttr("id_num")) );
		// List<Integer> errList = resMap.get("622726199304282052");
		System.out.println("这里记录的是已经回答错误的题目的总数:" + errorList.size());
		int count = errorList.size();
		if (count == 0) {
			info = "您当前分数是:100 分，请认真作答！";
		}
		int now_mark = 100 - count;
		info = "您当前分数是:" + now_mark + "分，请认真作答！";
		renderJson("{\"msg\":\"" + info + "\"}");
	}

	/**
	 * 根据客户端IP获取考生信息
	 */
	public void getZKZ() {
		HttpServletRequest request = getRequest();
//		String IP = IPUtil.getRealIpAddr(request);
		String IP = IPUtil.getRealIpAddr(request);
		setSessionAttr("id_num", new ExamService().getExam_idCard(IP));
		System.out.println("从session中获取用户的身份证编号:" + getSessionAttr("id_num"));
		List<DbExamer> examers = new ExamService().getUserToZKZ(IP);
		renderJson(examers);
	}

	/**
	 * 考试服务器计时
	 */
	public void DJS() {
		// TODO 以后开发
	}

	/**
	 * 试题写入到xml文件，已经测试通过...
	 */
	public void writeXML() {
		List<Stxxb> stxxbs = new ExamService().selectExam();

		// 用工厂类创建一个document实例
		Document doc = DocumentHelper.createDocument();
		// 创建根元素emps
		Element rootEle = doc.addElement("试卷");
		// 添加注释
		rootEle.addComment("这是一个dom4j生成的xml文件");

		int i = 0;
		for (Stxxb stxxb : stxxbs) {

			Element empEle = rootEle.addElement("试题");
			Element xh = empEle.addElement("序号");
			xh.setText(Integer.toString(++i));

			Element th = empEle.addElement("题号");
			th.setText(stxxb.getID().toString());

			Element tm = empEle.addElement("题目");
			tm.setText(stxxb.getSTTM());

			Element oa = empEle.addElement("选项A");
			oa.setText(stxxb.getXXA());

			Element ob = empEle.addElement("选项B");
			ob.setText(stxxb.getXXB());

			Element oc = empEle.addElement("选项C");
			oc.setText(stxxb.getXXC());

			Element od = empEle.addElement("选项D");
			od.setText(stxxb.getXXD());

			Element answer = empEle.addElement("正确答案");
			answer.setText(stxxb.getZQDA());
		}

		// 将document中的内容写入文件中
		try {
			// Writer out = new FileWriter("考生试题备份/emps.xml");
			FileOutputStream out = new FileOutputStream(new File(
					"考生试题备份/emps.xml"));
			// 格式化输出,类型IE浏览一样
			OutputFormat format = OutputFormat.createPrettyPrint();
			// OutputFormat format = OutputFormat.createCompactFormat();
			format.setEncoding("UTF-8");
			// 创建写出对象
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
			System.out.println("生成emps.xml成功。");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("失败了。");
		}
	}

	/**
	 * 返回错误数目最高的前10道题的结果
	 */
	public void rsError() {
		String sql = "select * from stcwb order by TOTAL DESC LIMIT 5";
		List<Stcwb> stcwbs = Stcwb.dao.find(sql);
		renderJson(stcwbs);
	}

	/**
	 * 查询成绩：SELECT cjd.`mark` ,cjd.`regtime`,db_examer.`name` FROM cjd LEFT JOIN
	 * db_examer ON cjd.`id_card`=db_examer.`id_card` AND
	 * db_examer.`name`='胡欣欣';
	 */
	public void getMark() {
		String sql = null;
		if (getPara("name") != null) {
			String name = getPara("name");
			sql = "SELECT cjd.`mark`,cjd.`id_card` ,cjd.`regtime`,db_examer.`name` FROM cjd LEFT JOIN db_examer ON cjd.`id_card`=db_examer.`id_card` WHERE db_examer.`name` LIKE '%"
					+ name + "%';";
		} else {
			sql = "SELECT cjd.`mark`,cjd.`id_card` ,cjd.`regtime`,db_examer.`name` FROM cjd LEFT JOIN db_examer ON cjd.`id_card`=db_examer.`id_card` ;";
		}
		System.out.println("查询考生信息SQL:" + sql);
		renderJson(DbExamer.dao.find(sql));
	}

	public static void main(String[] args) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sm.format(new Date()));
		// // Map<List<String>, List<String>> mm = new HashMap<List<String>,
		// List<String>>();
		// Map<String, List<Integer>> resMap = new HashMap<String,
		// List<Integer>>();
		// String id_num = "622726199304282052";
		// List<Integer> hList = new ArrayList<Integer>();
		// hList.add(115);
		// hList.add(100);
		// hList.add(112);
		// hList.add(132);
		// hList.add(178);
		// hList.add(125);
		// hList.add(110);
		// hList.add(15);
		// hList.add(25);
		//
		// resMap.put(id_num, hList);
		// String reg = "^\\d+$";
		// // System.out.println(resMap);
		// // System.out.println(resMap.size());
		// // System.out.println(resMap.entrySet());
		// // System.out.println(resMap.values()+"@");
		// // System.out.println("获取key"+resMap.keySet());
	}

}
