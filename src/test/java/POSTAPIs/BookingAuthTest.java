package POSTAPIs;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.equalTo;

public class BookingAuthTest {

	
	
	
	
	
	
	
	/////post call with hardcoded data
	
	@Test
	public void gettokenID() {
		
		
		RestAssured.baseURI ="https://restful-booker.herokuapp.com";
		JsonPath tokenID=given().log().all()
				.contentType(ContentType.JSON)
		.body("{\n" 
				+"    \"username\" : \"admin\",\n" 
				+"    \"password\" : \"password123\"\n"
                + "}")
		.when().log().all()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response().jsonPath();
		String tose=tokenID.getString("token");
		System.out.println(tose);
		Assert.assertNotNull(tose);
	}
	
	
	
	///post call with data import from json file
	@Test
	public void gettokenID_JsonFile() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		String toeknId=given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/java/src/test/resoources/basicauth.json"))
		.when().log().all()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response().path("token");
		System.out.println(toeknId);
		
	}
	
	
	
	//a test to create a user using post call and verify it using get call
	
	@Test
	public void addaUser() {
		
		//add a user code
		RestAssured.baseURI="https://gorest.co.in/";
		int id=given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer db783db25e085631df665c1276680cc7ac8af0daec919cd2dac69cc8c2042406")
		.body(new File("./src/test/java/src/test/resoources/AddUser.json")).
		when()
		.post("public/v2/users").
		then()
	     .assertThat()
	     .statusCode(201)
	     .and()
	     .body("name", equalTo("ThisIsMyName2"))
	     .extract().response().path("id");
		
		Assert.assertNotNull(id);
		
		System.out.println(id);
		
		
		
		
		///validate the same user created using get call
	given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer db783db25e085631df665c1276680cc7ac8af0daec919cd2dac69cc8c2042406")
		.when()
		.get("public/v2/users/" + id)
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("id", equalTo(id));
	}
}
	
		
		
		