package BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class baseclass{

	protected static WebDriver driver;
    public JavascriptExecutor js;
    Properties prop;

    public void driverSetup() {
    	
    	driver = new ChromeDriver();
    }
    
    public void preCondition() throws IOException {
        driverSetup();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies(); 
        cusWait(driver,20);
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop = new Properties();
        prop.load(fis);
        driver.get(prop.getProperty("URL"));
        driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.id("login")).click();
        js = (JavascriptExecutor) driver;
    }

    @AfterMethod
    public void postCondition() {
    	if (driver != null) {
            driver.quit();
            driver = null;  
        }
    }
    
    public String getTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public String tkscrnshtfail() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshot_" + getTimestamp() + ".png";
        File dest = new File("./Screenshots/FailedTestcases/" + screenshotPath);
        FileUtils.copyFile(src, dest);
        return dest.getAbsolutePath();
    }

    public String tkscrnshtpass() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshot_" + getTimestamp() + ".png";
        File dest = new File("./Screenshots/PassedTestCases/" + screenshotPath);
        FileUtils.copyFile(src, dest);
        return dest.getAbsolutePath();
    }

	
	public void cusWait(WebDriver driver,int wait) {
		 if (driver == null) {
		        throw new IllegalStateException("WebDriver is not initialized!");
		    }
		    new WebDriverWait(driver, Duration.ofSeconds(wait));
	}
	
	public void cusexpWait(WebElement ele,int min)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(min));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public boolean trueAssertValidation(boolean status)
	{
		if(status==true)
		{
			Assert.assertTrue(status);
			return true;
		}
		else
		{
			Assert.fail();
			return false;
		}
		
	}
	
	public void alertAccept(WebDriver driver) {
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void alertCancel(WebDriver driver) {
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
}
