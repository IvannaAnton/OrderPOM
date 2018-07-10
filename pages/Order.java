package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;

public class Order {
	public Order(WebDriver driver) {
		// using the driver to initialize all elements for us
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
	public WebElement product;

	@FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement quantity;

	@FindBy(id = "ctl00$MainContent$fmwOrder$txtTotal")
	public WebElement total;

	@FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
	public WebElement fullName;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
	public WebElement street;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
	public WebElement city;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
	public WebElement state;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
	public WebElement zipcode;

	@FindBy(xpath = "//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td/input")
	public List<WebElement> cardType;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
	public WebElement cardNumber;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox1")
	public WebElement expDate;

	@FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
	public WebElement button;

	@FindBy(xpath="//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td")
	public List<WebElement> row;
	

		
}
