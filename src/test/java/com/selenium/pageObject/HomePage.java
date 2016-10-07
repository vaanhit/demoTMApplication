package com.selenium.pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.utility.Constant;

public class HomePage {

	private static WebDriver driver = null;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public List<WebElement> CountHeaderHomepageLink() {
		List<WebElement> elements = new ArrayList<WebElement>();
		driver.get(Constant.url);
		elements = (driver.findElements(By.tagName("a")));
		// elements = driver.findElements(By.xpath("//a"));
		return elements;
	}

}
