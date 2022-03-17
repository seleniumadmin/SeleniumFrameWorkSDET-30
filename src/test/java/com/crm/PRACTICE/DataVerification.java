package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class DataVerification {
	@Test
    public void dataVerification() throws Throwable {
		String expData="Usharani";
		
		//step1:register the database
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		//step2:get connector from database -provide db name
	Connection	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
	
	//step3:issue create
	 Statement state=con.createStatement();

	 //step4 execute Query--provide table name
	 ResultSet result=state.executeQuery("select * from student");
	 while(result.next())
	 {
	String actData=result.getString(2);
	if(expData.equalsIgnoreCase(actData)) {
		System.out.println(actData + "   data is verified");
		
		break;
	}
		}
	 //step5:close the database
	 con.close();
	}
}
