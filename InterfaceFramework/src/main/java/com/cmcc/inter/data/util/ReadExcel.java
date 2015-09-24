package com.cmcc.inter.data.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author iversoncl
 * @Date 2015年7月8日
 * @Project InterfaceFramework
 */
public class ReadExcel {

	/**
	 * @Description:判断后读取excel文件
	 * @param path
	 * @return
	 * @throws IOException
	 *             List<LinkedHashMap>
	 * @author: iversoncl
	 * @time:2015年7月8日 下午2:02:00
	 */
	public List<Object[]> readExcel(String path) throws IOException {
		if (path == null || ExcelCommon.EMPTY.equals(path)) {
			return null;
		} else {
			String postfix = ExcelUtil.getPostfix(path);
			if (!ExcelCommon.EMPTY.equals(postfix)) {
				if (ExcelCommon.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readXls(path);
				} else if (ExcelCommon.OFFICE_EXCEL_2010_POSTFIX
						.equals(postfix)) {
					return readXlsx(path);
				}
			} else {
				System.out.println(path + ExcelCommon.NOT_EXCEL_FILE);
			}
		}
		return null;
	}

	/**
	 * @Description:读取xlsx结尾excel
	 * @param path
	 * @return
	 * @throws IOException
	 *             List<LinkedHashMap>
	 * @author: iversoncl
	 * @time:2015年7月8日 下午2:01:52
	 */
	public List<Object[]> readXlsx(String path) throws IOException {
		System.out.println(ExcelCommon.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		List<Object[]> list = new ArrayList<Object[]>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					Object[] object = new Object[xssfRow.getLastCellNum()];
					for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
						object[cellNum] = getValue(xssfRow.getCell(cellNum));

					}
					list.add(object);
				}
			}
		}
		return list;
	}

	/**
	 * @Description:读取xls结尾excel
	 * @param path
	 * @return
	 * @throws IOException
	 *             List<LinkedHashMap>
	 * @author: iversoncl
	 * @time:2015年7月8日 下午2:01:16
	 */
	public List<Object[]> readXls(String path) throws IOException {
		System.out.println(ExcelCommon.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<Object[]> list = new ArrayList<Object[]>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			//get excel sheet
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				//get row1 data
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					//get row data 字段 
					Object[] object = new Object[hssfRow.getLastCellNum()];
					for (int cellNum = 0; cellNum < hssfRow.getLastCellNum(); cellNum++) {
						//每个字段的值
						object[cellNum] = getValue(hssfRow.getCell(cellNum));
					}
					list.add(object);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("static-access")
	private Object getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return xssfRow.getBooleanCellValue();
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return xssfRow.getNumericCellValue();
		} else {
			return xssfRow.getStringCellValue();
		}
	}

	@SuppressWarnings("static-access")
	private Object getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return hssfCell.getBooleanCellValue();
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return hssfCell.getNumericCellValue();
		} else {
			return hssfCell.getStringCellValue();
		}
	}
	
	 public static void main(String[] args) throws IOException {
	        String excel2003_2007 = ExcelCommon.STUDENT_INFO_XLS_PATH;
	        String excel2010 = ExcelCommon.STUDENT_INFO_XLSX_PATH;
	        // read the 2003-2007 excel
	        List<Object []> list = new ReadExcel().readExcel(excel2003_2007);
	        if (list != null) {
	            for(Object [] s:list){
	            	for(int i=0;i<s.length;i++){
	            		System.out.print("" +s[i]+ "," );
	            	}
	            	 System.out.println();
	            }
	           
	        }
	        System.out.println("======================================");
	        // read the 2010 excel
	        List<Object []> list1 = new ReadExcel().readExcel(excel2010);
	        if (list1 != null) {
	        	 for(int i=0;i<list1.size();i++){
		            	System.out.println("No. : " + list1.get(i)[0] + ", name : " + list1.get(i)[1] + ", age : " + list1.get(i)[2] + ", score : " + list1.get(i)[3]);
		            }
	        }
	    }
	    
}
