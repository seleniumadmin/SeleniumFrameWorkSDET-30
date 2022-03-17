package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class ContactInfoPage extends WebDriverUtility{
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerTxt;
	
	//initializatio
	
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getHeaderTxt() {
		return headerTxt;
	}
	
	//business library
	public String ContactInfo() {
		String ContactInfo=headerTxt.getText();
		return ContactInfo;
		
	}

}
