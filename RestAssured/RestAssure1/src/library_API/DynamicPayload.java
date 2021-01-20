package library_API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload1;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicPayload {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String respBody = given().log().all().header("Content-Type", "application/json")
				.body(payload1.AddBook(isbn, aisle)).when().post("/Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println("Response body is:");
		System.out.println(respBody);
		// JsonPath js1 = new JsonPath(respBody);

		JsonPath js1 = payload1.reusableMethods(respBody);
		String bookID = js1.getString("ID");
		System.out.println(bookID);
	}
	@Test(dataProvider = "DeleteIds")
	public void deleteBook(String bookID) {
		RestAssured.baseURI = "http://216.10.245.166";
		String respBody = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n"
						+ "\"ID\" : \""+bookID+"\"\r\n"
						+ "} \r\n"
						+ "").when().post("/Library/DeleteBook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println("Data is deleted:");
		System.out.println(respBody);
		
	}

	@DataProvider(name = "BooksData")
	public Object[][] getBooksData() {
		return new Object[][] { { "axc", "312" }, { "bbv", "8887" }, { "nhj", "780" } };
	}
	
	@DataProvider(name= "DeleteIds")
	public Object[][] deleteID() {
		return new Object[][] {{"axc312"},{"bbv8887"},{"nhj780"}};
	}
	
}
