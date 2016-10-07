package com.selenium.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebElement element = null;
	private WebDriver driver = null;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement txtbx_UserName() {
		element = driver.findElement(By.name("username"));
		return element;
	}

	public WebElement txtbx_Password() {
		element = driver.findElement(By.name("password"));
		return element;
	}

	public WebElement btn_LogIn() {
		element = driver.findElement(By.xpath(".//*[@id='inputPassword']"));
		return element;
	}

}
