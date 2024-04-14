package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {

		this.prop = prop;
	}

	public ChromeOptions ChromeOptionsManager() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			//co.addArguments("--remote-allow-origins=*");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			//co.addArguments("--remote-allow-origins=*");
			co.addArguments("--incognito");

		}
		return co;

	}

	public FirefoxOptions FirefoxOptionsManager() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");

		}
		return fo;

	}

	public EdgeOptions EdgeOptionsManager() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			//eo.addArguments("--remote-allow-origins=*");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			//eo.addArguments("--remote-allow-origins=*");
			eo.addArguments("--incognito");

		}
		return eo;

	}

}
