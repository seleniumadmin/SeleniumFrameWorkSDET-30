package com.crm.OrganizationsTest;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
//import com.crm.GenericLibrary.ExcelFileUtility;
//import com.crm.GenericLibrary.JavaUtility;
//import com.crm.GenericLibrary.PropertyFileUtility;
//import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
//import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationPage;

public class CreateOrgWithIndBaseClass extends BaseClass {
	@Test(groups="regressionSuite")
	public void createOrgWithIndBaseClass() throws Throwable {
	
		
		String OrgName=eLib.readDataFromExcelSheet("Org", 4, 2)+"_"+jLib.getRandamNumber();
		String IndName=eLib.readDataFromExcelSheet("Org", 4, 3);

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
		else 
		{
			System.out.println("organization is failed");
		}
		
	}
	@Test
	public void CreateSampleTestCase1(){
		System.out.println("sample test1");
		
	}
	

}
