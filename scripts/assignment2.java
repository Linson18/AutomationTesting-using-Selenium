package scripts;


	import java.io.File;

	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxBinary;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxProfile;

	public class assignment2 {
		public static void main(String[] args) throws InterruptedException {
			File pathToBinary = new File(
					"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);// implementation
																			// of an
																			// interface

			driver.get("https://www.ata123456789123456789.appspot.com");
			WebElement input1 = 	driver.findElement(By.id("ID_nameField1"));
			input1.clear();
			Thread.sleep(500);
			input1.sendKeys("6");
			Thread.sleep(500);
			WebElement input2 = 	driver.findElement(By.id("ID_nameField2"));
			input2.clear();
			Thread.sleep(500);
			input2.sendKeys("16");
			Thread.sleep(500);
			WebElement operation = driver.findElement(By.id("gwt-uid-2"));
			operation.click();
			driver.findElement(By.id("ID_calculator")).click();
			Thread.sleep(500);
			WebElement res = driver.findElement(By.id("ID_nameField3"));
			Thread.sleep(500);
			System.out.println("Result of calculation is: " + res.getAttribute("value"));
			driver.close();

		}
}