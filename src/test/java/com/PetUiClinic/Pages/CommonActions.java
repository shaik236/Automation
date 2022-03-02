package com.PetUiClinic.Pages;

import com.PetUiClinic.Tests.PetUiClinicUI_Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;

public class CommonActions {
	private static final Logger logger = LogManager.getLogger(PetUiClinicUI_Test.class);
	
	public WebDriver driver;
	
	public CommonActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	 public void ClickElement(WebElement ele) {
	        ele.click();
	    }

	public static String generateRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		logger.info(generatedString);
		return generatedString;
	}

	public static String getProperty(String propertyName)
	{
		File file = new File("configs//Configuration.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String val = prop.getProperty(propertyName);
		logger.info("val is - " + val);
		if(val!= null) return val;
		else
		{
			throw new RuntimeException("url not specified in the Configuration.properties file.");
		}
	}
	 


}
