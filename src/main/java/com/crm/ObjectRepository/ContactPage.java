package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage 
{
//declaration
@FindBy(xpath="//img[@title='Create Contact...']")
private WebElement createContactLookUpImg;

@FindBy(xpath="//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selected_id']")
private WebElement checkBox;

//initialization
public ContactPage(WebDriver driver) 
{
	PageFactory.initElements(driver, this);
}

//utility
public WebElement getCreateContactLookUpImg() 
{
	return createContactLookUpImg;
}

public WebElement getCheckBox() 
{
	return checkBox;
}

//business library
public void clickOnCreateContactLookUpImg() 
{
	createContactLookUpImg.click();
}

public void clickOnCheckBox() 
{
   checkBox.click();
   }
}



