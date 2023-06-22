package testLayer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePackage.BaseHRMClass;
import pompackage.PomLogin;
import testdata.ExcelSheet;

public class LoginTest extends BaseHRMClass {
	
	PomLogin log;
	public LoginTest()
	{
		//Calling constructor of parent class
		super(); //it will read all property of config.property
	}
	
	@BeforeMethod
	public void initsetup() throws InterruptedException
	{
		initiation();
		//calling method of base calss, it will read all data and pass here
	    screenshots("Login");
	    log = new PomLogin();
	}

	@Test(priority = 1)
	public void title()
	{
		String actual = log.verifytitle();
		System.out.println("actual");
		Assert.assertEquals(actual, "OrangeHRM");
	}

	@DataProvider
	public Object[][] Details()
	{
		//this object array will accept data from excel sheet
		Object result[][] = ExcelSheet.readdata("Sheet1");
	    return result;
	}
	
	
	@Test (priority = 2, dataProvider = "Details")
	public void login(String name, String password) throws InterruptedException
	{
		log.typeusername(name);
	    log.typepassword(password);
	    Thread.sleep(4000);
	   // log.clickbutton();
	    
	    Thread.sleep(4000);
	}
	
	
	
	@AfterMethod
	public void close()
	{
		driver.close();
	}
}
