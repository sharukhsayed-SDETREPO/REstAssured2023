package SCHEMAValidations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.pet.api.PetLombokPOJO;
import com.pet.api.PetLombokPOJO.Category;
import com.pet.api.PetLombokPOJO.Tags;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidationTest {	
		
	@Test
	public void addUserSchemaTest() {
RestAssured.baseURI="https://petstore.swagger.io";
		
		//post with serialization
		Category category =new Category(300,"Catname");
	Tags tag1=new Tags(400, "Tagname1");
	Tags tag2=new Tags(200, "Tagname2");
	Tags tag3=new Tags(100, "Tagname3");
	
	List<Tags> tags =Arrays.asList(tag1,tag2,tag3);
	List<String> photoUrls=Arrays.asList("https://dog.com","https://dog1.com","https://dog2.com");
		
	
		PetLombokPOJO petpost= new PetLombokPOJO("FirstNamePet", photoUrls, 300, category, tags, "available");
		
		given()
		.contentType(ContentType.JSON)
		.body(petpost)
		.when()
		.post("/v2/pet/")
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.body(JsonSchemaValidator.matchesJsonSchema(new File("D:\\New folder\\REstAssured2023\\src\\test\\java\\src\\test\\resources\\createuserschema.json")));
	}
	
	
	@Test
	public void getUserSchemaTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1. add user - POST
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6").
		when()
			.get("/public/v2/users/").
		then().log().all()
			.assertThat()
				.statusCode(200)
				.body(matchesJsonSchemaInClasspath("createuserschema.json"));
	}
	
	
	@Test
	public void addUserSchemaTestB() {
RestAssured.baseURI="https://petstore.swagger.io";
		
		//post with serialization
		Category category =new Category(300,"Catname");
	Tags tag1=new Tags(400, "Tagname1");
	Tags tag2=new Tags(200, "Tagname2");
	Tags tag3=new Tags(100, "Tagname3");
	
	List<Tags> tags =Arrays.asList(tag1,tag2,tag3);
	List<String> photoUrls=Arrays.asList("https://dog.com","https://dog1.com","https://dog2.com");
		
	
		PetLombokPOJO petpost= new PetLombokPOJO("FirstNamePet", photoUrls, 300, category, tags, "available");
		
		given()
		.contentType(ContentType.JSON)
		.body(petpost)
		.when()
		.post("/v2/pet/")
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.body(matchesJsonSchemaInClasspath("createuserschema.json") );
				}
}