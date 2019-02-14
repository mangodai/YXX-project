package com.chen.exam.utils.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


public class Dom4jXML {
	/**
	 * 利用dom4j生成XML
	 * 
	 * @author Daniel Cheng
	 */

	public void createXML() {
		// 用工厂类创建一个document实例
		Document doc = DocumentHelper.createDocument();
		// 创建根元素emps
		Element rootEle = doc.addElement("emps");
		// 添加注释
		rootEle.addComment("这是一个dom4j生成的xml文件");
		
		for (int i = 1; i <= 100; i++) {
			// emps根节点下创建一个emp节点
			Element empEle = rootEle.addElement("emp");
			// emp添加属性id="1"
			empEle.addAttribute("id", ""+i);
			// emp节点下创建一个name节点
			Element nameEle = empEle.addElement("name");
			// name节点下创建一个文本节点zhangsan
			nameEle.setText("111");
			// 再为name节点创建一个兄弟节点
			Element sexEle = empEle.addElement("sex");
			sexEle.setText("111");
			// 再为name节点创建一个兄弟节点
			Element sex1Ele = empEle.addElement("sex1");
			sex1Ele.setText("111");
			// 再为name节点创建一个兄弟节点
			Element sex2Ele = empEle.addElement("sex2");
			sex2Ele.setText("111");
			// 再为name节点创建一个兄弟节点
			Element sex3Ele = empEle.addElement("sex3");
			sex3Ele.setText("111");
			
		}
		
		// 将document中的内容写入文件中
		try {
//			Writer out = new FileWriter("考生试题备份/emps.xml");
			FileOutputStream out=new FileOutputStream(new File("考生试题备份/emps.xml"));
			// 格式化输出,类型IE浏览一样
			OutputFormat format = OutputFormat.createPrettyPrint();
			// OutputFormat format = OutputFormat.createCompactFormat();
			format.setEncoding("UTF-8");
			format.setIndent("");
			format.setLineSeparator("/n");
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

	
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();// 这段代码放在程序执行前
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
		Date beginTime = new Date(begin);
		System.out.println("开始生成XML文件的时间:"+formatter.format(beginTime));
		new Dom4jXML().createXML();
		long end = System.currentTimeMillis();// 这段代码放在程序执行后
		Date endTIme = new Date(end);
		System.out.println("结束生成XML文件的时间:"+formatter.format(endTIme));
		long hs = end-begin;
		System.out.println("耗时：" + hs+ "毫秒");
		
		
		/**
		 * 获取当前时间
		 */
//		long currentTime = System.currentTimeMillis();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
//		Date date = new Date(currentTime);
//		System.out.println(formatter.format(date));
		
	}

}