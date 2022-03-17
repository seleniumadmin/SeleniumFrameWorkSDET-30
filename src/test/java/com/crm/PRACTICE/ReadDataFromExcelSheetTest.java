package com.crm.PRACTICE;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ReadDataFromExcelSheetTest {
	@Test
	public void readDataFromExcel() throws Throwable {
		//step1:load excel file
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Data.xlsx");
		
		//step2 create a workbook
		Workbook wb=WorkbookFactory.create(fis);
		//get sheet
		Sheet sh=wb.getSheet("Sheet1");
		//sheet 4 get the row
		Row ro=sh.getRow(0);
		//step5 get the cell
		Cell ce=ro.getCell(0);
		
		//step6 read the data from the cell
				String value=ce.getStringCellValue();
		        System.out.println(value);
		
		
	}

}
