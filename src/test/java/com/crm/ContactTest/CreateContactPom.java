package com.crm.ContactTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.ContactInfoPage;
import com.crm.ObjectRepository.ContactPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactPom {
	@Test
	public void createContactPom() throws Throwable {
		PropertyFileUtility pLib=new PropertyFileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelFileUtility eLib=new ExcelFileUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		/*step1:read all necessary data*/
		String BROWSER=pLib.readDataFromPropertyFile("browser");
		String URL=pLib.readDataFromPropertyFile("url");
		String USERNAME=pLib.readDataFromPropertyFile("username");
		String PASSWORD=pLib.readDataFromPropertyFile("password");
		
		/*read data from excel sheet*/
		String LastName=eLib.readDataFromExcelSheet("Contact", 1, 2)+"_"+jLib.getRandamNumber();
		
		/*step2:launch the browser*/
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("invalid browser");
		}
		
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		
		/*step3:login to application*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		/*step4:navigate toContact Link*/
		HomePage hp=new HomePage(driver); 
		hp.ClickOnContactLnk();
		
		
		/*step5:click on crete contac btn*/
		
		ContactPage cp=new ContactPage(driver);
		cp.clickOnCreateContactLookUpImg();
		
		/*step6 enter the mandatory feilds and save*/
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createNewContact(LastName);
		
		//step7:verification
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actContactName=cip.ContactInfo();
		if(actContactName.contains(LastName)) {
			System.out.println(actContactName+"------>data is verified");
			
		}
		else
		{
			System.out.println("data is invalid");
		}
		
		//step sign out
		hp.signOutOfApp(driver);
		
		//close
		driver.quit();
		
		
	}

}
