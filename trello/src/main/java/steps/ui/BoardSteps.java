package steps.ui;

import java.util.Base64;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AuthenticationPage;
import pages.BoardPage;
import pages.DashboardPage;
import pages.HomePage;

public class BoardSteps {
	private HomePage homePage = HomePage.getInstance();
	private AuthenticationPage authenticationPage = null;
	private DashboardPage dashboardPage = null;
	private BoardPage boardPage = null;
	private String boardName = null;

	@Given("user navigates to trello home page")
	public void user_navigates_to_trello_home_page() {

	}

	@When("user clicks on login link")
	public void user_clicks_on_login_link() {
		authenticationPage = homePage.clickOnLogInLink();
	}

	@When("user enters email ID as {string}")
	public void user_enters_email_id_as(String email) {
		authenticationPage.enterEmailAddress(new String(Base64.getDecoder().decode(email)));
	}

	@When("user clicks on continue button")
	public void user_clicks_on_continue_button() {
		authenticationPage.clickOnContinueButton();
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		dashboardPage = authenticationPage.clickOnLogInButton();
	}

	@When("user enters password as {string}")
	public void user_enters_password_as(String password) {
		authenticationPage.enterPassword(new String(Base64.getDecoder().decode(password)));
	}

	@Then("user should be successfully logged in")
	public void user_should_be_successfully_logged_in() {
		dashboardPage.validateLogin();
	}

	@Given("user is logged in to trello portal")
	public void user_is_logged_in_to_trello_portal() {
		dashboardPage.validateLogin();
	}

	@When("user clicks on create board button")
	public void user_clicks_on_create_board_button() {
		dashboardPage.clickOnCreateBoardButton();
	}

	@When("user enters board title as {string}")
	public void user_enters_board_title_as(String boardName) {
		this.boardName = boardName;
		dashboardPage.enterBoardTitle(boardName);
	}

	@When("user clicks on create button")
	public void user_clicks_on_create_button() {
		boardPage = dashboardPage.clickOnCreateBoardSubmitButton();
	}

	@Then("board should be created")
	public void board_should_be_created() {
		boardPage.validateBoardName(boardName);
	}

	@Given("user is logged in to trello portal and user is on board named as {string}")
	public void user_is_logged_in_to_trello_portal_and_user_is_on_board_named_as(String boardName) {
		boardPage.validateBoardName(boardName);
	}

	@When("user clicks on add list")
	public void user_clicks_on_add_list() {
		boardPage.clickOnAddListButton();
	}

	@When("user enters list name as {string}")
	public void user_enters_list_name_as(String listTitle) {
		boardPage.enterListTitle(listTitle);
	}

	@When("user clicks on add list submit button")
	public void user_clicks_on_add_list_submit_button() {
		boardPage.clickOnAddListSubmitButton();
	}

	@Then("list with name {string} should be created")
	public void list_with_name_should_be_created(String expectedListTitle) {

	}

	@Given("user goes to board name {string}")
	public void user_goes_to_board_name(String boardName) throws InterruptedException {
		boardPage = dashboardPage.goToBoard(boardName);
	}
	
	@Then("user delete all list which has name {string}")
	public void user_delete_all_list_which_has_name(String listName) throws InterruptedException {
	    boardPage.archiveAllListWhichHasName(listName);
	}
	
	@Then("list with name {string} should not be there on board {string}")
	public void list_with_name_should_not_be_there_on_board(String listName, String boardName) {
	    int count = boardPage.countAllListWhichHasName(listName);
	    Assert.assertEquals(count, 0);
	}
	
	@When("user adds card named {string} to list {string}")
	public void user_adds_card_named_to_list(String cardName, String listName) {
	    boardPage.addCardToList(cardName, listName);
	}
	
	@Then("card {string} should be added to list {string}")
	public void card_should_be_added_to_list(String cardName, String listName) {
	    boardPage.checkCardNameExistsInList(cardName, listName);
	}
	
	@When("user udpates card name of {string} card to {string} in {string} list")
	public void user_udpates_card_name_of_card_to_in_list(String currentCardName, String updateCardName, String listName) {
	    boardPage.updateCardNameTo(currentCardName, updateCardName, listName);
	}
	
	@Then("card name should be updated to {string} in list {string}")
	public void card_name_should_be_updated_to_in_list(String cardName, String listName) {
		boardPage.checkCardNameExistsInList(cardName, listName);
	}
}
