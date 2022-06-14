
package com.crm.ContactTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
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

public class CreateContMultipeDataTest {
	//create obj for all utilities
		PropertyFileUtility pLib=new PropertyFileUtility(); 
		ExcelFileUtility eLib=new ExcelFileUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		JavaUtility jLib=new JavaUtility();
		
		@Test(dataProvider="ContTestData")
		public void createContactWithManField(String LastName) throws Throwable {
			String BROWSER=pLib.readDataFromPropertyFile("browser");
			String URL=pLib.readDataFromPropertyFile("url");
			String USERNAMR=pLib.readDataFromPropertyFile("username");
			String PASSWORD=pLib.readDataFromPropertyFile("password");
			
			String lastname=LastName+jLib.getRandamNumber();
			
			//launch the browser
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
			
			//login to application
			LoginPage lp=new LoginPage(driver);
			lp.loginToApp(USERNAMR, PASSWORD);
			Reporter.log("login successful", true);
			
			//navigate to application
			HomePage hp=new HomePage(driver);
			hp.ClickOnContactLnk();
			Reporter.log("navigate to contact link", true);
			
			//create contact
			ContactPage cp=new ContactPage(driver);
			cp.clickOnCreateContactLookUpImg();
			Reporter.log("click on create org link", true);
			
			//create new Contact
			CreateContactPage ccp=new CreateContactPage(driver);
			ccp.createNewContact(lastname);
			Reporter.log("create org with mandatory field", true);
			
			//validate
			ContactInfoPage cip=new ContactInfoPage(driver);
			String actHeader=cip.ContactInfo();
			if(actHeader.contains(lastname)) {
				System.out.println("passes");
				
			}
			else {
				System.out.println("failed");
			}
			Reporter.log("verification successful", true);
			
			//logout
			hp.signOutOfApp(driver);
			
			driver.close();
		}
		@DataProvider(name="ContTestData")
		public Object[][] getData() throws Throwable {
			Object[][] data=eLib.readmultipleDataFromExcel("ContMultipledata");
			return data;


}
		}
