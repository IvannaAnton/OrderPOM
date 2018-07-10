package testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ProductsPage;
import pages.WebOrderAllOrdersPage;
import pages.WebOrdersLogInPage;

public class TestBase {
	public WebDriver driver;
	public WebOrderAllOrdersPage allOrders;
	public WebOrdersLogInPage loginPage;
	public ProductsPage productPage;
	public String user = "Tester";
	public String password = "test";
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
	}
	@BeforeMethod
	public void setUpAplication() {
	    loginPage = new WebOrdersLogInPage(driver);
	    loginPage.logIn(user, password);
	    
		
	}
	@AfterMethod
	public void logout() {
		allOrders = new WebOrderAllOrdersPage(driver);
    	allOrders.logout();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
