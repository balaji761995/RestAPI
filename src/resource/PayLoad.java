package resource;

import io.restassured.path.json.JsonPath;

public class PayLoad {
	
	public static String addPlace()
	{
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Bangalore\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
	}
	
	public static String newBook(String isbn, String asile)
	{
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Python\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+asile+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
	}
	public static String authCred()
	{
		return "{ \"username\": \"myuser\", \"password\": \"mypassword\" }";
	}
	
	public static JsonPath jsonToString(String msg)
	{
		JsonPath js = new JsonPath(msg);
		return js;
	}

}
