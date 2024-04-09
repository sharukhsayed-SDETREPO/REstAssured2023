package GetAPIRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class GetAPIwithPathParameter {

	
	/*
	 * //query parameter is always written i
	 *n key value pairs where key is predefined in 
	 *your api documentation<key,value>
	 */
/*
 * /path parameter is also written 
 * in key and value pairs but key
 *  can be any value that user decides	
 */
	//normal json payload consisting of a internal array ,data extratced using path parameter
	@Test
	public void GetCircuitDataApiwithPathparameter() {
		RestAssured.baseURI="http://ergast.com/";
		given().log().all()
		.pathParam("year", "2012")
		.when().log().all()
		.get("api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("MRData.CircuitTable.season", equalTo("2012"))
		.and()
		.body("MRData.CircuitTable.Circuits.circuitId.size()",equalTo(20) )
		.and()
		.body("MRData.CircuitTable.Circuits.circuitId",hasSize(20) );
	}
	
	
	////printing all the circuit Ids using list 
	
	
	
	@Test(dataProvider  ="dataForCircuitSeason")
	
	public void print_AllCircuitIDsforthepast5years(String year,int abd ) {
		RestAssured.baseURI="http://ergast.com/";
		
		JsonPath js=given()
		.pathParam("year", year)
		.when()
		.get("api/f1/{year}/circuits.json")
		.then()
		.extract().response().jsonPath();
		
		
		List<String> circuitIds=js.getList("MRData.CircuitTable.Circuits.circuitId");
		
		
		
		for(int i=0;i<circuitIds.size();i++) {
			String ciruiutID=circuitIds.get(i);
			System.out.println("Circuit ID fo year" + year +"is given as  :" +ciruiutID );
		}
		
		
		
		
		
		
		
		
	}
	
	////same can be achieved by data provider
	
	@DataProvider
	
	public Object[][] dataForCircuitSeason() {
		return new Object [][] {
			{"2019",21},
			{"2020",14},
			{"2021",21},
			{"2022",22},
			{"2023",22}
		};
	}
	
	@Test (dataProvider = "dataForCircuitSeason")
	public void GetCircuitDataApiwithPathparameterand_DataProvider(String DataYear,int totlacts) {
	  RestAssured.baseURI="http://ergast.com/";
	  given().log().all()
	  .pathParam("year",DataYear)
	  .when().log().all()
	   .get("api/f1/{year}/circuits.json")
	   .then().log().all()
	   .assertThat()
	   .statusCode(200)
	   .and()
	   .body("MRData.CircuitTable.season", equalTo(DataYear))
	   .and()
	   .body("MRData.CircuitTable.Circuits.circuitId.size()",equalTo(totlacts) )
	   .and()
	   .body("MRData.CircuitTable.Circuits.circuitId.",hasSize(totlacts) )
	   ;
	  
	}
}
