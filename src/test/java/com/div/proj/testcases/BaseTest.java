package com.div.proj.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.div.proj.ExtentListeners.ExtentListeners;
import com.div.proj.utilities.DriverFactory;
import com.div.proj.utilities.DriverManager;

public class BaseTest {

	private WebDriver driver;
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseTest.class);
	private String defaultUserName;
	private String defaultPassword;

	public boolean switchToWindow() {

		String winHandleBefore = driver.getWindowHandle();
		String windows = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		System.out.println("url" + driver.getCurrentUrl());
		return true;
	}

	public Boolean isNotDisplayed(WebElement element) {

		Boolean passFail = true;

		try {
			if (element.isDisplayed())
				passFail = false;
		} catch (NoSuchElementException e) {
			System.err.println("Unable to locate element '" + element + "'");
		} catch (NullPointerException e) {
			System.err.println("Unable to locate element '" + element + "'");
		} catch (Exception e) {
			System.err.println("Unable to check display status of element '" + element + "'");
			e.printStackTrace();
		}

		return passFail;

	}

	public Boolean waitForElementToLoad(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		return true;
	}

	public String getDefaultUserName() {
		return defaultUserName;
	}

	public void setDefaultUserName(String defaultUserName) {
		this.defaultUserName = defaultUserName;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	@BeforeSuite
	public void setUpFramework() {

		configureLogging();
		DriverFactory.setConfigPropertyFilePath(
				System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");

		if (System.getProperty("os.name").equalsIgnoreCase("mac")) {

			DriverFactory.setChromeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
			DriverFactory.setGeckoDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");

		} else {

			DriverFactory.setChromeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
			DriverFactory.setGeckoDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
			DriverFactory.setIeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");

		}
		/*
		 * Initialize properties Initialize logs load executables
		 * 
		 */
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.info("Config properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	public void logInfo(String message) {

		ExtentListeners.testReport.get().info(message);
	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties"
				+ File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	public void destroyFramework() {

	}

	public void openBrowser(String browser) {

		if (DriverFactory.isRemote()) {
			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {

				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);

			} else if (browser.equals("chrome")) {

				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("ie")) {

				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WIN10);
			}

		} else

		if (browser.equals("chrome")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
			driver = new FirefoxDriver();

		}

		DriverManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// setDefaultUserName(Config.getProperty("defaultUserName"));
		// setDefaultPassword(Config.getProperty("defaultPassword"));
	}

	public boolean click(WebElement element, int waitTime, int count) {
		boolean flag = false;
		try {

			//
			// WebDriverWait wait = new WebDriverWait(driver, 15);
			// wait.until(ExpectedConditions.elementToBeClickable(element));

			for (int i = 0; i < count; i++) {
				new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.elementToBeClickable(element));
				element.click();

			}
			flag = true;
			return flag;

		} catch (Exception Ex) {
			return flag;
		}
	}

	public boolean performClick(WebElement element) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(element));
			Actions actions = new Actions(driver);

			actions.moveToElement(element).click().build().perform();
			flag = true;
			return flag;

		}

		catch (Exception Ex) {
			return flag;
		}
	}

	public void quit() {

		DriverManager.getDriver().quit();
		log.info("Test Execution Completed !!!");
	}
}
