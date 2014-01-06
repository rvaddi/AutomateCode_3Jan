package com.amadeus.selenium.sqmobile.page.seat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.checkin.CheckinSendFlightInfo;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyFlightsPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;



public class CommonSeatMapPage extends SqMobileCommonPage{

	// Locators Of CommonSeatMap ---------------------------------------------------------

	protected static final By LOC_SUBMIT_SEAT = By.partialLinkText("Next Flight");
	protected static final By LOC_BUTTON_REVERT = By.id("revertBtn");
	protected static final By LOC_UNOCCUPIED_SEATS = By.cssSelector("input[atdelegate^='d']");
	protected static final By LOC_PASSNGR_ICONS= By.className("pax");

	protected static final By LOC_SEAT_NUMBER = By.className("seatnum");

	protected static final By LOC_MOVING_BANNER = By.id("movingBanner");
	protected static final By LOC_MOVING_BANNER_PRICE_INFO = By.id("seatPrice");
	protected static final By LOC_MOVING_BANNER_SEAT_INFO = By.id("charList");
	protected static final By LOC_MOVING_BANNER_FLIGHT_INFO = By.cssSelector(".is-seat>span");
	protected static final By LOC_BUSINESS_SEAT = By.className("bassinetSeat");
	protected static final By LOC_TX_SEAT_HEADER_TEXT = By.className("listStandard");
	protected static final By LOC_RD_SEAT_PAX_RADIO_SELECT = By.cssSelector("input[id^='seatPax']");
	protected static final By LOC_RD_SEAT_PAX_OLD_SEAT = By.className("seatNo");
	protected static final By LOC_LI_SEAT_CLEAR_SELECTION = By.partialLinkText("Clear seat selection");
	protected static final By LOC_TB_SEAT_LAYOUT = By.className("seatMap");
	protected static final By LOC_TB_SEAT_SAVE = By.partialLinkText("Save");
	protected static final By LOC_TB_SEAT_CANCEL = By.partialLinkText("Cancel");
	protected static final By LOC_TB_SEAT_TITLE = By.className("titlePageHome");
	protected static final By LOC_TB_SEAT_CONTINUE_PAYMENT = By.className("buttonRow2");
	protected static final By LOC_BUTTON_SEAT_SAVE = By.cssSelector("anchoredButton");
	protected static final By LOC_BUTTON_SEAT_BACK = By.cssSelector("div[class*=noBackButton] a");


	//----------------------------------------------------------------------------------

	public CommonSeatMapPage(SeleniumSEPTest test ,String pageName) throws Exception{
		super(test);
		this.pageName = pageName ;
	}

	private String pageName = "CommonSeatMapPage";

	public enum SeatPrefrence{
		BassinetSeat , Others ;
	}


	public enum Itinerary {
		DEPARTURE , RETURN ;
	}


	/**
	 * SelectSeats for All the passengers
	 * @throws Exception
	 */

	public void clickSeats() throws Exception{

		boolean passengerSelected  = false ;
		boolean seatNumberDisplayed = false ;
		List<WebElement> passengersElt = CheckUtils.getElements(getTest(), LOC_PASSNGR_ICONS); // passenger number
		List<WebElement> unoccupiedSeatsElt = CheckUtils.getElements(getTest(), LOC_UNOCCUPIED_SEATS); //
		int nbPax = passengersElt.size();
		if (nbPax > 0 ) {
			if(unoccupiedSeatsElt.size() > nbPax){
				for (int i = 0; i < nbPax ; i++) {
					// Selecting passenger
					passengerSelected =  selectPassenger(i+1);
					if(passengerSelected){
						// Selecting seat for the passenger
						ClickUtils.clickButtonOrFail(getTest(), unoccupiedSeatsElt.get(i), "Seat Couldn't be selected for passenger : "+(i+1), "Seat selected for the passenger : "+(i+1));
						seatNumberDisplayed = seatNumberDisplayedForPassenger(i+1);
						WebElement seatNumberElt = CheckUtils.getElement(getTest(), LOC_SEAT_NUMBER);
						if(seatNumberDisplayed){
							addValue("PASSENGER SEAT " + i, seatNumberElt.getText());
						}else{
							reporter.reportFailed(pageName, "Seat Number for ("+seatNumberElt.getText() + ") is not displayed properly after seat selection");
						}
					}else{
						reporter.reportFailed(pageName, "Passenger "+i+1 + " couldn't be selected");
					}
				}

			}
		}
	}


	/**
	 *  Selects a seats for all the passengers , clicks NextFlightButton if it is present and select the seats for return trip also
	 */
	public void bookSeatsForAllPax() throws Exception {
		clickSeats();
		if(nextFlightButtonPresent()){
			clickNextFlight();
			clickSeats();
		}
	}


