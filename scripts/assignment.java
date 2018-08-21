package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class assignment {

	public static void main(String[] args) throws InterruptedException {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);// implementation
																		// of an
																		// interface

		driver.get("https://www.google.com");
		WebElement ws = driver.findElement(By.id("lst-ib"));
		ws.sendKeys("Selenium");
		Thread.sleep(500);
		ws.clear();
		Thread.sleep(500);
		ws.sendKeys("Aditya Garg");
		Thread.sleep(500);
		ws.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(500);
		ws.sendKeys("g");
		Thread.sleep(500);
		ws.sendKeys(Keys.ENTER);
		
		driver.close();
		
		
		

	}

}
