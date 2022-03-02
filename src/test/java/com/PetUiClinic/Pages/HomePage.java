package com.PetUiClinic.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
	
	public WebDriver driver;  
	
    @FindBy(xpath = "//ul/li/a[@title='home page']")
    public WebElement homeLink;
    
    @FindBy(xpath = "//ul/li/a[@title='find owners']")
    public WebElement findownersLink;
    
    @FindBy(xpath = "//ul/li/a[@title='veterinarians']")
    public WebElement veterinariansLink;  
    
    @FindBy(xpath = "//div[@class='col-md-12']/img")
    public WebElement petImage;  
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void SelectNavigationItem(WebElement ele) {
        ele.click();
    }
    
    public boolean verifyImage(WebElement ele) {
    	
    	Boolean imageExists = false;
    	
    	imageExists = (Boolean) ((JavascriptExecutor)driver)
				.executeScript("return arguments[0].complete "
				+ "&& typeof arguments[0].naturalWidth != \"undefined\" "
				+ "&& arguments[0].naturalWidth > 0", ele);
    	
    	return imageExists;
    }
    
 

}
