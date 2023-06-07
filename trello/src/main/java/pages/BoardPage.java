package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.PredefinedActions;
import constant.ConfigFilePath;
import util.PropertyFileReader;

public class BoardPage extends PredefinedActions {
	private PropertyFileReader prop;
	private static BoardPage boardPage;

	private BoardPage() {
		prop = new PropertyFileReader(ConfigFilePath.BOARD_PAGE_PROPERTIES);
	}

	public static BoardPage getInstance() {
		if (boardPage == null)
			boardPage = new BoardPage();
		PageFactory.initElements(driver, boardPage);
		return boardPage;
	}

	public void validateBoardName(String expectedBoardName) {
		String actualBoardName = getElementText(prop.getValue("boardNameText"), true);
		Assert.assertEquals(actualBoardName, expectedBoardName);
	}

	public void clickOnAddListButton() {
		clickOnElement(prop.getValue("addListToBoardLink"), true);
	}

	public void enterListTitle(String title) {
		enterText(prop.getValue("enterListTitle"), title, true);
	}

	public void clickOnAddListSubmitButton() {
		clickOnElement(prop.getValue("addListButton"), true);
	}

	public List<WebElement> getAllLists() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getElements(prop.getValue("allListElements"), false);
	}

	public void archiveAllListWhichHasName(String listName) throws InterruptedException {
		List<WebElement> elements = getListsWithName(listName);
		for (WebElement element : elements) {
			clickOnElement(getElement(element, prop.getValue("listExtraListMenu")));
			clickOnElement(prop.getValue("archiveListLink"), true);
			Thread.sleep(500);
		}
	}

	public List<WebElement> getListsWithName(String listName) {
		List<WebElement> elements = getAllLists();
		List<WebElement> listWithName = new ArrayList<>();
		for (WebElement element : elements) {
			String listHeader = getElementText(getElement(element, prop.getValue("allListTextareaElements")));
			if (listHeader.equals(listName))
				listWithName.add(element);
		}
		return listWithName;
	}
	
	public WebElement getListWithName(String listName) {
		List<WebElement> elements = getListsWithName(listName);
		WebElement elementToReturn = null;
		for (WebElement element : elements) {
			String listHeader = getElementText(getElement(element, prop.getValue("allListTextareaElements")));
			if (listHeader.equals(listName)) {
				elementToReturn = element;
				break;
			}
		}
		return elementToReturn;
	}

	public Integer countAllListWhichHasName(String listName) {
		List<WebElement> elements = getListsWithName(listName);
		return elements.size();
	}

	public void addCardToList(String cardName, String listName) {
		WebElement element = getListWithName(listName);
		clickOnElement(getElement(element, prop.getValue("allListAddCardElements")));
		enterText(getElement(element, prop.getValue("allCardsTextareaElements")), cardName);
		clickOnElement(getElement(element, prop.getValue("addCardSubmitButton")));
	}
	
	public List<String> getListCardTitles(List<WebElement> cards) {
		List<String> cardNames = new ArrayList<>();
		for (WebElement card : cards) {
			cardNames.add(getElementText(getElement(card, prop.getValue("listCardTitles"))));
		}
		return cardNames;
	}
	
	public List<WebElement> getAllCardsFromList(String listName){
		WebElement element = getListWithName(listName);
		List<WebElement> cards = getElements(element, prop.getValue("listCards"), false);
		return cards;
	}
	
	public void checkCardNameExistsInList(String cardName, String listName) {
		List<String> cardNames = getListCardTitles(getAllCardsFromList(listName));
		Assert.assertEquals(cardNames.contains(cardName), true);
	}
	
	public void editCardWhichHasName(String cardName, String listName) {
		List<WebElement> cards = getAllCardsFromList(listName);
		for (WebElement card : cards) {
			String cardTitle = getElementText(getElement(card, prop.getValue("listCardTitles")));
			if(cardTitle.equals(cardName)){
				clickOnElement(getElement(card, prop.getValue("editCardButton")));
				break;
			}
		}
	}
	
	public void updateCardNameTo(String currentCardName, String updateCardName, String listName) {
		editCardWhichHasName(currentCardName, listName);
		enterText(prop.getValue("cardEditorTextarea"), updateCardName, true);
		clickOnElement(prop.getValue("cardEditorSaveButton"), true);
	}
}
