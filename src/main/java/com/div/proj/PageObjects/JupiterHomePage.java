package com.div.proj.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.div.proj.utilities.DriverManager;

public class JupiterHomePage extends BasePage{
	
//	@FindBy(xpath="//a[contains(@href,'contact')")
	
	@FindBy(linkText="Contact")
	public WebElement contact;
	
	@FindBy(linkText="Shop")
	public WebElement shop;
	
	public JupiterHomePage open(String url) {
		
		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return (JupiterHomePage) openPage(JupiterHomePage.class);
	}
	

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(contact);
	}
	
	public JupiterContactPage gotoContact() {
		
		click(contact,"Contact");
		System.out.println("Clicked on Contact" ); // debug
		return (JupiterContactPage) openPage(JupiterContactPage.class);
		
	}
	
	public ShopAddToCartPage gotoShop() {
		
		click(shop,"Shop");
		System.out.println("Clicked on Shop" ); // debug
		return (ShopAddToCartPage) openPage(ShopAddToCartPage.class);
		
	}

}
