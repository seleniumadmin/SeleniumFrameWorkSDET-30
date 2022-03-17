package com.crm.GenericLibrary;

//import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
/**
 * This class consists of all generic methods related to webDriversActions
 * @author usha
 *
 */

public class WebDriverUtility {
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	/**
	 * This method will wait for 20 seconds for the page load 
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	
	}
	/** 
	 * this method will wait for 20 seconds for the element to be visible
	 * @param driver
	 * @param element
	 */
	
	public void waitForElelemtToBeVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	/** 
	 * this method will wait for 10 seconds for an element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/** 
	 * this method will select data from dropdown using index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element,int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	/** 
	 * this method will select data from dropdown using visible text
	 * @param element
	 * @param text
	 */
	
	public void select(WebElement element,String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	
   /** this method will select data from dropdown using value
    * 
    * @param value
    * @param element
    */
	
	public void select(String value,WebElement element) {
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	/** this method will perform mouse hover action
	 * 
	 * @param driver
	 * @param element
	 */
    public void mouseHover(WebDriver driver,WebElement element) {
    	Actions act=new Actions(driver);
    	act.moveToElement(element).perform();
    }
    /**
     * this method will perform drag and drop action
     * @param driver
     * @param target
     * @param src
     */
    public void dragAndDrop(WebDriver driver,WebElement src, WebElement target) {
    	Actions act=new Actions(driver);
    	act.dragAndDrop(src, target).perform();
    }
    /**
     * this method will double click on webpage
     * @param driver
     * @param element
     */
    public void doubleClickAction(WebDriver driver,WebElement element) {
    	Actions act=new Actions(driver);
    	act.doubleClick(element).perform();
    }
    /** 
     * this method will right click on webpage
     * @param driver
     * @param element 
     */
    public void rightClick1(WebDriver driver) {
    	Actions act=new Actions(driver);
    	act.contextClick().perform();
    }
    /**
     * this method will right click webElement
     * @param driver
     * @param element
     */
    public void rightClick(WebDriver driver,WebElement element) {
    	Actions act=new Actions(driver);
    	act.contextClick(element).perform();
    	
    }
    /** this method will press enter key
     * 
     * @param driver
     */
    public void enterKeyPress(WebDriver driver) {
    	Actions act=new Actions(driver);
    	
    	act.sendKeys(Keys.ENTER).perform();
    	
    }
    /**
     * this method will press
     * @throws Throwable
     */
    public void enterKey() throws Throwable {
    	Robot rb=new Robot();
    	rb.keyPress(KeyEvent.VK_ENTER);
    	
    }
    /**
     * this method will release
     * @throws Throwable 
     */
    public void enterRelease() throws Throwable {
    	Robot rb=new Robot();
    	rb.keyRelease(KeyEvent.VK_ENTER);
    }
    /**
     * this method will switch the frame based on index
     * @param driver
     * @param index
     */
    public void switchToFrame(WebDriver driver,int index) {
    	driver.switchTo().frame(index);
    }
    /**
     * this method will switch the frame based on name or ID
     * @param driver
     * @param index
     * @param nameOrId
     */
    public void switchToFrame(WebDriver driver,int index,String nameOrId) {
    	driver.switchTo().frame(nameOrId);
    }
    /**
     * this method will switch the frame based on address of the element
     * @param driver
     * @param address
     */
    public void switchToFrame(WebDriver driver,WebElement address) {
    	driver.switchTo().frame(address);
    }
    /**
     * this method will accept alert popup
     * @param driver
     */
    public void acceptAlert(WebDriver driver) {
    	driver.switchTo().alert().accept();
    }
    /**
     * this method will cancel the alert popup 
     * @param driver
     */
    public void cancelAlert(WebDriver driver) {
    	driver.switchTo().alert().dismiss();
    }
    /**
     * this method switch to window depending on partial window title
     * @param driver
     * @param partialWinTitle
     */
    public void switchToWindow(WebDriver driver,String partialWinTitle) {
    	
    	//step1:use getWindowHandles to capture all window ids
    	Set<String> windows=driver.getWindowHandles();
    	
    	//step2:iterate thru the windos
    	Iterator<String> it=windows.iterator();
    	
    	//step3:check whether there is next window
    	while(it.hasNext())
    	{
    		//step4:capture current window id
    		String winId=it.next();
    		
    		//step5:switch to current window and capture title
    		String currentWinTitle=driver.switchTo().window(winId).getTitle();
    		
    		//step6:check whether the current window is expected
    		if(currentWinTitle.contains(partialWinTitle)) {
    			break;
    		}

    		
    	}
    	/**
    	 * this method will take screenShot and store it in folder called as Screenshot
    	 */
    	}
    public String getScreenShot(WebDriver driver,String screenShotName) throws Throwable {
    	        
    	//step1:  take screenshot
    	        TakesScreenshot ts=(TakesScreenshot)driver;
    	        
    	        //step2: access the method and photo will be stoerd in RAM location
    			File src=ts.getScreenshotAs(OutputType.FILE);
    			
    			//step3: specify the location
    			String path=".\\ScreenShot\\"+screenShotName+".png";
    					
    			File dst=new File(path);
    			
    			//step4: copy Photo from the RAM to specific location 
    			Files.copy(src, dst);
    			
    			return dst.getAbsolutePath();
    			//return path;
    	 }
    /**
     * this method perform random scroll
     * @param driver
     */
    public void scrollActions(WebDriver driver) {
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("window.scrollBy(0,500)","");
    }
    
    /**
     * this method will scroll until the specified element is found
     * @param driver
     * @param element
     */
    
    public void scrollActions(WebDriver driver,WebElement element) {
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	int y=element.getLocation().getY();
    	js.executeScript("window.scrollBy(0,"+y+")", element);
    	//js.executeScript("argumen[0].scrollIntoView()",element);
    }
   
    }
