package com.Booking.Common;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonActions {
	
public String getToken() {
		
		String payload = "{\n" + "\"username\":\"admin\",\"password\":\"password123\"" + "}";
		
		Response resp = given()
		.header("Content-Type","application/json")
		.body(payload).when()
		.post("https://restful-booker.herokuapp.com/auth").then().extract().response();
				
		JsonPath jsp =  new JsonPath(resp.asString());		
		String token = jsp.getString("token");		
			
		System.out.println("Generated Token at - " + token);
		
		return token;
	}

}
