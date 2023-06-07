package steps.api;

import api.GenericAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BoardAPISteps {

	GenericAPI genericAPI = GenericAPI.getInstance();

	@Given("user is authorized to access trello portal API")
	public void user_is_authorized_to_access_trello_portal_api() {
		genericAPI.isUserAuthorized();
	}

	@When("user sends api request for getting all the boards")
	public void user_sends_api_request_for_getting_all_the_boards() {
		genericAPI.getAllBoards();
	}

	@Then("user should be able to get all boards")
	public void user_should_be_able_to_get_all_boards() {
		genericAPI.validateBoards();
	}

	@When("user sends api request for creating list named as {string} to board {string}")
	public void user_sends_api_request_for_creating_list_named_as_to_board(String listName, String boardName) {
		genericAPI.createListInBoard(listName, boardName);
	}

	@Then("list {string} should be created successfully")
	public void list_should_be_created_successfully(String listName) {
		genericAPI.validateCreateListResponse(listName);
	}

	@When("user sends api request to update list name {string} with {string} on board {string}")
	public void user_sends_api_request_to_update_list_name_with_on_board(String oldListName, String newListName,
			String boardName) {
		genericAPI.updateListName(oldListName, newListName, boardName);
	}

	@Then("name of list {string} should be changed to {string}")
	public void name_of_list_should_be_changed_to(String oldListName, String newListName) {
		genericAPI.validateSuccessResponse();
	}

	@When("user sends api request to archive {string} list on board {string}")
	public void user_sends_api_request_to_archive_list_on_board(String listName, String boardName) {
		genericAPI.archiveList(listName, boardName);
	}

	@Then("list should be archived")
	public void list_should_be_archived() {
		genericAPI.validateSuccessResponse();
	}
}
