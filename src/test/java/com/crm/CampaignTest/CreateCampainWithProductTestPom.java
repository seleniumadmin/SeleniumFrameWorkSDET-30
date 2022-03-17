package com.crm.CampaignTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.CampaignPage;
import com.crm.ObjectRepository.CampainInfoPage;
import com.crm.ObjectRepository.CreateCampaignPage;
//import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.CreateProductPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductInfoPage;
import com.crm.ObjectRepository.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampainWithProductTestPom {
	@Test
	public void createCampainWithProductTestPom() throws Throwable {
	/*read data*/
	PropertyFileUtility pLib = new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtility eLib = new ExcelFileUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	
	/*Step 1: read all neccessary data*/
	String BROWSER = pLib.readDataFromPropertyFile("browser");
	String URL = pLib.readDataFromPropertyFile("url");
	String USERNAME = pLib.readDataFromPropertyFile("username");
	String PASSWORD = pLib.readDataFromPropertyFile("password");
	
	/*Step 2:read data from excel sheet*/
	String ProdName=eLib.readDataFromExcelSheet("Campaign", 1, 2)+"_"+jLib.getRandamNumber();
	String CampaignName=eLib.readDataFromExcelSheet("Campaign", 1, 3);
	
	/*launch the browser*/
	WebDriverManager.firefoxdriver().setup();
	WebDriverManager.chromedriver().setup();
	
	WebDriver driver=null;
	if(BROWSER.equalsIgnoreCase("chrome")) 
	    {
		driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else {
			System.out.println("invalid browser");
		}
	wLib.maximizeWindow(driver);
	wLib.maximizeWindow(driver);
	driver.get(URL);
	
	//step 3:login to app
	LoginPage lp=new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);
	
	//step4:navigate to productlink
	HomePage hp=new HomePage(driver);
	hp.CilckOnProductLnk();
	
	
	//step5:enter manadatory fields
	ProductPage p=new ProductPage(driver);
	p.clickProdImg();
	
	//step6 create Prod
	CreateProductPage cpp=new CreateProductPage(driver);
	cpp.createProdPage(ProdName);
    
	//verification
	ProductInfoPage pip=new ProductInfoPage(driver);
	String proNameInfo=pip.productInfopage();
	if(proNameInfo.contains(ProdName))
	{
		System.out.println(proNameInfo+"---------->data is verified");
	}
	else {
		System.out.println("data is valid");
	}
	//navigaget to campaign
	 hp.ClickOnMoreLnk();
	 hp.ClickOnCampaignLnk();
	 
	 CampaignPage cp = new CampaignPage(driver);
	 cp.clickOnCreateCampImg();
	 
	 
    //step6:create campaign
	 System.out.println(ProdName);
    CreateCampaignPage ccp=new CreateCampaignPage(driver);
    ccp.createCampainProd(driver, CampaignName, ProdName);
   
    
	//verification
    CampainInfoPage cip=new CampainInfoPage(driver);
    String campaignNameInfo=cip.campaignInfoPage();
    if(campaignNameInfo.contains(CampaignName)) {
    	System.out.println(campaignNameInfo+"----------->data is verifeid");
    }
    else {
         System.out.println("data is invalid");
    }
    
  //step sign out
  		hp.signOutOfApp(driver);
  		
  		//close
  		driver.quit();
    
	}
	
	}
