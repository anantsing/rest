package page;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pojo.DAddNewPlaceData;
import pojo.DLocation;
import pojo.DNewPlaceResponse;

@Test
public class DAddPlace {
	
	public void addNewPlaceonGoogle() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		DAddNewPlaceData d = new DAddNewPlaceData();
		DLocation k = new DLocation();
		k.setLat(-38.383494);
		k.setLng(33.427362);
		d.setLocation(null);
		d.setAccuracy(50);
		d.setPhone_number("98731");
		d.setAddress("bhopal");
		List<String>list = new ArrayList<String>();
		list.add("puppy");
		list.add("key");
		d.setType(list);
	     d.setName("karu");
	     d.setWebsite("http://google.com");
	     d.setLanguage("hinsi");
		
	     DNewPlaceResponse ret = given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json").
		body(d).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().
		response().as(DNewPlaceResponse.class);
		
	  
	 String status = ret.getStatus();
	Assert.assertEquals("OK",status);
	  
	  
	  
	  
	  
	  
}
}
