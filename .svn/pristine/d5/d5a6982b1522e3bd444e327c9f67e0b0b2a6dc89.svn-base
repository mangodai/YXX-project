/**
 * 
 */
package com.chen.exam.utils.excelToMysql.excel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chen.exam.utils.excelToMysql.common.Common;
import com.chen.exam.utils.excelToMysql.excel.vo.TM;



/**
 * DBUtil
 * @author Simple
 *
 */
public class DbUtil {

	/**
	 * @param sql
	 */
	public static void insert(String sql, TM tm) throws SQLException {
//		System.out.println(".................."+sql+"......");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Common.DRIVER);
			conn = DriverManager.getConnection(Common.URL, Common.USERNAME, Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, tm.getSTLB());
			ps.setString(2, tm.getSTDL());
			ps.setString(3, tm.getSTLX());
			ps.setString(4, tm.getSTTM());
			ps.setString(5, tm.getIMAGE());
			ps.setString(6, tm.getXXA());
			ps.setString(7, tm.getXXB());
			ps.setString(8, tm.getXXC());
			ps.setString(9, tm.getXXD());
			ps.setString(10, tm.getZQDA());
			ps.setString(11, tm.getSTJX());
			boolean flag = ps.execute();
			if(!flag){
				System.out.println("Save data : 试题类别 = " + tm.getSTLB() + " , 试题大类 = " + tm.getSTDL() + ", 试题类型 = " + tm.getSTLX() + " , 试题题目 = " + tm.getSTTM() + ""
						+ ", 图片位置 = " + tm.getIMAGE() + " succeed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List selectOne(String sql, TM tm) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			Class.forName(Common.DRIVER);
			conn = DriverManager.getConnection(Common.URL, Common.USERNAME, Common.PASSWORD);
//			System.out.println("---------"+sql+"----");
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("STLB").equals(tm.getSTLB()) 
						|| rs.getString("STDL").equals(tm.getSTDL())
						|| rs.getString("STLX").equals(tm.getSTLX())
						|| rs.getString("STTM").equals(tm.getSTTM())
						|| rs.getString("IMAGE").equals(tm.getIMAGE())
						|| rs.getString("XXA").equals(tm.getXXA())
						|| rs.getString("XXB").equals(tm.getXXB())
						|| rs.getString("XXC").equals(tm.getXXC())
						|| rs.getString("XXD").equals(tm.getXXD())
						|| rs.getString("ZQDA").equals(tm.getZQDA())
						|| rs.getString("STJX").equals(tm.getSTJX())){
					list.add(tm);
				}else{
					list.add(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}
	
	
	public static ResultSet selectAll(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(Common.DRIVER);
			conn = DriverManager.getConnection(Common.URL, Common.USERNAME, Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return rs;
	}

}
