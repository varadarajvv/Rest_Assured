package com.VRJD.PetStore.Utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ResourceBundle;

import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	private static final String accessUri = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
	private static final String clientId = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
	private static final String clientSecret = "erZOWM9g3UtwNRj340YYaK_W";
	private static final String clientGrantType = "client_credentials";
	private static final String clientScope = "trust";

	public static RequestSpecification requestSpecification;
	public static Response response;
	public static ResourceBundle resourceBundle;
	
	public static String base_URL = " ";
	public static String base_AccessTokenURL = " ";
	public static String okta_Token = " ";

	public static String post_abcd_URL = base_URL + "/api/users";
	public static String post_BearerToken_URL = base_URL + "/oauth2/v1/token";

	public static String generateAccessToken() throws InterruptedException {
		String accessToken = "";
		RequestSpecification requestSpecification = null;
		requestSpecification = RestAssured.given().auth().preemptive().basic(clientId, clientSecret).baseUri(accessUri)
				.header("accept","application/json").header("cache-control", "no-cache")
				.header("content-type", "application/x-www-form-urlencoded").formParam("grant-type", clientGrantType)
				.formParam("scope", clientScope);
		System.out.println(requestSpecification.toString());
		Thread.sleep(3000);
		Response response = requestSpecification.post();
		Thread.sleep(3000);
		System.out.println("Response Recieved: " + response.asString());
		JsonPath jsonPath = response.jsonPath();
		accessToken = jsonPath.getString("access_token");
		System.out.println("ACCESS TOKEN RECIEVED: " + accessToken);
		return accessToken;
	}

	public RequestSpecification requestSpecification() throws IOException {
		if (requestSpecification == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			requestSpecification = new RequestSpecBuilder()
					.setBaseUri(getBaseURI("BaseURI"))
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			return requestSpecification;
		}
		return requestSpecification;
	}
	
	public RequestSpecification requestSpecification(String baseURI) throws IOException {
		if (requestSpecification == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			requestSpecification = new RequestSpecBuilder()
					.setBaseUri(getBaseURI(baseURI))
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			return requestSpecification;
		}
		return requestSpecification;
	}

	public static String getBaseURI(String key) {
		resourceBundle = ResourceBundle.getBundle("config");
		return resourceBundle.getString(key);
	}

	public String getJsonPath(Response response, String key) {
		JsonPath jsonPath = new JsonPath(response.asString());
		return jsonPath.get(key).toString();
	}

	public static String getRequestBody(String filePath) throws FileNotFoundException {
		FileReader fileReader = new FileReader(filePath);
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		JSONObject data = new JSONObject(jsonTokener);
		return data.toString();
	}
	
	public static String randomNumbers() {

		final long min = 700000000000L;
		final long max = 800000000000L;

		System.out.println("Random value of type int between " + min + " to " + max + ":");
		long b = (long) (Math.random() * (max - min + 1) + min);

		String AWB = b + " ";
		System.out.println("Converted type: " + AWB.getClass().getName());
		System.out.println(AWB);
		return AWB;
	}

	public static Response extractResponse(String requestType, RequestSpecification requestSpecification, String URL) {
		if (requestType.equalsIgnoreCase("POST")) {
			requestSpecification.when();
			Response response = requestSpecification.post(base_URL + URL);
			System.out.println("Output:" + response.prettyPrint());
			return response;
		} else if (requestType.equalsIgnoreCase("GET")) {
			requestSpecification.when();
			Response response = requestSpecification.post(base_URL + URL);
			System.out.println("Output:" + response.prettyPrint());
			return response;
		}
		return null;
	}

	public static int verifyStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public static String verifyResponseBody(Response response, String key) {
		String value;
		JsonPath jsonPath = response.jsonPath();
		value = jsonPath.getString(key);
		return value;
	}


}
