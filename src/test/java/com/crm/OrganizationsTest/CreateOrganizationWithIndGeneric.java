package com.crm.OrganizationsTest;

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

public class CreateOrganizationWithIndGeneric {
	@Test
	public void creteOrganizationWithIndGeneric() throws Throwable {
		
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
		
		String OrgName=eLib.readDataFromExcelSheet("Org", 4, 2)+"_"+jLib.getRandamNumber();
		String IndType=eLib.readDataFromExcelSheet("Org", 4, 3);
		
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
		WebElement ele=driver.findElement(By.name("industry"));
		wLib.select(IndType, ele);
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/*step7 :logout of Application*/
		WebElement ele1=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));	
		wLib.mouseHover(driver, ele1);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		/*close the browse*/
		driver.quit();

}
}
