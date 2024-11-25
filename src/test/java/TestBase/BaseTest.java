package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest 
{
	public static WebDriver driver;
	public Logger log;
	
	@SuppressWarnings("deprecation")
	@Parameters({"os","browser"})
	@BeforeClass
    public void setup(String operatingSytem,String  browserName) throws IOException 
	{
		FileReader fr=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Config.Properties");
		Properties pr=new Properties();
		pr.load(fr);
		
		log=LogManager.getLogger();
		
		//Remote Execution on Selenium Grid
		if(pr.getProperty("exec_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
		   
			//os
			if(operatingSytem.equalsIgnoreCase("windows")) 
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(operatingSytem.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(operatingSytem.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			
			//browser
			switch(browserName)
			{
			  case "chrome" :capabilities.setBrowserName("Chrome");
			                 break;
			  case "edge"   :capabilities.setBrowserName("Edge");
                             break;
			  case "firefox":capabilities.setBrowserName("FireFox");
                             break;
              default       :System.out.println("Invalid browser name");
                             return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/hub"),capabilities);
		}
		
		//Local Execution
		if(pr.getProperty("exec_env").equalsIgnoreCase("local"))
		{	
		switch(browserName) 
		{
		case "chrome"   :driver=new ChromeDriver();
		                 break;
		case "edge"     :driver=new EdgeDriver();
                         break;
		case "firefox"  :driver=new FirefoxDriver();
                         break;  
		default         :System.out.println("Invalid browser name");
		                 return;
		}
	}
		driver.get(pr.getProperty("AppUrl"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public String getScreenShot(String filename)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourceFile=ts.getScreenshotAs(OutputType.FILE);
		String filepath=System.getProperty("user.dir")+"\\Screenshots\\"+filename;
		File TargetFile=new File(filepath);
		sourceFile.renameTo(TargetFile);
		return filepath;
	}
	
	
	@AfterClass
    public void tearDown()
	{
		driver.quit();
	}
}
