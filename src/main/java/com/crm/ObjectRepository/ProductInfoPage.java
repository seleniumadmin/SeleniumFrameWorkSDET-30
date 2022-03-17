package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class ProductInfoPage extends WebDriverUtility {
	
	//declaration
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement headerTxt;
	
	//initialization
	public ProductInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
    
	//utilization
	public WebElement getHeaderTxt() {
		return headerTxt;
	}
	
	//business library
	public String productInfopage() {
		String prodInfoPage=headerTxt.getText();
		return prodInfoPage;
		
	}
	

}
