package resource;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {
	
	@Test
	public void authTest()
	{
		SessionFilter session = new SessionFilter();
		RestAssured.baseURI="http://localhost:8080/";
		
		//Creating new session and fetching session id using session filter
		
		String response = given().log().all().header("Content-Type", "application/json").body("{ \"username\": \"balaji761995\", \"password\": \"Jun2020@\" }")
		.filter(session).when().log().all().post("rest/auth/1/session ").then().log().all().extract().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String sessionId = js.getString("session.value");
		System.out.println(sessionId);
		
		//Creating new issue in the Jira and extracting issue id from JSON response
		
		String msg = given().log().all().headers("Content-Type", "application/json")
		.body("{"+
			    "\"fields\": {"+
			       "\"project\":{"+
			          "\"key\": \"TP\""+
			       "},"+
			       "\"summary\": \"jIRA Issue 01\","+
			       "\"description\": \"Creating my first bug\","+
			       "\"issuetype\": {"+
			          "\"name\": \"Bug\""+
			       "}"+
			   "}}").filter(session).when().log().all().post("/rest/api/2/issue").then().log().all().assertThat().statusCode(201).extract().asString();
		JsonPath js1 = resource.PayLoad.jsonToString(msg);
		String id = js1.getString("id");
		System.out.println(id);
		
		//Adding comments to the issue using the issue id
		
		String addCommentResponse = given().log().all().pathParam("key", id).header("Content-Type","application/json")
		.body("{\r\n" +

"    \"body\": \"first comment\",\r\n" +

"    \"visibility\": {\r\n" +

"        \"type\": \"role\",\r\n" +

"        \"value\": \"Administrators\"\r\n" +

"    }\r\n" +

"}").filter(session).when().log().all().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract().asString();
		
		System.out.println(addCommentResponse);
		
		JsonPath js4 = new JsonPath(addCommentResponse);
		String comment = js4.getString("body");
		System.out.println(comment);
		
		
		//Adding attachments to the issue
		
		given().log().all().header("X-Atlassian-Token","no-check").filter(session).pathParam("key", id)
		.header("Content-Type","multipart/form-data").multiPart("file", new File("testFile")).when().log().all().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
	}

}
