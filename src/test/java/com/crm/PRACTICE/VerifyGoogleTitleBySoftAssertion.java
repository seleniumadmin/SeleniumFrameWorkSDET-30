package com.crm.PRACTICE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyGoogleTitleBySoftAssertion 
{
	@Test
	public void verifyGoogle() 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String actualData=driver.getTitle();
		String expectedData="Google";
		
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(actualData, expectedData);
		sa.assertAll();
		
		if(expectedData.contains(actualData)) 
		{
			System.out.println("data is verified");
		}
		driver.close();
		
		
		
		
		
		
	}

}
