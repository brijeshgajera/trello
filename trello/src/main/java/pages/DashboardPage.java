package pages;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.PredefinedActions;
import constant.ConfigFilePath;
import util.PropertyFileReader;

public class DashboardPage extends PredefinedActions{
	private PropertyFileReader prop;
	private static DashboardPage dashboardPage;
	
	private DashboardPage() {
		prop = new PropertyFileReader(ConfigFilePath.DASHBOARD_PAGE_PROPERTIES);
	}
	
	public static DashboardPage getInstance() {
		if(dashboardPage == null)
			dashboardPage = new DashboardPage();
		PageFactory.initElements(driver, dashboardPage);
		return dashboardPage;
	}
	
	public void validateLogin() {
		Assert.assertTrue(isElementDisplayed(prop.getValue("accountMenuButton"), true));
	}
	
	public void clickOnAccountButton() {
		clickOnElement(prop.getValue("accountMenuButton"), true);
	}
	
	public void clickOnCreateBoardButton() {
		clickOnElement(prop.getValue("createButtonMenu"), true);
		clickOnElement(prop.getValue("createBoardButton"), true);
	}
	
	public void enterBoardTitle(String title) {
		enterText(prop.getValue("boardTitleInput"), title, true);
	}
	
	public BoardPage clickOnCreateBoardSubmitButton() {
		clickOnElement(prop.getValue("createBoardSubmitButton"), true);
		return BoardPage.getInstance();
	}
	
	public BoardPage goToBoard(String boardName) throws InterruptedException {
		if(!isElementDisplayed(prop.getValue("workspaceUL"), true)){
			clickOnElement(prop.getValue("trelloWorkspaceLink"), true);
		}
		clickOnElement(prop.getValue("goToBoardLink"), true);
		Thread.sleep(1000);
		List<WebElement> elements = getElements(prop.getValue("getBoardsName"), false);
		for (WebElement element : elements) {
			if(getElementText(element).equalsIgnoreCase(boardName)) {
				clickOnElement(element);
				break;
			}
		}
		return BoardPage.getInstance();
	}

}
