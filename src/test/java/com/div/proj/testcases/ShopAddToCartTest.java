package com.div.proj.testcases;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.div.proj.PageObjects.CartPage;
import com.div.proj.PageObjects.JupiterHomePage;
import com.div.proj.PageObjects.ShopAddToCartPage;
import com.div.proj.utilities.Constants;
import com.div.proj.utilities.DataProviders;
import com.div.proj.utilities.DataUtil;
import com.div.proj.utilities.ExcelReader;

public class ShopAddToCartTest extends BaseTest {

	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
	public void ShopAddToCartTest(Hashtable<String,String> data) throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "ShopAddToCartTest", data.get("Runmode"), excel);
		log.info("Inside Shop - Add to cart Test");
		openBrowser(data.get("browser"));
		logInfo("Launched Browser : "+data.get("browser"));
		JupiterHomePage home = new JupiterHomePage().open("https://jupiter.cloud.planittesting.com/");
		
		
		ShopAddToCartPage shop = home.gotoShop();

//		shop.funnyCowBuy.click();
//		Thread.sleep(1000);
//		shop.funnyCowBuy.click();
		
		performClick(shop.funnyCowBuy);
		Thread.sleep(1000);



		log.info("Ah what the heck. Lets add 2 Funny Cow Toys");
		
		shop.fluffyBunnyBuy.click();
		log.info("Adding a Fluffy Bunny. Mum is losing her mind");
		Thread.sleep(1000);
		performClick(shop.funnyCowBuy);
		


		CartPage cart = shop.gotoCart();
		
		String funnyCowQty = cart.funnyCowCart.getAttribute("value");
		
		log.info("Funny Cow Quantity added :" + funnyCowQty);
		
		softAssert.assertTrue(funnyCowQty.equals("2"),"Funny Cow Quantity mismatch");
		
		String fluffyBunnyQty = cart.fluffyBunnyCart.getAttribute("value");
		
		log.info("Fluffy Bunny Quantity added :" + fluffyBunnyQty);
		
		softAssert.assertTrue(fluffyBunnyQty.equals("1"),"Fluffy Bunny Quantity mismatch");
		
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		
		logInfo("Shop Add To Cart Test Completed");
		System.out.println("Browser closed");
		quit();
		
	}

}
