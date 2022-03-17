package com.crm.ContactTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.HomePage;

public class ContactDynamicWebElementTest extends BaseClass{
	@Test
	public void contactCheckBox() {
		//navigate to contact link
		HomePage hp=new HomePage(driver);
		hp.ClickOnContactLnk();
		 
		List<WebElement>checkboxes=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selected_id']"));
		int Count=checkboxes.size();
		
		System.out.println("no of checkBoxes in table:"+Count);
		//get text
		 for(WebElement allCheckBoxes:checkboxes) {
			 allCheckBoxes.click();
			
		
		
	}

}
}
