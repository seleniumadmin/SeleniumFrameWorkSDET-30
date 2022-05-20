package com.crm.ContactTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrgaTestGeneric {
	@Test
	public void ctreateContactWithOrgTest() throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		
		/*read data*/
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		/*Step 1: read all neccessary data*/
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String OrgName=eLib.readDataFromExcelSheet("Contact", 7, 2)+"_"+jLib.getRandamNumber();
		String LastName=eLib.readDataFromExcelSheet("Contact", 7, 3)+"_"+jLib.getRandamNumber();
		
		/*step2 :launch the browser*/
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
		
		//verify
		String header=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(OrgName)) {
			System.out.println("orgnname create");
		}
		else {
			System.out.println("orgname not created");
		}
		
		//navigate contact
        driver.findElement(By.xpath("//a[.='Contacts']")).click();
		
		//step5 create contact
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//step6:create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//img[@alt='Select']")).click();
		
		wLib.switchToWindow(driver, "Accounts");
		
		driver.findElement(By.name("search_text")).sendKeys(OrgName);
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[.='"+OrgName+"']"));
		
		wLib.switchToWindow(driver, "Contacts");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		//step6 logout application
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    wLib.mouseHover(driver, ele);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
				
	    /*Step 8: close the browser*/
		driver.quit();
		
		
		
		
		
		
		
		
	}
	

}
