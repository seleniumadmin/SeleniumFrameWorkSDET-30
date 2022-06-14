package com.crm.PRACTICE;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Select9thCheckboxAndDeleteSameCheckbox {
	@Test
	public void select9thCheckboxAndDeleteSameCheckbox() throws InterruptedException 
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
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@id='43']")).click();
		driver.findElement(By.linkText("del")).click();
		Thread.sleep(2000);
		Alert a=driver.switchTo().alert();
		a.accept();
		
	}

}
