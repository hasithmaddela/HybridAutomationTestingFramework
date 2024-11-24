package Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseTest;


public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkreporter; //Handles the UI of the Report
	public ExtentReports extent;              //Handles common info for the Report
	public ExtentTest test;                   //Creates a Test Entry and updates the Status
   
	public  void onTestStart(ITestResult result)
	 {
		
		sparkreporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\TestReport.html");
		sparkreporter.config().setDocumentTitle("Test Report");
		sparkreporter.config().setTheme(Theme.DARK);
	    
		extent=new ExtentReports();
		extent.setSystemInfo("Environment", "QA");
	  }

	  public void onTestSuccess(ITestResult result) 
	  {
	    test=extent.createTest(result.getName());
	    test.log(Status.PASS, "Test Case Passed:"+result.getName());    
	  }

	  public void onTestFailure(ITestResult result)
	  {
		  test=extent.createTest(result.getName());
		  test.log(Status.FAIL, "Test Case Failed:"+result.getName());
		  test.log(Status.INFO,"Test Case Failed cause is:"+result.getThrowable().getMessage());
		  
		  try {
			  String filepath=new BaseTest().getScreenShot(result.getName());
			  test.addScreenCaptureFromPath(filepath);
		  }
		  catch(Exception e) 
		  {
			  e.printStackTrace();
		  }
	  }

	  public void onTestSkipped(ITestResult result)
	  {
		  test=extent.createTest(result.getName());
		  test.log(Status.SKIP, "Test Case Skipped:"+result.getName());
		  test.log(Status.INFO,"Test Case Skipped cause is:"+result.getThrowable().getMessage());
	  }

	
	  public  void onFinish(ITestContext context)
	  {
         extent.flush();
	  }
	
}
