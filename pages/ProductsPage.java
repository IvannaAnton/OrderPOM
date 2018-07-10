package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	public ProductsPage(WebDriver driver) {
		// using the driver to initialize all elements for us
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[@class='ProductsTable']/tbody/tr")
	public List<WebElement> productRows;

	@FindBy(xpath = "//table[@class='ProductsTable']/tbody/tr/td[1]")
	public List<WebElement> productNames;

	@FindBy(xpath = "//table[@class='ProductsTable']/tbody/tr/td[2]")
	public List<WebElement> prices;

	@FindBy(xpath = "//table[@class='ProductsTable']/tbody/tr/td[3]")
	public List<WebElement> discounts;

}
