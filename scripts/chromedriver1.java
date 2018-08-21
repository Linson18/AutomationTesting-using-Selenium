package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class chromedriver1 {
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		//driver.manage().window().maximize();
		driver.get("https://www.annauniv.edu/");
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td[5]/div/a")).click();
		Thread.sleep(50);
		WebElement mainMenu = driver.findElement(By.name("link13"));
		//Create object 'action' of an Actions class
		Actions actions = new Actions(driver);
		//To mouseover on main menu
		Thread.sleep(500);
		actions.moveToElement(mainMenu);
		//Sub Menu
		WebElement subMenu = driver.findElement(By.id("menuItemHilite33"));
		//To mouseover on sub menu
		Thread.sleep(500);
		actions.moveToElement(subMenu);
		//build() method is used to compile all the actions into a single step 
		Thread.sleep(500);

		actions.click().build().perform();
		//WebElement title  = driver.findElement(By.Attribute)
		
		//driver.close();


	}

}
