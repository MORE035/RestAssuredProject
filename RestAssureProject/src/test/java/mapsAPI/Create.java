package mapsAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Create {
	
	@Test
	public void createLocationByPojo() {
		AddPlace ap=new AddPlace();
		ap.setAccuracy(60);
		ap.setAddress("6th ward bapatla");
		ap.setLanguage("French-IN");
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		ap.setName("Vasanth");
		ap.setPhone_number("(+91) 983 893 3937");
		List<String> list=new ArrayList<String>(Arrays.asList("Deeps Enyterprice","Vasanth garmets"));
	
		ap.setTypes(list);
		ap.setWebsite("www.vasanth.com");
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		given().queryParam("key", "qaclick123").log().all().body(ap).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200);
	}

}
