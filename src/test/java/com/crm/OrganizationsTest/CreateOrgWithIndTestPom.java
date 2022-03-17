package com.crm.OrganizationsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithIndTestPom {
	@Test
	public void createOrgWithPom() throws Throwable {
		
		//WebDriverManager.chromedriver().setup();
		//WebDriverManager.firefoxdriver().setup();
		
		PropertyFileUtility pLib=new PropertyFileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelFileUtility eLib=new ExcelFileUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		/*step1:read all necessary data*/
		String BROWSER=pLib.readDataFromPropertyFile("browser");
		String URL=pLib.readDataFromPropertyFile("url");
		String USERNAME=pLib.readDataFromPropertyFile("username");
		String PASSWORD=pLib.readDataFromPropertyFile("password");
		
		String OrgName=eLib.readDataFromExcelSheet("Org", 4, 2)+"_"+jLib.getRandamNumber();
		String IndName=eLib.readDataFromExcelSheet("Org", 4, 3);
		
		/*step2:launch the browser*/
		//WebDriverManager.firefoxdriver().setup();
		//WebDriverManager.chromedriver().setup();
		
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

		/*step4:navigate tonOrganization Link*/
		HomePage hp=new HomePage(driver); 
		hp.ClickOnOrgLnk();
		
		/*step5:click on create organization btn*/
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		
		/*step6: enter the mandatory fields and save */
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.createOrgwithInd(OrgName, IndName);
		
		/*step7:verification*/
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actHeader=oip.OrgNameInfo();
		if(actHeader.contains(OrgName)) {
			System.out.println(actHeader+"-------->organization is created");
		}
		else {
			System.out.println("organization is failed");
		}
		/*step8:signout of app*/
		hp.signOutOfApp(driver);
		
       /*step9:close the application*/
		driver.quit();
	
	}

}
