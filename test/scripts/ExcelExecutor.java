package scripts;

import java.sql.Timestamp;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//file to execute
public class ExcelExecutor {
	WebDriver driver;
	Sheet excelSheet;
	
	//calls OpenBrowser Class file
	@BeforeTest
	public void setUp() throws Exception {
	java.util.Date date= new java.util.Date();
	System.out.println("\n\nExecution Log - Start Time - " + new Timestamp(date.getTime()));
	System.out.println("Launching google chrome with new profile..");
	System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\" + "chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize(); 
	}
	

    @Test
public void testLogin() throws Exception {
    	GetByObjectAndAct getAndAct = new GetByObjectAndAct(driver);//Calls GetByObjectAndAct class file
        //Read keyword sheet
        excelSheet = ReadExcelFileSheet.getExcelSheet("test\\resources\\data\\","TestCase.xlsx" , "Frameworksheet");//calls ReadExcelFileSheet.class file
      //Find number of rows in excel file
    int rowCount = excelSheet.getLastRowNum()-excelSheet.getFirstRowNum();
    //Create a loop over all the rows of excel file to read it
    for (int i = 1; i < rowCount+1; i++) {
    //Loop over all the rows
    Row row = excelSheet.getRow(i);
    //Check if the first cell contain a value, if yes, That means it is the new testcase name
    if(row.getCell(0).toString().length()==0){
    //Print testcase detail on console
    System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
    row.getCell(3).toString()+"----"+ row.getCell(4).toString());
   
    //Call perform function to perform operation on UI
    try {
   
    getAndAct.performAction(row.getCell(1).toString(), row.getCell(2).toString(),
    row.getCell(3).toString(), row.getCell(4).toString());
  
    }
    catch(Exception e){
          System.out.println("fail =" + e.getMessage());
    }//end catch
        }//end if
    else{
    //Print the new  testcase name when it started
    System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
    }//end else
    }//end for
}
	
	@AfterClass
    public void tearDown(){
   
    driver.quit();
java.util.Date date= new java.util.Date();
System.out.println("\n\nExecution Log - End Time - " + new Timestamp(date.getTime()));
    }

}
