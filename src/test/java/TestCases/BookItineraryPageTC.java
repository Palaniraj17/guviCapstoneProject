package TestCases;

import java.io.IOException;

import org.testng.annotations.*;

import BasePackage.baseclass;
import PagesClass.BookHotelPage;
import PagesClass.BookedItineraryPage;
import PagesClass.BookingConfirmationPage;
import PagesClass.SearchHotelPage;
import PagesClass.SearchHotelResultsPage;

@Listeners(TestReports.TestListener.class)
public class BookItineraryPageTC extends baseclass {
	
	SearchHotelPage shp;
	SearchHotelResultsPage shrp;
	BookHotelPage bhp;
	BookingConfirmationPage bcp;
	BookedItineraryPage bip;

	@BeforeMethod
	public void setupPageObjects() throws IOException 
	{
		preCondition();
		shp = new SearchHotelPage(driver);
		shrp = new SearchHotelResultsPage(driver);
		bhp=new BookHotelPage(driver);
		bcp= new BookingConfirmationPage(driver);
		bip=new BookedItineraryPage(driver);
	}
	
	@Test
	public void logoutfromBookItinerary() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bcp.myitineraryBtnVerifybcp();
		bip.getColumnDetailsafterbk();
		bip.clickloutBtnBookItinerary();
	}

	@Test
	public void searchOrderID() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bip.searchOrderID();
	}
	

	@Test
	public void bookHotelandCancelOrder() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bip.searchOrderID();
	}
}
