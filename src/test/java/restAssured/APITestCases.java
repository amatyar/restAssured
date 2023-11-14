package restAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;
public class APITestCases 
{
	@Test
	public void TestCaseOne() 
	{
		RestAssured.baseURI ="https://reqres.in/api";
		
		given()
		.when()
		.get("/users?page=2")
		.then()
		.statusCode(200)
		.header("Content-Type","application/json; charset=utf-8")
		.body("data[0].email",equalTo("michael.lawson@reqres.in"));
	}
	@Test
	public void TestCaseTwo() 
	{
		RestAssured.baseURI ="https://reqres.in/api";
		
		Response res =given()
		.when()
		.get("/users?page=2");
		// Testing 
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8" );
		//Testing
		String email = res.jsonPath().get("data[0].email").toString();
		Assert.assertEquals(email, "michael.lawson@reqres.in");	
		
	}
	@Test
	public void TestCaseThree()
	{
		RestAssured.baseURI = "https://reqres.in/api";
		boolean found = false;
		Response res = RestAssured.given()
				.when()
				.get("/users?page=2");
		
		JSONObject jsonResponse = new JSONObject(res.getBody().asString());
		
		JSONArray data = jsonResponse.getJSONArray("data");
		
		for(int i =0; i<data.length();i++) {
			
			JSONObject user = data.getJSONObject(i);
			
			String firstName = user.getString("first_name");
			
			System.out.println(firstName);
			
			if(firstName.equals("Tobias")) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found);
	}
	@Test
	public void testCase4()
	{
		RestAssured.baseURI ="https://reqres.in/api";
		boolean found =false;
		Response rest=given().when().get("users?page=2");
		JSONObject obj = new JSONObject(rest.asString());
		for(int i=0; i<obj.getJSONArray("data").length();i++) 
		{
			String FirstName= obj.getJSONArray("data").getJSONObject(i).getString("first_name").toString();
			System.out.println(FirstName);
			if(FirstName.equals("Tobias")) 
			{
				found=true;
				break;
			}
		}
		Assert.assertTrue(found);
	}
}
