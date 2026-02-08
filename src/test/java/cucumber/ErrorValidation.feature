@tag
Feature: Error Validation
	I want to use this template for my feature file
	
	
	@ErrorValidation
	Scenario Outline: Title of scenario outline
		Given I landed on Eccomerce Page
		When Logged in with username <username> and password <password>
		Then "Incorrect email or password." message is displayed
		
		Examples:
			| username | password | 
			| jo4blue@gmail.com | asdadada | 
			| yorforger4455@gmail.com | blavlabba |  