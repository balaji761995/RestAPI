import org.testng.annotations.Test;

import POJO_class.CourseList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class OAuthAPI {
	
	
	@Test
	public void oauthTest()
	{
		String url = "https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2F4AHN6fI0cLqAqFK81nN8ZlRMF7MnUZK61l5mvZxLspY6zkyD5Xv9dlgpH-mW_mSUjcJP5VjeQ7nmONZxwKCs-sQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partialURl = url.split("code=")[1];
		String code = partialURl.split("&scope")[0];
		
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code",code).queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(accessTokenResponse);
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		CourseList finalResponse = given().contentType("application/json").queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(CourseList.class);
		
		System.out.println(finalResponse.getInstructor());
	}

}
