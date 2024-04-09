package PackageDeleteAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUser {


	public String GenerateRandome() {
		return "APIaUTOMATION"+ System.currentTimeMillis() + "@GMAIL.COM";
	}
	
	
	////create the user
	////delete the user
	
	
	
	@Test
	public void DeleteCreatedUser() {
		RestAssured.baseURI="https://gorest.co.in";
		
		
		Userwer usr=new Userwer.UserwerBuilder()
		.name("namebefore")
		.email(GenerateRandome())
		.gender("male")
		.status("active")
		.build();
				
		
		
		Response res=given()
		.contentType(ContentType.JSON)
		.body(usr)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when().log().all()
		.post("/public/v2/users/")
		;
		
		res.prettyPrint();
		int id=res.jsonPath().get("id");
		System.out.println("generate id is " + id);
		
		
		///delete does not require any body 
		given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when().log().all()
		.delete("/public/v2/users/" + id)
		.then().log().all()
		.assertThat()
		.statusCode(204);
		
		
		//now valdiate with get call 
		given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when().log().all()
		.get("/public/v2/users/" + id)
		.then().log().all()
		.assertThat()
		.statusCode(404)
		.body("message", equalTo("Resource not found"));
		
	}
}
