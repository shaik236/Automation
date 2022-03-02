package com.PetUiClinic.Pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import com.PetUiClinic.Tests.PetUiClinicUI_Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class OwnersPage {
    private static final Logger logger = LogManager.getLogger(PetUiClinicUI_Test.class);
	
public WebDriver driver;  
	
    @FindBy(id = "firstName")
    public WebElement firstName;
    
    @FindBy(id = "lastName")
    public WebElement lastName;
    
    @FindBy(id = "address")
    public WebElement address;
    
    @FindBy(id = "city")
    public WebElement city;
    
    @FindBy(id = "telephone")
    public WebElement telephone;
    
    @FindBy(linkText = "Add Owner")
    public WebElement AddOwnerLink;
    
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement AddOwnerButton;
    
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement FindOwnerButton;
    
    @FindBy(xpath = "//tr/th/text()[.='Name']/../../td")
    public WebElement ownerName;
    
    @FindBy(xpath = "//tr/th/text()[.='Address']/../../td")
    public WebElement ownerAddress;
    
    @FindBy(xpath = "//tr/th/text()[.='City']/../../td")
    public WebElement ownerCity;
    
    @FindBy(xpath = "//tr/th/text()[.='Telephone']/../../td")
    public WebElement ownerTelephone;
    
    @FindBy(xpath = "(//div[@class='container xd-container']//h2)[1]")
    public WebElement ownerInfo;
    
    @FindBy(linkText = "Add New Pet")
    public WebElement AddPetLink;
    
    @FindBy(id = "name")
    public WebElement petName;
    
    @FindBy(id = "birthDate")
    public WebElement petBirthDt;
    
    @FindBy(xpath = "(//tbody/tr/td/dl[@class='dl-horizontal']/dd)[1]")
    public WebElement nameOfPet;
    
    @FindBy(xpath = "(//tbody/tr/td/dl[@class='dl-horizontal']/dd)[2]")
    public WebElement birthdateOfPet;
    
    @FindBy(xpath = "(//tbody/tr/td/dl[@class='dl-horizontal']/dd)[3]")
    public WebElement typeOfPet;
    
    
    public OwnersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterDetails(WebElement ele,String text) {
        ele.click();
        ele.sendKeys(text);
    }
    
    public void AddOwner(String fname, String lname, String oaddress, String ocity, String otelephone) throws InterruptedException {
    	AddOwnerLink.click();
		enterDetails(firstName, fname);
		enterDetails(lastName, lname);
		enterDetails(address, oaddress);
		enterDetails(city, ocity);
		enterDetails(telephone, otelephone);

		AddOwnerButton.click();
    }
    
	 public void fluentWait(String txt) {
		 @SuppressWarnings("unchecked")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
                 .ignoring(NoSuchElementException.class);
		 wait.until(ExpectedConditions.textToBePresentInElement(ownerInfo, txt));
	 }
	 
	 public void selectPetType(String petCategory) {
		 Select petTypeDropdown = new Select(driver.findElement(By.id("type")));
		 petTypeDropdown.selectByVisibleText(petCategory);
	 }
	 
	 public Boolean validateDateFormat(String dateToValdate) {

		 DateFormat formatter = new SimpleDateFormat("MMddyyyy");
		 formatter.setLenient(false);
		 try {
		     Date date= formatter.parse(dateToValdate);
		     return true;
		 } catch (ParseException e) {
		     return false;
		 }
		}

}
