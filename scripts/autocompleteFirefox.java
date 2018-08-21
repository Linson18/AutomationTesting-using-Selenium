package scripts;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

public class autocompleteFirefox {

	public static void main(String[] args) throws InterruptedException, IOException {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		
		driver.get("https://jqueryui.com/");
		Actions actions = new Actions(driver);//use actions for on mouse over actions
		driver.manage().window().maximize();
		WebElement autoComplete = driver.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(2) > a"));
		autoComplete.click();
		Thread.sleep(1000);
		driver.switchTo().frame(driver.findElement(By.cssSelector("#content > iframe")));
		WebElement tag = driver.findElement(By.cssSelector("#tags"));
		tag.sendKeys("j");
		Thread.sleep(1000);
		 actions.moveToElement(tag).perform();
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\Screenshots\\File1.jpg"));
		
		List<WebElement> options = driver.findElements(By.cssSelector("#ui-id-1"));
		Iterator<WebElement> i = options.iterator();
		while(i.hasNext()) {
		    WebElement row = i.next();
		    System.out.println(row.getText());
		}
		

		List<WebElement> optionsToSelect = driver.findElements(By.tagName("li"));
		for(WebElement option : optionsToSelect){
	        if(option.getText().equals("Java")) {
	            option.click();
	        }
	     
	        scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("D:\\Screenshots\\File2.jpg"));
			
			//driver.close();

	}

}
}
