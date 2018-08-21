package scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class flipkart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
driver.get("https://www.flipkart.com/");
driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
driver.findElement(By.cssSelector("body > div.mCRfo9 > div > div > button")).click();
driver.manage().window().maximize();
driver.findElement(By.xpath("//*[@id='container']/div/header/div[1]/div/div/div/div[2]/form/div/div[1]/div/input")).sendKeys("Sony");
driver.findElement(By.xpath("//*[@id='container']/div/header/div[1]/div/div/div/div[2]/form/div/div[2]/button")).click();
driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div[3]")).click();
for(int i = 2; i<=6; i++ )
{
String tempProduct1 = driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["+i+"]/div/div/div/a/div[3]/div[2]/div[1]/div/div[1]")).getText();
int tp1price = Integer.parseInt(tempProduct1.substring(1).replace(",", ""));
System.out.println("Price of product "+(i-1)+" is: "+ tp1price);
String tempProduct2 = driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["+(i+1)+"]/div/div/div/a/div[3]/div[2]/div[1]/div/div[1]")).getText();
int tp2price = Integer.parseInt(tempProduct2.substring(1).replace(",", ""));
System.out.println("Price of product "+i+" is: "+ tp2price);
if(tp1price<= tp2price) {
				System.out.println("Moving to next product");
				continue;
			} else {
				System.out.println("Sorting failed!!!!!");
			}
		}
driver.close();
	}
}