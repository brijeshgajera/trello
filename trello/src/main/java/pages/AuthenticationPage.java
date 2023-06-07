package pages;

import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;
import constant.ConfigFilePath;
import util.PropertyFileReader;

public class AuthenticationPage extends PredefinedActions {
	private PropertyFileReader prop;
	private static AuthenticationPage authenticationPage;

	private AuthenticationPage() {
		prop = new PropertyFileReader(ConfigFilePath.AUTHENTICATION_PAGE_PROPERTIES);
	}

	public static AuthenticationPage getInstance() {
		if (authenticationPage == null)
			authenticationPage = new AuthenticationPage();
		PageFactory.initElements(driver, authenticationPage);
		return authenticationPage;
	}

	public void enterEmailAddress(String emailId) {
		enterText(prop.getValue("email"), emailId, true);
	}

	public void clickOnContinueButton() {
		clickOnElement(prop.getValue("continueButton"), true);
	}

	public void enterPassword(String password) {
		enterText(prop.getValue("password"), password, true);
	}

	public DashboardPage clickOnLogInButton() {
		clickOnElement(prop.getValue("logInButton"), true);
		return DashboardPage.getInstance();
	}
}
