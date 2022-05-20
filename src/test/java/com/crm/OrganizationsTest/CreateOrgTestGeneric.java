package com.crm.OrganizationsTest;

//import java.io.FileInputStream;
//import java.util.Properties;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

public class CreateOrgTestGeneric {
	@Test
	public void creteOrgTest() throws Throwable {
		
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		/* step 1:read all necessary data*/
		//read data from property file
		
		String BROWSER=pLib.readDataFromPropertyFile("browser");
		String URL=pLib.readDataFromPropertyFile("url");
		String USERNAME=pLib.readDataFromPropertyFile("username");
		String PASSWORD=pLib.readDataFromPropertyFile("password");
		
		//read data from data from excel sheet
		
		String OrgName=eLib.readDataFromExcelSheet("Org", 1, 2)+"_"+jLib.getRandamNumber();
		
		/*step2 :launch the browser*/
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
			System.out.println("invalid value");
		}
		
		
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		/* step3 login to application*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/*step 4:navigate organization link*/
		driver.findElement(By.linkText("Organizations")).click();
		
		/*step 5:click on create orgnization buton*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/*step 6:enter mandatoery feilds and save*/
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/*step7 :logout of Application*/
		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));	
		wLib.mouseHover(driver, ele);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		/*close the browse*/
		driver.quit();
		}

	
	}


