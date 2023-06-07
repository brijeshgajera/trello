package steps.ui;

import java.io.IOException;

import base.PredefinedActions;
import constant.ConfigFilePath;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import util.PropertyFileReader;


public class AutomationHooks extends AbstractTestNGCucumberTests{
    @Before
    public void beforeScenario(Scenario scenario) throws IOException {
        PropertyFileReader propertyFileReader = new PropertyFileReader(ConfigFilePath.CONFIGFILEPATH);
        String url = propertyFileReader.getValue("url");
        String browser = propertyFileReader.getValue("browser");
        PredefinedActions.start(browser,url);
    }

    @After
    public void afterScenario(Scenario scenario){
        
        System.out.println("After Scenario");
        PredefinedActions.quit();
    }
    
    @AfterStep
	public void addScreenshot(Scenario scenario){

    	if(scenario.isFailed()){
            scenario.attach(PredefinedActions.captureScreenshot(scenario.getName()),"image/png","failed_image");
    	}	
	}
}