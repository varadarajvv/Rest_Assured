package com.VRJD.PetStore.StepDefinitions;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.junit.Assert;

import com.VRJD.PetStore.Utilities.APIResources;
import com.VRJD.PetStore.Utilities.TestDataBuild;
import com.VRJD.PetStore.Utilities.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class E2E_Steps extends Utils{
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	static String filePath = System.getProperty("user.dir") + "";
	
	@Given("User Payload with {string}")
	public void user_payload_with(String name) throws IOException {
		requestSpecification = given().spec(requestSpecification()).body(data.createUserPayload(name));
	}

	@When("User calls {string} with {string} HTTP Request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		if (method.equalsIgnoreCase("POST"))
			response = requestSpecification.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = requestSpecification.when().get(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("PUT"))
			response = requestSpecification.when().put(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("DELETE"))
			response = requestSpecification.when().delete(resourceAPI.getResource());
	}
	
	@Then("Verify Status Code {string}")
	public void verify_status_code(String statusCode) {
 		Assert.assertEquals("Response Status Code Result: ", Integer.parseInt(statusCode), response.getStatusCode());
	}

	@Then("Verify {string} in Response Body is {string}")
	public void verify_in_response_body_is(String jsonKey, String jsonValue) {
		String responseString = response.asString();
		JsonPath jsonPath = new JsonPath(responseString);
		String key = jsonPath.getString(jsonKey) ;
		Assert.assertEquals("Response Body Result: ", jsonValue, key);
	}

	@Given("Fetch User details by {string}")
	public void fetch_user_details_by(String name) throws IOException {
		requestSpecification = given().spec(requestSpecification().pathParams("username", name));
	}
	
	@Given("Update User details by {string}")
	public void update_user_details_by(String name) throws IOException {
		requestSpecification = given().spec(requestSpecification().pathParams("username", name).body(data.updateUserPayload(name)));
	}

	@Given("Delete User by {string}")
	public void delete_user_by(String name) throws IOException {
		requestSpecification = given().spec(requestSpecification().pathParams("username", name));
	}


}
