package POSTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateAUserWithPojoTest {

	
	public static String generateRandomEmail() {
		return "apiautomation"+ System.currentTimeMillis()+ "@gmail.com";
		
		//or 
	//	return "apiautomation"+ UUID.randomUUID()+ "@gmail.com";
	}
	
	
	//a test to create a user using post call and verify it using get call
	
		@Test
		public void addaUser() {
			User usr =new User("ThisisMYAPI", generateRandomEmail(), "male", "active");
			//add a user code
			RestAssured.baseURI="https://gorest.co.in/";
			int id=given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer db783db25e085631df665c1276680cc7ac8af0daec919cd2dac69cc8c2042406")
			.body(usr).
			when().log().all()
			.post("public/v2/users").
			then().log().all()
		     .assertThat()
		     .statusCode(201)
		     .and()
		     .body("name", equalTo(usr.getName()))
		     .body("email", equalTo(usr.getEmail()))
		     .extract().response().path("id");
			
			Assert.assertNotNull(id);
			
			System.out.println(id);
		
			
			
			
			///validate the same user created using get call
		given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer db783db25e085631df665c1276680cc7ac8af0daec919cd2dac69cc8c2042406")
			.when().log().all()
			.get("public/v2/users/" + id)
			.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.body("id", equalTo(id));
		}
}
