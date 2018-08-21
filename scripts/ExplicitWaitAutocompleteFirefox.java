package scripts;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitAutocompleteFirefox {

	public static void main(String[] args) throws InterruptedException {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);

		driver.get("https://jqueryui.com/");
		//driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Actions actions = new Actions(driver);// use actions for on mouse over
												// actions
		driver.manage().window().maximize();
		WebElement autoComplete = driver
				.findElement(By
						.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(2) >a "));
		autoComplete.click();
		// Thread.sleep(1000);
		driver.switchTo().frame(driver.findElement(By.cssSelector("#content > iframe")));
		WebElement List = driver.findElement(By.cssSelector("#tags"));//of searchbox
		List.sendKeys("j");
		WebElement AutoList = driver.findElement(By.id("ui-id-1"));//list of options box
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(AutoList));

		List<WebElement> options = driver.findElements(By.cssSelector("#ui-id-1"));//list of options - individual
		Iterator<WebElement> i = options.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			System.out.println(row.getText());
		}

		List<WebElement> optionsToSelect = driver
				.findElements(By.tagName("li"));
		for (WebElement option : optionsToSelect) {
			if (option.getText().equals("Java")) {
				option.click();
			}
		}
		
		driver.close();

	}
}
