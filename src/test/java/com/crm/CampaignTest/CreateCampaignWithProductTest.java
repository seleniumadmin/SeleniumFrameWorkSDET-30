package com.crm.CampaignTest;

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
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateCampaignWithProductTest {
@Test
public void createCampaignWithProduct() throws Throwable {
	
	/*generate random number*/
	Random ran = new Random();
	int random = ran.nextInt(500);
	
	/*step1:read all necessary data*/
	FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
	Properties pObj=new Properties();
	pObj.load(fis);
	String BROWSER=pObj.getProperty("browser");
	String URL=pObj.getProperty("url");
	String USERNAME=pObj.getProperty("username");
	String PASSWORD=pObj.getProperty("password");
	
	//read data from excelsheet
	FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
	Workbook wb=WorkbookFactory.create(fi);
	org.apache.poi.ss.usermodel.Sheet sh=wb.getSheet("Campaign");
	Row ro=sh.getRow(1);
	Cell cel=ro.getCell(2);
	String prodName=cel.getStringCellValue();
	String ProdNameRan=prodName+" "+random;
	
	Cell ce=ro.getCell(3);
	String campaignName = ce.getStringCellValue()+random;
	
	/*launch the brwoser*/
	WebDriver driver=null;
	if(BROWSER.equalsIgnoreCase("chrome")) {
		driver=new ChromeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("firefox")) {
		driver=new FirefoxDriver();
	}
	else {
		System.out.println("invalid value");
	}
	
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.get(URL);
	
	/*step 3:login to application*/
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
	
	/*step 4:navigate product link*/
	driver.findElement(By.linkText("Products")).click();
	
	/*step 5:click on create product button*/
	driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	
	/*step6 :enter mandatory fields*/
	driver.findElement(By.name("productname")).sendKeys(ProdNameRan);
	
	driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
	
	
	/*step7:veryfy the product*/
	String header=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
	if(header.contains(ProdNameRan)) {
		System.out.println("product created");
		
	}
	else {
		System.out.println("product not created");
	}
	
	/*navigate to campain*/
	driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']")).click();
	driver.findElement(By.linkText("Campaigns")).click();
	
	
	/*Step 9: Create Campaign*/
	driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
	
	/*Step 10: enter mandatory fields*/
	driver.findElement(By.name("campaignname")).sendKeys(campaignName);
	
	/*Step 11: Product*/
	driver.findElement(By.xpath("//img[@alt='Select']")).click();
	
	/*Step 12: choose org */
	Set<String> win = driver.getWindowHandles();
	
	for(String winId:win)
	{
		driver.switchTo().window(winId);
	}
	driver.findElement(By.name("search_text")).sendKeys(ProdNameRan);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//a[text()='"+ProdNameRan+"']")).click();
	
	//driver.findElement(By.xpath("//a[text()='"+ProdNameRan+"']")).click();
	
	Set<String> win1 = driver.getWindowHandles();
	for(String wi : win1)
	{
		driver.switchTo().window(wi);
	}
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	/*Step 13: verfify for campaign*/
	String campaignHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(campaignHeader.contains(campaignName))
	{
		System.out.println(campaignHeader +" campaign created");
	}
	else
	{
		System.out.println("campaign not created");
	}
	
	/*Step 14: logout and close the browser*/
	WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions act1 = new Actions(driver);
	act1.moveToElement(element).perform();				
	driver.findElement(By.linkText("Sign Out")).click();
	driver.quit();
	
	
	
	}
}


