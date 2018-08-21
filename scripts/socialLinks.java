package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class socialLinks {

	public static void main(String[] args) {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);// implementation
																		// of an
																		// interface

		driver.get("http://www.agiletestingalliance.org/");
		// String link1 =
		// driver.findElement(By.xpath("/html/body/footer/div/a[1]")).getAttribute("href");
		// System.out.println("link1"+ link1);
		
		//WebElement ws =driver.findElement(By.xpath("/html/body/footer/div/a[1]"));
		//System.out.println(ws.getLocation());
		//System.out.println();
		//System.out.println(ws.getAttribute("class"));
		

		for (int i = 1; i < 5; i++) {
			String link1 = driver.findElement(
					By.xpath("/html/body/footer/div/a[" + i + "]"))
					.getAttribute("href");
			System.out.println("Link "+i+": " + link1 );
		}
		driver.close();

	}

}
