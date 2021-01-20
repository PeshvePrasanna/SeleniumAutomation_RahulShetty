package crud_operations;

import files.payload1;
import io.restassured.path.json.JsonPath;

public class Complex_JsonParser {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(payload1.coursePrice());
		// Print Number of Courses (i.e 3)
		int count = js.getInt("courses.size()");
		System.out.println(count);
		// Print purchase amount
		int purchase_amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchase_amount);
		// Print title of second course
		String secondCoursename = js.get("courses[1].title");
		System.out.println(secondCoursename);
		System.out.println("***************----------***************");
		// Print all course titles and respective prices
		for (int i = 0; i < count; i++) {
			// System.out.println(js.get("courses["+i+"].title").toString());
			// System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(
					js.get("courses[" + i + "].title") + " - " + js.get("courses[" + i + "].price").toString());
		}
		// Print number of copies sold for RPA course
		System.out.println("***************----------***************");
		for (int j = 0; j < count; j++) {
			String courseTitles = js.getString("courses[" + j + "].title");
			if (courseTitles.equalsIgnoreCase("java")) {
				System.out.println("Number of copies sold for JAVA is " + js.getInt("courses[" + j + "].copies"));
				break;
			}

		}
		System.out.println("***************----------***************");
		int sumOfPrices = 0;
		for (int k = 0; k < count; k++) {
			int coursePrice = js.get("courses[" + k + "].price");
			int copiesSold = js.get("courses[" + k + "].copies");
			int totalAmount = copiesSold * coursePrice;
			sumOfPrices = totalAmount + sumOfPrices;
		}
		System.out.println("Total sum of prices of all the courses is = " + sumOfPrices);
	}

}
