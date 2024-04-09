package AuthAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTestClass {
	
	
	@BeforeTest
	public void generateAlluer() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	
	
	@Test
	public void JWTAuthWithJsonBody() {
		RestAssured.baseURI="https://fakestoreapi.com";
		
		String jwttoken=given()
		.contentType(ContentType.JSON)
		.body("{ \"username\": \"mor_2314\",\r\n" + 
				"                \"password\": \"83r5^_\"}")
		.when()
		.post("/auth/login")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response().path("token");
		
		System.out.println(jwttoken);
		System.out.println(jwttoken.split("\\.")[0]);
		System.out.println(jwttoken.split("\\.")[1]);
		System.out.println(jwttoken.split("\\.")[2]);
		}
	
	
	
	@Test
	public void basicAuthTest() {
		RestAssured.baseURI="https://the-internet.herokuapp.com";
	String body=	given()
		.auth().basic("admin", "admin")
		.when()
		.get("/basic_auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response().body().asString();
		
		System.out.println(body);
		
	}
	
	
	
	
	@Test
	public void preemptiveasicAuthTest() {
		RestAssured.baseURI="https://the-internet.herokuapp.com";
	String body=	given()
		.auth().preemptive().basic("admin", "admin")
		.when()
		.get("/basic_auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response().body().asString();
		
		System.out.println(body);
		
	}
	
	
	
	@Test
	public void DigestAuthTest() {
		RestAssured.baseURI="https://the-internet.herokuapp.com";
	String body=	given()
		.auth().digest("admin", "admin")
		.when()
		.get("/basic_auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response().body().asString();
		
		System.out.println(body);
		
	}
	
	
	
	
	@Test
	public void APIKeyAuthTest() {
		RestAssured.baseURI="http://api.weatherapi.com";
	String body=	given()
		.queryParam("q", "London")
		.queryParam("aqi", "no")
		.queryParam("key", "0b7567ac66234b9daba104729230806")
		.when()
		.get("/v1/current.json")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response().body().asString();
		System.out.println(body);
		
	}

	

}
