package restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Utilities_API {
	private String accessToken="fcfda1b3d05e7ce4c2899f34adb5cb287d277a15897a2fdddb7478e2d2c90508";
	
	public Utilities_API(String accessToken)
	{
		this.accessToken = accessToken;
		RestAssured.baseURI="https://gorest.co.in/public/v2";
		
	}
	
	//GetUser
	public Response getRequest(String endpoint) 
	{
		return RestAssured
				.given()
				.header("Authorization", "Bearer "+accessToken)
				.header("Content-Type","Application/json")
				.header("Accept", "Application/json")
				.when()				
				.get(endpoint);
		
	}
	//Post User
	public Response PostRequest(String endpoint, String payload)
	{
		return RestAssured
				.given()
				.header("Authorization", "Bearer "+accessToken)
				.header("Content-Type","Application/json")
				.header("Accept", "Application/json")
				.body(payload)
				.when()				
				.post(endpoint);
	}
	//Put User
	public Response PutRequest(String endpoint, String payload)
	{
		return RestAssured
				.given()
				.header("Authorization", "Bearer "+accessToken)
				.header("Content-Type","Application/json")
				.header("Accept", "Application/json")
				.body(payload)
				.when()				
				.put(endpoint);
	}
	//Delete User
	public Response DeleteRequest(String endpoint)
	{
		return RestAssured
				.given()
				.header("Authorization", "Bearer "+accessToken)
				.header("Content-Type","Application/json")
				.header("Accept", "Application/json")
				.when()				
				.delete(endpoint);
	}

}
