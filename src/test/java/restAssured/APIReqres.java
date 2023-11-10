package restAssured;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIReqres {
	//https://reqres.in/api/users?page=2
	  @Test
	  public void testQueryandPathparameters() {
	    
	    Response response = RestAssured
	        .given()
	        .pathParam("mypath", "users")
	        .queryParam("page", 1)
	        .when().get("https://reqres.in/api/{mypath}");
	    response.then().log().all();
	    response.then().statusCode(200);
	    
	    response.then().log().headers();
	    response.then().log().body();
	    
	    response.then().header("Content-Type", Matchers.equalTo("application/json; charset=utf-8"));
	    response.then().header("Server", Matchers.equalTo("cloudflare"));
	    response.then().header("Content-Encoding", Matchers.equalTo("gzip"));
	    response.then().header("Connection", Matchers.equalTo("keep-alive"));
	    response.then().header("Cache-Control", Matchers.equalTo("max-age=14400"));
	    response.then().header("Vary", Matchers.equalTo("Accept-Encoding"));
	    response.then().header("CF-Cache-Status", Matchers.equalTo("REVALIDATED"));
	  }
}
