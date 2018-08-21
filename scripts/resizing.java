package scripts;

import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.thoughtworks.selenium.Wait;

public class resizing {

	public static void main(String[] args) {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC6\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.get("https://www.google.co.in");
		driver.manage().window().maximize();//to maximize window
		
		driver.manage().window().setPosition(new Point(0, -3000));//to minimize window  indirectly by placing window out of screen space
		
		//specify size of window
		Dimension n = new Dimension(360,592); 
		//specify point where window should appear
		Point point=new Point(100, 100);
		driver.manage().window().setPosition(point);
		 driver.manage().window().setSize(n);
	}

}
