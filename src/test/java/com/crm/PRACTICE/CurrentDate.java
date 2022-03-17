package com.crm.PRACTICE;

import java.util.Date;

import org.testng.annotations.Test;

public class CurrentDate {
	@Test
	public void currentDate() {
		Date d=new Date();
		String d1=d.toString();
		String[] date=d1.split(" ");
		 
		String mon=date[1];
		String day=date[2];
		String time=date[3].replace(":", "-");
		String year=date[4];
		
		String dateFormate=day+"-"+mon+"-"+" "+year+"-"+time;
		System.out.println(dateFormate);
	}

}
