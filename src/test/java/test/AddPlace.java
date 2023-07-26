  package test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;


import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

//import java.lang.reflect.Type;

//import javax.json.bind.Jsonb;

//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import groovy.json.JsonOutput;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
//import io.restassured.response.Response;
import payload.RequestPayload;
import pojo.PlaceData;
import pojo.Location;

public class AddPlace {
	Workbook Book;
	static String placeId;
	
	@BeforeMethod
	public void setup() {
	 RestAssured.baseURI="https://rahulshettyacademy.com";
		
	}
	@Test(priority = 1, description = "crete record on server",invocationCount = 1,groups = "smoke",dataProvider = "username")
	public void createGooglePlace(String name,String address){
		PlaceData d  = new PlaceData();
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		d.setLocation(l);
		d.setAccuracy(50);
		d.setPhone_number("98731");
		d.setAddress("bhopal");
		List<String>list = new ArrayList<String>();
		list.add("shop");
		list.add("key");
		d.setType(list);
	     d.setName("singh");
	     d.setWebsite("http://google.com");
	     d.setLanguage("hinsi");
	     
		
		
		String response	=	given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json").
				body(d).
				when().post("maps/api/place/add/json").
				then().log().all().assertThat().statusCode(200)
				             .header("Server","Apache/2.4.52 (Ubuntu)").
				             body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\SCOREME-LT-ANANT\\Downloads\\software\\api automation\\API\\src\\test\\java\\testData\\test.json"))).extract().response().asString();//all resonse comes instring form
	     
		
		      JsonPath js = new JsonPath(response);
		       placeId  = js.getString("place_id");
		      
		      System.out.println(placeId);
				
	}
	@Test(priority =3, description = "fetch data from server")
	public  void getGooglePlace() {
		
		String expectedaddress =  "Delhi up";
		String res =  given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId).
				header("Content-Type","application/json").		
		when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1= new JsonPath(res);
	    String add= js1.getString("address");
	   System.out.println(add);
	   assertEquals(expectedaddress, add);
       
		
		
		
	}
	@Test(priority = 2)
	public void updatAddress() {
		 RestAssured.baseURI="https://rahulshettyacademy.com";
		String response	=	given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json").
				body("{\r\n"
						+ "\"place_id\":\""+placeId+"\",\r\n"
						+ "\"address\":\"Delhi up\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}").
		when().put("maps/api/place/update/json").
				then().log().all().assertThat().statusCode(200)
				            .header("Server","Apache/2.4.52 (Ubuntu)").
			        extract().response().asString();//all resonse comes instring form
		     
	}
	
	
	@DataProvider(name = "username")
 	public Object[][] getdata(){
		 	try {
				FileInputStream file=new FileInputStream("C:\\Users\\SCOREME-LT-ANANT\\Downloads\\software\\api automation\\API\\src\\test\\java\\testData\\Book2.xlsx");
				 try {
					Book = WorkbookFactory.create(file);
				} catch (EncryptedDocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    			Sheet sheet = Book.getSheetAt(0);
		    			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		 			
		    			for(int i=0; i<sheet.getLastRowNum(); i++ ) {
		    				
		    				for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
		    						
		    				data[i][j] =	sheet.getRow(i+1).getCell(j).toString();
		    						
		    				}
		    			}
		    			return data;
	}
}
		 			

	
	
	

	
 

	   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
