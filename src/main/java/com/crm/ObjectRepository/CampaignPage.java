package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CampaignPage extends WebDriverUtility {
	@FindBy(xpath="//img[@alt='Create Campaign...']")
	private WebElement createCampLookUpImg;
	
	//initialization
	public CampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
     //utilization
	public WebElement getCreateCampLookUpImg() {
		return createCampLookUpImg;
	}
	//business library
	public void clickOnCreateCampImg() {
		createCampLookUpImg.click();
	}
	
}
