package scripts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class screenshotmozilla {

	public static void main(String[] args) throws InterruptedException,
			IOException {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);// implementation
																		// of an
																		// interface

		driver.get("https://www.annauniv.edu/");
		driver.findElement(
				By.xpath("/html/body/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td[5]/div/a")).click();//of departments

		WebElement mainMenu = driver.findElement(By.name("link13"));//of faculty of civil engineering
		Actions actions = new Actions(driver);
		Thread.sleep(500);
		actions.moveToElement(mainMenu);
		// Sub Menu
		WebElement subMenu = driver.findElement(By.id("menuItemHilite33"));//department of ocean management
		Thread.sleep(1500);
		 actions.moveToElement(subMenu).perform();
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
		driver.close();

	}

}
