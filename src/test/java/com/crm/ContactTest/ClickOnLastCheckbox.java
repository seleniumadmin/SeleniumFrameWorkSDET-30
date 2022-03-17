package com.crm.ContactTest;

import java.util.List;

//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
//import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.HomePage;

public class ClickOnLastCheckbox extends BaseClass{
	@Test
	public void clickOnLastCheckbox() throws Throwable  {
		//navigate to contact link
				HomePage hp=new HomePage(driver);
				
				hp.ClickOnContactLnk();
				
				
				//WebDriverUtility wb=new WebDriverUtility();
				 
				List<WebElement> CheckBox=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selected_id']"));
				
				int totalCheckboxes=CheckBox.size();
				/*for(int i=totalCheckboxes-1;i<totalCheckboxes;i++) {
					
					CheckBox.get(i).click();
					Thread.sleep(3000);}*/
				int count = 0;

				
				for (WebElement webElement : CheckBox) 
				{
					count++;
					if (count==totalCheckboxes)
					{
						webElement.click();
	
					}
				
				}
				
				
				
					
				}
	
				
				
				
				
	}


