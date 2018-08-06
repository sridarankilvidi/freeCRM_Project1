package freeCRM_Cucumber_BDD_page;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class freeCRMBase {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop;
	public static Long waitTime;
	public static String loginPageTitle;
	public static String homePageTitle;
	
	public void BaseSetup() {
		
		prop = new Properties();
				
		try {
			FileInputStream ip = new FileInputStream("C:/Users/srida/Documents/JavaTrainingSession/freeCRM-Cucumber-BDD/freeCRM.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:/Users/srida/Documents/selenium/chromedriver/chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")){
			System.setProperty("webdriver.firefox.driver", "C:/Users/srida/Documents/selenium/geckodriver/geckodriver-v0.19.1-win64/geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		
		driver.get("http://freecrm.com/index.html");
		
		driver.manage().deleteAllCookies();
		
		final Long pageTimeout = Long.parseLong(prop.getProperty("PAGE_LOAD_TIMOUT"), 10);
		final Long implicitWait = Long.parseLong(prop.getProperty("IMPLICIT_WAIT"), 10);
		waitTime = Long.parseLong(prop.getProperty("ELEMENT_WAIT_TIME"), 10);
		loginPageTitle = prop.getProperty("loginTitle");
		homePageTitle = prop.getProperty("homePageTitle");
		
		driver.manage().timeouts().pageLoadTimeout(pageTimeout,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(implicitWait,TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver, waitTime);
	}
	
}
