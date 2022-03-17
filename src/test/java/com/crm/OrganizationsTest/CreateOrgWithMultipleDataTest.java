package com.crm.OrganizationsTest;

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
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationPage;

public class CreateOrgWithMultipleDataTest {
    
	//create obj for all utilities
	PropertyFileUtility pLib=new PropertyFileUtility(); 
	ExcelFileUtility eLib=new ExcelFileUtility();
	WebDriverUtility wLib=new WebDriverUtility();
	JavaUtility jLib=new JavaUtility();
	
	@Test(dataProvider="OrgTestData")
	public void createorgWithMultipleData(String orgName, String indType) throws Throwable {
		//read data
		String BROWSER=pLib.readDataFromPropertyFile("browser");
		String URL=pLib.readDataFromPropertyFile("url");
		String USERNAMR=pLib.readDataFromPropertyFile("username");
		String PASSWORD=pLib.readDataFromPropertyFile("password");
		
		String orgname=orgName+jLib.getRandamNumber();
		
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
	    Reporter.log("login Successful", true);
	    
	    //navigate to aplication
	    HomePage hp=new HomePage(driver); 
	    hp.ClickOnOrgLnk();
	    Reporter.log("navigate to org link", true);
	    
	    //create Org
	    OrganizationPage op=new OrganizationPage(driver);
	    op.clickOnCreateOrgImg();
	    Reporter.log("click on create org link", true);
	    
	    //create new org
	    CreateOrganizationPage cop=new CreateOrganizationPage(driver);
	    cop.createOrgwithInd(orgname, indType);
	    Reporter.log("create org with industry type", true);
	    
	    //valiadte
	    OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	    String actHeader=oip.OrgNameInfo();
	    if(actHeader.contains(orgname)) {
	    	System.out.println("passed");
	    }
	    else {
	    	System.out.println("failed");
	    }
	    
	    //logout
	    hp.signOutOfApp(driver);
	    driver.quit();
	}
	@DataProvider(name="OrgTestData")
	public Object[][] getData() throws Throwable {
		Object[][] data=eLib.readmultipleDataFromExcel("OrgMultipledata");
		return data;
	}
	
}
