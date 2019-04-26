package reports;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import genericfunctions.Constants;
import testcases.CRMMenuItems;
import webpages.CRMCompanies;
import webpages.CRMMouse;
import webpages.CRMPro;
import webpages.CRMQuestionMark;
import webpages.CRMQuickMark;
import webpages.RedBus;

public class CRMProReport {

	public static WebDriver driver = null;
	public ExtentHtmlReporter htmlreporter = null;
	public ExtentReports extent = null;
	public static ExtentTest logger = null;
	// public CRMPro crm = null;
	// public CRMCompanies crmcomp = null;
	// public CRMQuestionMark crmcomp = null;
	 public CRMQuickMark crmcomp = null;

	//public CRMMouse crmcomp = null;

	@BeforeSuite(alwaysRun = true)
	public void report() {

		htmlreporter = new ExtentHtmlReporter("./Report/CRMComp.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);

	}

	@BeforeSuite(alwaysRun = true)
	public void browseropen() {

		getBrowser("chrome");
		getApp("CRMPRO");
		driver.manage().window().maximize();
	}

	@BeforeMethod(alwaysRun = true)
	public void pageobj(Method name) {
		logger = extent.createTest(name.getName(), name.getDeclaringClass().getName());
		// crm = new CRMPro(driver);
	//	 crmcomp = new CRMCompanies(driver);
		// crmcomp = new CRMQuestionMark(driver);
		 crmcomp = new CRMQuickMark(driver);

		//crmcomp = new CRMMouse(driver);
	}

	public void getBrowser(String browser) {

		switch (browser) {

		case "chrome":
			System.setProperty(Constants.chromekey, Constants.chromepath);
			driver = new ChromeDriver();
			break;
		case "firefox":

			System.setProperty(Constants.firefoxkey, Constants.firefoxpath);
			driver = new FirefoxDriver();
			break;
		case "ie":

			System.setProperty(Constants.iekey, Constants.iepath);
			driver = new InternetExplorerDriver();
			break;

		default:
			break;
		}
	}

	public void getApp(String application) {

		switch (application) {

		case "redbus":
			driver.get("https://www.redbus.in/");
			break;
		case "CRMPRO":
			driver.get("https://classic.crmpro.com/index.html");
			break;
		case "hms":
			driver.get("http:/hms.com");
			break;
		default:
			break;
		}

	}

	public static void getScreenshot(String path) {
		try {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			//FileHandler.copy(src, new File("./Report/Screenshot/" + "jc"+timeStamp+".png"));
			FileHandler.copy(src, new File(path));
		} catch (Exception e) {

		}
	}

	@AfterMethod
	public static void verifystatus(ITestResult result) {

		/*
		 * try {
		 * 
		 * if(result.getStatus()==ITestResult.SUCCESS) { }else }
		 * if(result.getStatus()==ITestResult.FAILURE) { logger.log(Status.FAIL, }
		 * "Failed"); Assert.fail(" "); } {
		 * 
		 * } } catch(Exception e) { Assert.fail(e.getMessage()); }
		 * 
		 * 
		 */

		try {
			if (result.getStatus() == ITestResult.FAILURE) {

				logger.fail(MarkupHelper.createLabel(result.getName() + "Test Case Failed", ExtentColor.RED));

				logger.fail(result.getThrowable());

				getScreenshot("./Report/Screenshot/" + result.getName() + ".png");

				String screenlocation = "./Screenshot/" + result.getName() + ".png";

				logger.fail("Screenshot shot Reference ",
						MediaEntityBuilder.createScreenCaptureFromPath(screenlocation).build());

			}

		} catch (Exception e) {
			logger.log(Status.FAIL, "failed due to below error");
			Assert.fail(e.getMessage());
		}
	}

	@AfterSuite(alwaysRun = true)
	public void printreport() {
		extent.flush();
	}

}
