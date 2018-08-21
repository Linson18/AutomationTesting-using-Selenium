package scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitImplicit {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.co.in");
		// driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 5);//explicitWait
		WebElement searchbox = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("lst-i")));
		driver.findElement(By.id("lst-ib")).sendKeys("Selenium ide");
		driver.findElement(By.name("btnK")).click();
		driver.close();

	}

}
