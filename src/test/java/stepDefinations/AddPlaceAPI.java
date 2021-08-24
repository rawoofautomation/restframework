package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;


import datagenerator.APIResources;
import datagenerator.TestDataBuild;
import datagenerator.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serialdeserial.AddPlacePlayload;
import serialdeserial.Location;

public class AddPlaceAPI extends Utils{

	RequestSpecification body;
	ResponseSpecification respspec;
	Response post_response;
	TestDataBuild a = new TestDataBuild();
	JsonPath jp ;
	static String res_place ;
	
	
	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws Exception
	{
	 
	// request spec builders
	
	//  separating body part as it is common
	 body = given().spec(requestSpecification()).body(a.appPlacePayload(name,language,address));
	

	// separating response spec builders which validate the common values in response
	
	 
	
}

@When("user calls {string} with {string} http request")
public void user_calls_with_post_http_request(String httpReqMethod,String httpmethod) {

//	APIResources resource =APIResources.valueOf(httpReqMethod);
	
	respspec = 
			new ResponseSpecBuilder().expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
	
	
	 post_response =  
			
			httpMethod(body,httpReqMethod,httpmethod)
			.then().spec(respspec).assertThat()
			//.body("scope",equalTo("APP"))     // not working for delete place API
			.header("server", "Apache/2.4.18 (Ubuntu)")
			
			.extract().response();
	 
	 System.out.println(post_response.asString());
			
}

@Then("the API call will get success code {int}")
public void the_api_call_will_get_success_code(Integer statusCode) 
{

	assertTrue(post_response.getStatusCode()==statusCode);
	
}

@Then("chech {string} in response body is {string}")
public void chech_in_response_body_is(String key, String value) {

	String status = post_response.asString();
	 jp = new JsonPath(status);
	
	assertTrue(jp.getString(key).equalsIgnoreCase(value));
	
}


@Then("verify {string} from {string} is matching with {string} http request")
public void verify_from_is_matching(String name, String httpmethod, String httpreqmethod) throws Exception  {

	APIResources resource =APIResources.valueOf(httpmethod);
	
	 
	 res_place = getValueFromJSON(post_response, "place_id");
	
	
	Response get_res = given().spec(requestSpecification()).queryParam("place_id", res_place).
	 when().get(resource.getResource()).then().spec(respspec).extract().response();
	System.out.println(get_res);


	
	String res_name = getValueFromJSON(get_res,"name");
	
	assertTrue(res_name.equalsIgnoreCase(name+" enterprises private limited"));
}
	

@Given("delete palce payload")
public void delete_palce_payload() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	
	body =given().spec(requestSpecification()).body(a.getDeletePlacePayLoad(res_place ));
	
	
	
}
	
}
