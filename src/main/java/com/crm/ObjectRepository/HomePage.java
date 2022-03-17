package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class HomePage extends WebDriverUtility {
//step1:Declaration
	@FindBy(linkText="Organizations")
	private WebElement organizationLnk;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLnk;
	
	@FindBy(linkText="Opportunities")
	private WebElement opportunityLnk;
	
	@FindBy(linkText="Products")
	private WebElement productLnk;
	
	@FindBy(linkText="More")
	private WebElement moreLnk;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLnk;
	
	//step2:initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
   
	//step3:generate getters
	public WebElement getOrganizationLnk() {
		return organizationLnk;
	}

	public WebElement getContactLnk() {
		return contactLnk;
	}

	public WebElement getOpportunityLnk() {
		return opportunityLnk;
	}

	public WebElement getProductLnk() {
		return productLnk;
	}

	public WebElement getMoreLnk() {
		return moreLnk;
	}

	public WebElement getCampaignLnk() {
		return campaignLnk;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	
	//step4:business library
	public void ClickOnOrgLnk() {
		organizationLnk.click();
	}
	public void ClickOnContactLnk() {
		contactLnk.click();
	}
	public void CilckOnProductLnk() {
		productLnk.click();
	}
	
	public void ClickOnCampaignLnk() {
		campaignLnk.click();
	}
	public void ClickOnMoreLnk() {
		moreLnk.click();
	}
	
	
	public void signOutOfApp(WebDriver driver)
	{
		mouseHover(driver,administratorImg);
		signOutLnk.click();
	}
	
	
	
}
