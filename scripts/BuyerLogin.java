package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BuyerLogin {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver","test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://205.147.102.59:9001/");
		driver.manage().window().maximize();
		WebElement SignUp = driver.findElement(By.id("show_login"));
		SignUp.click();
		WebElement UserName = driver.findElement(By.cssSelector("#login_email"));
		UserName.sendKeys("linsonmiranda.97@gmail.com");
		Thread.sleep(1000);
		WebElement Password = driver.findElement(By.cssSelector("#login_password"));
		Password.sendKeys("Linson123");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#login_submit")).click();
		Thread.sleep(1000);
		WebElement Product = driver.findElement(By.cssSelector("#search"));
		Product.sendKeys("Cotton");
		driver.findElement(By.id("searchlisting")).click();
		WebElement prodElement1 = driver.findElement(By.id("featured_product featured_view"));
		prodElement1.click();
		
	}

}
