package com.crm.PRACTICE;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;

public class PracticeForGenericUtils {
	@Test
	public void practice() throws Throwable {
		
		
		//java utility
		JavaUtility jLib=new JavaUtility();
		int ran=jLib.getRandamNumber();
		String dat=jLib.getSystemDateInFormat();
		String date=jLib.getSystemDate();
		System.out.println(ran + date);
		System.out.println(dat);
		
		//Property file utility
		PropertyFileUtility pLib=new PropertyFileUtility();
		String brows=pLib.readDataFromPropertyFile("browser");
		System.out.println(brows);
		
		
		//Excel File Utility
		ExcelFileUtility eLib=new ExcelFileUtility();
		String value=eLib.readDataFromExcelSheet("Org", 3, 2);
	}

}
