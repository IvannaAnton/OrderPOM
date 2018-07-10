package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebOrdersLogInPage {
	
	public WebOrdersLogInPage(WebDriver driver) {
		//using the driver to initialize all elements for us
		
		PageFactory.initElements(driver, this);
		
	}
	//specify a way to locate the element
	@FindBy(id="ctl00_MainContent_username")
	public WebElement userName;
	
	@FindBy(id="ctl00_MainContent_password")
	public WebElement password;
	
	@FindBy(id="ctl00_MainContent_login_button")
	public WebElement loginButton;
	
	@FindBy(id="ctl00_MainContent_status")
	public WebElement status;
	
	public void logIn(String uid, String pwd) {
		userName.sendKeys(uid);
		password.sendKeys(pwd);
	    loginButton.click();
	}
	

}
