import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resource.PayLoad;

import static io.restassured.RestAssured.*;

public class LibraryAPI {
	
	public String id;
	
	@Test(dataProvider="bookData")
	public void addBook(String isbn, String asile)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().body(PayLoad.newBook(isbn,asile)).when().post("Library/Addbook.php").then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = new JsonPath(response);
		id = js.getString("ID");
		System.out.println(id);
	}

	@DataProvider
	public Object[][] bookData()
	{
		return new Object[][] {{"bhu","780"},{"yhn","543"}};
	}
	
	@Test
	public void delBook()
	{
		
	}
	
}
