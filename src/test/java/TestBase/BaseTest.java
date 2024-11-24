package TestBase;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest 
{
	public static WebDriver driver;
	
	@BeforeClass
    public void setup() 
	{
		driver=new ChromeDriver();
		
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
