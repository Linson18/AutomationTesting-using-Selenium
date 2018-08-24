package scripts;

//package scripts;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CalculatorDataDriven {

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
        Object[][] retObjArr=getExcelData("test\\resources\\data\\Calculator.xlsx","Calculator");//add excel file details
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP") //make changes as per requirement
	public void testDataProviderExample(String firstNumber, String secondNumber,String Operation, String result) throws Exception {
	driver.get("https://www.ata123456789123456789.appspot.com");
	driver.findElement(By.id("ID_nameField1")).clear();
	driver.findElement(By.id("ID_nameField1")).sendKeys(firstNumber);
	driver.findElement(By.id("ID_nameField2")).clear();
	driver.findElement(By.id("ID_nameField2")).sendKeys(secondNumber);
	driver.findElement(By.xpath("//*[@id='gwt-uid-"+Operation+"']")).click();
	driver.findElement(By.id("ID_calculator")).click();
	WebDriverWait wait = new WebDriverWait(driver, 5);
	WebElement ResultBox = driver.findElement(By.id("ID_nameField3"));
	wait.until(ExpectedConditions.visibilityOf(ResultBox));
	String res = ResultBox.getAttribute("value");
	System.out.println("Result of calculation is: " + res);
	
	if(verifyResult(result))
		Assert.assertTrue(verifyResult(result));
	}
	@AfterClass
    public void tearDown(){
		driver.quit();
 
		java.util.Date date= new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - " + new Timestamp(date.getTime()));
    } 
 
public boolean verifyResult(String result)
    {
    boolean x = (driver.findElement(By.id("ID_nameField3")).getAttribute("value")).equals(result);
    return x;
    }
 
}
