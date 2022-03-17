package com.crm.ContactTest;

//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
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

public class CreateContactTestGeneric {
	@Test
	public void createContactTest() throws Throwable {
		
		
		/*read data*/
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		
		
		/* step 1:read all necessary data*/
		
	
		String BROWSER=pLib.readDataFromPropertyFile("browser");
		String URL=pLib.readDataFromPropertyFile("url");
		String USERNAME=pLib.readDataFromPropertyFile("username");
		String PASSWORD=pLib.readDataFromPropertyFile("password");
		
		//read data from data from excel sheet
		
		 String LastName=eLib.readDataFromExcelSheet("Contact", 1, 2)+"_"+jLib.getRandamNumber();
	
		
		/*step2 :launch the browser*/
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else {
			System.out.println("invalid browser");
		}
		
		
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		
		
	   //step3 login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		 
		//step4 navigate to contact link
	
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		
		//step5 create contact
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//step6:create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step6 logout application
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mouseHover(driver, ele);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		/*Step 8: close the browser*/
		driver.quit();
	}

}
