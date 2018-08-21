package scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class flipkartsamsung {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
driver.get("https://www.flipkart.com/");
driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
driver.findElement(By.xpath("//*[@id='container']/div/header/div[1]/div/div/div/div[2]/form/div/div[1]/div/input")).sendKeys("samsung");
driver.findElement(By.cssSelector("#container > div > header > div._1tz-RS > div > div > div > div._1NLCcM > form > div > div.col-1-12 > button")).click();
driver.findElement(By.cssSelector("#container > div > div.t-0M7P._2doH3V > div._3e7xtJ > div > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child(1) > div > div._3ywJNQ > div:nth-child(4)")).click();
for(int i=2;i<7;i++){
	String s1= driver.findElement(By.cssSelector("#container > div > div.t-0M7P._2doH3V > div._3e7xtJ > div > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child("+i+") > div > div > div > a > div._1-2Iqu.row > div.col.col-5-12._2o7WAb > div._6BWGkk > div > div._1vC4OE._2rQ-NK")).getText();
	int price1= Integer.parseInt(s1.substring(1).replace("," , ""));
	System.out.println("price of" +i+ "element is:" +price1);
	String s2= driver.findElement(By.cssSelector("#container > div > div.t-0M7P._2doH3V > div._3e7xtJ > div > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child("+(i+1)+") > div > div > div > a > div._1-2Iqu.row > div.col.col-5-12._2o7WAb > div._6BWGkk > div > div._1vC4OE._2rQ-NK")).getText();
	int price2= Integer.parseInt(s2.substring(1).replace("," , ""));
	System.out.println ("price of  " +(i+1)+ " element is:"  +price2);
	if (price1<=price2){
		System.out.println("move to next element");
	continue;
	}
	else{
		System.out.println("invalid");
	}
	
	
}
//driver.close();

	}
}
