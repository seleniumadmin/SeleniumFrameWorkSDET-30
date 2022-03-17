package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampainInfoPage {
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerTxt;
	
	public CampainInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getheaderTxt() {
		return headerTxt;
	}
	
	public String campaignInfoPage() {
		String campInfo=headerTxt.getText();
		return campInfo;
	}

	/*private String campInfoPage() {
		// TODO Auto-generated method stub
		return null;*/
	}


