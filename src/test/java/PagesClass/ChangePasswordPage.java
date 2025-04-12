package PagesClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import BasePackage.baseclass;

public class ChangePasswordPage extends baseclass{

	WebDriver driver;
	Properties prop;
	SearchHotelResultsPage shrp= new SearchHotelResultsPage(driver);
	SearchHotelPage shp= new SearchHotelPage(driver);
	BookingConfirmationPage bcp=new BookingConfirmationPage(driver);
	
	public ChangePasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}	
	
	@FindBy(xpath="//input[@id='current_pass']")
	private WebElement crntPasswrd;
	
	@FindBy(xpath="//input[@id='new_password']")
	private WebElement newPasswrd;
	
	@FindBy(xpath="//input[@id='re_password']")
	private WebElement renewPasswrd;
	
	@FindBy(xpath="//input[@name='change_password_Submit']")
	private WebElement chgpwdSubmit;
	
	@FindBy(xpath="//span[@class='reg_error'][contains(text(),'suc')]")
	private WebElement chgpwdsuccessmsg;
	
	@FindBy(xpath="//span[@class='reg_error'][contains(text(),'current')]")
	private WebElement chgpwdfailsmsg;
	
	@FindBy(linkText = "Change Password")
	private WebElement changepwdlink;
	
	@FindBy(xpath="//td[@class='login_title']")
	private WebElement header;
	
	@FindBy(linkText ="Logout")
	private WebElement logoutBtn;
	
	@FindBy(xpath="//td[@class='reg_success']")
	private WebElement logoutMsg;
	
	public void changePwd(String Newpassword) throws IOException {
		clickChangepwd();
		FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop = new Properties();
        prop.load(fis);
		crntPasswrd.sendKeys(prop.getProperty("password"));
		newPasswrd.sendKeys(Newpassword);
		renewPasswrd.sendKeys(Newpassword);
		chgpwdSubmit.click();
		String expMsg="Your Password is successfully updated!!!";
		if(chgpwdsuccessmsg.getText().equals(expMsg)) {
			Assert.assertTrue(true, "User password is changed successfully");
			System.out.println("User password is changed successfully");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User password is not getting changed successfully");
		}
	}
	
	public void clickChangepwd() throws IOException {
		PageFactory.initElements(driver, this);
		changepwdlink.click();
		cusexpWait(header, 5);
		String expTitle="Change Password(Fields marked with Red asterix (*) are mandatory)";
		if(header.getText().equals(expTitle)) {
			Assert.assertTrue(true, "User is navigate to change password page");
			System.out.println("User is navigate to change password page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User is unable to navigate change password page");
		}
		
	}
	public void logoutBtnVerifybcp() throws IOException {
		if(logoutBtn.isDisplayed())
			{logoutBtn.click();
		cusexpWait(logoutMsg, 5);
		String expTitle="You have successfully logged out. Click here to login again";
		if(logoutMsg.getText().equals(expTitle)) {
			Assert.assertTrue(true, "User is navigate to search hotel page");
			System.out.println("User is navigate to search hotel page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User is unable to navigate search hotel page");
		}	
	}}
	public void backtoDefpassword(String pd) throws IOException {
		logoutBtnVerifybcp();
		FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop = new Properties();
        prop.load(fis);
        driver.get(prop.getProperty("URL"));
        driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.id("password")).sendKeys(pd);
        driver.findElement(By.id("login")).click();
        js = (JavascriptExecutor) driver;
        clickChangepwd();
		crntPasswrd.sendKeys(pd);
		newPasswrd.sendKeys(prop.getProperty("password"));
		renewPasswrd.sendKeys(prop.getProperty("password"));
		chgpwdSubmit.click();
		String expMsg="Your Password is successfully updated!!!";
		if(chgpwdsuccessmsg.getText().equals(expMsg)) {
			Assert.assertTrue(true, "User password is changed successfully");
			System.out.println("User password is changed successfully");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User password is not getting changed successfully");
		}
	
}
}