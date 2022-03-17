package com.crm.ContactTest;

import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateContactTest {
	@Test
	public void createConatctTest() throws Throwable {
		/* step 1:read all necessary data*/
		//read data from property file
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String BROWSER=pObj.getProperty("browser");
		String URL=pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD=pObj.getProperty("password");
		
		//read data from data from excel sheet
		FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fi);
		 org.apache.poi.ss.usermodel.Sheet sh=wb.getSheet("Contact");
		Row ro=sh.getRow(1);
		Cell cel=ro.getCell(2);
		String LastName=cel.getStringCellValue();
		
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
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
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
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(ele));
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		/*Step 8: close the browser*/
		driver.quit();
	}

}
