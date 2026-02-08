package Sample.FrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sample.FrameworkDesign.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver); //We are sending this driver to other test classes (e.g. LandingPage, ProductCatalogue, etc.)
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText()
				.equalsIgnoreCase(productName));
		return match;
	}
	
	@FindBy(css=".totalRow button")
	WebElement CheckoutEle;
	
	public CheckoutPage goToCheckOut()
	{
		CheckoutEle.click();
		return new CheckoutPage(driver);
	}
	
}
