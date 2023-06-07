package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.CucumberOptions;
import steps.ui.AutomationHooks;

@CucumberOptions(
		features = "./src/test/java/features/ui/",
		glue = "steps.ui",
		monochrome = true,
		dryRun = false,
		tags = "@ui",
		publish = false,
		plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		})

public class TestRunnerUI extends AutomationHooks {
	
	@Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
