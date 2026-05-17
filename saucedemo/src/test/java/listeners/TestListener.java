package listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;

public class TestListener implements ITestListener{
	
	public void onTestStart(ITestResult result) {
		BaseClass.extentTest = BaseClass.reports.createTest(result.getName());
		
	}
	
	public void onTestFailure(ITestResult result) {
		BaseClass.extentTest.fail("TestFailed"+result.getName());
		TakesScreenshot screenshot = (TakesScreenshot) BaseClass.driver;
		File source =  screenshot.getScreenshotAs(OutputType.FILE);
		String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		File destination = new File("screenshot/screenshot_"+simpleDateFormat+".png");
		
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseClass.extentTest.addScreenCaptureFromPath(destination.getAbsolutePath());
	  }
	
	public void onTestSuccess(ITestResult result) {
		BaseClass.extentTest.pass("TestSuccess"+result.getName());

	}

}
