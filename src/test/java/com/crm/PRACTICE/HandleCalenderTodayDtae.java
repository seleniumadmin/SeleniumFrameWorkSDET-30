package com.crm.PRACTICE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleCalenderTodayDtae {
	@Test
	public void handleCalenderTodayDate() throws InterruptedException 
	{
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.makemytrip.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	Actions actions=new Actions(driver);
	actions.moveByOffset(10, 10).click().perform();
	
	driver.findElement(By.xpath("//span[@class='langCardClose']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();
	Thread.sleep(2000);
	String dataXpath="//div[text()='May 2022']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='28']";
	driver.findElement(By.xpath(dataXpath)).click();
	}

}
