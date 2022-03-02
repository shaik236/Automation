package com.PetUiClinic.Tests;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.PetUiClinic.Pages.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetUiClinicUI_Test {
	private static final Logger logger = LogManager.getLogger(PetUiClinicUI_Test.class);
	WebDriver driver;

	public static String lastName;
	public static String petName;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		lastName = CommonActions.generateRandomString();
		petName = CommonActions.generateRandomString();

	}

	@Before
	public void setUp() throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String Urle = CommonActions.getProperty("URL");
		logger.info(Urle);
		driver.get(Urle.toString());
	}

	@After
	public void tearDown() throws Exception {
		
		driver.quit();
		
	}

	@Test
	public void Test1_VerifyHomePageImage() {
		HomePage home = new HomePage(driver);		
		assertTrue(home.verifyImage(home.petImage));
	}

	@Test
	public void Test2_VerifyAddOwnerWorkflow() throws InterruptedException {
		String fname = "Steve2";
//		String lname = CommonActions.generateRandomString();
//		lastName = lname;
		String ocity = "Texas";
		String oaddress = "Lu street";
		String otelephone = "12758587";

		HomePage home = new HomePage(driver);
		CommonActions common = new CommonActions(driver);
		common.ClickElement(home.findownersLink);
		OwnersPage owner =  new OwnersPage(driver);
		owner.AddOwner(fname, lastName, oaddress, ocity, otelephone);

		common.ClickElement(home.findownersLink);
		owner.enterDetails(owner.lastName, lastName);
		common.ClickElement(owner.AddOwnerButton);

		owner.fluentWait("Owner Information");

		assertTrue(owner.ownerName.getText().equals(fname +" "+lastName));
		assertTrue(owner.ownerAddress.getText().equals(oaddress));
		assertTrue(owner.ownerCity.getText().equals(ocity));
		assertTrue(owner.ownerTelephone.getText().equals(otelephone));

	}

	@Test
	public void Test3_VerifyAddPetWorkflow() throws InterruptedException {
//		String lname = lastName;
		String petname = petName;
		String birthdate = "12122021";//MMddyyyy
		String petType = "dog";

		logger.info(lastName);

		HomePage home = new HomePage(driver);
		CommonActions common = new CommonActions(driver);
		OwnersPage owner =  new OwnersPage(driver);
		common.ClickElement(home.findownersLink);
		owner.enterDetails(owner.lastName, lastName);
		common.ClickElement(owner.FindOwnerButton);


		common.ClickElement(owner.AddPetLink);
		owner.enterDetails(owner.petName, petname);

		Boolean isValidBirthDate = owner.validateDateFormat(birthdate);
		logger.info(isValidBirthDate);
		if(isValidBirthDate) {
			owner.enterDetails(owner.petBirthDt, birthdate);
		}else {
			assertTrue(isValidBirthDate);
		}
		owner.selectPetType(petType);
		common.ClickElement(owner.AddOwnerButton);

		assertTrue(owner.nameOfPet.getText().equals(petname));
		assertTrue(owner.typeOfPet.getText().equals(petType));
	}

}
