package restAssured;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class APITests 
	{
	String token = "Bearer cd2ac6be51d3e141feb492b0cc0ddd8d3eb8d0bc7da80d1a1ef57ecdd74bf517";

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
	@Test
	//By Using HashMap
	public void createUserHM()
	{
		String email = getSaltString() + "@gmail.com";
		HashMap<String, String> payload = new HashMap<>();
		payload.put("name", "Manjusha Mehrotra");
		payload.put("gender", "female");
		payload.put("email", email);
		payload.put("status", "inactive");
		
	}
	@Test
	//By Using ORG
	public void createUserORG()
	{
		String email = getSaltString() + "@gmail.com";
		JSONObject jpayload = new JSONObject();
		jpayload.put("name", "Manjusha Mehrotra");
		jpayload.put("gender", "female");
		jpayload.put("email", email);
		jpayload.put("status", "inactive");
		//Convert JSON object to a string
		String payload = jpayload.toString();
		
	}
	
	@Test
	//By Using POJO.json
	public void createUserPojo()
	{
		String email = getSaltString() + "@gmail.com";
		
		Person payload = new Person("Manjusha Mehrotra",email,"female","inactive");
		
	}
	@Test
	//By Using External JSON
	public void createUserExternalJSON() throws FileNotFoundException
	{
		String email = getSaltString() + "@gmail.com";
		//Create a fileReader to read the JSON file
		String path="C:\\Users\\Rabindra\\eclipse-workspace\\restAssured\\src\\test\\java\\restAssured\\Obj.json";
		FileReader fileReader = new FileReader(path);
		JSONTokener tokener = new JSONTokener(fileReader);
		JSONObject jsonpayload = new JSONObject(tokener);
		String payload = jsonpayload.toString();
		RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		Response response = RestAssured.given().header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.body(payload)
				.when().post();

		response.then().log().all();
		response.then().statusCode(201);
		
		
		
	}
	
}
