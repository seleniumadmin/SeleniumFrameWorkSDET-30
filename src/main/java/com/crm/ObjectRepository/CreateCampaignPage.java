package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateCampaignPage extends WebDriverUtility {
	@FindBy(name="campaignname")
   private WebElement campnameEdt;
	
	@FindBy(xpath="//input[@name='product_name']/following::img[@alt='Select']")
	private WebElement proLookUpImg;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[2]")
	private WebElement saveBtn;
	
	@FindBy(id="search_txt")
	private WebElement searchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	//initializatio
	
	public CreateCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//utilization
	public WebElement getCampnameEdt() {
		return campnameEdt;
	}

	public WebElement getProLookUpImg() {
		return proLookUpImg;
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
	/**
	 * 
	 * @param driver
	 * @param campaignName
	 * @param proName
	 * @throws Throwable 
	 */
	public void createCampainProd(WebDriver driver,String campaignName,String proName) throws Throwable {
		
		campnameEdt.sendKeys(campaignName);
		proLookUpImg.click();
		switchToWindow(driver,"Products");
		searchEdt.sendKeys(proName);
		searchBtn.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[.='"+proName+"']")).click();
		switchToWindow(driver, "Campaigns");
		saveBtn.click();
		
		
	}
	
}
