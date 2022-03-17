package com.crm.ContactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactAllCheckbox1 {
	@Test
	public void contactAllCheckbox ()  throws Throwable {
		//read data from property file
				FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				Properties pObj=new Properties();
				pObj.load(fis);
				String BROWSER=pObj.getProperty("browser");
				String URL=pObj.getProperty("url");
				String USERNAME=pObj.getProperty("username");
				String PASSWORD=pObj.getProperty("password");
				
				WebDriver driver=null;
				if(BROWSER.equalsIgnoreCase("chrome")) 
				{
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver();
				}
				else if(BROWSER.equalsIgnoreCase("firefox")) {
					driver=new FirefoxDriver();
				}
				else {
					System.out.println("invalid browser");
				}
				
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.get(URL);
				
				//step3 login to application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				 
				//step4 navigate to contact link
			
				driver.findElement(By.xpath("//a[.='Contacts']")).click();
				
			List<WebElement>checkboxes=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selected_id']"));
			//count
			int Count=checkboxes.size();
			
			System.out.println("no of checkBoxes in table:"+Count);
			//get text
			 for(WebElement allCheckBoxes:checkboxes) {
				 allCheckBoxes.click();
				 //System.out.println(allCheckBoxes.getText());
				 
				 
				 
			 }
			
			
	}			
			
}
