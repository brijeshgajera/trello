package pages;

import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;
import constant.ConfigFilePath;
import util.PropertyFileReader;

public class HomePage extends PredefinedActions{
	private PropertyFileReader prop;
	private static HomePage homePage;

	private HomePage() {
		prop = new PropertyFileReader(ConfigFilePath.HOME_PAGE_PROPERTIES);
	}
	
	public static HomePage getInstance() {
		if(homePage == null)
			homePage = new HomePage();
		PageFactory.initElements(driver, homePage);
		return homePage;
	}
	
	public AuthenticationPage clickOnLogInLink() {
		clickOnElement(prop.getValue("logInLink"),true);
		return AuthenticationPage.getInstance();
	}
}
