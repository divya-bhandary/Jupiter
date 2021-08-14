package com.div.proj.testcases;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.div.proj.PageObjects.JupiterContactPage;
import com.div.proj.PageObjects.JupiterHomePage;
import com.div.proj.utilities.Constants;
import com.div.proj.utilities.DataProviders;
import com.div.proj.utilities.DataUtil;
import com.div.proj.utilities.ExcelReader;

public class ValidateMandatoryFieldsContactTest extends BaseTest {

		
	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
	public void validateMandatoryFieldsContactTest(Hashtable<String,String> data) throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "ValidateMandatoryFieldsContactTest", data.get("Runmode"), excel);
		log.info("Inside Validate Mandatory Fields Contact Test");
		openBrowser(data.get("browser"));
		logInfo("Launched Browser : "+data.get("browser"));
		JupiterHomePage home = new JupiterHomePage().open("https://jupiter.cloud.planittesting.com/");
		
		
		JupiterContactPage contact = home.gotoContact();
		System.out.println("contact");


		contact.submit.click();
		System.out.println("contact - submit clicked");
		logInfo("Clicked on Submit in Contact page");
		
		softAssert.assertTrue(contact.forenameError.getText().contains("Forename is required"), "Forename field is empty");
		softAssert.assertTrue(contact.emailError.getText().contains("Email is required"), "Email field is empty");
		softAssert.assertTrue(contact.messageError.getText().contains("Message is required"), "Message field is empty");
		
		
		contact.forename.sendKeys("Test");
		contact.email.sendKeys("test@gmail.com");
		contact.message.sendKeys("Test Message");
		

	softAssert.assertTrue(isNotDisplayed(contact.mandatoryFieldError));
		
//		softAssert.assertTrue(!contact.forenameError.isDisplayed(),"Forename Error exists");
//		softAssert.assertTrue(isNotDisplayed(contact.emailError),"Email Error exists");
//		softAssert.assertTrue(isNotDisplayed(contact.messageError),"Message Error exists");
		
		logInfo("Check for Error messages no longer displayed - Success");
		
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		
		logInfo("Validate Mandatory Fields Contact Test Completed");
		System.out.println("Browser closed");
		quit();
		
	}

}
