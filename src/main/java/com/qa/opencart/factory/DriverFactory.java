package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	WebDriver driver;

	public WebDriver init_driver(Properties prop) {
		
		String browser=prop.getProperty("browser").toLowerCase().trim();
		String url=prop.getProperty("url").toLowerCase().trim();

		System.out.println("The given browser name is:" + browser);

		switch (browser) {
		case "chrome":
			System.out.println("Launching the browser:" + browser);
			driver = new ChromeDriver();
			break;
		case "edge":
			System.out.println("Launching the browser:" + browser);
			driver = new EdgeDriver();
			break;
		case "firefox":
			System.out.println("Launching the browser:" + browser);
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("The given browser name is:" + browser + ".Please enter the correct browser name");
			break;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;

	}

	public Properties init_prop() {
		Properties prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream("./src/main/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;

	}

}
