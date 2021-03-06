package com.crm.PRACTICE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssertLoginPage {
	@Test
	public void assertLoginPage() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		String url=driver.getCurrentUrl();
		System.out.println(url);
		String expected="http://localhost:8888/";
		//String expected="http://localhost:8888/";
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(url, expected);
		sa.assertAll();
		
		if(expected.contains(url)) {
			System.out.println("verified");
		}
		driver.close();
		
	}
	

}
