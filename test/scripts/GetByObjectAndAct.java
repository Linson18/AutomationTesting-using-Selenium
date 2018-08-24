package scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class GetByObjectAndAct {

		WebDriver driver;
		OpenBrowser browserobj;
		Actions action ;
		public GetByObjectAndAct(WebDriver driver){
		this.driver = driver;
		action = new Actions(this.driver);
		}
		public void performAction(String operation,String objectName,String objectType,String value) throws Exception{
		System.out.println("performing action");
		switch (operation.toUpperCase()) {

		case "CLICK":
		//Perform click
		driver.findElement(this.getByObject(objectName,objectType)).click();
		Thread.sleep(3000);
		break;
		case "SETTEXT":
		//Set text on control
		driver.findElement(this.getByObject(objectName,objectType)).clear();
		driver.findElement(this.getByObject(objectName,objectType)).sendKeys(value);
		break;
		case "GOTOURL":
		//Get url of application
		driver.get(value);
		break;
		case "GETTEXT":
		//Get text of an element
		String str = driver.findElement(this.getByObject(objectName,objectType)).getText();
		System.out.println(str);
		break;
		case "TIMEOUT":
		//Get url of application
		float sleeptime = Float.parseFloat(value);
		Thread.sleep((long)(sleeptime)*1000);
		break;
		case "MOUSEOVER"://for other menus
			action.moveToElement(driver.findElement(this.getByObject(objectName,objectType)));
			Thread.sleep(1000);
			break;
		case "BUILDACTION"://for last  menu
			action.click().build().perform();
			break;
		case "GETATTRIBUTE":
			String result = driver.findElement(this.getByObject(objectName,objectType)).getAttribute("value");
			System.out.println(result);
			break;
		default:
		break;
		}
		}
		/**
		* Find element BY using object type and value
		* * @param objectName
		* @param objectType
		* @return
		* @throws Exception
		*/
		private By getByObject(String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
		return By.xpath(objectName);
		}
		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
		return By.className(objectName);
		}
		//find by id
		else if(objectType.equalsIgnoreCase("ID")){
		return By.id(objectName);
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
		return By.name(objectName);
		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
		return By.cssSelector(objectName);
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
		return By.linkText(objectName);
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
		return By.partialLinkText(objectName);
		}else
		{
		throw new Exception("Wrong object type");
		}
		}
		}

