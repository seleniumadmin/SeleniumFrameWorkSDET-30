package com.crm.OrganizationsTest;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CreateOrganizationWithPropertyFileTest {
	@Test
	public void createOrgTest() throws Throwable {
		
		//step1:read data from file
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String BROWSER=pObj.getProperty("browser");
		String URL=pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD=pObj.getProperty("password");
		
		//step2:launch the browser
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome")) 
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else 
		{
			System.out.println("invalid browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(URL);
		
		//step3:lon
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step3 navigate to organization
		driver.findElement(By.linkText("Organizations")).click();
		
		//step4 click on create organization link
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//step5 create org with mandatory feilds
		driver.findElement(By.name("accountname")).sendKeys("ALL STATES");
		
		//step6 save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		driver.quit();
	}
	}


