package com.crm.ContactTest;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.apache.poi.sl.usermodel.Sheet;
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
import org.testng.annotations.Test;

public class CreateContactWithOrgTest {
	@Test
	public void createContactWithOrgTest() throws Throwable {
	/*generate random number*/
	Random ran=new Random();
	int random=ran.nextInt(500);
	
	/*step1:read all necessary data*/
	//read data from property file
	FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
	Properties pObj=new Properties();
	pObj.load(fis);
	String BROWSER=pObj.getProperty("browser");
	String URL=pObj.getProperty("url");
	String USERNAME=pObj.getProperty("username");
	String PASSWORD=pObj.getProperty("password");
	
	//read data from excel sheet
	FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
	Workbook wb=WorkbookFactory.create(fi);
	org.apache.poi.ss.usermodel.Sheet sh=wb.getSheet("Contact");
	Row ro=sh.getRow(4);
	Cell cel=ro.getCell(3);
	String OrgName=cel.getStringCellValue();
	String OrgNameRan=OrgName+" "+random;
	
	Cell ce=ro.getCell(2);
	String lastName=ce.getStringCellValue()+random;
	
	/*step 2:launch the browser*/
	WebDriver driver=null;
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
	}
	else
	{
		System.out.println("invalid browser");
	}
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get(URL);
	
	/*Step 3: login to application*/
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
 /*step4:navigate to organizatio link*/
	driver.findElement(By.linkText("Organizations")).click();
	
	/*step5:click on create organization link*/
	driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	
	/*step 6:enter mandatory feilds and save*/
	driver.findElement(By.name("accountname")).sendKeys(OrgNameRan);
	driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
	/*verify for organization*/
	String header=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(header.contains(OrgNameRan)) {
		System.out.println(header);
	}
	else {
		System.out.println(header);
		System.out.println("Org not created");
	    }
	/*step8:navigate to contacts link*/
	driver.findElement(By.linkText("Contacts")).click();
	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	driver.findElement(By.name("lastname")).sendKeys(lastName);
	driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
	
	/*step9:choose organizaton*/
	Set<String> win=driver.getWindowHandles();
	for(String winId:win) {
		driver.switchTo().window(winId);
		
	}
	driver.findElement(By.name("search_text")).sendKeys(OrgNameRan);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//a[text()='"+OrgNameRan+"']")).click();
	 
	Set<String> win1=driver.getWindowHandles();
	for(String wi:win1) {
	
	driver.switchTo().window(wi);
	
	}
	driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
	
	//step10 verify the header
	String contactHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(contactHeader.contains(lastName))
	{
		System.out.println(contactHeader+" contact created");
	}
	else
	{
		System.out.println("contact not created");
	}
	//step11:logout application
	WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions act1 = new Actions(driver);
	act1.moveToElement(element).perform();				
	driver.findElement(By.linkText("Sign Out")).click();
	driver.quit();

	}
}
