package com.Booking.Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import com.Booking.Common.*;

import java.util.HashMap;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Booking_Test {
	
	public static String token = "";
	public static int id = 0;
	public CommonActions common;
	public static String firstname;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		firstname = com.PetUiClinic.Pages.CommonActions.generateRandomString();
	}
	
	@Before
	public void setUp() throws Exception {
		
		common = new CommonActions();
	}

	//Validate Create Booking
	@Test
	public void Test1_CreateBooking() {

		String payload = "{\n" + "\"depositpaid\":true,\"firstname\":\"" + firstname + "\",\"additionalneeds\":\"Breakfast\",\"totalprice\":111,\"lastname\":\"auto1\","
				+ "\"bookingdates\": {\r\n"
				+ "            \"checkin\": \"2018-01-01\",\r\n"
				+ "            \"checkout\": \"2019-01-01\"\r\n"
				+ "        }"
				+ "}";

		Response resp = given()
				.baseUri(Constants.URL)
					.contentType("application/json")
					.body(payload).
				when().post("/booking").then().extract().response();

		JsonPath jsp =  new JsonPath(resp.asString());

		id = Integer.parseInt(jsp.getString("bookingid"));

		System.out.println("New id is - " + jsp.getString("bookingid"));

		assertTrue(jsp.getString("booking.firstname").equals(firstname));

	}

	//Validate Above booking is created fetching via booking id
	@Test
	public void Test2_GetBooking() {

		given()
		.baseUri(Constants.URL)
		.contentType("application/json")
		.get("/booking/" + id).then().statusCode(200);
	}


	//Validate Update of Booking
	@Test
	public void Test3_UpdateBooking() {

		String payload = "{\n" + "\"depositpaid\":true,\"firstname\":\"" + firstname + "\",\"additionalneeds\":\"Lunch\",\"totalprice\":111,\"lastname\":\"auto1\","
				+ "\"bookingdates\": {\r\n"
				+ "            \"checkin\": \"2018-01-01\",\r\n"
				+ "            \"checkout\": \"2019-01-01\"\r\n"
				+ "        }"
				+ "}";

		Response resp = given()
		.baseUri(Constants.URL)
		.contentType("application/json")
		.header("Accept","application/json")
		.header("Cookie","token=" + common.getToken())
		.body(payload).when().put("/booking/" + id).then().statusCode(200).extract().response();

		JsonPath jsp =  new JsonPath(resp.asString());

		assertTrue(jsp.getString("additionalneeds").equals("Lunch"));

	}

	//Validate Delete Booking
	@Test
	public void Test4_DeleteBooking() {

		given()
		.baseUri(Constants.URL)
		.contentType("application/json")
		.header("Cookie","token=" + common.getToken())
		.delete("/booking/" + id).then().statusCode(201)
		.log().all();
	}


	@After
	public void tearDown() throws Exception {
	}

}
