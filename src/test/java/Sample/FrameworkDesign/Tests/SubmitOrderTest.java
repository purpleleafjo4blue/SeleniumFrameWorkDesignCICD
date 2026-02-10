package Sample.FrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Sample.FrameworkDesign.TestComponents.BaseTest;
import Sample.FrameworkDesign.pageobjects.CartPage;
import Sample.FrameworkDesign.pageobjects.CheckoutPage;
import Sample.FrameworkDesign.pageobjects.ConfirmationPage;
import Sample.FrameworkDesign.pageobjects.OrderPage;
import Sample.FrameworkDesign.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	//Sample comment for CICD lecture from RahulShettyAcademy
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups={"Purchase"})
	public void SubmitOrderTest(HashMap<String,String>input) throws IOException, InterruptedException
	{
		
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product")); //throws InterruptedException - Line 26
		CartPage cartpage = productcatalogue.goToCartPage(); //Product catalogue does not have the goToCartPage but from the AbstractComponent class.
		//Product catalogue class can access this method because it is connected to AbstractComponent through child inheritance. "extends"
		
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		//Code to perform actions can be placed in the page object files but code to validate (Assertions) should only be on test classes.
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.SelectCountry("india");
		ConfirmationPage confirmpage = checkoutpage.submitOrder();
		
		String confirmMessage = confirmpage.GetConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	
	//To verify ZARA COAT 3 is displaying in the orders page.
	
	@Test(dependsOnMethods = {"SubmitOrderTest"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue = landingpage.loginApplication("jo4blue@gmail.com", "samplePass123");
		OrderPage orderpage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/* Old code with hashmaps
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "jo4blue@gmail.com");
		map1.put("password", "samplePass123");
		map1.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "yorforger4455@gmail.com");
		map2.put("password", "samplePass123");
		map2.put("product", "ADIDAS ORIGINAL");
		*/
		
		//Old code -> return new Object[][] {{"jo4blue@gmail.com", "samplePass123", "ZARA COAT 3"}, {"yorforger4455@gmail.com", "samplePass123", "ADIDAS ORIGINAL"}};
		
		//DataReader datareader = new DataReader();
		//List<HashMap<String, String>> data = datareader.getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Sample//FrameworkDesign//Data//PurchaseOrder.json");
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"//src//test//java//Sample//FrameworkDesign//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}}; //throws IOException for line 74
	}
}
