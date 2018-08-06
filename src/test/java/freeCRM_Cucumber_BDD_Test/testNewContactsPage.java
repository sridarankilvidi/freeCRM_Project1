package freeCRM_Cucumber_BDD_Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import freeCRM_Cucumber_BDD_page.CommonUtil;
import freeCRM_Cucumber_BDD_page.freeCRMBase;
import freeCRM_Cucumber_BDD_page.homePage;
import freeCRM_Cucumber_BDD_page.loginPage;
import freeCRM_Cucumber_BDD_page.newContactsPage;

public class testNewContactsPage extends freeCRMBase{
	loginPage loginpage;
	homePage homepage;
	newContactsPage newcontactpage;
	CommonUtil util;
	String username;
	String pwd;
			
	@BeforeClass 
	public void newContactPageSetup() throws InterruptedException{
		BaseSetup();
		loginpage = new loginPage();
		// get un and pwd from property file to log into application
		username = prop.getProperty("un");
		pwd = prop.getProperty("pwd");
		
		homepage = loginpage.login(username, pwd);
		if (homepage !=null){
			Reporter.log("Login is successful",true);
			driver.switchTo().frame("mainpanel");
			
		}else{
			Reporter.log("Login is NOT successful",true);
			Assert.fail();
		}
		util = new CommonUtil();
	}
	
	@Test(priority=2)
	public void testClickOnNewContactsLink() throws InterruptedException{
		newcontactpage = homepage.clickOnNewContactsLink();
		if (newcontactpage != null){
			Reporter.log("New contact form page is displayed successfully!",true);
		}else{
			Reporter.log("New contact form page is NOT displayed successfully!",true);
			Assert.fail();	
		}
	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object[][] data = util.getTestData("Sheet1");
		return data;
	}
	
	@Test(priority=3, dataProvider="getCRMTestData")
	public void testCreateNewContacts(String title,String firstname,String lastname,String company) throws InterruptedException{
		
		String label = title+" "+firstname+" "+lastname;
		
		newcontactpage.createNewContacts(title,firstname,lastname,company);
		boolean labelexist = newcontactpage.verifyNewContactCreation(label);
		if (labelexist){
			Reporter.log("New contact is created successfully!:"+label,true);
		}else{
			Reporter.log("New contact is not created successfully!:"+label,true);
		}
		homepage.clickOnNewContactsLink();
	}
	
	@AfterClass 
	public void tearDown() throws InterruptedException{
		loginpage = homepage.clickLogout();
		if (loginpage!=null){
			Reporter.log("Logged out of CRM application",true);
		}else{
			Reporter.log("Log out failed! However, closing the browser",true);
		}
		driver.quit();
	}
}
