package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice6cross2 
{
	@Test(dataProvider="getData")
	public void saampleDataProviderPractice(String Name,int qty) 
	{
		System.out.println(Name+"----"+qty);
	}
	@DataProvider
	public Object[][] getData()
	{
		Object[][] obj=new Object[6][2];
		
		obj[0][0]="samsung";
	    obj[0][1]=12;
	    		
	    obj[1][0]="Nokia";
	    obj[1][1]=42;
	    
	    obj[2][0]="mi";
	    obj[2][1]=50;
	    
	    obj[3][0]="vivo";
	    obj[3][1]=62;
	    
	    obj[4][0]="oppo";
	    obj[4][1]=67;
	    
	    obj[5][0]="apple";
	    obj[5][1]=34;
		return obj;
	    
	    		
	    		
	}

}
