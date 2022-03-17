package com.crm.OrganizationsTest;

import java.io.FileInputStream;
//import java.io.IOException;
import java.util.Properties;
import java.util.Random;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateOrganizationWithIndustryType {
	@Test
	public void creteOrganizationWithIndustryType() throws Throwable, Throwable {
		
		/*generate ramdom number*/
		Random ran=new Random();
		int random=ran.nextInt(500);
		
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
		 org.apache.poi.ss.usermodel.Sheet sh=wb.getSheet("Org");
		 
		Row ro=sh.getRow(4);
		Cell cel=ro.getCell(2);
		String OrgName=cel.getStringCellValue();
		//String OrgNameRan=OrgName+" "+random;
		
		Cell cel1=ro.getCell(3);
		String IndType=cel1.getStringCellValue();  
		
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
		
		/*step3:login to application*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/*step4:create or navigate to organization link*/
		driver.findElement(By.linkText("Organizations")).click();
		
		/*step5:click on create organization button*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/*step6:enter mandatory feilds*/
		driver.findElement(By.name("accountname")).sendKeys(OrgName+" "+random);
	
		
		/*step7:select value from industry type*/
		WebElement dropDown=driver.findElement(By.name("industry"));
		Select se=new Select(dropDown);
		se.selectByVisibleText(IndType);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		/*Step 9: logout of application*/
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(ele));
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		WebElement ele1=driver.findElement(By.xpath("//a[.='Sign Out']"));
		ele1.click();
		
		/*Step 10: close the browser*/
		driver.quit();
		
		}

}
