package com.crm.ContactTest;

import java.io.FileInputStream;
//import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.poi.EncryptedDocumentException;
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

public class ReferenceBox {
	@Test
	public void referencebox() throws Throwable, Throwable {
		/*read all necessary data*/
		//step1:read data property file
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
	
		String BROWSER=pObj.getProperty("browser");
		String URL=pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD=pObj.getProperty("password");
		
		/* read data from excel sheet*/
		FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fi);
	    org.apache.poi.ss.usermodel.Sheet sh=wb.getSheet("Contact1");
	    Row ro=sh.getRow(26);
		Cell cel=ro.getCell(2);
	     String LastName=cel.getStringCellValue();
	     Cell cel2=ro.getCell(3);
	     String EmailId=cel2.getStringCellValue();
		
		//step2:step3:launch the browser
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid Browser");
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(URL);
		
		/*step 3:login to application*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/*step 4:place the mouse cursor and click on contact link*/
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		/*step5:create a contact and check reference box*/
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.id("email")).sendKeys(EmailId);
		
	 
		WebElement checkbox=driver.findElement(By.name("reference"));
		if(checkbox.isEnabled()) {
			System.out.println("pass:checkbox is enabled ");
			}
		else {
			System.out.println("fail:checkbox is not enabled");
		}
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		//step6:logout
	WebElement ele1=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.visibilityOf(ele1));
	Actions act = new Actions(driver);
	act.moveToElement(ele1).perform();
	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	
	/*step 6:close the browser*/
	driver.quit();

	

	}

}
