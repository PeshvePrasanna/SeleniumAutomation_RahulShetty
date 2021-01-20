package basiclevel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowser {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Automate\\Auomation\\src\\driverexecutables\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.airbnb.com");
		driver.manage().window().maximize();
		String currenturl = driver.getCurrentUrl();
		String PageTitle = driver.getTitle();
		System.out.println(PageTitle);
		System.out.println(currenturl);
		
		driver.quit();

	}

}
