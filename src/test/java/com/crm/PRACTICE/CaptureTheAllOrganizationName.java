package com.crm.PRACTICE;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaptureTheAllOrganizationName 
{
 @Test
 public void captureTheAllOrganizationName() 
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
		List<WebElement> ele=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[3]"));
		for(WebElement allName:ele)
		{
			//allName.getText();
			String text=allName.getText();//syop(allName.getText());
			System.out.println(text);
		}
 }
}
