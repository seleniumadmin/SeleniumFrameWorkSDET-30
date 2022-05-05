package com.crm.GenericLibrary;

import java.util.Date;
import java.util.Random;
/**
 * this class consists of generic methods wrt to java
 * @author usha
 *
 */

public class JavaUtility {
	    /**
		 * This method will generate a random and return it to user
		 * @return
		 */
		public int getRandamNumber() 
		{
			Random ran = new Random();
			int random=ran.nextInt(500);
			return random;
		}
		/**
		 * this method will generate current sys
		 * tem date and return it to user
		 */
		
		public String getSystemDate() {
			Date d=new Date();
			String date=d.toString();
			return date;
		}
		/** 
		 * 
		 * this method will generate current date and return date format
		 * @return
		 */
		public String getSystemDateInFormat() {
			Date d = new Date();
			String d1 = d.toString();
			String[] date = d1.split(" ");
			
		    String mon = date[1];
		    String day = date[2];
		    String time = date[3].replace(":", "-");
		    String year = date[5];
		    
		    String DateFormat = day+"-"+mon+"-"+year+"-"+time;
		    return DateFormat;
		  
		}
	}
	


