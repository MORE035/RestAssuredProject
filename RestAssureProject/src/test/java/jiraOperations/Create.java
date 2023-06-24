package jiraOperations;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.Map;


public class Create {
	public String  JSESSIONID;
	SessionFilter sFilter=new SessionFilter();
	@Test(priority = 0)
	public void sessionCreation() {
		
		RestAssured.baseURI="http://localhost:8080/";
	Map<String, String> sessionResponce=	given().header("content-type","application/json").body("{ \"username\": \"mvk035\", \"password\": \"MOREVASANTH35@\" }")
		.when().filter(sFilter).post("rest/auth/1/session").then().extract().cookies();
//	JsonPath js=new JsonPath(sessionResponce);
//	System.out.println(js.getString(""));
	JSESSIONID=sessionResponce.get("JSESSIONID");
	
	//System.out.println(sessionResponce);
	}
	
	@Test
	public void creatIssue() {
		RestAssured.baseURI="http://localhost:8080/";
	String response=	given().header("Content-Type","application/json").body(" {\r\n"
				+ "     \"fields\": {\r\n"
				+ "        \"project\":\r\n"
				+ "         {\r\n"
				+ "            \"key\": \"JWR\"\r\n"
				+ "        },\r\n"
				+ "         \"summary\": \"password isuue\",\r\n"
				+ "           \"description\": \"When we click forgot password link , its not naviagte to Forgot page\",\r\n"
				+ "            \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ " }").filter(sFilter).when().post("rest/api/2/issue").then().extract().response().asPrettyString();
	
	System.out.println(response);
	}

}
