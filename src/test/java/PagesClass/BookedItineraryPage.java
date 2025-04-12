package PagesClass;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import BasePackage.baseclass;

public class BookedItineraryPage extends baseclass {

	WebDriver driver;
	String searchInput[] = { "Sydney", "Hotel Hervey", "Double", "6 - Six", 
			"10/04/2025", "10/06/2025", "4 - Four","4 - Four" };
	SearchHotelResultsPage shrp= new SearchHotelResultsPage(driver);
	SearchHotelPage shp= new SearchHotelPage(driver);
	BookingConfirmationPage bcp=new BookingConfirmationPage(driver);
	
	public BookedItineraryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}	
	
	@FindBy(xpath="//input[starts-with(@id, 'hotel_name_')]")
	private WebElement afbkHotename;
	
	@FindBy(xpath="//input[starts-with(@id, 'location_')]")
	private WebElement afbkLocation;
	
	@FindBy(xpath="//input[starts-with(@id, 'room_type_')]")
	private WebElement afbkRoomType;
	
	@FindBy(xpath="//input[starts-with(@id, 'rooms_')]")
	private WebElement afbkrooms;
	
	@FindBy(xpath="//input[starts-with(@id, 'arr_date_')]")
	private WebElement afbkardate;
	
	@FindBy(xpath="//input[starts-with(@id, 'dep_date_')]")
	private WebElement afbkdepdate;

	@FindBy(xpath="//input[starts-with(@id, 'order_id_')][@class='select_text']")
	private WebElement afbkOrderID;
	
	@FindBy(xpath="//input[@value='check_all']")
	private WebElement allBookingsCheckbx;
	
	@FindBy(xpath="//input[@name='cancelall']")
	private WebElement cancelBookingBtn;
	
	@FindBy(linkText="Booked Itinerary")
	private WebElement bookitlink;
	
	@FindBy(xpath = "//a[contains(text(), 'Book')]")
	private WebElement Bookedhotellink;
	
	@FindBy(xpath="//td[@class='login_title']")
	private WebElement header;
	
	@FindBy(xpath = "//a[contains(text(), 'Search')]")
	private WebElement SearchHotellink;
	
	@FindBy(xpath="//input[@id='logout']")
	private WebElement logotBtn;
	
	@FindBy(xpath="//td[@class='reg_success']")
	private WebElement logoutMsg;
	
	@FindBy(xpath="//input[@name='order_no']")
	private WebElement OrderNo;
	
	@FindBy(xpath="//input[@id='order_id_text']")
	private WebElement searchOrderID;
	
	@FindBy(xpath="//input[@id='search_hotel_id']")
	private WebElement searchOrderIDbtn;
	
	@FindBy(xpath="//label[@id='search_result_error']")
	private WebElement searchresult;
	
	@FindBy(xpath="//input[@name='my_itinerary']")
	private WebElement myItineraryBtn;
	
	@FindBy(xpath="//td[@class='login_title'][contains(text(), 'Book')]")
	private WebElement bookHotelheader;
	
	@FindBy(xpath="//input[@name='ids[]']")
	private WebElement selectBookedhotelbox;
	
	public void getColumnDetailsafterbk() throws IOException {
		PageFactory.initElements(driver, this);
		List<WebElement> searchResults = driver.findElements(By.xpath("//table/tbody/tr[2]/td/table/tbody/tr"));
		int totalavailablehotels = searchResults.size() - 1;
		for (int i = 1; i < totalavailablehotels; i++) {
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
	
	}
	
	public void deleteAllbookings() throws IOException, InterruptedException {
		clickBookedHotels();
		List<WebElement> searchResults = driver.findElements(By.xpath("//table/tbody/tr[2]/td/table/tbody/tr"));
		int totalavailablehotels = searchResults.size() - 1;
			if(totalavailablehotels>0) {
			allBookingsCheckbx.click();
			cancelBookingBtn.click();
			alertAccept(driver);
			Thread.sleep(10000);
		}
		clickSearchHotellink();
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

	public void clickloutBtnBookItinerary() throws IOException {
		logotBtn.click();
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
	
	public String getOrderIDNo() throws IOException {
		String ordNo=OrderNo.getDomAttribute("value");
		return ordNo;
	}
	
	public void searchOrderID() throws IOException, InterruptedException {
		String id=getOrderIDNo();
		myitineraryBtnVerifybcp();
		searchOrderID.sendKeys(id);
		Thread.sleep(3000);
		searchOrderIDbtn.click();
		cusexpWait(searchresult, 15);
		char result=searchresult.getText().charAt(0);
		System.out.println(result);
		if(result==1)
		{
			if(afbkOrderID.getText()==getOrderIDNo()) {
				Assert.assertTrue(true, "Order ID verification is done");
				System.out.println(afbkOrderID.getText());
				System.out.println("Order ID verification is done");
				tkscrnshtpass();
			} else {
				tkscrnshtfail();
				Assert.fail("Order ID verification is unabel to done");
			}
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
	
	public void cancelBookhotel(String bookID) throws IOException, InterruptedException
	{
		searchOrderID();
		selectBookedhotelbox.click();
		cancelBookingBtn.click();
		alertAccept(driver);
	}
}

