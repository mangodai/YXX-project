/**
 * 
 */
package com.chen.exam.utils.excelToMysql.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.chen.exam.utils.excelToMysql.common.Common;
import com.chen.exam.utils.excelToMysql.excel.vo.TM;



/**
 * 获取excel中的数据
 * @author Simple
 *
 */
public class ReadExcel {

	public List<TM> readXls() throws IOException {
		InputStream is = new FileInputStream(Common.EXCEL_PATH);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		TM tm = null;
		List<TM> list = new ArrayList<TM>();

		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}

			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					tm = new TM();
					HSSFCell STLB = hssfRow.getCell(0);
					HSSFCell STDL = hssfRow.getCell(9);
					HSSFCell STLX = hssfRow.getCell(1);
					HSSFCell STTM = hssfRow.getCell(2);
					HSSFCell IMAGE = hssfRow.getCell(3);
					HSSFCell XXA = hssfRow.getCell(4);
					HSSFCell XXB = hssfRow.getCell(5);
					HSSFCell XXC = hssfRow.getCell(6);
					HSSFCell XXD = hssfRow.getCell(7);
					HSSFCell ZQDA = hssfRow.getCell(8);
					HSSFCell STJX = hssfRow.getCell(10);
					
					tm.setSTLB(getValue(STLB));
					tm.setSTDL(getValue(STDL));
					tm.setSTLX(getValue(STLX));
					tm.setSTTM(getValue(STTM));
					tm.setIMAGE(getValue(IMAGE));
					tm.setXXA(getValue(XXA));
					tm.setXXB(getValue(XXB));
					tm.setXXC(getValue(XXC));
					tm.setXXD(getValue(XXD));
					tm.setZQDA(getValue(ZQDA));
					tm.setSTJX(getValue(STJX));
					list.add(tm);
				}
			}
		}
		return list;
	}
	
	 @SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }
}
