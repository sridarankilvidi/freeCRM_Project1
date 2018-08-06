package freeCRM_Cucumber_BDD_Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import freeCRM_Cucumber_BDD_page.freeCRMBase;
import freeCRM_Cucumber_BDD_page.homePage;
import freeCRM_Cucumber_BDD_page.loginPage;

public class testLoginPage extends freeCRMBase{
	loginPage loginpage;
	homePage homepage;
	
	@BeforeClass
	void initLoginPage() throws InterruptedException{
		BaseSetup();
		loginpage = new loginPage();
		//loginpage.loginPageSetup();
	}
	
	@Test(priority=0)
	void testGetTitle(){
		String title = loginpage.getLPTitle();
		if (title.equalsIgnoreCase(loginPageTitle)){
			Reporter.log("Login page is Displayed successfully",true);
		}else{
			Reporter.log("Login page is NOT Displayed successfully",true);
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] dp() {
		Object[][] loginData = {
							{"sridarankilvidi", "Passw0rd"},
							{"name2", "pwd2"},
							{"sridarankilvidi", "Passw0rd"},
							};
	
	    return loginData; 
   
	}
	
	@Test(priority=1, dataProvider = "dp")
	void testLogin(String un, String pwd) throws InterruptedException{
		
		homepage = loginpage.login(un,pwd);
		
		if (homepage !=null){
			Reporter.log("Login is successful",true);
			driver.switchTo().frame("mainpanel");
			//homepage.homePageSetup();
			loginpage=homepage.clickLogout();
			if (loginpage != null){
				Reporter.log("Logout is successful",true);
			}else{
				Reporter.log("Logout is NOT successful",true);
				Assert.fail();
			}
		}else{
			Reporter.log("Login is NOT successful",true);
			Assert.fail();
		}
	}
	
	@AfterClass
	void tearDownLP(){
		driver.quit();
	}
	
}
