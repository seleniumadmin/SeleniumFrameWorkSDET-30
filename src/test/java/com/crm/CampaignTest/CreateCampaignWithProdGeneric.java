package com.crm.CampaignTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProdGeneric 
{
	@Test
	public void createCampaignWithProdGeneric() throws Throwable 
	{
		/*read data*/
		PropertyFileUtility pLib=new PropertyFileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelFileUtility eLib=new ExcelFileUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//step1:read all necessary data
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		/*Step 2:read data from excel sheet*/
		String ProdName=eLib.readDataFromExcelSheet("Campaign", 1, 2)+"_"+jLib.getRandamNumber();
		String CampaignName=eLib.readDataFromExcelSheet("Campaign", 1, 3)+"_"+jLib.getRandamNumber();
		//String catType=pLib.readDataFromPropertyFile("Campaign")
		
		
		/*launch the browser*/
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome")) 
		    {
			driver=new ChromeDriver();
			}
			else if(BROWSER.equalsIgnoreCase("firefox")) {
				driver=new FirefoxDriver();
			}
			else {
				System.out.println("invalid browser");
			}
		wLib.maximizeWindow(driver);
		wLib.maximizeWindow(driver);
		driver.get(URL);
		
		//step3 login to application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
		//step4 create producut
				/*step 4:navigate product link*/
				driver.findElement(By.linkText("Products")).click();
				
				/*step 5:click on create product button*/
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				
				/*step6 :enter mandatory fields*/
				driver.findElement(By.name("productname")).sendKeys(ProdName);
				
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
				
				
				/*step7:veryfy the product*/
				String header=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
				if(header.contains(ProdName)) {
					System.out.println("product created");
					
				}
				else {
					System.out.println("product not created");
				}
				
				/*navigate to campain*/
				driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']")).click();
				driver.findElement(By.linkText("Campaigns")).click();
				
				
				/*Step 9: Create Campaign*/
				driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
				
				/*Step 10: enter mandatory fields*/
				driver.findElement(By.name("campaignname")).sendKeys(CampaignName);
				
				/*Step 11: Product*/
				driver.findElement(By.xpath("//img[@alt='Select']")).click();
				
				wLib.switchToWindow(driver, "Products");
				
				//driver.findElement(By.id("search_txt"));
				//driver.findElement(By.name("search")).click();
				
				driver.findElement(By.name("search_text")).sendKeys(ProdName);
				driver.findElement(By.name("search")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[.='"+ProdName+"']")).click();
				
				wLib.switchToWindow(driver, "Campaigns");
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verify campaign
				String header1=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(header1.contains(CampaignName)) {
					System.out.println("campaign created");
				}
				else {
					System.out.println("Campain not created");
				}
				
				
				/*Step 14: logout and close the browser*/
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			    wLib.mouseHover(driver, element);			
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
		
				
		
	}
	

}
