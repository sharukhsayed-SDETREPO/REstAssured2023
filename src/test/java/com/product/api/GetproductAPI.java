package com.product.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetproductAPI {

	
	@Test
	public void Getproduct_With_POJO_only() {
		RestAssured.baseURI="https://fakestoreapi.com";
		Response res=given().log().all()
		.when().log().all()
		.get("/products")
		
		;
		
		String wer=given().log().all()
		.when().log().all()
		.get("/products").then().log().all().extract().response().asString();
		ObjectMapper mp=new ObjectMapper();
		try {
			Productresponse[] pm=mp.readValue(wer, Productresponse[].class);
			
			
			
			for (Productresponse pk:pm) {
				System.out.println("ID : " + pk.getId());
				System.out.println("Title : " + pk.getTitle());
				System.out.println("price : " + pk.getPrice());
				System.out.println("Description : " + pk.getDescription());
				System.out.println("category : " + pk.getCategory());
				System.out.println("image : " + pk.getImage());
				System.out.println("rating rate : " + pk.getRating().getRate());
				System.out.println("rating count : " + pk.getRating().getCount());
				System.out.println("-------------");
			}
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	@Test
	public void Getproduct_With_POJO_LOmbok() {
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response res=given().log().all()
		.when().log().all()
		.get("/products");
		;
		ObjectMapper mp=new ObjectMapper();
		try {
			ProductLombokPojo [] pm2=mp.readValue(res.getBody().asString(), ProductLombokPojo[].class);
			
	
		
			for (ProductLombokPojo pk:pm2) {
		
				System.out.println("ID : " + pk.getId());
				System.out.println("Title : " + pk.getTitle());
				System.out.println("price : " + pk.getPrice());
				System.out.println("Description : " + pk.getDescription());
				System.out.println("category : " + pk.getCategory());
				System.out.println("image : " + pk.getImage());
				System.out.println("rating rate : " + pk.getRating().getRate());
				System.out.println("rating count : " + pk.getRating().getCount());
				System.out.println("-------------");
				System.out.println("-------------");
			}
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
