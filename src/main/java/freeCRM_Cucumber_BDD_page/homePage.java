package freeCRM_Cucumber_BDD_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

	public class homePage extends freeCRMBase{
		
		public static WebElement namelabel,logoutLink,contactsLink, newContactsLink;
		public newContactsPage newcontactpage;
		
	/*	public void homePageSetup() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
					("//td[contains(text(),'User: sridaran kilvidi')]")));
		label = driver.findElement(By.xpath
				("//td[contains(text(),'User: sridaran kilvidi')]"));
		
		logoutLink = driver.findElement(By.xpath("//a[contains (text(),'Logout')]"));
		
		}*/
		
		public boolean getLabel(String label){
			/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
					("//td[contains(text(),'User: sridaran kilvidi')]")));
			label = driver.findElement(By.xpath
				("//td[contains(text(),'User: sridaran kilvidi')]"));*/
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
					("//td[contains(text(),'"+label+"')]")));
			namelabel = driver.findElement(By.xpath("//td[contains(text(),'"+label+"')]"));
			return namelabel.isDisplayed();
		}
		
		public String getHPTitle(){
			return driver.getTitle();
		}
		
		public newContactsPage clickOnNewContactsLink() throws InterruptedException{
						
			contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
			newContactsLink = driver.findElement(By.xpath("//a[contains(text(),'New Contact')]"));
					
			Actions action = new Actions(driver);
			action.moveToElement(contactsLink).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(newContactsLink));
			newContactsLink.click();
			Thread.sleep(5000);
			newcontactpage = new newContactsPage();
			boolean contactlabel = newcontactpage.validateContactsLabel();
			if (contactlabel){
				return newcontactpage;
			}else{
				newcontactpage = null;
				return null;
			}
			
		}
		
		public loginPage clickLogout() throws InterruptedException{
			logoutLink = driver.findElement(By.xpath("//a[contains (text(),'Logout')]"));
			logoutLink.click();
			Thread.sleep(10000);
			String title = driver.getTitle();
						
			if (title.equalsIgnoreCase(loginPageTitle)){
				return new loginPage();
			}else{
				Assert.fail();
				return null;
			}
			
		}
		
}
