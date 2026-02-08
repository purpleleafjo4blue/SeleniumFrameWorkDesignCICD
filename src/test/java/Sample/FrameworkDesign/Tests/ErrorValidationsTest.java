package Sample.FrameworkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Sample.FrameworkDesign.TestComponents.Retry;

import Sample.FrameworkDesign.TestComponents.BaseTest;
import Sample.FrameworkDesign.pageobjects.CartPage;
import Sample.FrameworkDesign.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class) //make sure import the Retry class from TestComponents for the Retry.class to work
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		landingpage.loginApplication("jo4blue@gmai.com", "amplePass123");
		Assert.assertEquals("Incorrect email or password.", landingpage.GetErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.loginApplication("jo4blue@gmail.com", "samplePass123");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName); //throws InterruptedException - Line 26
		CartPage cartpage = productcatalogue.goToCartPage(); //Product catalogue does not have the goToCartPage but from the AbstractComponent class.
		//Product catalogue class can access this method because it is connected to AbstractComponent through child inheritance. "extends"
		
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		//Code to perform actions can be placed in the page object files but code to validate (Assertions) should only be on test classes.
		Assert.assertFalse(match);
		
		
	}

}
