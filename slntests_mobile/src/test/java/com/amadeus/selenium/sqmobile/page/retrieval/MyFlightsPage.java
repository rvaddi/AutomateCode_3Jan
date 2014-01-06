package com.amadeus.selenium.sqmobile.page.retrieval;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.checkin.CheckinSendFlightInfo;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.search.ATCSearchPage;
import com.amadeus.selenium.sqmobile.page.seat.CommonSeatMapPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class MyFlightsPage extends SqMobileCommonPage {

	protected final static By LOC_BOOKING_DETAILS= By.cssSelector(".booking>p");
	protected final static By LOC_TRIP_PANELS =  By.cssSelector(".panel.trip");
	protected final static By LOC_BUTTON_SELECT_MEAL = By.className("addMeal");
	protected final static By LOC_BUTTON_CHECKIN = By.className("checkin");
	protected final static By LOC_FLIGHT_NUMBER = By.cssSelector(".flight-number>strong");
	protected final static By LOC_BUTTON_CHANGE_TRIP = By.className("navigation");

	protected final static By LOC_BUTTON_SELECT_SEATS = By.cssSelector(".moreInfo a");
	protected final static By LOC_BUTTON_SELECT_MYMEAL = By.cssSelector("li [class*=meal]");



	public MyFlightsPage(SeleniumSEPTest test) throws Exception{
		super(test);

		// Check that we're on the right page.
		if(! WaitUtils.waitForElementPresent(getTest(), LOC_TRIP_PANELS, 30)){
			reporter.fail("This is not my flights page");
		}else{
			reporter.reportPassed("My Flights page", "In my flights page");
		}
	}

	String pageName = "MyFlights Page" ;
	//-------------------------------------------------------------------------------------

	public enum Itinerary {
		DEPARTURE, RETURN ;
	}

	/**
	 * Validates Booking Reference
	 * @throws Exception
	 * @author bsingh
	 */
	public void validateBookingReference() throws Exception {
		WebElement ticketElt = CheckUtils.getElement(getTest(), LOC_BOOKING_DETAILS);
		if(ticketElt!=null && ticketElt.isDisplayed()){
			if(ticketElt.getText().contains(getValue("PNR/TICKET"))){
				reporter.reportPassed("PNR/TICKET :", ticketElt.getText());
			}else{
				reporter.reportFailed("PNR/TICKET :", ticketElt.getText());
			}
		}else{
			reporter.reportFailed(pageName, "PNR / TicketNumber element is not displayed");
		}
	}


	/**
	 * Validates the Panel based on itinerary type
	 * @param itinerary DEPARTURE or RETURN
	 * @throws Exception
	 */
	public void validateItineraryPanels(Itinerary itinerary) throws Exception{
		List<WebElement> panelElts = CheckUtils.getElements(getTest(), LOC_TRIP_PANELS);
		if(panelElts!=null && !panelElts.isEmpty()){
			if(itinerary.equals(Itinerary.DEPARTURE)){
				WebElement flightNumber = CheckUtils.getElement(getTest(), panelElts.get(0), LOC_FLIGHT_NUMBER);
				if( flightNumber!=null ){
					addValue(itinerary+" FLIGHT NUMBER", flightNumber.getText().trim());
					reporter.reportPassed(itinerary+" Flight Number : ", flightNumber.getText().trim());
				}else{
					reporter.reportFailed(pageName , "Departure Flight Number is not displayed properly");
				}
			}else if(panelElts.size()>1 && itinerary.equals(Itinerary.RETURN)){
				WebElement flightNumber = CheckUtils.getElement(getTest(), panelElts.get(1), LOC_FLIGHT_NUMBER);
				if(flightNumber !=null){
					addValue(itinerary+" FLIGHT NUMBER", flightNumber.getText().trim());
					reporter.reportPassed(itinerary+" RETURN", flightNumber.getText().trim());
				}else{
					reporter.reportFailed(pageName, "Return Flight Number is not displayed properly");
				}
			}
		}
	}


	/**
	 * Clicks On Change Trip Button
	 * @throws Exception
	 */
	public ATCSearchPage actionChangeTrip() throws Exception{
		List<WebElement> submitButtons = CheckUtils.getElements(getTest(), LOC_BUTTON_CHANGE_TRIP);
		for(WebElement button : submitButtons){
			if(button.getText().trim().toLowerCase().contains("change")){
				ClickUtils.clickButtonOrFail(getTest(), button, "Change Trip Button could not clicked");
				reporter.report(  pageName , "Change Trip Button has been clicked ");
				waitForOverlayLoading(60);
				break;
			}
		}
		return PageFactory.getPageObject(ATCSearchPage.class);
	}

	/**
	 * Clicks MealPreference Button
	 * @throws Exception
	 */
	public void clickSelectMealPreference() throws Exception{
		ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SELECT_MEAL, "SelectMealPreference couldn't be clicked" , "SelectMealPreference clicked successfully");
	}


	public CheckinSendFlightInfo actionClickCheckIn() throws Exception{
		ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CHECKIN, "CheckIn Button couldn't be clicked", "CheckIn Button clcked successfully" );
		return PageFactory.getPageObject(CheckinSendFlightInfo.class);
	}

	public void clickSelectSeats() throws Exception
	{
		List<WebElement> seatsButtons = CheckUtils.getElements(getTest(), LOC_BUTTON_SELECT_SEATS);
		for(int i=0;i<=1;i++)
		{
			reporter.report("Time ", "Running"+i);
			WebElement button=seatsButtons.get(i);
			ClickUtils.clickButtonOrFail(getTest(), button, "Select Seats Button couldn't be clicked", "Select Seats Button clcked successfully" );

			waitForOverlayLoading(200);
			CommonSeatMapPage commonSeatPage=PageFactory.getPageObject(CommonSeatMapPage.class);
			commonSeatPage.validateSelectedSeat();
			commonSeatPage.clickBackButton();

			//eturn PageFactory.getPageObject(CheckinSendFlightInfo.class);
		}
	}

	public void clickMyMealPrefereance(){
		waitForOverlayLoading(200);
		ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SELECT_MYMEAL, "SelectMealPreference couldn't be clicked" , "SelectMealPreference clicked successfully");
	}
}

