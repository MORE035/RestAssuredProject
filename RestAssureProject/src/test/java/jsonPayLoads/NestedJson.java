package jsonPayLoads;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class NestedJson {
	
	@Test
	public void nestedData() {
		JsonPath js=new JsonPath("C:\\Users\\SPUGE\\eclipse-workspace\\RestAssureProject\\src\\test\\java\\jsonPayLoads\\samplePayload.json");
	System.out.println(js.get("Name"));
	}

}
