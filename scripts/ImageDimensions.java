package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImageDimensions {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("http://www.agiletestingalliance.org/");
		driver.manage().window().maximize();
		
		int width = driver.findElement(By.cssSelector("body > div:nth-child(3) > div.latest_eventsbanner > a:nth-child(3) > img")).getSize().getWidth();
		
		int height = driver.findElement(By.cssSelector("body > div:nth-child(3) > div.latest_eventsbanner > a:nth-child(3) > img")).getSize().getHeight();
		
		System.out.println("Image dimensions are: " + width +" x " + height );
		driver.close();

	}

}
