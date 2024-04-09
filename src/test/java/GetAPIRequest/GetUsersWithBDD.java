package GetAPIRequest;

/////VVVIP we can now import all static methods without mentioning the class name
import static io.restassured.RestAssured.given;
/// matcher library
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class GetUsersWithBDD {


	
	@Test
	
	public void GetAPIWithBDD() {
		//This approach is called builder pattern
	given().log().all()
		 .when().log().all()
	           .get("https://fakestoreapi.com/products")
	                   .then().log().all()
	                       .assertThat()
	                            .statusCode(200)
	                                 .and()    
	                                     .contentType(ContentType.JSON)
	                                           .and()
	                                          .header("Connection", "keep-alive")
	                                            .and()
	                                               .body("$.size()", equalTo(20))   //$ means take all the response body and .size will return its size
	                                                   .and()
	                                                       .body("id", is(notNullValue()))
	                                                          .and()
	                                                                .body("title", hasItem("Mens Cotton Jacket"));
	                                                   } 
	
	
	
	
	
	
	
	@Test
	public void GetUserAPITEST() {
		//This approach is called builder pattern
		RestAssured.baseURI = "https://gorest.co.in";
		
   given().log().all()
   .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
   
		 .when().log().all()
	           .get("/public/v2/users/")
	                   .then().log().all()
	                       .assertThat()
	                            .statusCode(200)
	                                 .and()    
	                                 .statusLine("HTTP/1.1 200 OK")
	                                     .contentType(ContentType.JSON)
	                                           .and()
	                                     
	                                               .body("$.size()", equalTo(10));   //$ means take all the response body and .size will return its size
	                                                   
	                                                   } 
	
	
	
	@Test
	public void GetUSERAPITEST_WithQueryparameter() {
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		 .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		 .queryParam("limit", 5)
		 .when().log().all()
		  .get("/public/v2/users/")
		  .then().log().all()
		  .assertThat()
		  .statusCode(200)
		  .and()
		  .contentType(ContentType.JSON);
	}
	
	
	
	
	
	@Test
	public void GetUSERAPITEST_WithmultipleQueryparameter() {
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
	
		.queryParam("status", "active")
		.queryParam("gender", "male")
		.when().log().all()
		.get("public/v2/users/") 
		.then().log().all()
		.assertThat()
		.statusCode(200)
		 .and()
		  .contentType(ContentType.JSON);
		
	}
	
	
	/////json array with hardcoded indexed values
	
	@Test
	public void getProductDataAPI_With_Extract_Body() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response resp=given().log().all()
		.queryParam("limit", 5)
		.when().log().all()
		.get("/products");
	JsonPath js=	resp.jsonPath();
	resp.prettyPrint();
	int FirstID=js.getInt("[0].id");
	String FirstproductTitle=js.getString("[0].title");
	float price=js.getFloat("[0].price");
	int count =js.getInt("[0].rating.count");
	System.out.println("First ID is :" +FirstID);
	System.out.println("First product title is :" +FirstproductTitle);
	System.out.println("First price is :" +price);
	System.out.println("First count is :" +count);
		
	}
	
	
	//json array ,extracting all the data in list and then printing 
	
	@Test 
	
	public void getAllDatafrom_Json_Array_Response() {
		RestAssured.baseURI="https://fakestoreapi.com";
		Response res=given().log().all()
		.queryParam("limit", 10)
		.when().log().all()
		.get("/products");
	
		
		JsonPath js=res.jsonPath();
		List<Integer> idList=js.getList("id");
		List<String> TitleList=js.getList("title");
		List<Object> priceList=js.getList("price");
		List<Integer> CountList=js.getList("rating.count");
		
		for(int i=0;i<idList.size();i++) {
			int ID =idList.get(i);
			String Tilte=TitleList.get(i);
			Object price=( priceList.get(i));
			int count =CountList.get(i);
			System.out.println("ID :" + ID + "Title :" + Tilte + "Price :" + price + "Count :" + count );
		  }
		
		
		
	}
		//normal json paylaod object  ,extracting a single instance that is available 
		
		@Test 
		
		public void getAllDatafrom_Json_NonArray_Response() {
			RestAssured.baseURI="https://gorest.co.in";
			Response res=given().log().all()
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.queryParam("id", "6732586")
			.when().log().all()
			.get("public/v2/users");
		
			JsonPath js=res.jsonPath();
			
			String email=js.getString("email");
			System.out.print(email);;
			
	}
	
		
		//normal json paylaod object  ,extracting a single instance that is available  WITH EXTRACT KEYWORD
		
				@Test 
				
				public void getAllDatafrom_Json_NonArray_Response_With_Extract() {
					RestAssured.baseURI="https://gorest.co.in";
				Response res=	given().log().all()
							.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
							
					.when().log().all()
					.get("public/v2/users/6732586")
					.then().log().all()
					 .extract().response();
		
				String email =res.path("email");
				int id =res.path("id");
				System.out.println(email + "  "+id);
					
			}	
	
				
			
	
	
}
