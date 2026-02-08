@tag
Feature: Purchase the order from Ecommerce website.
	I want to use this template for my feature file
	
	Background:
	Given I landed on Eccomerce Page
	
	@Regression
	Scenario Outline: Positive test of submitting the order
		Given Logged in with username <username> and password <password>
		When I add product <productName> to Cart
		And Checkout <productName> and submit the order.
		Then "Thankyou for the order." message is displayed on ConfirmationPage
		
		Examples:
			| username | password | productName |
			| jo4blue@gmail.com | samplePass123 | ZARA COAT 3 |
			| yorforger4455@gmail.com | samplePass123 |  ADIDAS ORIGINAL |