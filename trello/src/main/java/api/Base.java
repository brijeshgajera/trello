package api;

import java.io.File;
import java.util.Map;

import constant.ConfigFilePath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.PropertyFileReader;

public class Base {
	protected PropertyFileReader prop;
	private static final String BASE_URL = "https://api.trello.com";
	
	protected Base() {
		prop = new PropertyFileReader(ConfigFilePath.CREDENTIALS);
	}

	protected RequestSpecification baseRequest(Map<String, Object> queryParam) {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.queryParam("key", prop.getValue("key"));
		request.queryParam("token", prop.getValue("token"));
		if (!queryParam.isEmpty()) {
			for (Map.Entry<String, Object> entry : queryParam.entrySet())
				request.queryParam(entry.getKey(), entry.getValue());
		}
		return request;
	}
	
	protected RequestSpecification baseRequest() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.queryParam("key", prop.getValue("key"));
		request.queryParam("token", prop.getValue("token"));
		return request;
	}

	protected Response getRequest(String requestURL, Map<String, Object> queryParam) {

		Response response = baseRequest(queryParam).get(requestURL);
		return response;
	}
	
	protected Response getRequest(String requestURL) {

		Response response = baseRequest().get(requestURL);
		return response;
	}

	protected Response postRequest(String requestURL, Map<String, Object> queryParam) {
		Response response = baseRequest(queryParam).post(requestURL);
		return response;
	}
	
	protected Response postRequest(String requestURL) {
		Response response = baseRequest().post(requestURL);
		return response;
	}

	protected Response putRequest(String requestURL, Map<String, Object> queryParam) {
		Response response = baseRequest(queryParam).put(requestURL);
		return response;
	}
	
	protected Response putRequest(String requestURL) {
		Response response = baseRequest().put(requestURL);
		return response;
	}

	protected Response deleteRequest(String requestURL) {
		Response response = baseRequest().delete(requestURL);
		return response;
	}
	
	protected File getJsonFile(String fileName) {
		return new File("./src/main/resources/json/"+fileName);
	}
}
