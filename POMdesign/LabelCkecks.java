package POMdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import pages.Order;
import pages.ProductsPage;
import pages.WebOrderAllOrdersPage;
import pages.WebOrdersLogInPage;
import testBase.TestBase;

public class LabelCkecks extends TestBase {

	Order order;

	@Test(priority = 1)
	public void varification() {
		//assertEquals(driver.getTitle(), "Web Orders Login");
		allOrders = new WebOrderAllOrdersPage(driver);
		System.out.println(allOrders.webOrders.isDisplayed());
		assertTrue(allOrders.webOrders.isDisplayed(), "Web Orders is not displayed");
		assertTrue(allOrders.listAll.isDisplayed(), "List Of All Orders label is not displayed");
		assertEquals(allOrders.wellcome.getText().replace(" | Logout", ""), "Welcome, " + user + "!");
		assertTrue(allOrders.viewAllOrders.isDisplayed(), "viewAllOrders is not displayed");
		assertTrue(allOrders.orderTab.isDisplayed(), "orderTab is not displayed");
	}

	@Test(priority = 2)
	public void avalibleProducts() {
		//assertEquals(driver.getTitle(), "Web Orders");
		allOrders = new WebOrderAllOrdersPage(driver);
		allOrders.viewAllProducts.click();
		productPage = new ProductsPage(driver);
		List<String> expected = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actual = new ArrayList<>();

		for (WebElement prod : productPage.productNames) {
			actual.add(prod.getText());

		}
		assertEquals(actual, expected);
		for (WebElement row : productPage.productRows) {
			System.out.println(row.getText());
			String[] prodData = row.getText().split(" ");
			switch (prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1], "$100");
				assertEquals(prodData[2], "8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1], "$80");
				assertEquals(prodData[2], "15%");
				break;
			case "ScreenSavery":
				assertEquals(prodData[1], "$20");
				assertEquals(prodData[2], "10%");
				break;
			}
		}
	}

	@Test(priority = 3)
	public void placeOrder() {
		assertEquals(driver.getTitle(), "Web Orders");
		allOrders = new WebOrderAllOrdersPage(driver);
		allOrders.orderTab.click();

		// product block
		order = new Order(driver);

		Select prd = new Select(order.product);
		prd.selectByIndex(new Faker().number().numberBetween(0, 3));
		String product = prd.getFirstSelectedOption().getText();

		String quantity = String.valueOf(new Faker().number().numberBetween(1, 10));
		order.quantity.sendKeys(quantity);

		// information block
		String fullName = new Faker().name().fullName();
		order.fullName.sendKeys(fullName);

		String adress = new Faker().address().streetAddress();
		order.street.sendKeys(adress);

		String city = new Faker().address().city();
		order.city.sendKeys(city);

		String state = new Faker().address().state();
		order.state.sendKeys(state);

		String zip = "22209";
		order.zipcode.sendKeys(zip);

		// payment block
		String cardName = null;
		for (int i = 0; i < order.cardType.size(); i++) {
			WebElement cardChoise = driver.findElement(By
					.xpath("(//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td/input)[" + (i + 1) + "]"));
			cardChoise.click();
			cardName = cardChoise.getAttribute("value");
		}
		String card = new Faker().finance().creditCard().replaceAll("-", "");
		order.cardNumber.sendKeys(card);
		
		String exp = "10/20";
		order.expDate.sendKeys(exp);

		// click to order
		order.button.click();

		// Verification
		String expected = "New order has been successfully added.";
		String actual = driver.findElement(By.xpath("//strong")).getText();
		assertEquals(actual, expected);

		// click back to the page
		allOrders.viewAllOrders.click();

		// Verification on the other page
		String date = "07/09/2018";
		List<String> expectedList = Arrays.asList(product, quantity, date, adress, city, state, zip, cardName, card,
				exp);
		List<String> actualList = new ArrayList<>();
		for (int i = 2; i < order.row.size() - 1; i++) {
			actualList.add(order.row.get(i).getText());

		}

		assertEquals(actualList, expectedList);

	}

}
