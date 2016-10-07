package com.selenium;

import java.io.File;
import java.util.List;

import org.eclipse.jetty.util.security.Password;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.appModule.SignInAction;
import com.selenium.pageObject.HomePage;
import com.selenium.pageObject.LoginPage;
import com.selenium.utility.Constant;
import com.selenium.utility.ExcelUtils;

public class DemoTMTestNG {

	private WebDriver driver;
	static String driverPath = Constant.driverPath;

	@BeforeClass
	public void setup() throws Exception {
		File file = new File(driverPath);
		System.setProperty(Constant.driverName, file.getAbsolutePath());
		driver = new InternetExplorerDriver();
		
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	}

	@Test
	private void headerCheckTestCase1() {
		HomePage chkHeader = new HomePage(driver);
		List<WebElement> elements = chkHeader.CountHeaderHomepageLink();

		System.out.println("Number of links   " + elements.size());
		Assert.assertEquals(elements.size(), 7);
	}

	@Test
	private void LoginTestCase1() throws Exception {
		String username = ExcelUtils.getCellData(0, 1);
		String passwprd = ExcelUtils.getCellData(1, 2);

		SignInAction.execute(driver, username, passwprd);

		System.out.println("Sign in successfull");
	}

	@AfterClass
	private void exit() {
		driver.quit();
	}
}
