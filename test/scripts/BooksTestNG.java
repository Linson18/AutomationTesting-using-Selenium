package scripts;

import java.io.File;
import java.sql.Timestamp;
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

public class BooksTestNG {

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
		Object[][] retObjArr=getTableArray("test\\resources\\data\\book_data1.xls", "DataPool", "bookTestData1");
		System.out.println("*****************  2 *************************");
        return(retObjArr);
	}
	
	@Test (dataProvider = "Cycle1") 
	public void testDataProviderExample(String booktitle, String authorname) 
            {
			System.out.println("*****************  3 *************************");
			driver.get("http://www.barnesandnoble.com/");//website link
			//to handle website popup 
			try {
				driver.findElement(By.className("icon-close-modal")).click();
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("popup did not appear");
				}
			
			System.out.println("*****************  3.1 *************************");
			driver.findElement(By.id("searchBarBN")).clear();
			driver.findElement(By.id("searchBarBN")).sendKeys(booktitle);
			driver.findElement(By.className("icon-search-2")).click();
			driver.findElement(By.linkText(booktitle)).click();
			s_assert.assertTrue(verifyTextPresent(authorname));
			
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
