package SpecificationConcept;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.http.ResponseParseException;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class ResponseSpecBuilder {
	
	
	public 	static ResponseSpecification res200() {
	ResponseSpecification res=	new io.restassured.builder.ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare").build();
	return res;
	}
	
	
	public 	static ResponseSpecification res400() {
		ResponseSpecification res=	new io.restassured.builder.ResponseSpecBuilder()

			.expectStatusCode(401)
			.expectHeader("Server", "cloudflare").build();
		return res;
		}
	
	
	
	
	
	public static ResponseSpecification res200_bodyvalidations() {
		ResponseSpecification resp= new io.restassured.builder.ResponseSpecBuilder()
				.expectHeader("Server", "cloudflare")
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectBody("$.size()", equalTo(10))
				.expectBody("id", hasSize(10)).build();
				return resp;
		      
	}
	
	//test to validate th response using spec
	@Test
	public void validateresponsefor_GORest() {
		RestAssured.baseURI="https://gorest.co.in";
		given().log().all()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when().log().all()
		.get("public/v2/users/")
		.then().log().all()
		.assertThat()
		.spec(res200());
		
		
	}
	
	
	//validate 400
	@Test
	public void validateresponsefor_GORestwith401fail() {
		RestAssured.baseURI="https://gorest.co.in";
		given().log().all()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac")
		.when().log().all()
		.get("public/v2/users/")
		.then().log().all()
		.assertThat()
		.spec(res400());
		
		
	}
	
	
	
	//validate body 
	@Test
	
	public void validatebody_using_spec() {
		RestAssured.baseURI="https://gorest.co.in";
		given().log().all()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when().log().all()
		.get("public/v2/users/")
		.then().log().all()
		.assertThat()
		.spec(res200_bodyvalidations());
		
	}
}
 