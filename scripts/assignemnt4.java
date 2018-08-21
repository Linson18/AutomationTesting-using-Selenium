package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class assignemnt4 {

	public static void main(String[] args) throws InterruptedException {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);// implementation
																		// of an
																		// interface

		driver.get("https://www.annauniv.edu/");
		driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td[5]/div/a")).click();
		Actions builder= new Actions(driver);
		WebElement mainMenu = driver.findElement(By.name("link13"));
		//Create object 'action' of an Actions class
		Actions actions = new Actions(driver);
		//To mouseover on main menu
		Thread.sleep(500);
		actions.moveToElement(mainMenu);
		//Sub Menu
		WebElement subMenu = driver.findElement(By.id("menuItemHilite33"));
		//To mouseover on sub menu
		Thread.sleep(1500);
		actions.moveToElement(subMenu);
		//build() method is used to compile all the actions into a single step 
		Thread.sleep(500);

		actions.click().build().perform();
		
		//WebElement title  = driver.findElement(By.Attribute)
		
		driver.close();

	}

}
