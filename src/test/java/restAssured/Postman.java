package restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Postman 
{
	//https://reqres.in/api/users/2
@Test
public void getUsers() {
	RestAssured.baseURI="https://reqres.in/api";
	Response resp =
			given()
			.when()
			.get("users/2");
	resp.then().statusCode(200);
	
	//Next Assertion
	resp.then().assertThat()
	.body("data.id", equalTo(2))
	.body("data.email", equalTo("janet.weaver@reqres.in"))
	.body("data.first_name", equalTo("Janet"))
	.body("data.last_name", equalTo("Weaver"))
	.body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
	resp.then().log().all();
}
@Test
public void createUsers() {
	 // Set the base URI for the API
    RestAssured.baseURI = "https://reqres.in/api";

    // Define the request payload (user data) in JSON format
    String requestBody = "{\"name\": \"Rohan Amatya\", \"job\": \"Software Engineer\"}";

    // Make a POST request to create a new user
    Response response = given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .when()
        .post("/users");

    // Validate the response status code
    response.then().statusCode(201);

    // Validate the response body using Hamcrest matchers
    response.then().assertThat()
        .body("name", equalTo("Rohan Amatya"))
        .body("job", equalTo("Software Engineer"));
}	
@Test
public void updateUsers() {
	// Set the base URI for the API
    RestAssured.baseURI = "https://reqres.in/api";

    // Define the request payload (user data) in JSON format
    String requestBody = "{\"name\": \"Amatya Rabindra\", \"job\": \"Software Engineer Jr.\"}";

    // Make a PUT to update the user
    Response response = given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .when()
        .put("users/2");

    // Validate the response status code
    response.then().statusCode(200);

    // Validate the response body using Hamcrest matchers
    response.then().assertThat()
        .body("name", equalTo("Amatya Rabindra"))
        .body("job", equalTo("Software Engineer Jr."));
}
@Test
public void deleteUsers() {
	RestAssured.baseURI = "https://reqres.in/api";
	 Response response = given()
	            .when()
	            .delete("users/2");
	
	 response.then().statusCode(204);
}
	
}
