package com.user.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)///to ignore non null values
@Data
@NoArgsConstructor
@AllArgsConstructor
@lombok.Builder
public class UserClassPOJO {



	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name ;
	
	@JsonProperty("email")
	private String email ;
	
	@JsonProperty("gender")
	private String gender ;
	
	@JsonProperty("status")
	private String status ;
	
	
	public UserClassPOJO(String name, String email, String gender, String status) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
}
