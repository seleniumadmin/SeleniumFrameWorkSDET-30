package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCUpdate 
{
	@Test
	public void sampleJDBCQueryUpdate() throws Throwable 
	{
		
		Connection con=null;
		try 
		
		{
		
		//spet 1:register the database 
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		//step 2:get connector from database-provide db name
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
		System.out.println("connection established");
		
		//step 3:issue create statement 
		Statement stat=con.createStatement();		
		
		//step4:execute query
		int result=stat.executeUpdate("insert into student values(7,'rani','Female')");
		if(result==1)
		{
			System.out.println("data added successfully");
		}
		else 
		{
			System.out.println("data not added");
		}
	}
	catch(Exception e) 
		{
		
	}
		
	finally 
	{
		//step5
		con.close();
		System.out.println("connection closed");
	}

	}
	}
