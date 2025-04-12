package PagesClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import BasePackage.baseclass;

public class BookingConfirmationPage extends baseclass{

	WebDriver driver;

	public BookingConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='order_no']")
	private WebElement OrderNo;
	
	@FindBy(xpath="//td[@class='login_title']")
	private WebElement bookConfirmheader;
	
	@FindBy(xpath="//input[@name='search_hotel']")
	private WebElement searchHotelBtn;
	
	@FindBy(xpath="//input[@name='my_itinerary']")
	private WebElement myItineraryBtn;
	
	@FindBy(xpath="//input[@name='logout']")
	private WebElement logoutBtn;
	
	@FindBy(xpath="//td[@class='login_title']")
	private WebElement header;
	
	@FindBy(xpath="//td[@class='login_title'][contains(text(), 'Book')]")
	private WebElement bookHotelheader;
	
	@FindBy(xpath="//td[@class='reg_success']")
	private WebElement logoutMsg;

	
	
	public void orderNoVerify() throws IOException 
	{
		PageFactory.initElements(driver, this);
		String number = OrderNo.getText();
		if (number != null) 
		{
			Assert.assertTrue(true, "Order number is displaying as expected");
			System.out.println("Order number is displaying as expected");
			tkscrnshtpass();
		} else 
		{
			tkscrnshtfail();
			Assert.fail("Order number is not displaying as expected");
		}
	}
	
	public void searchBtnVerifybcp() throws IOException {
		searchHotelBtn.click();
		cusexpWait(header, 5);
		String expTitle="Search Hotel (Fields marked with Red asterix (*) are mandatory)";
		if(header.getText().equals(expTitle)) {
			Assert.assertTrue(true, "User is navigate to search hotel page");
			System.out.println("User is navigate to search hotel page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User is unable to navigate search hotel page");
		}	
	}
	
	public void myitineraryBtnVerifybcp() throws IOException {
		PageFactory.initElements(driver, this);
		myItineraryBtn.click();
		cusexpWait(bookHotelheader, 5);
		String expTitle="Booked Itinerary";
		System.out.println(bookHotelheader.getText());
		if(bookHotelheader.getText().equals(expTitle)) {
			Assert.assertTrue(true, "User is navigate to book hotel page");
			System.out.println("User is navigate to book hotel page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User is unable to navigate book hotel page");
		}
	}
	
	public void logoutBtnVerifybcp() throws IOException {
		logoutBtn.click();
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
	}
	
}
