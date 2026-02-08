package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//Note: cucumber cannot run on its own. It can only provide implementations and framework features.
//It needs a runner like TestNGTestRunner or JUnitRunner.

@CucumberOptions(features = "src/test/java/cucumber", //locate where cucumber files are located
glue = "Sample.FrameworkDesign.cucumberStepDefinitions", //locate where the step definitions are
monochrome = true, //make cucumber readable
tags = "@Regression", //Just like groups in TestNG, you can select which tests in a specific tag to run.
plugin = {"html:target/cucumber.html"}) //enable plugin for html
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
}
