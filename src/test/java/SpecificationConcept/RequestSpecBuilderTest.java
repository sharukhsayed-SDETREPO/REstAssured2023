package SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class RequestSpecBuilderTest {

	
	
	
	
	public static RequestSpecification User_spec() {
		RequestSpecification reqspec =new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
				.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.setContentType(ContentType.JSON)
				
				.build();
		return reqspec;
	}
	
	@Test
	public void Get_user_With_SPEC() {
		given().log().all()
		.spec(User_spec())
		.when().log().all()
		
		.get("/public/v2/users/")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		 
	}
	
	@Test
	public void Get_user_With_SPEC_withParam() {
		given().log().all()
		.queryParam("gender", "male")
		.queryParam("name", "Naveen")
		.when().log().all()
		.spec(User_spec())
		.get("/public/v2/users/")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
	}
	
	
	
}
