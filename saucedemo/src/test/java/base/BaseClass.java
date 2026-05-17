package base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;	

	public static final Logger logger = LogManager.getLogger(BaseClass.class);

	public static ExtentReports reports = new ExtentReports();
	public static ExtentTest extentTest ;

	@BeforeSuite
	public void setupExtentReport() {

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("testReports/testReport_"+timeStamp+".html");

		reports.attachReporter(sparkReporter);

	}


	@BeforeMethod
	public void initBrowser(Method method) {

		logger.info("Initializing browser");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");

	}


	@AfterMethod 
	public void teardown() throws InterruptedException {
		//Thread.sleep(3000); 
		driver.quit(); 
	}


	@AfterSuite
	public void flusReport() {

		reports.flush();

	}


}
