package PagesClass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import BasePackage.baseclass;

public class BookHotelPage extends baseclass {

	WebDriver driver;
	String expValue[]={"Please Enter your First Name","Please Enter you Last Name","Please Enter your Address","Please Enter your 16 Digit Credit Card Number",
			"Please Select your Credit Card Type","Please Select your Credit Card Expiry Month","Please Enter your Credit Card CVV Number"};
	String mandFills[]= {"TestFirstName","TestLastName","42,test street,Demo, State, Zip-12346","4111111111111111","1635"};
	
	public BookHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='first_name']")
	private WebElement bkfname;
	
	@FindBy(xpath="//input[@id='last_name']")
	private WebElement bklname;
	
	@FindBy(xpath="//textarea[@id='address']")
	private WebElement bkaddress;
	
	@FindBy(xpath="//input[@id='cc_num']")
	private WebElement bkccnum;
	
	@FindBy(xpath="//input[@id='cc_cvv']")
	private WebElement bkcccvv;
	
	@FindBy(xpath="//input[@id='book_now']")
	private WebElement bkBooknowbtn;
	
	@FindBy(xpath="//input[@id='cancel']")
	private WebElement bkCancelbtn;
	
	@FindBy(xpath = "//select[@id='cc_type']")
	public WebElement bkcctypeDD;
	
	@FindBy(xpath = "//select[@id='cc_exp_month']")
	public WebElement bkccexpmonthDD;
	
	@FindBy(xpath = "//select[@id='cc_exp_year']")
	public WebElement bkccexpyearDD;
	
	@FindBy(xpath="//label[@id='first_name_span']")
	private WebElement fnameerrormsg;
	
	@FindBy(xpath="//label[@id='last_name_span']")
	private WebElement lnameerrormsg;
	
	@FindBy(xpath="//label[@id='address_span']")
	private WebElement addresserrormsg;
	
	@FindBy(xpath="//label[@id='cc_num_span']")
	private WebElement ccnumerrormsg;
	
	@FindBy(xpath="//label[@id='cc_type_span']")
	private WebElement cctypeerrormsg;
	
	@FindBy(xpath="//label[@id='cc_expiry_span']")
	private WebElement ccexperrormsg;
	
	@FindBy(xpath="//label[@id='cc_cvv_span']")
	private WebElement cccvverrormsg;
	
	@FindBy(xpath="//td[@class='login_title']")
	private WebElement bookConfirmheader;
	
	@FindBy(xpath="//label[@id='process_span']")
	private WebElement loadingMsg;
	
	public void clickBookNowwithoutfill() throws IOException {
		bkBooknowbtn.click();
    	String actValue[]= {fnameerrormsg.getText(),lnameerrormsg.getText(),addresserrormsg.getText(),ccnumerrormsg.getText(),
    			cctypeerrormsg.getText(),ccexperrormsg.getText(),cccvverrormsg.getText()};
    	for(int i=0;i<expValue.length;i++)
    	{
    		if(expValue[i].equals(actValue[i]))
    		{
    			Assert.assertTrue(true, "Validation is displaying as expected");
    			System.out.println("Validation is displaying as expected");
    			tkscrnshtpass();
    		} else {
    			tkscrnshtfail();
    			Assert.fail("Validation is not displaying as expected");
    		
    		}
    	}
    }
	
	public void fillManfieldBookNow() throws IOException {
		cusexpWait(bkaddress, 10);
		for(int i=0;i<mandFills.length;i++) {
			switch(i) {
			case 0:
				bkfname.sendKeys(mandFills[i]);break;
			case 1:
				bklname.sendKeys(mandFills[i]);break;
			case 2:
				bkaddress.sendKeys(mandFills[i]);break;
			case 3:
				bkccnum.sendKeys(mandFills[i]);break;
			case 4:
				bkcccvv.sendKeys(mandFills[i]);break;
			}
		}
		bhpselectDDoption("creditcardtype","VISA");
		bhpselectDDoption("creditcardexpirymonth","May");
		bhpselectDDoption("creditcardexpiryyear","2026");
	}
	
	public void clickBookNow() throws IOException, InterruptedException {
		bkBooknowbtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("process_span")));
		String actValue=bookConfirmheader.getText();
		String expValue ="Booking Confirmation"; 
		System.out.println(actValue);
		if (expValue.equals(actValue)) {
			Assert.assertTrue(true, "The user is navigated to bookng confirmation page");
			System.out.println("The user is navigated to bookng confirmation page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("The user is unable to navigated to bookng confirmation page");
		}
	}
	
	
	public void bhpselectDDoption(String dropdownName, String optionToSelect) throws IOException {
		WebElement dropdownElement = null;
		switch (dropdownName.toLowerCase()) {
		case "creditcardtype":
			dropdownElement = bkcctypeDD;
			break;
		case "creditcardexpirymonth":
			dropdownElement = bkccexpmonthDD;
			break;
		case "creditcardexpiryyear":
			dropdownElement = bkccexpyearDD;
			break;
		default:
			Assert.fail("Invalid dropdown name: " + dropdownName);
		}
		cusexpWait(dropdownElement, 5);
		Select select = new Select(dropdownElement);
		select.selectByVisibleText(optionToSelect);
		String actualSelectedOption = select.getFirstSelectedOption().getText();
		if (optionToSelect.equals(actualSelectedOption)) {
			Assert.assertTrue(true, "Option '" + optionToSelect + "' is selected successfully");
			System.out.println("The" + optionToSelect + "' is selected successfully from " + dropdownName);
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("Expected option '" + optionToSelect + "' but got '" + actualSelectedOption + "'");
		}
	}
}
