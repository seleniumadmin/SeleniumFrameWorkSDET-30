package com.crm.PRACTICE;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class WriteDataIntoExcelSheetTest {
	@Test
	public void writeDataIntoExcelSheetTest() throws Throwable {
		//open file read mode
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet("Sheet1");
		
		//step4:get the row
		Row ro=sh.getRow(0);
		
		//create a cell to write a new data
		Cell ce=ro.createCell(1);
		
		//set a value
		ce.setCellValue("tc_100");
		
		
		//open a file in write mode
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\Data.xlsx");
		wb.write(fos);
		
	}

}
