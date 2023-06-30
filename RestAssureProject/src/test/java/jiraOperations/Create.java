package jiraOperations;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class Create {
	public static String  JSESSIONID;
	SessionFilter sFilter=new SessionFilter();
	@Test(priority = 0)
	public void sessionCreation() {
		
		RestAssured.baseURI="http://localhost:8080/";
	Map<String, String> sessionResponce=	given().header("content-type","application/json").body("{ \"username\": \"mvk035\", \"password\": \"MOREVASANTH35@\" }")
		.when().filter(sFilter).post("rest/auth/1/session").then().extract().cookies();
//	JsonPath js=new JsonPath(sessionResponce);
//	System.out.println(js.getString(""));
	JSESSIONID="JSESSIONID="+sessionResponce.get("JSESSIONID");
	
	System.out.println(sessionResponce);
	}
	
	@Test(dependsOnMethods = "sessionCreation")
	public void creatIssue() {
		RestAssured.baseURI="http://localhost:8080/";
	String response=	given().header("Content-Type","application/json").body(" {\r\n"
				+ "     \"fields\": {\r\n"
				+ "        \"project\":\r\n"
				+ "         {\r\n"
				+ "            \"key\": \"JWR\"\r\n"
				+ "        },\r\n"
				+ "         \"summary\": \"Login isuue\",\r\n"
				+ "           \"description\": \"When we click forgot password link , its not naviagte to Forgot page\",\r\n"
				+ "            \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ " }").header("Cookie",JSESSIONID).when().post("rest/api/2/issue").then().extract().response().asPrettyString();
	
	System.out.println(response);
	}
	@Test
	public void addComment() {
		given().header("Content-Type","application/json").pathParam("id", "10200").header("Cookie","JSESSIONID=14925D2A72D74D0ECDF087813F77C95A").body(

			"{\r\n"
			+ "    \"body\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}"	).when().post("/rest/api/2/issue/{id}/comment").then().log().all().extract().response();
		
	}
	@Test(dependsOnMethods = "sessionCreation")
	public void addAttachments() throws IOException {
		RestAssured.baseURI="http://localhost:8080/";

		given().header("X-Atlassian-Token","no-check").header("Cookie",JSESSIONID).pathParam("key", "10300").
		
		multiPart("file",new File("demo.text")).when().post("/rest/api/2/issue/{key}/attachments").then().log().all();


	}

	
}
