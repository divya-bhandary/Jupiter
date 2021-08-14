package com.div.proj.testcases;

import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.div.proj.utilities.Constants;
import com.div.proj.utilities.DataUtil;
import com.div.proj.utilities.DriverManager;
import com.div.proj.PageObjects.CartPage;
import com.div.proj.PageObjects.JupiterContactPage;
import com.div.proj.PageObjects.JupiterHomePage;
import com.div.proj.PageObjects.ShopAddToCartPage;
import com.div.proj.utilities.DataProviders;
import com.div.proj.utilities.ExcelReader;

public class CartTotalAmountTest extends BaseTest {

	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
	public void CartTotalAmountTest(Hashtable<String,String> data) throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "CartTotalAmountTest", data.get("Runmode"), excel);
		log.info("Inside Cart Total Amount Test");
		openBrowser(data.get("browser"));
		logInfo("Launched Browser : "+data.get("browser"));
		JupiterHomePage home = new JupiterHomePage().open("https://jupiter.cloud.planittesting.com/");
		
		
		ShopAddToCartPage shop = home.gotoShop();

		click(shop.stuffedFrogBuy,10,2);
		
		click(shop.fluffyBunnyBuy,10,5);
		
		click(shop.valentineBearBuy,10,3);
		
		log.info("Added 2 Frogs, 5 Bunnys, 3 Bears");
		
		

		CartPage cart = shop.gotoCart();
		
		/*Quantity added to the cart*/
		
		Double stuffedFrogQty = Double.parseDouble(cart.stuffedBearCart.getAttribute("value"));
		log.info("stuffed Frog Quantity added :" + stuffedFrogQty);
		softAssert.assertTrue(stuffedFrogQty==2,"stuffed Frog Quantity mismatch");
		
		
		Double fluffyBunnyQty = Double.parseDouble(cart.fluffyBunnyCart.getAttribute("value"));
		log.info("fluffy Bunny Quantity added :" + fluffyBunnyQty);
		softAssert.assertTrue(fluffyBunnyQty==5,"Fluffy Bunny Quantity mismatch");
		

		Double valentineBearQty = Double.parseDouble(cart.valentineBearCart.getAttribute("value"));
		log.info("valentine Bear Quantity added :" + valentineBearQty);
		softAssert.assertTrue(valentineBearQty==3,"valentine Bear Quantity mismatch");
		
		/*Price for each of the products*/
		Double stuffedFrogPrice = Double.parseDouble(cart.stuffedBearPrice.getText().replace("$", ""));
		Double fluffyBunnyPrice = Double.parseDouble(cart.fluffyBunnyPrice.getText().replace("$", ""));
		Double valentineBearPrice = Double.parseDouble(cart.valentineBearPrice.getText().replace("$", ""));
		
		
		/*SubTotal for each of the products*/
		Double stuffedFrogSubTotal = Double.parseDouble(cart.stuffedBearSubTotal.getText().replace("$", ""));
		Double fluffyBunnySubTotal = Double.parseDouble(cart.fluffyBunnySubTotal.getText().replace("$", ""));
		Double valentineBearSubTotal = Double.parseDouble(cart.valentineBearSubTotal.getText().replace("$", ""));
		
		Double total = Double.parseDouble(cart.total.getText().replace("Total: ", ""));
		
		softAssert.assertEquals(stuffedFrogQty*stuffedFrogPrice, stuffedFrogSubTotal,"The SubTotal for Stuffed Frog does not match the item price * quantity");
		softAssert.assertEquals(fluffyBunnyQty*fluffyBunnyPrice, fluffyBunnySubTotal,"The SubTotal for Fluffy Bunny does not match the item price * quantity");
		softAssert.assertEquals(valentineBearQty*valentineBearPrice, valentineBearSubTotal,"The SubTotal for Valentine Bear does not match the item price * quantity");
		
		
		softAssert.assertEquals(stuffedFrogSubTotal+fluffyBunnySubTotal+valentineBearSubTotal,total,"The sum of sub-totals does not match the total on the Cart Page");
		
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		
		logInfo("Cart Total Amount Test Completed");
		System.out.println("Browser closed");
		quit();
		
	}

}
