package com.crm.CampaignTest;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CampaignPage;
import com.crm.ObjectRepository.CampainInfoPage;
import com.crm.ObjectRepository.CreateCampaignPage;
import com.crm.ObjectRepository.CreateProductPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.ProductInfoPage;
import com.crm.ObjectRepository.ProductPage;

public class CreateCampaignWithProductBaseClass extends BaseClass {
@Test
public void createCampaignWithProductBaseClass() throws Throwable {
	/*Step 2:read data from excel sheet*/
	String ProdName=eLib.readDataFromExcelSheet("Campaign", 1, 2)+"_"+jLib.getRandamNumber();
	String CampaignName=eLib.readDataFromExcelSheet("Campaign", 1, 3);
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


}
}
