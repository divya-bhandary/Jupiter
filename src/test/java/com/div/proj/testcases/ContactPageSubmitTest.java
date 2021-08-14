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

public class ContactPageSubmitTest extends BaseTest {

	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP",invocationCount = 5)
	public void ContactPageSubmitTest(Hashtable<String,String> data) throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "ContactPageSubmitTest", data.get("Runmode"), excel);
		log.info("Inside Contact Page Success Test");
		openBrowser(data.get("browser"));
		logInfo("Launched Browser : "+data.get("browser"));
		JupiterHomePage home = new JupiterHomePage().open("https://jupiter.cloud.planittesting.com/");
		
		
		JupiterContactPage contact = home.gotoContact();

		contact.forename.sendKeys("Test");
		contact.email.sendKeys("test@gmail.com");
		contact.message.sendKeys("Test Message");
		
		contact.submit.click();
		
		Thread.sleep(3000);
//		waitForElementToLoad(contact.successMessage);
		softAssert.assertTrue(contact.successMessage.getText().contains("we appreciate your feedback"),"Success Message not present");
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		
		logInfo("Contact Page Submit Success Test Completed");
		System.out.println("Browser closed");
		quit();
		
	}

}
