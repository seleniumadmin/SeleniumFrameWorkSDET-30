package com.crm.PRACTICE;

import org.testng.annotations.Test;

public class DebugPracticeNew {
	@Test
	public void debugPracticeNew() {
		
		System.out.println("Demo");
		DebugPracticeNew d=new DebugPracticeNew();
		d.div(1, 0);
	}	
   public int div(int a,int b) {
	   int c=a/b;
	   return c;
   }


	}
