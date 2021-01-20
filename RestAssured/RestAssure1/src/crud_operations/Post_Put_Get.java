package crud_operations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Post_Put_Get {

	public static void main(String[] args) throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String respBody = given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Administrator\\Desktop\\addPlace.json")))).when()
						.post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response()
				.asString();
		System.out.println(respBody);
		JsonPath js = new JsonPath(respBody);
		String placeid = js.getString("place_id");
		System.out.println(placeid);
		String newAddress = "Huntsville Alabama, United States";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeid + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		String updatedRespBody = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
				.response().asString();
		JsonPath js1 = new JsonPath(updatedRespBody);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);

	}

}
