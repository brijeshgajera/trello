package api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GenericAPI extends Base {
	private static GenericAPI genericAPI;
	private Response response = null;

	public static GenericAPI getInstance() {
		if (genericAPI == null)
			genericAPI = new GenericAPI();
		return genericAPI;
	}

	public void isUserAuthorized() {
		response = baseRequest().get("/1/tokens/" + prop.getValue("token"));
		validateSuccessResponse();
	}

	public void getAllBoards() {
		response = getRequest("1/members/me/boards");
	}

	public void validateBoards() {
		response.then().assertThat().statusCode(200);
		// .body(JsonSchemaValidator.matchesJsonSchema(getJsonFile("get-boards-schema.json")));
	}

	public String getBoardId(String boardName) {
		getAllBoards();
		String boardId = null;
		JsonPath jsonPath = response.jsonPath();
		List<String> boardIds = jsonPath.getList("id");
		List<String> boardNames = jsonPath.getList("name");
		List<Boolean> closed = jsonPath.getList("closed");
		for (int i = 0; i < boardNames.size(); i++) {
			if (boardNames.get(i).equals(boardName) && !closed.get(i)) {
				boardId = boardIds.get(i);
				break;
			}
		}
		return boardId;
	}

	public void createListInBoard(String listName, String boardName) {
		String boardId = getBoardId(boardName);
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("idBoard", boardId);
		queryParams.put("name", listName);
		response = postRequest("/1/lists", queryParams);
		validateSuccessResponse();
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get("name"), listName);

	}

	public void validateCreateListResponse(String listName) {
		validateSuccessResponse();
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get("name"), listName);
	}

	public void getListsOnBoard(String boardName) {
		String boardId = getBoardId(boardName);
		response = getRequest("/1/boards/" + boardId + "/lists");
	}

	public String getListIdFromName(String listName, String boardName) {
		getListsOnBoard(boardName);
		String listId = null;
		JsonPath jsonPath = response.jsonPath();
		List<String> listIds = jsonPath.getList("id");
		List<String> listNames = jsonPath.getList("name");
		List<Boolean> closed = jsonPath.getList("closed");
		for (int i = 0; i < listNames.size(); i++) {
			if (listNames.get(i).equals(listName) && !closed.get(i)) {
				listId = listIds.get(i);
				break;
			}
		}
		return listId;
	}

	public void updateListName(String oldListName, String newListName, String boardName) {
		String listId = getListIdFromName(oldListName, boardName);
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("name", newListName);
		response = putRequest("/1/lists/"+listId, queryParams);
	}
	
	public void validateSuccessResponse() {
		response.then().assertThat().statusCode(200);
	}
	
	public void archiveList(String listName, String boardName) {
		String listId = getListIdFromName(listName, boardName);
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("value", true);
		response = putRequest("/1/lists/"+listId+"/closed", queryParams);
	}
}
