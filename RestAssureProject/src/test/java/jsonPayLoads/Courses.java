package jsonPayLoads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class Courses {
	
	@Test
	public void readJsonData() {
		JsonPath jp= new JsonPath(bodyPayload());
		//1)Print No of courses returned by API
		System.out.print("No of courses returned by API = ");
		System.out.println(jp.getInt("courses.size()"));//3 
		System.out.println();
		
		//2.Print Purchase Amount
		System.out.print("Print Purchase Amount is = ");
		System.out.println(jp.getInt("dashboard.purchaseAmount"));//910
		System.out.println();
		int actualPurchaseAmount=jp.getInt("dashboard.purchaseAmount");

		//3. Print Title of the first course
		System.out.print("Print Title of the first course = ");
		System.out.println(jp.getString("courses[0].title"));
		System.out.println();

		//4. Print All course titles and their respective Prices
		int courese=jp.getInt("courses.size()");
		System.out.println("All course titles and their respective Prices");
		for(int i=0;i<courese;i++) {
			System.out.println(jp.get("courses["+i+"].title")+" price is "+jp.get("courses["+i+"].price"));

		}
		//5. Print no of copies sold by RPA Course
		for(int i=0;i<courese;i++) {
			if(((String) jp.get("courses["+i+"].title")). equals("RPA")) {
				System.out.println(jp.get("courses["+i+"]"));
			}

		}
		// 6.Verify if Sum of all Course prices matches with Purchase Amount
		int sum=0;
		for(int i=0;i<courese;i++) {
			sum=sum+(jp.getInt("courses["+i+"].price")*jp.getInt("courses["+i+"].copies"));

		}
		
		Assert.assertEquals(sum, actualPurchaseAmount);
		

		
	}
	@Test
	public void readJsonDataByExternal() throws FileNotFoundException {
		File file=new File("C:\\Users\\SPUGE\\eclipse-workspace\\RestAssureProject\\src\\test\\java\\jsonPayLoads\\nestedPayLoad.json");
		JsonPath jp= new JsonPath(file);
		System.out.println(jp.get("courses.size()"));
		
		
	}
	@Test
	public void readJsonDataByExternal1() throws MalformedURLException {
		//File file=new File();
		URL url=new URL("https://gorest.co.in/public/v2/users");
		JsonPath jp= new JsonPath(url);
		System.out.println(jp.get("size()"));
		//System.out.println(jp.getList("id"));
		System.out.println(jp.getMap("[1]"));
//		for(int i=0;i<jp.getInt("size()");i++) {
//			System.out.println(jp.get("["+i+"].id"));
//		}
		
	}

	
	public static String bodyPayload() 
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
}


