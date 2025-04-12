package TestReports;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BasePackage.baseclass;

	public class TestListener implements ITestListener {

	    ExtentReports extent = ExtentReportManager.getReportInstance();
	    ExtentTest test;

	    public void onTestStart(ITestResult result) {
	        test = extent.createTest(result.getMethod().getMethodName());
	    }

	    public void onTestSkipped(ITestResult result) {
	        test.log(Status.SKIP, "Test Skipped");
	    }

	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }
	    
	    public void onTestSuccess(ITestResult result) {
	        try {
	            String path = new baseclass().tkscrnshtpass();
	            test.log(Status.PASS, "Test Passed");
	            test.addScreenCaptureFromPath(path);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void onTestFailure(ITestResult result) {
	        try {
	            String path = new baseclass().tkscrnshtfail();
	            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
	            test.addScreenCaptureFromPath(path);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

