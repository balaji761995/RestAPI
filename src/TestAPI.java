import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resource.PayLoad;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").body(PayLoad.addPlace())
				.when().log().all().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		given().log().all().queryParam("key", "qaclick123").body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\"70 Summer walk, Columbia\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"").when().log().all().put("/maps/api/place/update/json").then().log().all()
				.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")); 
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when().log().all()
			.get("/maps/api/place/get/json").then().log().all().assertThat().body("address", equalTo("70 Summer walk, California"));
	}
}