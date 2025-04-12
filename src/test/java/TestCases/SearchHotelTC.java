package TestCases;

import java.io.IOException;

import org.testng.annotations.*;

import BasePackage.baseclass;
import PagesClass.BookedItineraryPage;
import PagesClass.SearchHotelPage;
import PagesClass.SearchHotelResultsPage;

@Listeners(TestReports.TestListener.class)
public class SearchHotelTC extends baseclass {
	SearchHotelPage shp;
	SearchHotelResultsPage shrp;
	BookedItineraryPage bip;
	String searchInput[] = { "Sydney", "Hotel Hervey", "Double", "6 - Six", 
			"10/04/2025", "10/06/2025", "4 - Four","4 - Four" };
	
	@BeforeMethod
	public void setupPageObjects() throws IOException 
	{
		preCondition();
		shp = new SearchHotelPage(driver);
		shrp = new SearchHotelResultsPage(driver);
		bip=new BookedItineraryPage(driver);
	}

	@Test

	public void userNameverify() throws IOException, InterruptedException 
	{
		bip.deleteAllbookings();
		shp.SearchHeaderVerify();
		shp.searchUsernameverify();
		shp.linksVerification();
		shp.clickSearchHotellink();
		shp.clickBookedHotels();
		shp.clickChangepwd();
	}

	@Test
	public void optionSelectsearchhotel() throws IOException, InterruptedException 
	{
		bip.deleteAllbookings();
		shp.shpselectDDoption("Location", searchInput[0]);
		shp.shpselectDDoption("Hotels", searchInput[1]);
		shp.shpselectDDoption("RoomType", searchInput[2]);
		shp.shpselectDDoption("RoomNos", searchInput[3]);
		shp.fillDate("checkindate", searchInput[4]);
		shp.fillDate("checkoutdate", searchInput[5]);
		shp.shpselectDDoption("AdultRooms", searchInput[6]);
		shp.shpselectDDoption("ChildRooms", searchInput[7]);
		shp.searchHotelVerify();
	}

	@Test
	public void resetHotelSearch() throws IOException, InterruptedException 
	{
		bip.deleteAllbookings();
		optionSelectsearchhotel();
		shp.clickSearchHotellink();
		shp.resetsearchHtlVerify();
	}

	@Test
	public void withoutlocationsearch() throws IOException, InterruptedException 
	{
		bip.deleteAllbookings();
		shp.searchHotelVerify();
		shp.searchvalmsgDisplay();
	}

	@Test
	public void pageNavigation() throws IOException, InterruptedException
	{
		bip.deleteAllbookings();
		shp.clickSearchHotellink();
		shp.clickBookedHotels();
		shp.clickChangepwd();
	}

	@Test

	public void verifySearchResult() throws IOException, InterruptedException 
	{
		bip.deleteAllbookings();
		optionSelectsearchhotel();
		shp.getColumnDetails();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
	}
	
	@Test
	public void continueBtnvalidation() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		optionSelectsearchhotel();
		shp.getColumnDetails();
		shrp.selectContinuewithoutselect();
	}

	@Test
	public void cancelBtnValidation() throws IOException, InterruptedException {
	bip.deleteAllbookings();
		optionSelectsearchhotel();
		shp.getColumnDetails();
		shrp.clickCancelbuttonsearchresults();
		//alertAccept(driver);
	}
}
