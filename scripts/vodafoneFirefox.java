package scripts;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

public class vodafoneFirefox {

	public static void main(String[] args) throws InterruptedException {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.get("https://www.vodafone.in/home-mumbai");
		driver.manage().window().maximize();//to maximize window
		WebElement storeLocator = driver.findElement(By.id("ctl00_TM_liLocator"));
		storeLocator.click();
		
		Select dropDown = new Select(driver.findElement(By.name("ctl00$CU$ddlCircle")));
		List<WebElement> links = dropDown.getOptions();
		int itemCount = links.size();

		for(int l = 0; l < itemCount; l++)
		{
		    System.out.println(links.get(l).getText());
		}
		 
		dropDown.selectByVisibleText("Chennai");
		Thread.sleep(1000);
		
		dropDown = new Select(driver.findElement(By.name("ctl00$CU$ddlCircle")));
		dropDown.selectByVisibleText("Haryana");
		Thread.sleep(1000);
		 dropDown = new Select(driver.findElement(By.name("ctl00$CU$ddlCircle")));
		dropDown.selectByVisibleText("Mumbai");
		driver.close();
		
	}

}
