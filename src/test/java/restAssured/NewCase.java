package restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class NewCase {
	//https://reqres.in/api/users/2
	@Test
	public void getUsers() {
		RestAssured.baseURI="https://reqres.in/api";
		Response resp = given()
				.param("page", 2)
				.when().get("/users")
				.then()
				.extract()
				.response();
		int id= resp.jsonPath().getInt("data[0].id");
		int id1= resp.jsonPath().getInt("total");
		Response response =
				given()
				.pathParam("userId", id)
				.when()
				.get("users/{userId}");
		
				response.then().statusCode(200);
				
				response.then().assertThat()
				.body("data.id", equalTo(7))
				.body("data.email", equalTo("michael.lawson@reqres.in"))
				.body("data.first_name", equalTo("Michael"))
				.body("data.last_name", equalTo("Lawson"));
				
				response.then().log().all();
		
	}
}
