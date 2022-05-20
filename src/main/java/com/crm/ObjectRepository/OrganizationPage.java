package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage 
{

//step1 declaration
@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
private WebElement cteateorgLookUpImg;
   
// step2 initialization
public OrganizationPage(WebDriver driver) 
{
	PageFactory.initElements(driver, this);
}

//step3 utilization
public WebElement getCteateorgLookUpImg() 
{
	return cteateorgLookUpImg;
}

//business library
public void clickOnCreateOrgImg() 
{
	cteateorgLookUpImg.click();
}
}
