package freeCRM_Cucumber_BDD_Test;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import freeCRM_Cucumber_BDD_page.freeCRMBase;
import freeCRM_Cucumber_BDD_page.homePage;
import freeCRM_Cucumber_BDD_page.loginPage;

public class testHomePage extends freeCRMBase{
	loginPage loginpage;
	homePage homepage;
	String username;
	String pwd;
	String label;
	
	@BeforeTest
	void initHomePage() throws InterruptedException {
		BaseSetup();
		loginpage = new loginPage();
		// get un and pwd from property file to log into application
		username = prop.getProperty("un");
		pwd = prop.getProperty("pwd");
		
		homepage = loginpage.login(username, pwd);
		if (homepage !=null){
			Reporter.log("Login is successful",true);
			driver.switchTo().frame("mainpanel");
			//homepage.homePageSetup();
		}else{
			Reporter.log("Login is NOT successful",true);
			Assert.fail();
		}
	}
	
	@Test(priority=0)
	void testGetLabel(){
		// format the expected fn/ln lebel that appears after successful login
		// the format of label is : "User: sridaran kilvidi"
		
		label = "User: "+prop.getProperty("fName")+" "+prop.getProperty("lName");
		boolean labelvisible = homepage.getLabel(label);
		// if user name is displayed then home page is displayed
		if (labelvisible==true){
			Reporter.log("User Label is displayed",true);
		}else{
			Reporter.log("User Label is NOT displayed",true);
			Assert.fail();
		}
	}
	
	@Test (priority=1)
	void testClickOnNewContactsLink() throws InterruptedException{
		homepage.clickOnNewContactsLink();
	}
	
	/*@Test(priority=2)
	void testLogout() throws InterruptedException{
		loginpage = homepage.clickLogout();
		if (loginpage !=null){
			Reporter.log("Logout is successful",true);
		}else{
			Reporter.log("Logout is NOT successful",true);
			Assert.fail();
		}
		
	}*/
	
	@AfterTest
	void tearDownHP(){
		driver.quit();
	}
}
