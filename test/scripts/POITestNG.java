package scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class POITestNG {

	WebDriver driver;
	
	@SuppressWarnings("deprecation")
	public static String[][] getExcelData(String fileName, String sheetName) throws IOException {//why io exception
	String[][] arrayExcelData = null;
	Workbook wb = null;
	try {
	File file = new File(fileName);
	FileInputStream fs = new FileInputStream(file);
	// .xls
	if(fileName.substring(fileName.indexOf(".")).equals(".xlsx"))
	{
	//If it is .xlsx file then create object of XSSFWorkbook class
	wb = new XSSFWorkbook(fs);
	}
	else if(fileName.substring(fileName.indexOf(".")).equals(".xls"))
	{
	//If it is .xls file then create object of HSSFWorkbook class
	wb = new HSSFWorkbook(fs);
	}
	Sheet sh = wb.getSheet(sheetName);

	int totalNoOfRows = sh.getPhysicalNumberOfRows();
	int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();
	 
	System.out.println("totalNoOfRows="+totalNoOfRows+","+ " totalNoOfCols="+totalNoOfCols);
	arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
	for (int i= 1 ; i <= totalNoOfRows-1; i++) 
	{
	for (int j=0; j <= totalNoOfCols-1; j++) {
	sh.getRow(i).getCell(j).setCellType(1);// FETCHING FROM 1ST ROW AND 0TH COLUMN OF EXCEL SHEET
	arrayExcelData[i-1][j] = sh.getRow(i).getCell(j).getStringCellValue().toString();// PLACING VALUE OBTAINED ABOVE IN arrayExcelData array [0][0]
	}
	}
	} 
	catch (Exception e) {
	System.out.println("error in getExcelData()");
	}
	return arrayExcelData;
	}

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	    
	    driver.manage().window().maximize();
	    java.util.Date date= new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - " + new Timestamp(date.getTime()));
	}
	
	@DataProvider(name = "DP")
    public Object[][] createData1() throws IOException {
        Object[][] retObjArr=getExcelData("test\\resources\\data\\movie_POI.xlsx","MovieData");//add excel file details
        System.out.println("*****************  2 *************************");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP") //make changes as per requirement
	public void testDataProviderExample(String movieTitle, String directorName, String moviePlot, String actorName) throws Exception {
	System.out.println("*****************  3 *************************");
	driver.get("http://www.imdb.com/");
	System.out.println("*****************  3.1 *************************");
	driver.findElement(By.id("navbar-query")).sendKeys(movieTitle);
	driver.findElement(By.id("navbar-submit-button")).click();
	
	driver.findElement(By.linkText(movieTitle)).click();
	 
	if(verifyDirectorName(directorName))
	System.out.println("Found Dir Name: " + directorName);
	Assert.assertTrue(verifyDirectorName(directorName));
	 
if(verifyPlot(moviePlot))
	System.out.println("Found Movie Plot: " + moviePlot);
	Assert.assertTrue(verifyPlot(moviePlot));
	 
	if(verifyActorName(actorName))
	System.out.println("Found Actor Name: " + actorName);
	Assert.assertTrue(verifyActorName(actorName));
	 

	}

	@AfterClass
    public void tearDown(){
		driver.quit();
 
		java.util.Date date= new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - " + new Timestamp(date.getTime()));
    } 
 
public boolean verifyDirectorName(String value)
    {
    boolean x = (driver.findElement(By.linkText(value)).getText()).equals(value);
    return x;
    }
 
public boolean verifyPlot(String value)
{
boolean x = (driver.findElement(By.cssSelector("#title-overview-widget > div.plot_summary_wrapper > div.plot_summary > div.summary_text")).getText()).equals(value);
		return x;
}

public boolean verifyActorName(String value)
{
boolean x = (driver.findElement(By.linkText(value)).getText()).equals(value);
return x;
}


private boolean isElementPresent(By locator) 
{
	try {
	driver.findElement(locator);
	return true;
	} catch (NoSuchElementException e) 
	{
	return false;
	}
}
}
