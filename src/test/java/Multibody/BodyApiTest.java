package Multibody;

import java.io.File;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BodyApiTest {

	
	
	
	/////Body as raw text
	@Test
	public void testText() {
		RestAssured.baseURI= "http://httpbin.org";
	Response res=	RestAssured.given()
		.contentType(ContentType.TEXT)
		.body("This is my body")
		.when()
		.post("/post");
	
	res.prettyPrint();
	System.out.println(res.statusCode());
	
	String js= JsonPath.read(res.body().asString() ,"$.data");
	
	System.out.println(js);
	}
	
	
	
	
	
	

	/////Body as raw javascript
	@Test
	public void testJavaScript() {
		RestAssured.baseURI= "http://httpbin.org";
	Response res=	RestAssured.given()
		.header("Content-Type", "application/javascript") /////no content type for java script
		.body("function login () {\n"
				+"let x= 10; \n"
				+"let y=20; \n"
				+ "console.log(x+y);\n"
				+ "}")
		.when()
		.post("/post");
	
	res.prettyPrint();
	System.out.println(res.statusCode());
	
	String js= JsonPath.read(res.body().asString() ,"$.data");
	
	System.out.println(js);
	}
	
	
	
/////Body as raw HTML
	@Test
	public void testHTML() {
		RestAssured.baseURI= "http://httpbin.org";
	Response res=	RestAssured.given()
		.contentType(ContentType.HTML)
		.body("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <title>Your Page Title</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"    <h1>Hello, World!</h1>\r\n" + 
				"    \r\n" + 
				"    <p>This is a basic HTML sample.</p>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>")
		.when()
		.post("/post");
	
	res.prettyPrint();
	System.out.println(res.statusCode());
	
	String js= JsonPath.read(res.body().asString() ,"$.data");
	
	System.out.println(js);
	}
	
	
	
/////Body as raw XML
	@Test
	public void testXML() {
		RestAssured.baseURI= "http://httpbin.org";
	Response res=	RestAssured.given()
		.contentType(ContentType.XML)
		.body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<root>\r\n" + 
				"    <person>\r\n" + 
				"        <name>John Doe</name>\r\n" + 
				"        <age>30</age>\r\n" + 
				"        <city>New York</city>\r\n" + 
				"    </person>\r\n" + 
				"    <person>\r\n" + 
				"        <name>Jane Doe</name>\r\n" + 
				"        <age>25</age>\r\n" + 
				"        <city>Los Angeles</city>\r\n" + 
				"    </person>\r\n" + 
				"</root>\r\n" + 
				"")
		.when()
		.post("/post");
	
	res.prettyPrint();
	System.out.println(res.statusCode());
	
	String js= JsonPath.read(res.body().asString() ,"$.data");
	
	System.out.println(js);
	}
	
	
	
	
	
	
	
	/////Body as  MULTIPART FORM
	@Test
	public void testMUltipart() {
		RestAssured.baseURI= "http://httpbin.org";
	Response res=	RestAssured.given()
		.contentType(ContentType.MULTIPART)
		.multiPart("name", "testing")
		.multiPart("keyvalue",new File("C:\\Users\\shari\\Desktop\\docker issue.txt"))
		.when()
		.post("/post");
	
	res.prettyPrint();
	System.out.println(res.statusCode());
	
	String js= JsonPath.read(res.body().asString() ,"$.data");
	
	System.out.println(js);
	}
	
	
	@Test
	public void testBinaryFile   () {
		RestAssured.baseURI= "http://httpbin.org";
	Response res=	RestAssured.given()
		.header("Content-Type","application/pdf")
		.body(new File("C:\\\\Users\\\\shari\\\\Desktop\\\\map.pdf"))
		.when()
		.post("/post");
	
	res.prettyPrint();
	System.out.println(res.statusCode());
	
	String js= JsonPath.read(res.body().asString() ,"$.data");
	
	System.out.println(js);
	}
	
	
	
}
