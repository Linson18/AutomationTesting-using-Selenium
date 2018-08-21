package scripts;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class vodafonechrome {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.vodafone.in/home-mumbai");
		driver.manage().window().maximize();//to maximize window
		WebElement storeLocator = driver.findElement(By.id("ctl00_TM_liLocator"));
		storeLocator.click();
		
		Select dropDown = new Select(driver.findElement(By.name("ctl00$CU$ddlCircle")));
		List<WebElement> links = dropDown.getOptions();
		
		Iterator<WebElement> i = links.iterator();
		while(i.hasNext()) {
		    WebElement row = i.next();
		    System.out.println(row.getText());
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
