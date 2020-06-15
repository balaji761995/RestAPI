import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class OAuthAPI {
	
	
	@Test
	public void oauthTest()
	{
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0gF1FzFjCmgg-M1Rzk4LGJ9V_2bi6jqWKTjlEhUwwcyiSFlyi4WBS7HEIKdDF7l58c9VKsnv-_moMAZqSLUuTiA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
		String partialURl = url.split("code=")[1];
		String code = partialURl.split("&scope")[0];
		
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code",code).queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(accessTokenResponse);
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		String finalResponse = given().contentType("application/json").queryParam("access_token", accessToken)
		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(finalResponse);
	}

}
