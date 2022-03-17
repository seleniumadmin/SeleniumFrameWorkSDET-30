package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class ProductPage extends WebDriverUtility{
	
	//declaration
@FindBy(xpath="//img[@alt='Create Product...']")
private WebElement createProdImg;

//initialization
public ProductPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}
//utilization

public WebElement getcreateProdImg() {
	return createProdImg;
}
//business library
public void clickProdImg() {
	createProdImg.click();
}
}
