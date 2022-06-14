package com.crm.PRACTICE;

import java.awt.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrintMobileNames {
	@Test
	public void printMobileNames() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
	
		driver.findElement(By.name("q")).sendKeys("mobiles");
		driver.findElement(By.xpath("//button")).click();
		
		java.util.List<WebElement> ele = driver.findElements(By.xpath("//div[contains(text(),'REDMI ')]"));
		System.out.println(ele.size());
		for(WebElement allOption:ele) 
			
		{
			System.out.println(allOption.getText());
		}
		
		//driver.close();
		
	}

}
