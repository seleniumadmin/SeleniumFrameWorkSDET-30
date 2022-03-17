package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility {
//step1 Declaration
	@FindBy(name="accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropDown;
	
	@FindBy(name="button")
	private WebElement saveBtn;
	 
	//step2 initialization
	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//step 3:utilization

	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	//business library
	public void createNewOrg(String orgName) {
		OrgNameEdt.sendKeys(orgName);
		saveBtn.click();
		
	}
	public void createOrgwithInd(String orgName,String indType) {
		OrgNameEdt.sendKeys(orgName,indType);
		select(industryDropDown,indType);
	    saveBtn.click();
	}
	public void createOrgWithIndType(String orgName, String indType, String type) {
	
	OrgNameEdt.sendKeys(orgName);
	select(industryDropDown,indType);
	select(typeDropDown,type);
	saveBtn.click();
}
	}
