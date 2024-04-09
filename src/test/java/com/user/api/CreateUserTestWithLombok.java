package com.user.api;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class CreateUserTestWithLombok {

	public static String  generateRanddomeEmail() {
		return "apiAutomation"+System.currentTimeMillis() +"@gmail.com";
	}
	
	
	
	
	@Test 
	public void CreatAndValidateUser() {
		RestAssured.baseURI="https://gorest.co.in";
		///post method with serialization
		UserClassPOJO userpost= new UserClassPOJO("trdstr", generateRanddomeEmail(), "male", "active");
		Response post=given()
		.contentType(ContentType.JSON)
		.body(userpost)
		.header("Authorization", "Bearer db783db25e085631df665c1276680cc7ac8af0daec919cd2dac69cc8c2042406")
		
		.when()
		.post("/public/v2/users/");
	
		int id=post.jsonPath().get("id");
		System.out.println("Generate ID from post call is " + id);
		
		
		
		/////using get method  deserilization 
		Response get=given()
              .header("Authorization", "Bearer db783db25e085631df665c1276680cc7ac8af0daec919cd2dac69cc8c2042406")
		.when()
		.get("/public/v2/users/" + id);
		
		ObjectMapper om= new ObjectMapper();
		
		try {
			UserClassPOJO userget=om.readValue(get.getBody().asString(), UserClassPOJO.class);
			
			System.out.println(userget.getId() + ":" + userget.getEmail() + ":" + userget.getGender() +":" + userget.getName() + ":" +userget.getStatus());
			
			
			Assert.assertEquals(userpost.getName(), userget.getName());
			Assert.assertEquals(userpost.getGender(), userget.getGender());
			Assert.assertEquals(userpost.getEmail(), userget.getEmail());
			Assert.assertEquals(userpost.getStatus(), userget.getStatus());
			Assert.assertEquals(id, userget.getId());
		} catch (JsonProcessingException e) {
	
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}
	////with builder patter we do not need any class constructor
	@Test 
	public void CreatAndValidateUser_UsingBuilderPatter() {
		////serializaton using builder patter 
		UserClassPOJO user=	new UserClassPOJO.UserClassPOJOBuilder()
		.name("NewName")
		.email(generateRanddomeEmail())
		.status("active")
		.gender("male")
		.build();
		
		RestAssured.baseURI ="https://gorest.co.in";
		Response respost=given().log().all()
		.body(user)
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer db783db25e085631df665c1276680cc7ac8af0daec919cd2dac69cc8c2042406")
		.when().log().all()
		.post("public/v2/users/");
		
		
		int id=respost.jsonPath().getInt("id");
		System.out.println("generated id with builder pattern " +id );
		
		
		////validate using get call and desiralize 
		ObjectMapper omp=new ObjectMapper();
				
		Response resget=given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer db783db25e085631df665c1276680cc7ac8af0daec919cd2dac69cc8c2042406")
		.when()
		.get("public/v2/users/" + id);
		
		try {
			UserClassPOJO usegetbldr=omp.readValue(resget.getBody().asString(), UserClassPOJO.class);
			
			
			Assert.assertEquals(user.getEmail(), usegetbldr.getEmail());
			Assert.assertEquals(user.getGender(), usegetbldr.getGender());
			Assert.assertEquals(user.getName(), usegetbldr.getName());
			Assert.assertEquals(user.getStatus(), usegetbldr.getStatus());
			Assert.assertEquals(id, usegetbldr.getId());
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
	
		
		
		
		
		
		
	}

}
