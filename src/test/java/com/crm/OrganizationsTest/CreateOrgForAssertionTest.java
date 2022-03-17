package com.crm.OrganizationsTest;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationPage;

public class CreateOrgForAssertionTest extends BaseClass {

	@Test
	public void createOrgTestBaseClass() throws Throwable {
		
		String OrgName=eLib.readDataFromExcelSheet("Org", 1, 2)+"_"+jLib.getRandamNumber();
		SoftAssert sa=new SoftAssert();
	
		/*step4:navigate tonOrganization Link*/
		HomePage hp=new HomePage(driver); 
		hp.ClickOnOrgLnk();
		String ExpData="Organizations";
		String actData=driver.findElement(By.linkText("Organizations")).getText(); 
		
		
		/*step5:click on create organization btn*/
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		String expHeader="Creating New Organizationwer";
		String actHeader=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		sa.assertEquals(actHeader, expHeader);
		
		/*step6: enter the mandatory fields and save */
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName);
		
		
		/*step7:verification*/
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName=oip.OrgNameInfo();
		Reporter.log(actOrgName+"org created",true);
		sa.assertTrue(actOrgName.contains("abcd"));
		
		/*if(actOrgName.contains(OrgName)) {
			System.out.println(actOrgName+"---->data verified");
		}
		else {
			System.out.println("data invalid");
		}*/
		
		System.out.println("passs");
		System.out.println("all ok");
		sa.assertAll("all Ok");

	}
}
