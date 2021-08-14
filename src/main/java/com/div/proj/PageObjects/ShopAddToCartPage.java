package com.div.proj.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.div.proj.utilities.DriverManager;

public class ShopAddToCartPage extends BasePage{
	

	@FindBy(xpath="//div[contains(.,\"Funny Cow\")]/p/a")
	public WebElement funnyCowBuy;
	
	@FindBy(xpath="//div[contains(.,\"Fluffy Bunny\")]/p/a")
	public WebElement fluffyBunnyBuy;
	
	@FindBy(xpath="//div[contains(.,\"Stuffed Frog\")]/p/a")
	public WebElement stuffedFrogBuy;
	
	@FindBy(xpath="//div[contains(.,\"Valentine Bear\")]/p/a")
	public WebElement valentineBearBuy;
	
	@FindBy(xpath="//*[@id=\"nav-cart\"]/a")
	public WebElement cart;
	
	

@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(funnyCowBuy);
	}
	
public CartPage gotoCart() {
	
	click(cart,"cart");
	System.out.println("Clicked on Cart" ); // debug
	return (CartPage) openPage(CartPage.class);
	
}
	
}
