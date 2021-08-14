package com.div.proj.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.div.proj.utilities.DriverManager;

public class JupiterContactPage extends BasePage{
	
	
//	@FindBy(xpath="//*[contains(text(),'Cliq')]")
	@FindBy(id="forename")
	public WebElement forename;
	
	@FindBy(id="surname")
	public WebElement surname;
	
	@FindBy(id="email")
	public WebElement email;
	
	@FindBy(id="telephone")
	public WebElement telephone;
	
	@FindBy(id="message")
	public WebElement message;

	@FindBy(id="surname")
	public WebElement Surname;
	
	
//	@FindBy(xpath="//a[contains(@href,'Submit')")
//	public WebElement submit;
	
	@FindBy(partialLinkText="Submit")
	public WebElement submit;
	
	@FindAll( { @FindBy(id="forename-err"),
				@FindBy(id="email-err"),
				@FindBy(id="message-err")
	}) 
	public WebElement mandatoryFieldError;
	
	@FindBy(id="forename-err") //Forename is required
	public WebElement forenameError;
	
	@FindBy(id="email-err") //Email is required
	public WebElement emailError;
	
	@FindBy(id="message-err") //Message is required
	public WebElement messageError;
	

//	@FindBy(className="alert alert-success")
	@FindBy(xpath="//div[@class='alert alert-success']")
	public WebElement successMessage;

@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(forename);
	}
	

	
}
