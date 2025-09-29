package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Home_Base {

	
	public 	static WebDriver driver;
	
	public Home_Base() {
		super();
	}
	
	
public static void setup() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://erail.in/");
		Thread.sleep(3000);
	}


//This is for the Testing
}
