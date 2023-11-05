package restAssured;

import static org.hamcrest.Matchers.equalTo;
import java.util.Random;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class API 
{
	String token = "Bearer fcfda1b3d05e7ce4c2899f34adb5cb287d277a15897a2fdddb7478e2d2c90508";
@Test
public void getUser()
{
	RestAssured.baseURI="https://gorest.co.in/public/v2/users";
	 Response res = RestAssured.given().header("Accept","application/json")
	.header("Content-Type","application/json").header("Authorization",token)
	.when().get();
	
	 res.then().statusCode(200);
	 res.then().log().all();
//	 res.then().assertThat().body("id", equalTo(5674294));
}

@Test
public void createUser() {
	String email = getSaltString()+"@gmail.com";
	String requestBody ="{\"name\":\"Gurdev Verma\", \"gender\":\"female\", \"email\":\""+email+"\", \"status\":\"active\"}";
	RestAssured.baseURI="https://gorest.co.in/public/v2/users";
	
	Response res = RestAssured.given().header("Accept","application/json")
			.header("Content-Type","application/json").header("Authorization",token)
			.body(requestBody).when().post();
	res.then().log().all();
	res.then().statusCode(201);
}
@Test
public void updateUser() {

	String email = getSaltString()+"@gmail.com";
	String requestBody = "{\"name\":\"Tara  Khana\",\"email\":\"" + email + "\", \"status\":\"active\"}";
	RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
	Response response = RestAssured
			.given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.body(requestBody).when().patch("/5674293");
	response.then().log().all();
	response.then().statusCode(200);
}
@Test
public void deleteUsers() 
	{
		RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		Response response = RestAssured
		.given()
		.header("Accept", "application/json")
		.header("Content-Type", "application/json")
		.header("Authorization", token)
		.when().delete("/5674305");


		response.then().log().all();
		response.then().statusCode(204);
	}

protected String getSaltString() 

	{
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 10) 
    { 
        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
        salt.append(SALTCHARS.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr;

	}
}
