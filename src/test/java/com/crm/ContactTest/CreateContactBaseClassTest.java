package com.crm.ContactTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
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
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateContactBaseClassTest extends BaseClass {
	@Test
	public void createContactBaseClass() throws Throwable {
	/*read data from excel sheet*/
	String LastName=eLib.readDataFromExcelSheet("Contact", 1, 2)+"_"+jLib.getRandamNumber();
	
	/*step4:navigate toContact Link*/
	HomePage hp=new HomePage(driver); 
	hp.ClickOnContactLnk();
	Assert.fail();
	
	
	
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

}
}
