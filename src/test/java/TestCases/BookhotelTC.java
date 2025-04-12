package TestCases;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BasePackage.baseclass;
import PagesClass.BookHotelPage;
import PagesClass.BookedItineraryPage;
import PagesClass.BookingConfirmationPage;
import PagesClass.SearchHotelPage;
import PagesClass.SearchHotelResultsPage;

@Listeners(TestReports.TestListener.class)
public class BookhotelTC extends baseclass{
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
	public void booknowPageerrorValidation() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.clickBookNowwithoutfill();
	}

	@Test
	public void bookNowcomplete() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
	}
}
