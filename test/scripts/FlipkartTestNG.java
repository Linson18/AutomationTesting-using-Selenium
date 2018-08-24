package scripts;

import java.io.File;
import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

public class FlipkartTestNG {

	private WebDriver driver;
	SoftAssert s_assert = new SoftAssert();
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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
	public void testDataProviderExample(String ProductName) 
            {
			//System.out.println("*****************  3 *************************");
			driver.get("https://www.flipkart.com/");//website link
			try {
				driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
				} catch (Exception e) {
				//e.printStackTrace();
				System.out.println("popup did not appear");
				}

			
			//System.out.println("*****************  3.1 *************************");
			driver.findElement(By.xpath("//*[@id='container']/div/header/div[1]/div/div/div/div[2]/form/div/div[1]/div/input")).clear();
			driver.findElement(By.xpath("//*[@id='container']/div/header/div[1]/div/div/div/div[2]/form/div/div[1]/div/input")).sendKeys(ProductName);
			driver.findElement(By.xpath("//*[@id='container']/div/header/div[1]/div/div/div/div[2]/form/div/div[2]/button")).click();
			s_assert.assertTrue(verifyTextPresent(ProductName));
			//s_assert.assertTrue(verifyTextPresent(Price));
			//s_assert.assertTrue(verifyTextPresent(Rating));
			
			
			try{
			switch(ProductName)
			{
				case "Sony": 
				case "Samsung":
					for(int i = 2; i<=5; i++ )
					{
						int j = i+1;
					String tempProduct1 = driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["+i+"]/div/div/div/a/div[3]/div[2]/div[1]/div/div[1]")).getText();
					
					int tp1price = Integer.parseInt(tempProduct1.substring(1).replace(",", ""));
					System.out.println("Price of product "+(i-1)+" is: "+ tp1price);
					String tempProduct2 = driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["+j+"]/div/div/div/a/div[3]/div[2]/div[1]/div/div[1]")).getText();
					int tp2price = Integer.parseInt(tempProduct2.substring(1).replace(",", ""));
					System.out.println("Price of product "+(j-1)+" is: "+ tp2price);
					if(tp1price<= tp2price) {
									System.out.println("Moving to next product");
									continue;
								} else {
									System.out.println("Sorting failed!!!!!");
								}
					}
					break;
					
				case "Redmi":
				case "Oneplus":
					for(int i = 2; i<=5; i++ )
					{
						int j = i+1;
					String tempProduct1 = driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["+i+"]/div/div/div/a/div[2]/div[2]/div[1]/div/div")).getText();
					
					int tp1price = Integer.parseInt(tempProduct1.substring(1).replace(",", ""));
					System.out.println("Price of product "+(i-1)+" is: "+ tp1price);
					String tempProduct2 = driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["+j+"]/div/div/div/a/div[2]/div[2]/div[1]/div/div")).getText();
					int tp2price = Integer.parseInt(tempProduct2.substring(1).replace(",", ""));
					System.out.println("Price of product "+(j-1)+" is: "+ tp2price);
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
//}
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
