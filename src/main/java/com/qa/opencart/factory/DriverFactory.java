package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.frameworkexceptions.FrameworkExceptions;

public class DriverFactory {
	WebDriver driver;
	OptionsManager optionsManager;
	public static String highlightElement;
	

	public WebDriver init_driver(Properties prop) {

		String browser = prop.getProperty("browser").toLowerCase().trim();
		String url = prop.getProperty("url").toLowerCase().trim();
		optionsManager=new OptionsManager(prop);
		highlightElement=prop.getProperty("highlight");

		System.out.println("The given browser name is:" + browser);

		switch (browser) {
		case "chrome":
			System.out.println("Launching the browser:" + browser);
			driver = new ChromeDriver(optionsManager.ChromeOptionsManager());
			break;
		case "edge":
			System.out.println("Launching the browser:" + browser);
			driver = new EdgeDriver(optionsManager.EdgeOptionsManager());
			break;
		case "firefox":
			System.out.println("Launching the browser:" + browser);
			driver = new FirefoxDriver(optionsManager.FirefoxOptionsManager());
			break;
		default:
			System.out.println("The given browser name is:" + browser + ".Please enter the correct browser name");
			throw new FrameworkExceptions("BROWSERNOTFOUND");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;

	}

	public Properties init_prop() {
		Properties prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa"
		String environment = System.getProperty("env");
		System.out.println("Running testcases on:" + environment);

		try {

			if (environment == null) {
				ip = new FileInputStream("./src/main/resources/config/config.properties");
				System.out.println("No environment has been provided.Hence running it on qa...");
			} else {
				switch (environment.toLowerCase().trim()) {
				case "dev":
					ip = new FileInputStream("./src/main/resources/config/devConfig.properties");
					break;
				case "qa":
					ip = new FileInputStream("./src/main/resources/config/config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/main/resources/config/stageConfig.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/main/resources/config/prodConfig.properties");
					break;
				default:
					System.out
							.println("Please provide correct environment details for the Regression suite to run.Given:"
									+ environment);
					throw new FrameworkExceptions("NOENVIRONMENTPROVIDED");
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;

	}

}
