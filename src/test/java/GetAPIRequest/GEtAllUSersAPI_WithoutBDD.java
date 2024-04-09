package GetAPIRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GEtAllUSersAPI_WithoutBDD {

	
	RequestSpecification req;
	@BeforeClass
	public void Setup(){
		RestAssured.baseURI = "https://gorest.co.in"; // define your base URL (URI)
		req= RestAssured.given();// start creating your request
		req.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
	}
	
	
	
	
	
	@Test
	public void GetUserAPITEST() {

		
		Response response = req.get("/public/v2/users/");
		// verify status code
		int statuscode = response.getStatusCode();
		System.out.println("status code :" + statuscode);
		Assert.assertEquals(statuscode, 200);

		// verify status message

		String statusmsg = response.getStatusLine();
		System.out.println("YOur response " + statusmsg);

		// fetch AND PRINT THE BODY the body
		response.prettyPrint();

		/// fetch a specific header
		String headername = response.header("Content-type");
		System.out.println("Your header value is :" + headername);

		// fetch all the header and print it

		List<Header> allheads = response.headers().asList();

		for (Header h : allheads) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

	}

	@Test

	public void GetUserAPITEST_AddedQueryParam() {

	System.out.println("-------------------------------------");
		req.queryParam("status", "active");
		req.queryParam("gender", "male");
		Response response = req.get("/public/v2/users/");
		// verify status code
		int statuscode = response.getStatusCode();
		System.out.println("status code :" + statuscode);
		Assert.assertEquals(statuscode, 200);

		// verify status message
		System.out.println("-----------------------------------------");
		String statusmsg = response.getStatusLine();
		System.out.println("YOur response " + statusmsg);

		// fetch AND PRINT THE BODY the body
		response.prettyPrint();

	}
	
	
	
	@Test

	public void GetUserAPITEST_AddedQueryParam_usingMAP() {
         Map<String ,String> queryparams=new HashMap<String,String>();
	
	
		queryparams.put("id", "6732586");
		queryparams.put("geder", "male");
		req.queryParams(queryparams);
		Response response = req.get("/public/v2/users/");
		// verify status code
		int statuscode = response.getStatusCode();
		System.out.println("status code :" + statuscode);
		Assert.assertEquals(statuscode, 200);

		// verify status message
		System.out.println("-----------------------------------------");
		String statusmsg = response.getStatusLine();
		System.out.println("YOur response " + statusmsg);

		// fetch AND PRINT THE BODY the body
		response.prettyPrint();

	}
}
