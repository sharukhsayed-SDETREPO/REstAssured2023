package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class OAuth2Test {

	
	
	
	
	static String accessToken;
	
	
	@BeforeTest
	public void getAccesstokeforOauth2() {
		RestAssured.baseURI="https://test.api.amadeus.com";
		  accessToken= given().log().all()
		  .header("Content-Type","application/x-www-form-urlencoded")
		  //same as the above header  .contentType(ContentType.URLENC)
		  .formParam("grant_type", "client_credentials")
		  .formParam("client_id", "bkerRmUAuxtj44og3mgw1T89VeZZzit7")
		  .formParam("client_secret", "7qd2vbD32s6yaXeY")
		  .when().log().all()
		  .post("/v1/security/oauth2/token")
		  .then().log().all()
		  .assertThat()
		  .statusCode(200)
		  .and()
		  .extract().response().jsonPath().getString("access_token");
		  System.out.println("Your access toke for OAuth2 is generated ===>" +accessToken);
		
		
	}
	
	
	
	
	
	@Test
	public void GetFlightDetails() {
		given().log().all()
		.header("Authorization","Bearer "+accessToken)
		.queryParam("origin", "PAR")
		.queryParam("maxPrice", 200)
		.when().log().all()
		.get("v1/shopping/flight-destinations")
		.then()
		.assertThat()
		.statusCode(200);
	}
	
}
