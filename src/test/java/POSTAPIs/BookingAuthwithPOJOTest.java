package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.Credentials;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class BookingAuthwithPOJOTest {
	
	
	
	
	@Test
	
	public void GetTokenIDfor_BasicAuth() {
		Credentials cred=new Credentials("admin", "password123");
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
	String	token =given().log().all()
			.contentType(ContentType.JSON)
		.body(cred)
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.extract().response().jsonPath().getString("token");
	
	Assert.assertNotNull(token);
	}
	

}
