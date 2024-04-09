package JsonPathValidatorTest;




import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class JsonPathTest {

	
	
	
	
	
	public RequestSpecification Requestspecbuild() {
		RequestSpecification spec=new RequestSpecBuilder()
				.setBaseUri("http://ergast.com")
				.addPathParam("year", 2017)
				.setContentType(ContentType.JSON)
				.build();
		return spec;
	
			
	}
	
	@Test
	public void generateJsonpathforreadingCirCuits() {
		
		//first convert the whole respones in string from .then().extract().response() is not mandatory since get returns the response only 
		String res=given().log().all()
		.spec(Requestspecbuild())
		.when().log().all()
	 .get("/api/f1/{year}/circuits.json").then().extract().response().asString();
		
		List<String> js=JsonPath.read(res,"$..Circuits..Location.country");//This JsonPath comes from Jayway and not restassured like before
          int numbofct=JsonPath.read(res,"$.MRData.CircuitTable.Circuits.length()");///no .. when using length method ,otherwise it wont work
		System.out.println(numbofct);
		System.out.println(js);
		
	}
	
	
	
	
	
	@Test
	public void GetProductDetails() {
		RestAssured.baseURI="https://fakestoreapi.com";
		String res=given()
		.when().log().all()
		.get("/products").asString();
		
		List<Float> rates=JsonPath.read(res, "$[*].rating.rate");
		System.out.println(rates);
		
		System.out.println("----------------------------");
		List<Float> rateslessthan3=JsonPath.read(res, "$[?(@.rating.rate<3)].rating.rate");
		System.out.println(rateslessthan3);
		
		System.out.println("----------------------------");
		
	
		
		List<Double> listofid3=JsonPath.read(res, "$[?(@.id==3)].price");
		System.out.println(listofid3);
		System.out.println("----------------------------");
		
		
/*//		List<Object> count=JsonPath.read(res, "$[?(@.id==3)]..count");
//		System.out.println(count);
//		System.out.println("----------------------------");
//		
*/		
		
		//fetching two attributes to get the List<Map<String,Object>>
		///\\ to negate the double quotes for title ,id ,rating
		List<Map<String ,Object>> jewelryitems=JsonPath.read(res, "$[?(@.category=='jewelery')].[\"title\",\"id\",\"rating\"]");
	System.out.println(jewelryitems);
	System.out.println("+++++++++++");
		for(Map<String,Object> respl:jewelryitems) {
			
			String title =(String) respl.get("title");
			int id=(int) respl.get("id");
			System.out.println(id);
		System.out.println(	title);
		Object rating =(Object) respl.get("rating");
		
		
		String ratinginfo=rating.toString();
		//System.out.println(ratinginfo);
		        String []arr=ratinginfo.split("count=");
		        System.out.println(arr[1].replace("}", ""));
	
		
		}
		System.out.println("----------------------------");
		
		
		
		////
		
	}
}
