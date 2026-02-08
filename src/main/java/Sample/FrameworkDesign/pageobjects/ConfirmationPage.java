package Sample.FrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sample.FrameworkDesign.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

		WebDriver driver;
		
		public ConfirmationPage(WebDriver driver)
		{
			super(driver); //We are sending this driver to other test classes (e.g. LandingPage, ProductCatalogue, etc.)
			//initialization
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css=".hero-primary")
		WebElement confirmMessage;
		
		public String GetConfirmMessage()
		{
			return confirmMessage.getText();
		}
}
