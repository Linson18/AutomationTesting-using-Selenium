package scripts;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class navigation {

	public static void main(String[] args) {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		//driver.get("https://www.google.co.in");
		driver.navigate().to("http://toolsqa.com/selenium-webdriver/browser-navigation-commands/");//navigate to specific webpage
		driver.navigate().back();//navigate one page back
		driver.navigate().forward();//navigate one page ahead
	}

}
