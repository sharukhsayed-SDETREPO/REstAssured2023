package XMLAPIs;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;

public class GetCircuitXMLAPIsTest {

	
	
	
	
	@Test
	public void GetUsersXMLAPIsTest() {
		RestAssured.baseURI="http://ergast.com";
		String res=given()
		.when()
		.get("/api/f1/2017/circuits.xml")
		.then()
		.extract().response().asString();
		
		
		//System.out.println(res);
		
		
		
		XmlPath xml=new XmlPath(res);
		//priting all tags
		System.out.println("Printing all circuits");
		 List<String> allciruicts= xml.getList("MRData.CircuitTable.Circuit.CircuitName");
		 for(String d:allciruicts) {
			 System.out.println(d);
		 }
		 System.out.println("-----------------------");
		 
		 
		 ///printing all attributes for Circuit Tags
		 System.out.println("Printing all circuitIds");
		 List<String> citcuitIds= xml.getList("MRData.CircuitTable.Circuit.@circuitId");
		 for(String d:citcuitIds) {
			 System.out.println(d);
		 }
		 System.out.println("-----------------------");
		/////printing a specific tag having a specific value---using deep scanning 
		 System.out.println("printing a specific tag having a specific value");
		String df= xml.get("**.findAll{it.@circuitId=='americas'}.Location.Locality").toString();
		 
		 System.out.println(df);
		 
		 
		 
		 ////printing a attribute with a query
		 System.out.println("printing a specific attribute witth a query/fliter");
			String latval= xml.get("**.findAll{it.@circuitId=='americas'}.Location.@lat").toString();
			 
			 System.out.println(latval);
			 
			 System.out.println("printing a specific attribute witth a query/fliter");
				String longval= xml.get("**.findAll{it.@circuitId=='americas'}.Location.@long").toString();
			 
				 System.out.println(longval);
				 
				 String url= xml.get("**.findAll{it.@circuitId=='americas'}.@url").toString();
				 
				 System.out.println(url);
				 
				 
				 System.out.println("--------------------");
				 //fetch locality using compound condition
				 
			List<String> allloc=	 xml.getList("**.findAll{it.@circuitId=='americas' || it.@circuitId=='bahrain'}.Location.Locality");
			System.out.println(allloc);
			
			
			
			////fetchin a tag based on a query/filter
			System.out.println("-------------------------");
			String circuidname= xml.get("**.findAll{it.@circuitId=='americas'}.CircuitName");
			System.out.println(circuidname);
			
			System.out.println("--------------");
			/////all urls
			List<String> urls=xml.getList("MRData.CircuitTable.Circuit.@url");
			
			System.out.println(urls);
			
			
	}
	
	
	
}
