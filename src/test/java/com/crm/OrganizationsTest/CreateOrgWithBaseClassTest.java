package com.crm.OrganizationsTest;

import org.openqa.selenium.By;
import org.testng.Assert;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
//import com.crm.GenericLibrary.JavaUtility;
//import com.crm.GenericLibrary.PropertyFileUtility;
//import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
//import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithBaseClassTest extends BaseClass{
	
	@Test(retryAnalyzer=com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	public void createOrgWithBaseClass() throws Throwable {
		
		
		String OrgName=eLib.readDataFromExcelSheet("Org", 1, 2)+"_"+jLib.getRandamNumber();
	

		/*step4:navigate tonOrganization Link*/
		HomePage hp=new HomePage(driver); 
		hp.ClickOnOrgLnk();
		Assert.fail();
		
		/*step5:click on create organization btn*/
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		
		/*step6: enter the mandatory fields and save */
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName);
		
		
		/*step7:verification*/
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName=oip.OrgNameInfo();
		if(actOrgName.contains(OrgName)) {
			System.out.println(actOrgName+"---->data verified");
		}
		else {
			System.out.println("data invalid");
		}
		

	}

}
