package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

public class tooltips {

	public static void main(String[] args) {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);// implementation
																		// of an
																		// interface

		driver.get("https://www.google.co.in");
		String toolTipText = driver.findElement(By.id("lst-ib")).getAttribute(
				"title");
		System.out.println(toolTipText);

		WebElement google = driver.findElement(By.tagName("img"));
		String actualTooltip = google.getAttribute("title");

		// Assert the tooltip's value is as expected
		System.out.println(actualTooltip);
	}
}
