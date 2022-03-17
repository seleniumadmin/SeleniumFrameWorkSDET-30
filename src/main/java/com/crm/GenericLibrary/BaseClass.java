package com.crm.GenericLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	//create object of all utilities
	 public DatabaseUtility bdLib=new DatabaseUtility();
	 public ExcelFileUtility eLib=new ExcelFileUtility();
	 public JavaUtility jLib=new JavaUtility();
	 public PropertyFileUtility pLib=new PropertyFileUtility();
	 public WebDriverUtility wLib=new WebDriverUtility();
	 public WebDriver driver=null;
	 public static WebDriver sDriver;
	 @BeforeSuite(groups= {"smokeSuite","regressionSuite"})
	 public void connectDataBase() {
		 //dbLib.connectTodb();
		 Reporter.log("=====db connection successful=====",true);
	 }
	 
	 @BeforeClass(groups= {"smokeSuite","regressionSuite"})
	 //@Parameters("browser")
	// @BeforeTest
	 public void launchTheBrowser() throws Throwable {
		 //read data from property file
		 String BROWSER=pLib.readDataFromPropertyFile("browser");
		 String URL=pLib.readDataFromPropertyFile("url");
		 
		 //create RunTime polymorphism
		 if(BROWSER.equalsIgnoreCase("chrome")) {
			 WebDriverManager.chromedriver().setup();
			 driver=new ChromeDriver();
		 }
		 else if(BROWSER.equalsIgnoreCase("firefox")){
			 WebDriverManager.firefoxdriver().setup();
			 driver=new FirefoxDriver();
		 }
		 else {
			 System.out.println("invalid user");
		 }
		 
		 sDriver=driver;
		 wLib.maximizeWindow(driver);
		 wLib.waitForPageLoad(driver);
		 driver.get(URL);
		 Reporter.log("=====browser launch successfull=====",true);
		 
		 }
	 @BeforeMethod(groups= {"smokeSuite","regressionSuite"})
	 public void login() throws Throwable {
		 String USERNAME=pLib.readDataFromPropertyFile("username");
		 String PASSWORD=pLib.readDataFromPropertyFile("password");
		 LoginPage lp=new LoginPage(driver);
		 lp.loginToApp(USERNAME, PASSWORD);
		 Reporter.log("======login successful=======",true);
	 }
	 
	 @AfterMethod(groups= {"smokeSuite","regressionSuite"})
	 public void logout() {
		 HomePage hp=new HomePage(driver);
		 hp.signOutOfApp(driver);
		 Reporter.log("=====logout successful=====",true);
	 }
	 //@AfterClass(groups= {"smokeSuite","regressionSuite"})
	 
	 @AfterTest
	 public void closeBrowser() {
		 driver.quit();
		 Reporter.log("======browser close succeessful=====",true);
	 }
	 @AfterSuite(groups= {"smokeSuite","regressionSuite"})
	 public void closeDb() {
		 //dbLib.closeDB();
		 Reporter.log("======database close successful====",true);
	 }

}
