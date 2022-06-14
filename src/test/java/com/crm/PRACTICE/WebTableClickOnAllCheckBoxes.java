package com.crm.PRACTICE;

import java.util.List;
//import java.awt.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableClickOnAllCheckBoxes {
	@Test
	public void webTableClickOnAllCheckBoxes() throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		
		List<WebElement> allCheckBox=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selectall']"));
		for(WebElement sel:allCheckBox)
		{
			Thread.sleep(3000);
			sel.click();
			
		}
		for(WebElement sel:allCheckBox)
		{
			if(sel.isSelected()) 
			{
				Thread.sleep(3000);
			sel.click();
			}
			
		
		driver.close();
		
		}
	}
}


