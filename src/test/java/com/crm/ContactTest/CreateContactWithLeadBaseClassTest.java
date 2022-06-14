package com.crm.ContactTest;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.ContactInfoPage;
import com.crm.ObjectRepository.ContactPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationPage;

public class CreateContactWithLeadBaseClassTest extends BaseClass {
	@Test
	public void createContactWithBaseClass() throws Throwable {
	
		
		/*read data from excel sheet*/
	String LastName=eLib.readDataFromExcelSheet("Contact", 4, 2)+"_"+jLib.getRandamNumber();
	String OrgName=eLib.readDataFromExcelSheet("Contact", 4, 3)+"_"+jLib.getRandamNumber();
	String LeadSource=eLib.readDataFromExcelSheet("Contact", 4, 4);
	
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
    //create contact with lead
    ContactPage cp=new ContactPage(driver);
    cp.clickOnCreateContactLookUpImg();
    
    CreateContactPage ccp=new CreateContactPage(driver);
    ccp.createContactOrgWithLead(LastName, LeadSource, OrgName, driver);
    
    //verification
  
  		ContactInfoPage cip=new ContactInfoPage(driver);
  		String actHeader=cip.ContactInfo();
  		if(actHeader.contains(LastName)) {
  			System.out.println(actHeader+"----->contact created");
  			}
  		else {
  			System.out.println("contact not created");
  		}
  		    
    }
}
