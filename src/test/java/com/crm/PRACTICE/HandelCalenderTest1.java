package com.crm.PRACTICE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandelCalenderTest1 {
	@Test
	public void calender1() {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.goibibo.com/flights/");
		
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		
		String dataXpath="//div[text()='March 2022']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='12']";
		driver.findElement(By.xpath(dataXpath)).click();
	}

}