	/**
	 * Validates the SeatMapPage
	 */
	public void validateSeatMapPage() {
		boolean revertButtonPresent = revertButtonPresent();
		if(!revertButtonPresent){
			reporter.reportFailed(pageName, "RevertButton not present");
		}

		// Validating for SeatAvailability
		List<WebElement> unoccupiedSeatsElt = CheckUtils.getElements(getTest(), LOC_UNOCCUPIED_SEATS);
		if(unoccupiedSeatsElt.isEmpty() || !(unoccupiedSeatsElt.size()>=1)){
			reporter.reportFailed("Number of Available Seats : ", ""+unoccupiedSeatsElt.size());
		}else{
			reporter.reportPassed("Number of Available Seats : ", ""+unoccupiedSeatsElt.size());
		}
	}


	/**
	 * To Select the passengers from the available passengers
	 * @throws Exception
	 * @param passengerNumber nth passenger to be selected (eg 1 for first passenger)
	 * @return true if the passenger is selected properly , false otherwise
	 */
	public boolean selectPassenger(int passengerNumber) {
		boolean passengerSelected = false ;
		List<WebElement> passengersElt = CheckUtils.getElements(getTest(), LOC_PASSNGR_ICONS); // passenger number
		if(passengerNumber<=passengersElt.size()){
			ClickUtils.clickButtonOrFail(getTest(), passengersElt.get(passengerNumber -1), "Passenger : "+passengerNumber + " Couldn't be selected");
			passengerSelected = true ;
		}else{
			reporter.reportFailed(pageName, "Passenger not available");
		}
		return passengerSelected ;
	}


	/**
	 * Checks whether the SeatNumber is displayed
	 * @param passengerNumber passenger number for which seat number is to be checked
	 * @return true if seatNumber is displayed else false
	 */
	public boolean seatNumberDisplayedForPassenger(int passengerNumber) {
		boolean seatNumberDisplayed = false;
		WebElement seatNumberElt = CheckUtils.getElement(getTest(), LOC_SEAT_NUMBER);
		if(seatNumberElt!=null && seatNumberElt.isDisplayed() && !seatNumberElt.equals("")){
			if(selectPassenger(passengerNumber)){
				seatNumberDisplayed = true ;
			}
		}
		return seatNumberDisplayed;
	}


	/**
	 * Clicks NextFlight Button
	 */
	public void clickNextFlight(){
		ClickUtils.clickButtonOrFail(getTest(), LOC_SUBMIT_SEAT, "NextFlight Button couldn't be clicked", "NextFlight Button clicked");
		reporter.reportPassed(pageName, "NextFlight Button clicked successfully");
	}


	/**
	 * Checks the presence of NextFlight Button
	 * @return true if button is present , false otherwise
	 */
	public boolean nextFlightButtonPresent() {
		boolean buttonPresent = CheckUtils.checkElementPresent(getTest(), LOC_SUBMIT_SEAT , "NextFlightButton is present");
		return buttonPresent;
	}


	/**
	 * Validates the price in moving banner
	 */
	public void validateBannerPrice()  {
		WebElement bannerPriceElt = CheckUtils.getElement(getTest(), LOC_MOVING_BANNER_PRICE_INFO);
		if(bannerPriceElt!=null && bannerPriceElt.isDisplayed()){
			reporter.reportPassed("Banner Price : ", "Banner Price displayed properly : "+bannerPriceElt.getText());
		}else{
			reporter.reportFailed("Banner Price : ", "Banner Price is not displayed");
		}
	}


	/**
	 * Checks the presence of RevertButton
	 * @return true if Revert Button is Present , false otherwise
	 */
	public boolean revertButtonPresent() {

		boolean buttonPresent = CheckUtils.checkElementPresent(getTest(), LOC_BUTTON_REVERT , "Revert is present");
		return buttonPresent;
	}

	public void validateSelectedSeat(){
		int i = 0;
		WebElement link1 = CheckUtils.getElement(getTest(), LOC_TB_SEAT_TITLE);
		if (link1 != null && link1.isDisplayed()) {
			i = i + 1;
			reporter.reportPassed("Validating Your Selected Seat Page", "YOUR SELECTED SEAT Name is displayed");
		}
		WebElement link2 = CheckUtils.getElement(getTest(), LOC_TB_SEAT_LAYOUT);
		if (link2 != null && link2.isDisplayed()) {
			i = i + 1;
			reporter.reportPassed("Validating Your Selected Seat Page", "seat layout is displayed");
		}
		WebElement link3 = CheckUtils.getElement(getTest(), LOC_BUTTON_SEAT_SAVE);
		if (link3 != null && link3.isDisplayed()) {
			i = i + 1;
			reporter.reportPassed("Validating Your Selected Seat Page", "Save Button is displayed");
		}
		else {
			reporter.reportFailed("Validating Your Selected Seat Page", "All the links are not displayed");
		}
	}

	public MyFlightsPage clickBackButton() throws Exception
	{
		ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SEAT_BACK, "Back Button not clicked");
		return PageFactory.getPageObject(MyFlightsPage.class);
	}
}
