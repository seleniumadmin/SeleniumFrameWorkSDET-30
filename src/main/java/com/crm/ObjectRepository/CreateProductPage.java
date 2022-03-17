package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateProductPage extends WebDriverUtility {
@FindBy(name="productname")
private WebElement prodNameEdt;

@FindBy(xpath="//input[@title='Save [Alt+S]']")
private WebElement saveBtn;

//initialization
public CreateProductPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

//utilization
public WebElement getProdNameEdt() {
	return prodNameEdt;
}

public WebElement getSaveBtn() {
	return saveBtn;
}

//business Libraries
public void createProdPage(String prodName) {
	prodNameEdt.sendKeys(prodName);
	saveBtn.click();
	
}


}
