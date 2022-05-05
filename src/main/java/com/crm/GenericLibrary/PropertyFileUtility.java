package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * this class will read data from property file and return value to user
 * @author usha
 
 */



public class PropertyFileUtility {
	/**
	 * this method will read data from property file and return value to user
	 * @param
	 * @return 
	 * @throws Throwable 
	 * 
	 */
	public String readDataFromPropertyFile(String key) throws Throwable {
		
		FileInputStream fis=new FileInputStream(IPathConstants.FilePath);
		Properties pLib=new Properties();
		pLib.load(fis);
		String value=pLib.getProperty(key);
		return value; 
	}
}
