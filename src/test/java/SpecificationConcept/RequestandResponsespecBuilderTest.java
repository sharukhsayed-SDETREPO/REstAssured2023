package SpecificationConcept;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
public class RequestandResponsespecBuilderTest {

	
	
	
	
	
	public static RequestSpecification requestspec() {
		RequestSpecification reqspec=new RequestSpecBuilder()
				.setBaseUri("http://ergast.com")
				.addPathParam("year", 2023)
				.setContentType(ContentType.JSON)
				.build();
		return reqspec;
	}
	
	public static ResponseSpecification responsepec() {
		ResponseSpecification resspecv= new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.expectBody("MRData.total", equalTo("22") ).build();
		
		return resspecv;
	}
	
	
	
@Test
public void validaterequestandres_spec() {
	
	given().log().all()
	.spec(requestspec())
	.when().log().all()
	
	.get("/api/f1/{year}/circuits.json")
	.then()
	.assertThat()
	.spec(responsepec());
	
	
}
}
