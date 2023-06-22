package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseHRMClass {
	
	//browser info
	//url info
	//username and password
	
	public static Properties prop = new Properties();
	public static WebDriver driver;
	
	//Step 1
    public BaseHRMClass()
    {
    	try {
    	FileInputStream file = new FileInputStream("C:\\Selenium\\Selenium_WorkSpace\\HRManagement\\src\\test\\java\\environmentVariables\\Config.properties");
        prop.load(file);
    	}
    	catch(FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	catch(IOException a)
    	{
    		a.printStackTrace();
    	}
    }
    	
    
    //Step 2
    	public static void initiation() throws InterruptedException
    	{
    		//It will get the property from config file and store in variable
    		String browsername = prop.getProperty("browser"); 
    		//driver path
    		if(browsername.equals("chrome"))
    		{
    			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    			driver = new ChromeDriver();
    		}
    		else if(browsername.equals("Firefox"))
    		{
    			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
    			driver = new FirefoxDriver();
    		}
    		
    		driver.manage().window().maximize();
    		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    		driver.get(prop.getProperty("url"));
    		Thread.sleep(3000);
    		
    	 }
    		//maximize window, pageload.implicit, url
    	
    	public static void screenshots(String Filename) 
    	{
    		File files = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	   try {
    		FileUtils.copyFile(files, new File("C:\\Selenium\\Selenium_WorkSpace\\HRManagement\\src\\test\\java\\screenshots\\ScreenShots" + Filename + ".jpg"));
    	
    	       }
    	   catch(IOException e)
    	   {
    		   e.printStackTrace();
    	   }
  }
}
