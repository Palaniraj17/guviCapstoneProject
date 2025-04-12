package PagesClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import BasePackage.baseclass;

public class SearchHotelPage extends baseclass {
	WebDriver driver;
	Properties prop;
	String searchInput[] = { "Sydney", "Hotel Hervey", "Double", "6 - Six", 
			"10/04/2025", "10/06/2025", "4 - Four","4 - Four" };
	SearchHotelResultsPage shrp;
	
	public SearchHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		shrp = new SearchHotelResultsPage(driver);
	}

	@FindBy(xpath = "//td[1][contains(text(),'Welcome to Adactin Group of Hotels')]")
	private WebElement searchhotelheader;

	@FindBy(id = "username_show")
	private WebElement usernameSearch;

	@FindBy(linkText = "Search Hotel")
	private WebElement SearchHotellink;

	@FindBy(xpath = "//a[contains(text(), 'Book')]")
	private WebElement Bookedhotellink;

	@FindBy(linkText = "Change Password")
	private WebElement changepwdlink;

	@FindBy(linkText = "Logout")
	private WebElement logoutLink;

	@FindBy(xpath = "//select[@id='location']")
	public WebElement shplocationDD;

	@FindBy(xpath = "//select[@id='hotels']")
	private WebElement shphotelsDD;

	@FindBy(xpath = "//select[@id='room_type']")
	private WebElement shpRoomtypeDD;

	@FindBy(xpath = "//select[@id='room_nos']")
	private WebElement shpRoomnosDD;

	@FindBy(xpath = "//select[@id='adult_room']")
	private WebElement shpadroomDD;

	@FindBy(xpath = "//select[@id='child_room']")
	private WebElement shpchroomDD;

	@FindBy(xpath = "//input[@id='datepick_in']")
	private WebElement shpindate;

	@FindBy(xpath = "//input[@id='datepick_out']")
	private WebElement shpoutdate;

	@FindBy(xpath = "//input[@id='Submit']")
	private WebElement shpSubmit;

	@FindBy(xpath = "//input[@id='Reset']")
	private WebElement shpReset;
	
	@FindBy(xpath="//td[@class='login_title']")
	private WebElement header;

	@FindBy(xpath="//span[contains(text(),'Please Select a Location')]")
	private WebElement searchValmsg;
	
	public void SearchHeaderVerify() throws IOException {
		PageFactory.initElements(driver, this);
		cusexpWait(searchhotelheader, 10);
		boolean status = searchhotelheader.isDisplayed();
		if (status == true) {
			Assert.assertTrue(true, "The Sign up option is displaying");
			System.out.println("User is logged in the application successfully");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User is unable to login in the application");
		}
	}

	public void searchUsernameverify() throws IOException {
		PageFactory.initElements(driver, this);
		cusexpWait(usernameSearch, 10);
		String actnamear[] = usernameSearch.getDomAttribute("value").split(" ");
		String actname = actnamear[1].replace("!", "");
		FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
		prop = new Properties();
		prop.load(fis);
		String expname = prop.getProperty("username");
		if (expname.equals(actname)) {
			Assert.assertTrue(true, "User name verification is done after logged in the application");
			System.out.println("User name verification is done after logged in the application");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User name verification is not done after logged in the application");
		}
	}

	public void linksVerification() throws IOException {
		PageFactory.initElements(driver, this);
		cusexpWait(SearchHotellink, 10);
		cusexpWait(Bookedhotellink, 10);
		cusexpWait(changepwdlink, 10);
		cusexpWait(logoutLink, 10);
		if (SearchHotellink.isEnabled() && Bookedhotellink.isEnabled() && changepwdlink.isEnabled()
				&& logoutLink.isEnabled()) {
			Assert.assertTrue(true, "The links are available in search hotel page");
			System.out.println("The links are available in search hotel page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("The links are not available in search hotel page");
		}
	}

	public void fillDate(String datename,String datevalue) throws IOException {
		PageFactory.initElements(driver, this);
		WebElement dateField = null;
		switch (datename.toLowerCase()) {
		case "checkindate":
			dateField=shpindate;
			break;
		case "checkoutdate":
			dateField=shpoutdate;
			break;
		default:
			Assert.fail("Invalid date field name: " + datename);	
		}
		dateField.clear();
		dateField.sendKeys(datevalue);
	}

	public void shpselectDDoption(String dropdownName, String optionToSelect) throws IOException {
		WebElement dropdownElement = null;
		switch (dropdownName.toLowerCase()) {
		case "location":
			dropdownElement = shplocationDD;
			break;
		case "hotels":
			dropdownElement = shphotelsDD;
			break;
		case "roomtype":
			dropdownElement = shpRoomtypeDD;
			break;
		case "roomnos":
			dropdownElement = shpRoomnosDD;
			break;
		case "adultrooms":
			dropdownElement = shpadroomDD;
			break;
		case "childrooms":
			dropdownElement = shpchroomDD;
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
	
	public void searchHotelVerify() throws IOException {
		shpSubmit.click();
		cusexpWait(header, 10);
		if (header.isDisplayed()) {
			Assert.assertTrue(true, "Now user could able to view the appropriate hotels list");
			System.out.println("Now user could able to view the appropriate hotels list");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("The user unable to view the appropriate hotels list");
		}
	}
	
	public void resetsearchHtlVerify() throws IOException, InterruptedException {
		shpReset.click();
		Thread.sleep(2000);
		defvalueDDverify("Location");
		defvalueDDverify("hotels");
		defvalueDDverify("roomtype");
		defvalueDDverify("roomnos");
		defvalueDDverify("adultrooms");
		defvalueDDverify("childrooms");
		
	}
	
	public void defvalueDDverify(String dropdownName) throws IOException
	{
		WebElement dropdownElement = null;
		Select select; String actvalue ="";
		switch (dropdownName.toLowerCase()) {
		case "location":
			dropdownElement = shplocationDD;
			select = new Select(dropdownElement);
			actvalue = select.getFirstSelectedOption().getText();
			break;
		case "hotels":
			dropdownElement = shphotelsDD;
			select = new Select(dropdownElement);
			actvalue = select.getFirstSelectedOption().getText();
			break;
		case "roomtype":
			dropdownElement = shpRoomtypeDD;
			select = new Select(dropdownElement);
			actvalue = select.getFirstSelectedOption().getText();
			break;
		case "roomnos":
			dropdownElement = shpRoomnosDD;
			select = new Select(dropdownElement);
			actvalue = select.getFirstSelectedOption().getText();
			break;
		case "adultrooms":
			dropdownElement = shpadroomDD;
			select = new Select(dropdownElement);
			actvalue = select.getFirstSelectedOption().getText();
			break;
		case "childrooms":
			dropdownElement = shpchroomDD;
			select = new Select(dropdownElement);
			actvalue = select.getFirstSelectedOption().getText();
			break;
		default:
			Assert.fail("Invalid dropdown name: " + dropdownName);
		}
		cusexpWait(dropdownElement, 5);
		if(actvalue.equals("- Select Location -") || actvalue.equals("- Select Hotel -") || actvalue.equals("- Select Room Type -") || 
				actvalue.equals("1 - One") || actvalue.equals("0 - None"))
		{
			Assert.assertTrue(true, "Reset is done for the "+dropdownName);
			System.out.println("Reset is done for the "+dropdownName);
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("Reset is done for the "+dropdownName);
		}
		}

	public void clickSearchHotellink() throws IOException {
		SearchHotellink.click();
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
	
	public void clickBookedHotels() throws IOException {
		Bookedhotellink.click();
		cusexpWait(header, 5);
		String expTitle="Booked Itinerary";
		if(header.getText().equals(expTitle)) {
			Assert.assertTrue(true, "User is navigate to booked hotels page");
			System.out.println("User is navigate to booked hotels page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User is unable to navigate booked hotels page");
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
	
	public void searchvalmsgDisplay() throws IOException {
		PageFactory.initElements(driver, this);
		if(searchValmsg.isDisplayed())
		{
			Assert.assertTrue(true, "Search validation message is displaying , please select location");
			System.out.println("Search validation message is displaying , please select location");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("Search validation message is not displaying");
		}
		}

	public void FillOptionsSearchHotel() throws IOException {
		shpselectDDoption("Location", searchInput[0]);
		shpselectDDoption("Hotels", searchInput[1]);
		shpselectDDoption("RoomType", searchInput[2]);
		shpselectDDoption("RoomNos", searchInput[3]);
		fillDate("checkindate", searchInput[4]);
		fillDate("checkoutdate", searchInput[5]);
		shpselectDDoption("AdultRooms", searchInput[6]);
		shpselectDDoption("ChildRooms", searchInput[7]);
		searchHotelVerify();

	}
	
	public void getColumnDetails() throws IOException {
		PageFactory.initElements(driver, this);
		List<WebElement> searchResults = driver.findElements(By.xpath("//table/tbody/tr[2]/td/table/tbody/tr"));
		int totalavailablehotels = searchResults.size() - 1;
		for (int i = 0; i < totalavailablehotels; i++) {
			for (int j = 1; j <= 6; j++) {
				switch (j) {
				case 1:
					String htlname = shrp.getTextFromColumn(i, "hotel name");
					if (searchInput[1].equals(htlname)) 
					{
						Assert.assertTrue(true, "The verification is done for " + htlname);
						System.out.println("The verification is done for " + htlname);
						tkscrnshtpass();
					} else 
					{
						tkscrnshtfail();
						Assert.fail("The verification is not done for " + htlname);
					}
					break;
				case 2:
					String loc = shrp.getTextFromColumn(i, "location");
					if (searchInput[0].equals(loc)) {
						Assert.assertTrue(true, "The verification is done for " + loc);
						System.out.println("The verification is done for " + loc);
						tkscrnshtpass();
					} else {
						tkscrnshtfail();
						Assert.fail("The verification is not done for " + loc);
					}
					break;
				case 3:
					String roomType = shrp.getTextFromColumn(i, "rooms type");
					if (searchInput[2].equals(roomType)) {
						Assert.assertTrue(true, "The verification is done for " + roomType);
						System.out.println("The verification is done for " + roomType);
						tkscrnshtpass();
					} else {
						tkscrnshtfail();
						Assert.fail("The verification is not done for " + roomType);
					}
					break;
				case 4:
					String ardate = shrp.getTextFromColumn(i, "arrival date");
					if (searchInput[4].equals(ardate)) {
						Assert.assertTrue(true, "The verification is done for " + ardate);
						System.out.println("The verification is done for " + ardate);
						tkscrnshtpass();
					} else {
						tkscrnshtfail();
						Assert.fail("The verification is not done for " + ardate);
					}
					break;
				case 5:
					String depdate = shrp.getTextFromColumn(i, "departure date");
					if (searchInput[5].equals(depdate)) {
						Assert.assertTrue(true, "The verification is done for " + depdate);
						System.out.println("The verification is done for " + depdate);
						tkscrnshtpass();
					} else {
						tkscrnshtfail();
						Assert.fail("The verification is not done for " + depdate);
					}
					break;
				case 6:
					String rooms[] = shrp.getTextFromColumn(i, "rooms").split(" ");
					String actVal = rooms[0];
					String expVal[] = searchInput[3].split(" ");
					String expv = expVal[0];
					if (expv.equals(actVal)) {
						Assert.assertTrue(true, "The verification is done for " + rooms[1]);
						System.out.println("The verification is done for " + rooms[1]);
						tkscrnshtpass();
					} else {
						tkscrnshtfail();
						Assert.fail("The verification is not done for " + rooms[1]);
					}
					break;
				}
			}
		}
	
}}
