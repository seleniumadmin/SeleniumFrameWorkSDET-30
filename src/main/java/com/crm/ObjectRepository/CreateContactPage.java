package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility
{
	//step1:Declaration
	
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
    private WebElement orgNameLookUpImg;
	
	@FindBy(name="leadsource")
	private WebElement leadSourceDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	@FindBy(id="email")
	private WebElement emaildEdt;
	
	@FindBy(name="donotcall")
	private WebElement portalUserCheckBox;
	
	//initialization
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
//utilization
	
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getEmailEdt() {
		return emaildEdt;
	}

	public WebElement getportalUserCheckBox() {
		return portalUserCheckBox;
	}

	public WebElement getOrgNameLookUpImg() {
		return orgNameLookUpImg;
	}

	public WebElement getLeadSourceDropDown() {
		return leadSourceDropDown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	//business library
	public void portalUserBox(String lastName, String emailId) {
		lastNameEdt.sendKeys(lastName);
		emaildEdt.sendKeys(emailId);
		portalUserCheckBox.click();
		saveBtn.click();
	}
	
	
	public void createNewContact(String lastName) {
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	public void createContactOrgWithLead(String lastName,String orgName,String leadSource, WebDriver driver) {
	lastNameEdt.sendKeys(lastName);
	orgNameLookUpImg.click();
	switchToWindow(driver,"Accounts");
	searchEdt.sendKeys(orgName);
	searchBtn.click();
	driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	switchToWindow(driver,"Conatacts");
	select(leadSource,leadSourceDropDown);
	saveBtn.click();
		
	}
	public void createContactWithOrg(WebDriver driver,String lastName,String orgName) throws Throwable {
		lastNameEdt.sendKeys(lastName);
		orgNameLookUpImg.click();
		switchToWindow(driver,"Accounts");
		searchEdt.sendKeys(orgName);
		searchBtn.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		//driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchToWindow(driver,"Conatacts");
		saveBtn.click();
		
		//select(leadSourceDropDown,Lead)
		//saveBtn.click();	}
   }
	}
	
	
	
	
	

