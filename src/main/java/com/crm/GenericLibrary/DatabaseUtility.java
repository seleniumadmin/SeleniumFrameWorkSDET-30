package com.crm.GenericLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
/**
 * this class contains generic methods to read data from db
 * @author My PC
 *
 */

public class DatabaseUtility 
{
	Connection con=null;
	/**
	 * this method will register the driver and establish connection with database
	 * @throws Throwable
	 */
	public void connectToDb() throws Throwable 
	{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection(IPathConstants.DbUrl, IPathConstants.DbUsername,IPathConstants.DbPassword);
		
	}
	/**
	 * this method will close the database connection
	 * @throws Throwable
	 */
	public void closeDB() throws Throwable 
	{
		con.close();
	}
	/**
	 * this method will execute the query and return the matching data into the user
	 * @param query
	 * @param columnIndex
	 * @param expData
	 * @throws Throwable
	 */
	public String ExecuteQueryAndGetData(String query,int columnIndex,String expData) throws Throwable 
	{
		String data=null;
		boolean flag=false;
	    ResultSet result=con.createStatement().executeQuery(query);
	 
	 while(result.next())
	 {
		 data=result.getString(columnIndex);
		 if(data.equalsIgnoreCase(expData)) 
		 {
			 flag=true;//flag rising
			 break;
		 }
	 }
	 if(flag) 
	 {
		 System.out.println(data+"------>data verified");
		 return expData;
	 }
	 else 
	 {
		 System.out.println("data not verified");
		 return "";
	 }
	
				
	}
	

}
