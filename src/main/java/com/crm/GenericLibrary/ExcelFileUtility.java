package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author My PC
 *
 */

public class ExcelFileUtility {
	/**
	 * This method will rread data from excel sheet and return the value when sheetname
	 * row no and cell number is specified
	 * @param SheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromExcelSheet(String SheetName,int rowNo,int cellNo) throws Throwable {
		FileInputStream fis=new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(SheetName);
		Row ro=sh.getRow(rowNo);
		Cell cel=ro.getCell(cellNo);
		String value=cel.getStringCellValue();
		return value;
	}
	/**
	 * this method will written data into excel sheet
	 * @param SheetName
	 * @param rowNo
	 * @param cellNo
	 * @param value
	 * @throws Throwable
	 */
	
	public void writeDataIntoExcel(String SheetName,int rowNo,int cellNo,String value) throws Throwable {
		FileInputStream fis=new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(SheetName);
		Row ro=sh.getRow(rowNo);
		Cell cel=ro.createCell(cellNo);
		cel.setCellValue(value);
        FileOutputStream fos=new FileOutputStream(IPathConstants.ExcelPath);
        wb.write(fos);
	}
	
	/**
	 * this method will return last row number
	 * @param sheet
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheet) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheet);
		int row=sh.getLastRowNum();
		return row;
	}
	public Object[][] readmultipleDataFromExcel(String SheetName) throws Throwable {
		FileInputStream fis=new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(SheetName);
		int lastRow=sh.getLastRowNum();
		int lastCell=sh.getRow(0).getLastCellNum();1
		
		Object[][] data=new Object[lastRow][lastCell];
		
		for(int i=0;i<lastRow;i++) {
			for(int j=0;j<lastCell;j++) {
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
		
		}
	}
