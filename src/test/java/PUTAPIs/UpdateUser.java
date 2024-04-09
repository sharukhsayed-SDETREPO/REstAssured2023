package PUTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUser {

	
	public String GenerateRandome() {
		return "APIaUTOMATION"+ System.currentTimeMillis() + "@GMAIL.COM";
	}
	
	@Test
	public void UpdateUserTest() {
		RestAssured.baseURI="https://gorest.co.in";
		 
		User user=new User("NameBeforeUpdate", GenerateRandome(), "male", "active");
		Response res=given()
				.contentType(ContentType.JSON)
		.body(user)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when().log().all()
		.post("/public/v2/users/")
		;
		
	int id=	res.jsonPath().get("id");
		System.out.println("Generated id is " + id);
		
		
		///updating the user created using setter methods from the same POJO instance
		user.setGender("female");
		user.setName("NameAftertheUpdate");;
		user.setStatus("inactive");
		
		
		given()
				.contentType(ContentType.JSON)
		.body(user)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
		.put("/public/v2/users/" + id)
		 .then().log().all()
		 .assertThat()
		 .statusCode(200)
		 .and()
		 
		    .body("gender",equalTo(user.getGender()))
		    .body("name",equalTo(user.getName()))
		    .body("status",equalTo(user.getStatus()))
		    .body("id", equalTo(id));
		    
		 
		
		;
		
	}
	
	
	
	
	@Test 
	public void UpdateUserTest_withBuilder() {
		RestAssured.baseURI="https://gorest.co.in";
		
		User usr=new User.UserBuilder()
		.name("namebefore")
		.email(GenerateRandome())
		.gender("male")
		.status("active")
		.build();
		
		
		
		Response res1=given()
		.contentType(ContentType.JSON)
		.body(usr)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
		.post("/public/v2/users/");
		
		
	    String res=res1.body().asString();
	  String name=  JsonPath.read(res, "$.name");
	  Integer id=  JsonPath.read(res, "$.id");
	  String email=  JsonPath.read(res, "$.email");
	  String gender=  JsonPath.read(res, "$.gender");
	  String status=  JsonPath.read(res, "$.status");
	  System.out.println(name + " :" + id + " :" + email + " :" +  gender + " :" + status) ;
	  
	  
	  
	  
	  ///upadting using put method 
	  
	  
	  usr.setGender("female");
	  usr.setName("SecondName");
	  usr.setStatus("inactive");
	  
	  
	  given()
	  .contentType(ContentType.JSON)
		.body(usr)
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
		.put ("/public/v2/users/" + id)
		.then().log().all()
		.assertThat().statusCode(200)
		.body("id", equalTo(id))
		 .body("name",equalTo(usr.getName()))
		    .body("status",equalTo(usr.getStatus()));
		  
	  
	  
	}
	
	
}
