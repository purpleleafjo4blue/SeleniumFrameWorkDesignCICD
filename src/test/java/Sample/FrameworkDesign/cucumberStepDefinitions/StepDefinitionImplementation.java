package Sample.FrameworkDesign.cucumberStepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Sample.FrameworkDesign.TestComponents.BaseTest;
import Sample.FrameworkDesign.pageobjects.CartPage;
import Sample.FrameworkDesign.pageobjects.CheckoutPage;
import Sample.FrameworkDesign.pageobjects.ConfirmationPage;
import Sample.FrameworkDesign.pageobjects.LandingPage;
import Sample.FrameworkDesign.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public CheckoutPage checkoutpage;
	public ConfirmationPage confirmpage;
	
	@Given("I landed on Eccomerce Page")
	public void I_landed_on_Eccomerce_Page() throws IOException
	{
		landingPage = LaunchApplication();
		//code
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productcatalogue = landingpage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	
	@And("^Checkout (.+) and submit the order.$")  //You can also use @When in this context
	public void checkout_and_submit_order(String productName)
	{
		CartPage cartpage = productcatalogue.goToCartPage(); 
		
		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutpage = cartpage.goToCheckOut();
		checkoutpage.SelectCountry("india");
		confirmpage = checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void messaage_is_displayed_on_Confirmation_page(String string)
	{
		String confirmMessage = confirmpage.GetConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	// "Incorrect email or password." message is displayed
	@Then("{string} message is displayed")
	public void loginerrormessage_is_displayed(String string)
	{
		Assert.assertEquals(string, landingpage.GetErrorMessage());
		driver.close();
	}
	
}
