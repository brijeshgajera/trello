package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "./src/test/java/features/api/",
		glue = "steps.api",
		monochrome = true,
		dryRun = false,
		tags = "@api",
		publish = false,
		plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		})
public class TestRunnerAPI extends AbstractTestNGCucumberTests {

}
