package freeCRM_Cucumber_BDD_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class newContactsPage extends freeCRMBase{
	
	//create home page web elements repository by pagefactory method
			
		@FindBy(xpath="//select[@name='title']")
		WebElement greetTitle;
		
		@FindBy(name="first_name")
		WebElement fname;
		
		@FindBy(name="surname")
		WebElement lname;
		
		@FindBy(name="client_lookup")
		WebElement company;
		
		@FindBy(xpath="//input[@type='submit' and @value='Save']")
		WebElement saveBtn;
		
		@FindBy(xpath="//fieldset/legend[contains(text(),'Contact Information')]")
		WebElement newContactPageVerify;
		
		
		
		//constructor - initializes all the webelements with driver
		public newContactsPage(){
			PageFactory.initElements(driver,this);
		}
		
		public boolean validateContactsLabel(){
			return newContactPageVerify.isDisplayed();
		}
		
		public boolean verifyNewContactCreation(String label){
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
					("//tbody/tr[1]/td[contains(text(),'"+label+"')]")));
			WebElement namelabel = driver.findElement(By.xpath("//tbody/tr[1]/td[contains(text(),'"+label+"')]"));
			return namelabel.isDisplayed();
		}
		
		public void selectContactsByName(String contactName){
			if (driver.findElement(By.xpath("//a[text()='"+contactName+"']")).isDisplayed()){
				
				driver.findElement(By.xpath("//a[text()='"+contactName+"']//parent::td[@class='datalistrow']"
					+"//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
			}
		}
		
		public void createNewContacts(String greet, String ftname, String ltname, String comp) throws InterruptedException{
			//WebElement greetTitle = driver.findElement(By.xpath("//select[@name='title']"));
			Select select = new Select(greetTitle);
			select.selectByVisibleText(greet);
			fname.clear();
			fname.sendKeys(ftname);
			lname.clear();
			lname.sendKeys(ltname);
			company.clear();
			company.sendKeys(comp);
			saveBtn.click();
			Thread.sleep(5000);
		}
}
