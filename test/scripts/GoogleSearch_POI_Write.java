package scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleSearch_POI_Write {

WebDriver driver;
 
public static String[][] getExcelData(String fileName, String sheetName) throws IOException {
String[][] arrayExcelData = null;
Workbook wb = null;
try {
File file = new File(fileName);
FileInputStream fs = new FileInputStream(file);
if(fileName.substring(fileName.indexOf(".")).equals(".xlsx")){
    //If it is xlsx file then create object of XSSFWorkbook class
    wb = new XSSFWorkbook(fs);
} else if(fileName.substring(fileName.indexOf(".")).equals(".xls")){
//If it is xls file then create object of HSSFWorkbook class
wb = new HSSFWorkbook(fs);
}
Sheet sh = wb.getSheet(sheetName);

int totalNoOfRows = sh.getPhysicalNumberOfRows();
int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();
 
System.out.println("totalNoOfRows="+totalNoOfRows+", totalNoOfCols="+totalNoOfCols);
arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
for (int i= 1 ; i <= totalNoOfRows-1; i++) {
for (int j=0; j <= totalNoOfCols-1; j++) {
sh.getRow(i).getCell(j).setCellType(1);
arrayExcelData[i-1][j] = sh.getRow(i).getCell(j).getStringCellValue().toString();
}
}
} catch (Exception e) {
e.printStackTrace();
System.out.println("error in getExcelData()");
}
return arrayExcelData;
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
        Object[][] retObjArr=getExcelData("test\\resources\\data\\SearchKeywords.xlsx","Superheroes");
System.out.println("*****************  2 *************************");
        return(retObjArr);
    }
    
@Test (dataProvider = "DP1") 
public void testDataProviderExample(String SearchKeyword,String results) throws Exception {
driver.get("https://www.google.co.in/");
System.out.println(SearchKeyword);
driver.findElement(By.id("lst-ib")).sendKeys(SearchKeyword);
driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
WebDriverWait wait = new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
Thread.sleep(3000);
// getScreenshot(SearchKeyword);
results = driver.findElement(By.id("resultStats")).getText();
results = results.substring(results.indexOf("About") + 6, results.indexOf(" results"));
 
writeToExcel("test\\resources\\data\\SearchKeywords.xlsx","Superheroes",SearchKeyword,results);
}
 
// public void getScreenshot(String fname) throws IOException{
// File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
// org.apache.commons.io.FileUtils.copyFile(scrFile, new File("E:\\Selenium Docs\\"+fname+".png"));
// }
 
@AfterClass
    public void tearDown(){
driver.quit();
 
java.util.Date date= new java.util.Date();
System.out.println("\n\nExecution Log - End Time - " + new Timestamp(date.getTime()));
    } 
 
public void writeToExcel(String fileName,String sheetName,String searchKeyword,String resultCount){
Workbook wb = null;
try {
File file = new File(fileName);
FileInputStream fs = new FileInputStream(file);
if(fileName.substring(fileName.indexOf(".")).equals(".xlsx")){
    //If it is xlsx file then create object of XSSFWorkbook class
    wb = new XSSFWorkbook(fs);
} else if(fileName.substring(fileName.indexOf(".")).equals(".xls")){
//If it is xls file then create object of XSSFWorkbook class
wb = new HSSFWorkbook(fs);
}
Sheet sh = wb.getSheet(sheetName);
int totalNoOfRows = sh.getPhysicalNumberOfRows();
// int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();
 
for (int i= 1 ; i <= totalNoOfRows-1; i++) {
if (sh.getRow(i).getCell(0).getStringCellValue().equals(searchKeyword)) {
//row = 1, col = 1
sh.getRow(i).createCell(1).setCellType(Cell.CELL_TYPE_STRING);
sh.getRow(i).createCell(1).setCellValue(resultCount);
break;
}
}
FileOutputStream fos=new FileOutputStream(fileName);
wb.write(fos);
fos.close();
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
