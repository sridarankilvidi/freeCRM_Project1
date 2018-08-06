package freeCRM_Cucumber_BDD_page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class loginPage extends freeCRMBase{
	
	public static WebElement userNameTxt, passwordTxt, loginBtn;

	/*public void loginPageSetup() throws InterruptedException {
		//userNameTxt = driver.findElement(By.name("username"));
		//passwordTxt = driver.findElement(By.name("password"));
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id<locator>));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			//		("//input[@type='submit' and @value='Login']")));
		//Thread.sleep(3000);
		//loginBtn = driver.findElement(By.xpath("//input[@type='submit' and @value='Login']"));
		
	}*/
	
	public homePage login(String un, String pwd){
		try{
			userNameTxt = driver.findElement(By.name("username"));
			userNameTxt.clear();
			userNameTxt.sendKeys(un);
			passwordTxt = driver.findElement(By.name("password"));
			passwordTxt.clear();
			passwordTxt.sendKeys(pwd);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath
					("//input[@type='submit' and @value='Login']")));
			loginBtn = driver.findElement(By.xpath("//input[@type='submit' and @value='Login']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", loginBtn);
			//loginBtn.click();
			Thread.sleep(10000);
			String title = driver.getTitle(); 
			if (title.equals(homePageTitle)){
				return new homePage();
			}else{
				return null;
			}
			
		}catch (Exception ex){
			//ex.printStackTrace();
			return null;
		}
			
	}
	
	public String getLPTitle(){
		return driver.getTitle();
	}

}
