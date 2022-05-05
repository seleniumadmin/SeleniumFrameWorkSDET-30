package com.crm.OrganizationsTest;

import java.io.FileInputStream;

import java.util.Properties;
import java.util.Random;
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

import io.github.bonigarcia.wdm.WebDriverManager;



public class CreateOrgtTest {
@Test
public void createOrgTest() throws Throwable {
	/*generate random number*/
	 Random	ran=new Random();
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
		Row ro=sh.getRow(1);
		Cell cel=ro.getCell(2);
		String OrgName=cel.getStringCellValue();
		
		
		/*step2 :launch the browser*/
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else {
			System.out.println("invalid value");
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
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
		driver.findElement(By.name("accountname")).sendKeys(OrgName+" "+random);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		/*data verification
		 */
		String actData=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actData.contains(OrgName))
		{
			System.out.println("data is added successfully");
		}
		else
		{
			System.out.println("data is invalid");
		}
		
		/*step7 :logout of Application*/
		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));	
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(ele));
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		/*close the browse*/
		driver.quit();

	
	
		
}


}
