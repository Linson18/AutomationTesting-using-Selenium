package scripts;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AmazonTestNG {
	private WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	    
	    driver.manage().window().maximize();
	    java.util.Date date= new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - " + new Timestamp(date.getTime()));
	}
	
	@DataProvider(name = "Cycle1") //name = "cycle1" ------ we can use multiple dataProviders which allows us to read from multiple files
	public Object[][] createData1(){
		Object[][] retObjArr=getTableArray("test\\resources\\data\\FlipkartDriver.xls", "Flipkart", "Hello");
        return(retObjArr);
	}
	
	@Test (dataProvider = "Cycle1") 
	public void testDataProviderExample(String ProductName) throws InterruptedException 
            {
			driver.get("https://www.amazon.in/");//website link
			
			
			Actions action = new Actions(driver);
			WebElement mainmenu =  driver.findElement(By.xpath("//*[@id='nav-link-shopall']"));
			WebElement submenu = driver.findElement(By.xpath("//*[@id='nav-flyout-shopAll']/div[2]/span[6]"));
			action.moveToElement(mainmenu).perform();
			//Thread.sleep(5000);
			action.moveToElement(submenu).perform();
			//Thread.sleep(5000);			 
			action.moveToElement(driver.findElement(By.xpath("//*[@id='nav-flyout-shopAll']/div[3]/div[6]/div[1]/div/a[1]/span"))).click().build().perform();
			//Thread.sleep(3000);

			WebElement searchBox =  driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
			searchBox.clear();
			searchBox.sendKeys(ProductName);
			driver.findElement(By.xpath("//*[@id='nav-search']/form/div[2]/div/input")).click();
			String ActualTitle = driver.findElement(By.cssSelector("#bcKwText > span")).getText().replace("\"", "");
			assertEquals(ActualTitle, ProductName);
			
			try{
			switch(ProductName)
			{
				case "Sony": 
					for(int i = 0; i<=3; i++ )
					{
						int j = i+1;
					String tempProduct1 = driver.findElement(By.xpath("//*[@id='result_"+i+"']/div/div[5]/div[1]/a/span[2]")).getText();
					int tp1price = Integer.parseInt(tempProduct1.substring(2).replace(",", ""));
					System.out.println("Price of product "+(i+1)+" is: "+ tp1price);
					String tempProduct2 = driver.findElement(By.xpath("//*[@id='result_"+j+"']/div/div[5]/div[1]/a/span[2]")).getText();
					int tp2price = Integer.parseInt(tempProduct2.substring(2).replace(",", ""));
					System.out.println("Price of product "+(j+1)+" is: "+ tp2price);
					if(tp1price<= tp2price) {
									System.out.println("Moving to next product");
									continue;
								} else {
									System.out.println("Sorting failed!!!!!");
								}
					}
					break;
					
					case "Samsung":
					for(int i = 0; i<=3; i++ )
					{
						int j = i+1;
					String tempProduct1 = driver.findElement(By.xpath("//*[@id='result_"+i+"']/div/div[6]/div[1]/a/span[2]")).getText();
					int tp1price = Integer.parseInt(tempProduct1.substring(2).replace(",", ""));
					System.out.println("Price of product "+(i+1)+" is: "+ tp1price);
					String tempProduct2 = driver.findElement(By.xpath("//*[@id='result_"+j+"']/div/div[6]/div[1]/a/span[2]")).getText();
					int tp2price = Integer.parseInt(tempProduct2.substring(2).replace(",", ""));
					System.out.println("Price of product "+(j+1)+" is: "+ tp2price);
					if(tp1price<= tp2price) {
									System.out.println("Moving to next product");
									continue;
								} else {
									System.out.println("Sorting failed!!!!!");
								}
					}
					break;
		            }
	
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Element not found");
			}
						
            }


	@AfterClass//can use @AfterTest
	//used for closing driver and obtaining timestamp
	public void tearDown(){
		driver.quit();
		 
		java.util.Date date= new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - " + new Timestamp(date.getTime()));
	 } 
	//findElementBy
	private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
		  
	//checks whether author name is correct
	 public boolean verifyTextPresent(String value)
    {
    boolean x = driver.getPageSource().contains(value);
    return x;
    }
    
	//method used for all tables to read data from them - utility
    public String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
        String[][] tabArray=null;
        try{
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName);
            
            int startRow,startCol, endRow, endCol,ci,cj;
            
            Cell tableStart=sheet.findCell(tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        }
        catch (Exception e)    {
            System.out.println("error in getTableArray()");
            
        }

        return(tabArray);
    }

}
