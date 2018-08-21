package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Wikipedia {

	public static void main(String[] args) {
		
		File pathToBinary = new File("C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		WebDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);//implementation of an interface
		
		//driver.get("https://www.google.co.in");
		//driver.findElement(By.id("lst-ib")).sendKeys("Wikipedia");
		driver.get("http://www.wikipedia.org");
		//driver.findElement(By.name("btnK")).click();
		//driver.findElement(By.linkText("Wikipedia")).click();
		driver.findElement(By.id("js-link-box-en")).click();
		driver.findElement(By.id("searchInput")).sendKeys("Selenium Software");
		driver.findElement(By.id("searchButton")).click();
		driver.findElement(By.linkText("Selenium (software)")).click();
		System.out.println("Heading is: " + driver.findElement(By.id("firstHeading")).getText());
		System.out.println("Title is: " + driver.getTitle());
	
	}

}
