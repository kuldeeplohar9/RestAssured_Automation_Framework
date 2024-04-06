package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//api.utilities.ExtentListenerClass
public class ExtentListenerClass implements ITestListener {
	ExtentSparkReporter htmlReporter;// user Interface//for look and feel the report
	ExtentReports reports;// common information
	ExtentTest test;// entries for test

	public void configureReport() {

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String reportName = "RestAssuredAutomationFramework_" + timeStamp + ".html";
		String reportPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportName;
		htmlReporter = new ExtentSparkReporter(reportPath);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		// add system information/environment info to reports
		reports.setSystemInfo("Machine", "testpc");
		reports.setSystemInfo("OS", "Windows 11");
		// reports.setSystemInfo("Browser", readConfig.getBrowser());
		reports.setSystemInfo("Username", "kuldeep");

		// configuration to change look and feel of report
		htmlReporter.config().setDocumentTitle("Extent Report for Ecommerce project");
		htmlReporter.config().setReportName("This Extent Report");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onStart(ITestContext result) {
		configureReport();
		System.out.println("on start method invoked");
	}

	@Override
	public void onFinish(ITestContext result) {
		System.out.println("onFinish invoked");
		reports.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Name of test method fail: " + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of test failed is: " + result.getName(), ExtentColor.RED));

		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + result.getName() + ".png";
		File screenShotFile = new File(screenshotPath);
		if (screenShotFile.exists()) {
			test.fail("Captured Screenshot is below: " + test.addScreenCaptureFromPath(screenshotPath));
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Name of test method onTestSkipped: " + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Name of test skipped is: " + result.getName(), ExtentColor.YELLOW));
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Name of test method successfully executed: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Name of test method onTestSuccess: " + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of test passed is: " + result.getName(), ExtentColor.GREEN));
	}
}
