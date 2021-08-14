package com.div.proj.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.div.proj.utilities.DriverManager;

public class CartPage extends BasePage{
	
	
	@FindBy(xpath="//td[contains(text(),' Funny Cow')]/parent::tr/td[3]/input")
	public WebElement funnyCowCart;
	
	@FindBy(xpath="//td[contains(text(),'Fluffy Bunny')]/parent::tr/td[3]/input")
	public WebElement fluffyBunnyCart;
	
	@FindBy(xpath="//td[contains(text(),'Valentine Bear')]/parent::tr/td[3]/input")
	public WebElement valentineBearCart;

	@FindBy(xpath="//td[contains(text(),'Stuffed Frog')]/parent::tr/td[3]/input")
	public WebElement stuffedBearCart;
	
	@FindBy(xpath="//td[contains(text(),'Fluffy Bunny')]/parent::tr/td[2]")
	public WebElement fluffyBunnyPrice;
	
	@FindBy(xpath="//td[contains(text(),'Valentine Bear')]/parent::tr/td[2]")
	public WebElement valentineBearPrice;

	@FindBy(xpath="//td[contains(text(),'Stuffed Frog')]/parent::tr/td[2]")
	public WebElement stuffedBearPrice;
	
	
	@FindBy(xpath="//td[contains(text(),'Fluffy Bunny')]/parent::tr/td[4]")
	public WebElement fluffyBunnySubTotal;
	
	@FindBy(xpath="//td[contains(text(),'Valentine Bear')]/parent::tr/td[4]")
	public WebElement valentineBearSubTotal;

	@FindBy(xpath="//td[contains(text(),'Stuffed Frog')]/parent::tr/td[4]")
	public WebElement stuffedBearSubTotal;
	
	@FindBy(xpath="//*[@class='total ng-binding']")
	public WebElement total;
	
	@FindBy(xpath="//a[@href=\"#/checkout\"]")
	public WebElement checkOut;
	

@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(checkOut);
	}
	

	
}
