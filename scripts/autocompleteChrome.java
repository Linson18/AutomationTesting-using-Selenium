package scripts;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class autocompleteChrome {
	

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver","test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
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
