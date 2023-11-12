package restAssured;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class APITesting {
	private String accessToken="fcfda1b3d05e7ce4c2899f34adb5cb287d277a15897a2fdddb7478e2d2c90508";
	int id =0;
	Utilities_API apiUtility = new Utilities_API(accessToken);
	String token = "Bearer fcfda1b3d05e7ce4c2899f34adb5cb287d277a15897a2fdddb7478e2d2c90508";
	protected String getSaltString() 
	{
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) 
        { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}

	@Test(priority =1)
	
	public void getRequestTest()
	{
		Response getResponse = apiUtility.getRequest("/users");
		getResponse.then().statusCode(200);
		getResponse.then().log().all();
	}
	
	@Test(priority =2)
	public void postRequestTest()
	{
		String email =getSaltString()+"@gmail.com";
		String payload = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\""+email+"\", \"status\":\"active\"}";
		Response postResponse = apiUtility.PostRequest("/users", payload);
		postResponse.then().statusCode(201);
		postResponse.then().log().all();
		id = postResponse.jsonPath().getInt("id");
	}
	@Test(priority =3)
	public void putRequestTest()
	{
		String email =getSaltString()+"@gmail.com";
		String payload = "{\"name\":\"Rabindra Amatya\", \"gender\":\"male\", \"email\":\""+email+"\", \"status\":\"active\"}";	
		Response putResponse = apiUtility.PutRequest("/users/" +id, payload);
		putResponse.then().statusCode(200);
		putResponse.then().log().all();
	}
	@Test(priority =4)
	public void deleteRequestTest()
	{
		Response deleteResponse = apiUtility.DeleteRequest("/users/" + id);
		deleteResponse.then().statusCode(204);
	}
}
