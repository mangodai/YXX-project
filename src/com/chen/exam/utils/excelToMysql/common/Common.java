/**
 * 
 */
package com.chen.exam.utils.excelToMysql.common;

/**
 * 对数据库的操作
 * @author Simple
 *
 */
public class Common {

	// connect the database
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_NAME = "chen";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "123456";
	public static final String IP = "127.0.0.1";
	public static final String PORT = "3306";
	public static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + DB_NAME;
	
	// common
	public static final String EXCEL_PATH = "lib/st.xls";
	
	// sql
	public static final String INSERT_STUDENT_SQL = "insert into STXXB(STLB,STDL,STLX,STTM,IMAGE,XXA,XXB,XXC,XXD,ZQDA,STJX) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_STUDENT_SQL = "update STXXB set STLB=?,STDL=?,STLX=?,IMAGE=?,XXA=?,XXB=?,XXC=?,XXD=?,ZQDA=?,STJX=? where STTM = ? ";
	public static final String SELECT_STUDENT_ALL_SQL = "select * from STXXB";
	public static final String SELECT_STUDENT_SQL =  "select * from STXXB where ID = ";
}
