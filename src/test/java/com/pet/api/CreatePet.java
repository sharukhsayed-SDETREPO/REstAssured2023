package com.pet.api;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombokPOJO.Category;
import com.pet.api.PetLombokPOJO.PetLombokPOJOBuilder;
import com.pet.api.PetLombokPOJO.Tags;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePet {

	
	
	
	@Test
	public void Createpet() {
		RestAssured.baseURI="https://petstore.swagger.io";
		
		//post with serialization
		Category category =new Category(300,"Catname");
	Tags tag1=new Tags(400, "Tagname1");
	Tags tag2=new Tags(200, "Tagname2");
	Tags tag3=new Tags(100, "Tagname3");
	
	List<Tags> tags =Arrays.asList(tag1,tag2,tag3);
	List<String> photoUrls=Arrays.asList("https://dog.com","https://dog1.com","https://dog2.com");
		
	
		PetLombokPOJO petpost= new PetLombokPOJO("FirstNamePet", photoUrls, 300, category, tags, "available");
		
		Response res=given()
		.contentType(ContentType.JSON)
		.body(petpost)
		.when()
		.post("/v2/pet/");
		
		System.out.println(res.statusCode());
		System.out.println(res.getStatusLine());
		res.prettyPrint();
		///de-serialization
		ObjectMapper omp =new ObjectMapper();
		try {
			PetLombokPOJO petlom=omp.readValue(res.getBody().asString(), PetLombokPOJO.class);
			System.out.println("Your name " + petlom.getName());
			System.out.println("Your photoUrls " + petlom.getPhotoUrls().get(0));
			System.out.println("Your photoUrls " + petlom.getPhotoUrls().get(1));
			System.out.println("Your id " + petlom.getId());
			System.out.println("Your category name icludes "+ petlom.getCategory().getName());
			System.out.println("Your category id icludes "+ petlom.getCategory().getId());
			System.out.println("Your tags name  are " + petlom.getTags().get(0).getName());
			System.out.println("Your tags name  are " + petlom.getTags().get(1).getName());
			System.out.println("Your tags Ids  are " + petlom.getTags().get(0).getId());
			System.out.println("Your tags Ids  are " + petlom.getTags().get(1).getId());
			System.out.println("Your status is " + petlom.getStatus());
			////Assertions 
			Assert.assertEquals(petpost.getName(), petlom.getName());
			Assert.assertEquals(petpost.getPhotoUrls().get(0), petlom.getPhotoUrls().get(0));
			Assert.assertEquals(petpost.getPhotoUrls().get(1), petlom.getPhotoUrls().get(1));
			Assert.assertEquals(petpost.getId(), petlom.getId());
			Assert.assertEquals(petpost.getCategory().getId(), petlom.getCategory().getId());
			Assert.assertEquals(petpost.getCategory().getName(), petlom.getCategory().getName());
			Assert.assertEquals(petpost.getTags().get(0).getId(), petlom.getTags().get(0).getId());
			Assert.assertEquals(petpost.getTags().get(1).getId(), petlom.getTags().get(1).getId());
			Assert.assertEquals(petpost.getTags().get(0).getName(), petlom.getTags().get(0).getName());
			Assert.assertEquals(petpost.getTags().get(1).getName(), petlom.getTags().get(1).getName());
			Assert.assertEquals(petpost.getStatus(), petlom.getStatus());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	///////same test with builder pattern
	
	@Test
	public void Createpet_withBuilder() {
		RestAssured.baseURI="https://petstore.swagger.io";
		 
		
		
		Category category= new Category.CategoryBuilder()
		      .id(200)
		      .name("CategoryName").build();
		
		Tags tag1=new Tags.TagsBuilder()
		.id(400)
		.name("TAg1").build();
		Tags tag2=new Tags.TagsBuilder()
				.id(400)
				.name("TAg1").build();
		
		//post with serialization
		PetLombokPOJO petpost=new PetLombokPOJO.PetLombokPOJOBuilder()
		 .name("namewithBuilder")
		 .photoUrls(Arrays.asList("https://photourl1.com","https://photourl2.com","https://photourl3.com"))
		 .id(100)
		 .category(category)
		 .tags(Arrays.asList(tag1,tag2))
		 .status("abailable").build();
				               
		
		Response res=given()
		.contentType(ContentType.JSON)
		.body(petpost)
		.when()
		.post("/v2/pet/");
		
		System.out.println(res.statusCode());
		System.out.println(res.getStatusLine());
		res.prettyPrint();
		///de-serialization
		ObjectMapper omp =new ObjectMapper();
		try {
			PetLombokPOJO petlom=omp.readValue(res.getBody().asString(), PetLombokPOJO.class);
			System.out.println("Your name " + petlom.getName());
			System.out.println("Your photoUrls " + petlom.getPhotoUrls().get(0));
			System.out.println("Your photoUrls " + petlom.getPhotoUrls().get(1));
			System.out.println("Your id " + petlom.getId());
			System.out.println("Your category name icludes "+ petlom.getCategory().getName());
			System.out.println("Your category id icludes "+ petlom.getCategory().getId());
			System.out.println("Your tags name  are " + petlom.getTags().get(0).getName());
			System.out.println("Your tags name  are " + petlom.getTags().get(1).getName());
			System.out.println("Your tags Ids  are " + petlom.getTags().get(0).getId());
			System.out.println("Your tags Ids  are " + petlom.getTags().get(1).getId());
			System.out.println("Your status is " + petlom.getStatus());
			////Assertions 
			Assert.assertEquals(petpost.getName(), petlom.getName());
			Assert.assertEquals(petpost.getPhotoUrls().get(0), petlom.getPhotoUrls().get(0));
			Assert.assertEquals(petpost.getPhotoUrls().get(1), petlom.getPhotoUrls().get(1));
			Assert.assertEquals(petpost.getId(), petlom.getId());
			Assert.assertEquals(petpost.getCategory().getId(), petlom.getCategory().getId());
			Assert.assertEquals(petpost.getCategory().getName(), petlom.getCategory().getName());
			Assert.assertEquals(petpost.getTags().get(0).getId(), petlom.getTags().get(0).getId());
			Assert.assertEquals(petpost.getTags().get(1).getId(), petlom.getTags().get(1).getId());
			Assert.assertEquals(petpost.getTags().get(0).getName(), petlom.getTags().get(0).getName());
			Assert.assertEquals(petpost.getTags().get(1).getName(), petlom.getTags().get(1).getName());
			Assert.assertEquals(petpost.getStatus(), petlom.getStatus());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
}
