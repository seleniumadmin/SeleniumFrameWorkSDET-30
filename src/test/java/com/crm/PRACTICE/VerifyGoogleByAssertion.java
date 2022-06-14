package com.crm.PRACTICE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyGoogleByAssertion 
{
	@Test
	public void verifyGoogleByAssertion() 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		String actualTitle=driver.getTitle();
		String expectedTitle="Google";
		
		Assert.assertEquals(actualTitle, expectedTitle);
		//System.out.println("The title are same");
		if(expectedTitle.contains(actualTitle)) 
		{
			System.out.println("data is verified");
		}
		 
		
		driver.close();
		
		
		
		
	}

}
