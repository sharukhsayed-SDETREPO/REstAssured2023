package com.pet.api;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.api.UserClassPOJO;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@lombok.Builder
public class PetLombokPOJO {

	
	private String name;
	private List<String> photoUrls;
	private Integer id;
	private Category category;
	private List<Tags> tags;
	private String status;
	
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@lombok.Builder
	
	public static class Category{
		private Integer id;
		private String name;
	}
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@lombok.Builder
	public static class Tags{
		private Integer id;
		private String name;
	}
	
	
}
