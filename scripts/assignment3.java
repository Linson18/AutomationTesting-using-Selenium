package scripts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class assignment3 {

	public static void main(String[] args) {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.get("http://www.agiletestingalliance.org/");
		
		List<WebElement> links = driver.findElements(By.xpath("/html/body/footer/div/a"));//stores all elements with similar xpath in list of webelements
		//to print elements using for each loop
		for(WebElement web : links)
		{
			System.out.println(web.getAttribute("href"));
		}

		driver.close();


	}

}
