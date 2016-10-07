package com.selenium.appModule;

import org.openqa.selenium.WebDriver;

import com.selenium.pageObject.LoginPage;

public class SignInAction {

	public SignInAction() {
		// TODO Auto-generated constructor stub
	}

	public static void execute(WebDriver driver, String userName, String password) {
		LoginPage loginPage = new LoginPage(driver);
		// loginPage.lnk_SignIn(driver).click();

		loginPage.txtbx_UserName().sendKeys(userName);
		loginPage.txtbx_Password().sendKeys(password);
		loginPage.btn_LogIn().click();

	}

}
