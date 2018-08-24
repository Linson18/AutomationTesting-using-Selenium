package scripts;

import java.io.File;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MovieTestNG {
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
	
	@Test (dataProvider = "Cycle1") 
	public void MovieTest(String MovieName, String DirectorName, String MoviePlot, String ActorName){
		System.out.println("*****************  3 *************************");
		driver.get("https://www.imdb.com");
		WebElement searchBox =  driver.findElement(By.xpath("//*[@id='navbar-query']"));
		searchBox.clear();
		searchBox.sendKeys(MovieName);
		driver.findElement(By.xpath("//*[@id='navbar-submit-button']")).click();
		driver.findElement(By.linkText(MovieName)).click();
		s_assert.assertTrue(verifyTextPresent(DirectorName));
		s_assert.assertTrue(verifyTextPresent(MoviePlot));
		s_assert.assertTrue(verifyTextPresent(ActorName));
	}
	

	@DataProvider(name = "Cycle1") //name = "cycle1" ------ we can use multiple dataProviders which allows us to read from multiple files
	public Object[][] createData1(){
		Object[][] retObjArr=getTableArray("test\\resources\\data\\book_data1.xls", "Movies", "MoviesTables");
		System.out.println("*****************  2 *************************");
        return(retObjArr);
	}
	
	@AfterClass//can use @AfterTest
	//used for closing driver and obtaining timestamp
	public void tearDown(){
		driver.quit();
		 
		java.util.Date date= new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - " + new Timestamp(date.getTime()));
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
