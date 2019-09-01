package org.wiki.setDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SetWebDriver {

	WebDriver driver = null;
	String browser = "chrome";
	
	public WebDriver setWebDriver() {
		String systemPath = System.getProperty("user.dir") + "\\lib\\";		
		
		if(browser.equalsIgnoreCase("firefox")){
			System.out.println("Inside Firefox if");
			System.setProperty("webdriver.gecko.driver", systemPath + "geckodriver.exe");
			driver = new FirefoxDriver();
		}  else if (browser.equalsIgnoreCase("ie")){
			System.out.println("Inside IE if");
			System.setProperty("webdriver.ie.driver", systemPath + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if(browser.equalsIgnoreCase("chrome")){
			System.out.println("Inside Chrome if");
			System.setProperty("webdriver.chrome.driver", systemPath + "chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.out.println("default opening chrome browser");
			System.setProperty("webdriver.chrome.driver", systemPath + "chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

}
