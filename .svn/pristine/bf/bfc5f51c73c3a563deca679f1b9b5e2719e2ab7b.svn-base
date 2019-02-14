/**
 * 
 */
package com.chen.exam.utils.excelToMysql.excel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.chen.exam.utils.excelToMysql.common.Common;
import com.chen.exam.utils.excelToMysql.excel.util.DbUtil;
import com.chen.exam.utils.excelToMysql.excel.vo.TM;


/**
 * 保存到excel中
 * @author Simple
 *
 */
public class SaveData2DB {

	@SuppressWarnings({ "rawtypes" })
	public void save() throws IOException, SQLException {
		ReadExcel xlsMain = new ReadExcel();
		TM tm = null;
		List<TM> list = xlsMain.readXls();

		for (int i = 0; i < list.size(); i++) {
			tm = list.get(i);
			List l = DbUtil.selectOne(Common.SELECT_STUDENT_SQL +  "'"+ tm.getID()+ "'" ,tm);
//			System.out.println(Common.SELECT_STUDENT_SQL + "'"+ tm.getID()+ "'");
			if (!l.contains(1)) {
				DbUtil.insert(Common.INSERT_STUDENT_SQL, tm);
			} else {
				System.out.println("The Record was Exist : 试题题目 = " + tm.getSTTM() + "  and has been throw away!");
			}
		}
	}
}
