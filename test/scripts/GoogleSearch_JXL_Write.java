package scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleSearch_JXL_Write {

WebDriver driver;
 
public String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
        String[][] tabArray=null;
        try{
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName);
            
            int startRow,startCol, endRow, endCol,ci,cj;
            
            //startRow = 1
            //startCol = 0
            //endRow = 7
            //endCol = 5
            Cell tableStart=sheet.findCell(tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
             
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            //String [5][4]
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

 
@DataProvider(name = "DP1")
    public Object[][] createData1() throws IOException {
        Object[][] retObjArr=getTableArray("test\\resources\\data\\SearchKeywords.xls","Superheroes","Search");
System.out.println("*****************  2 *************************");
        return(retObjArr);
    }
    
public void getScreenshot(String fname) throws IOException{
File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
org.apache.commons.io.FileUtils.copyFile(scrFile, new File("test\\resources\\screenshots\\"+fname+".jpg"));
}
 
@Test (dataProvider = "DP1") 
public void testDataProviderExample(String SearchKeyword,String results) throws Exception {
driver.get("https://www.google.co.in/");
System.out.println(SearchKeyword);
driver.findElement(By.id("lst-ib")).sendKeys(SearchKeyword);
driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
WebDriverWait wait = new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
getScreenshot("Before");
results = driver.findElement(By.id("resultStats")).getText();
results = results.substring(results.indexOf("About") + 6, results.indexOf(" results"));
 
writeToExcel("test\\resources\\data\\SearchKeywords.xls","Superheroes",SearchKeyword,results);
}
 
@AfterClass
    public void tearDown(){
driver.quit();
 
java.util.Date date= new java.util.Date();
System.out.println("\n\nExecution Log - End Time - " + new Timestamp(date.getTime()));
    }
 
public void writeToExcel(String fileName,String sheetName,String searchKeyword,String resultCount) throws BiffException, RowsExceededException, WriteException{
try {
Workbook workbook = Workbook.getWorkbook(new File(fileName));
WritableWorkbook writeableWB = Workbook.createWorkbook(new File(fileName),workbook);
WritableSheet sheet = writeableWB.getSheet(sheetName);
Cell keyCell = sheet.findCell(searchKeyword);
 
int keyRow = keyCell.getRow();
int keyCol = keyCell.getColumn();
Label label = new Label((keyCol+1), keyRow, resultCount);
sheet.addCell(label);
writeableWB.write();
writeableWB.close();
} catch (FileNotFoundException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}
}
 
public boolean verifyTextPresent(String value)
    {
    boolean x = driver.getPageSource().contains(value);
    return x;
    }
 
private boolean isElementPresent(By by) {
try {
driver.findElement(by);
return true;
} catch (NoSuchElementException e) {
return false;
}
}
}
