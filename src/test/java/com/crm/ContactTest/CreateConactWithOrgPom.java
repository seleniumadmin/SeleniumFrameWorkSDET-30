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
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateConactWithOrgPom {
	@Test
	public void createContactWithOrgPom() throws Throwable {
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
		String LastName=eLib.readDataFromExcelSheet("Contact", 4, 2)+"_"+jLib.getRandamNumber();
		String OrgName=eLib.readDataFromExcelSheet("Contact", 4, 3)+" "+jLib.getRandamNumber();
		
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
		hp.ClickOnOrgLnk();
		
		//step 5 click on crete organization page
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		
		//step 6 enter mandatory fields
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
        cop.createNewOrg(OrgName);
        
        //verification
        OrganizationInfoPage oip=new OrganizationInfoPage(driver);
        String actOrgNameInfo=oip.OrgNameInfo();
        if(actOrgNameInfo.contains(OrgName)) {
        	System.out.println(actOrgNameInfo+"------->data verified");
        	}
        else
        {
        	System.out.println("data is verified");
        }
        		
       //create contact link
        hp.ClickOnContactLnk();
        
        //click on crete cantact btn
		ContactPage cp=new ContactPage(driver);
		cp.clickOnCreateContactLookUpImg();
		
		//step6:enter mandatory feilds
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContactWithOrg(driver, LastName, OrgName);
		
		//verification
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actHeader=cip.ContactInfo();
		if(actHeader.contains(LastName)) {
			System.out.println(actHeader+"----->contact created");
			}
		else {
			System.out.println("contact not created");
		}
		
		//step8logout application
		
		hp.signOutOfApp(driver);
		
		//step9:close the application
		driver.close();
		
		
	
		
		
	}

}
