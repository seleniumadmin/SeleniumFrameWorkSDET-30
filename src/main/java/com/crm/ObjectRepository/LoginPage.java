package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
   //step1: declaration use annotation
	@FindBy(name="user_name")
	private WebElement userNameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement submitBtn;
	
	//step2: Initialization use constructor
	public LoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	//step3:Utilization provide getters
    public WebElement getUserNameEdt()
	{
		return userNameEdt;
	}

	public WebElement getPasswordEdt() 
	{
		return passwordEdt;
	}

	public WebElement getSubmitBtn() 
	{
		return submitBtn;
	}
	
	//business Library
	public void loginToApp(String userName,String password)
	{
		userNameEdt.sendKeys(userName);
		passwordEdt.sendKeys(password);
		submitBtn.click();
		}
		
}
