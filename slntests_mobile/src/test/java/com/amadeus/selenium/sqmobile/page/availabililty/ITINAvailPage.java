package com.amadeus.selenium.sqmobile.page.availabililty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class ITINAvailPage   extends CommanAvailPage{

	static String pageName = "ITIN Avail Page";

	protected static By LOC_PB_OUTBOUND_FLIGHTNUMBER = By.className("flight-number");
	protected static By LOC_PB_ITIN_AVAIL_TITLE = By.cssSelector(".sum>h1");
	protected static By LOC_PB_OUTBOUND_Date = By.cssSelector(".sum>p");
	protected static By LOC_PB_OUTBOUNDPANEL_CSS_LIST = By.cssSelector(".panel.list");
	protected static By LOC_PB_UNGROUPEDLIST_REFERENCE = By.className("ungroupedList");
	protected static By LOC_PB_OUTBOUND_TYPE = By.className("outbound");
	protected static By LOC_PB_INBOUND_TYPE = By.className("inbound");
	protected final static By LOC_PB_ITIN_SORTBY = By.className("sortButton");
	protected static By LOC_PB_UNGROUPED_FLIGHTLIST = By.cssSelector(".ungroupedList>li");
	

	public ITINAvailPage(SeleniumSEPTest test)
			throws Exception {
		super(test, pageName);
		boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_PB_OUTBOUND_FLIGHTNUMBER, 40);
		if(elementPresent){
			reporter.reportPassed(pageName, "OutBoundAvail Page loaded");
		}else{
			reporter.fail("OutBoundAvail Page not loaded ");
		}
	}


	/**
	 * Validate ITINAvail Page
	 * @throws IOException
	 */
	public void validatePage() throws IOException{

		CheckUtils.checkElementValue(getTest(), LOC_PB_ITIN_AVAIL_TITLE, "Title", "Select your trip");

		WebElement title = CheckUtils.getElement(getTest(), LOC_PB_OUTBOUND_Date);

		// Validate the From and To cities which are displayed
		// Use className "sum" for getting from and to cities.

		validateCityPair(title);

		List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_OUTBOUNDPANEL_CSS_LIST);

		// get the count of FFHeaders displayed
		int counter = 0;
		for (WebElement familyFare : fareFamily) {
			if (familyFare.isDisplayed()) {
				counter++;
			}
		}

		reporter.report(pageName, counter + " fare families are displayed");

		for(WebElement singleFareFamily : fareFamily ){
			expandAndValidateEachFamily(singleFareFamily);
		}
		this.automaticScreenShot();
	}




	/**
	 * Validate City Pair
	 * @param title : Used as a ref element to get the respective elements
	 * @throws IOException
	 */
	private void validateCityPair(WebElement title) throws IOException {

		List<WebElement> cityPair = CheckUtils.getElements(getTest(), LOC_PB_OUTBOUND_Date);

		if(cityPair.size() == 2 &&
				( (cityPair.get(0)).getText().length() >= 1 &&  (cityPair.get(1)).getText().length() >= 1 ) ){
			reporter.reportPassed(pageName, "Cities is displayed correctly");

			String firstCityPairs[] = (cityPair.get(0)).getText().trim().split(" ");

			addValue("FROM CITY", firstCityPairs[4]);
			addValue("TO CITY", firstCityPairs[6]);

			String secondCityPage[] = (cityPair.get(1)).getText().trim().split(" ");

			if(firstCityPairs[4].equalsIgnoreCase(secondCityPage[6])  && firstCityPairs[6].equalsIgnoreCase(secondCityPage[4]) ){
				reporter.report(pageName, "City Pair displayed properly : " + firstCityPairs[4] + " --> " + firstCityPairs[6]);
			}else{
				reporter.reportFailed(pageName, "Cities is not displayed correctly");
			}

		}else{
			reporter.reportFailed(pageName, "Cities is not displayed correctly");
		}
	}


	/**
	 * Method to select First DirectOutBound and DirectInbound Flight 
	 * @throws IOException
	 */
	public void selectDirectOutBoundDirectInBoundFlight() throws IOException{
		
		if(selectTheFlight("Direct","Direct")){
			reporter.report("ITINAvail Page : ", "Direct OutBound and Direct InBound Flights were selected");
		}else{
			reporter.reportFailed("ITINAvail Page : ", "There were NO Direct OutBound and Direct InBound Flights");
		}
	}
	

	/**
	 * Method to select first connecting outbound and connecting inbound flight
	 * @throws IOException
	 */
	public void selectConnectingoutBoundConnectingInBoundFlight() throws IOException{
		
		if(selectTheFlight("Stop","Stop")){
			reporter.report("ITINAvail Page : ", "Connecting OutBound and Connecting InBound Flights were selected");
		}else{
			reporter.reportFailed("ITINAvail Page : ", "There were NO Connecting OutBound and Connecting InBound Flights");
		}
		
	}
	
	
	/**
	 * Method to select first direct outBound and connecting Inbound flight
	 * @throws IOException
	 */
	
	public void selectDirectOutBoundConnectingInBoundFlight() throws IOException{
		
		if(selectTheFlight("Direct","Stop")){
			reporter.report("ITINAvail Page : ", "Direct OutBound and Connecting InBound Flights were selected");
		}else{
			reporter.reportFailed("ITINAvail Page : ", "There were NO Direct OutBound and Connecting InBound Flights");
		}
		
	}
	
	
	/**
	 * Method to select connecting outBound and Direct inbound flight
	 * @throws IOException
	 */
	public void selectConnectingOutBoundDirectInBoundFlight() throws IOException{
		if(selectTheFlight("Stop","Direct")){
			reporter.report("ITINAvail Page : ", "Connecting OutBound and Direct InBound Flights were selected");
		}else{
			reporter.reportFailed("ITINAvail Page : ", "There were NO Connecting OutBound and Direct InBound Flights");
		}
	}
	

	/**
	 * Expand and Validate Each Family Fare displayed in the avail page
	 * @param singleFareFamily
	 */
	protected void expandAndValidateEachFamily(WebElement singleFareFamily) {

		if (singleFareFamily.isDisplayed()) {
			expandFFList(singleFareFamily);

			validateITINSorting(singleFareFamily);
		}
	}

	/**
	 * Validate the ITIN Sorting elements displayed in the avail page of each Family fare
	 * @param singleFareFamily
	 */
	protected void validateITINSorting(WebElement singleFareFamily) {

		// select the sort by drop down
		WebElement sortByDropDown = CheckUtils.getElement(getTest(), singleFareFamily, LOC_PB_ITIN_SORTBY);

		sortingChecker(sortByDropDown, LOC_PB_OUTBOUND_TYPE, "DEPTIMEOUT", By.cssSelector(".departure.hour"));

		sortingChecker(sortByDropDown, LOC_PB_INBOUND_TYPE, "DEPTIMEIN", By.cssSelector(".departure.hour"));

		sortingChecker(sortByDropDown, LOC_PB_OUTBOUND_TYPE, "ARRTIMEOUT", By.cssSelector(".arrival.hour"));

		sortingChecker(sortByDropDown, LOC_PB_INBOUND_TYPE, "ARRTIMEIN", By.cssSelector(".arrival.hour"));

		sortingChecker(sortByDropDown, LOC_PB_OUTBOUND_TYPE, "DUROUT", By.cssSelector(".duration"));

		sortingChecker(sortByDropDown, LOC_PB_INBOUND_TYPE, "DURIN", By.cssSelector(".duration"));
	}

	
	/**
	 * Method to check for Sorting
	 * @param sortByDropDown
	 * @param tripTYpe
	 * @param dropDownValue
	 * @param sortBy
	 */

	protected void sortingChecker(WebElement sortByDropDown, By tripTYpe , String dropDownValue, By sortBy){


		List<Integer> webPageList = new ArrayList<Integer>();
		
		FillUtils.selectByValue(getTest(), sortByDropDown, dropDownValue); // sort by departure time of outbound flights
		WebElement ungroupdList = CheckUtils.getElement(getTest(), LOC_PB_UNGROUPEDLIST_REFERENCE);
		List<WebElement> outBoundLists  = CheckUtils.getElements(getTest(), ungroupdList, tripTYpe );

		for (WebElement eachOutBound : outBoundLists) {
			String onlyValues = CheckUtils.getElement(getTest(), eachOutBound, sortBy).getText().replaceAll("[^0-9]","");
			webPageList.add(Integer.parseInt(onlyValues));
		}

		if(isListSorted(webPageList)){
			reporter.report("ITINAvail Page : ", dropDownValue + " is sorted in ascending order ");
		}else{
			reporter.reportFailed("ITINAvail Page : ", dropDownValue + " is NOT sorted in ascending order ");
		}
	}


	/**
	 * Method to get the list of valid flight details
	 * 
	 */
	public List<WebElement> getValidFlightList(WebElement singleFareFamily){

		WebElement sortByDropDown = CheckUtils.getElement(getTest(), singleFareFamily, LOC_PB_ITIN_SORTBY);
		FillUtils.selectByValue(getTest(), sortByDropDown, "DEPTIMEOUT");

		WaitUtils.waitForElementPresent(getTest(), LOC_PB_ITIN_SORTBY, 5);
		WebElement flightSpan = CheckUtils.getElement(getTest(), singleFareFamily, LOC_PB_UNGROUPEDLIST_REFERENCE);
		List<WebElement> flightSpans =  CheckUtils.getElements(getTest(), flightSpan, By.tagName("li"));
		List<WebElement> flightList = new ArrayList<WebElement>();

		for (WebElement tempFlight : flightSpans) {
			if (tempFlight.isDisplayed()) {
				WebElement validElement = CheckUtils.getElement(getTest(), tempFlight, LOC_PB_BOUND_CLASS_FLIGHTNUMBER);
				if (validElement != null) {
					flightList.add(tempFlight);
				}
			}
		}
		return flightList;
	}


	/**
	 * Method to check whether the list is in ascending order
	 * @return true : is in ascending order, False : if the list is not in ascending order
	 */
	private static boolean isListSorted(List<Integer> intList) {
		List<Integer> copy =  new ArrayList(intList);
		Collections.sort(copy);
		return intList.equals(copy);
	}
	
	
	/**
	 * Method to select Flight based on Outbound and inbound flight type Direct / connecting
	 * @param outBoundFlightType
	 * @param inBoundFlightType
	 */
	private boolean selectTheFlight(String outBoundFlightType, String inBoundFlightType) {

		List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_OUTBOUNDPANEL_CSS_LIST);

		for(WebElement singleFareFamily : fareFamily ){
			if (singleFareFamily.isDisplayed()) {
				expandFFList(singleFareFamily);
				
				WebElement sortByDropDown = CheckUtils.getElement(getTest(), singleFareFamily, LOC_PB_ITIN_SORTBY);
				FillUtils.selectByValue(getTest(), sortByDropDown, "DEPTIMEOUT"); // sort by departure time of outbound flights
				
				WebElement ungroupdList = CheckUtils.getElement(getTest(), LOC_PB_UNGROUPEDLIST_REFERENCE);
				List<WebElement> flightPairList  = CheckUtils.getElements(getTest(), ungroupdList, LOC_PB_UNGROUPED_FLIGHTLIST );
				
				for (WebElement eachFlightPair : flightPairList) {
					
					WebElement outBoundSegment = CheckUtils.getElement(getTest(), eachFlightPair, LOC_PB_OUTBOUND_TYPE);
					WebElement inBoundSegment = CheckUtils.getElement(getTest(), eachFlightPair, LOC_PB_INBOUND_TYPE);
					
					if(CheckUtils.getElement(getTest(), outBoundSegment, LOC_PB_OUTBOUND_FLIGHTNUMBER).getText().contains(outBoundFlightType) &&
					CheckUtils.getElement(getTest(), inBoundSegment, LOC_PB_OUTBOUND_FLIGHTNUMBER).getText().contains(inBoundFlightType)){
						outBoundSegment.click();
						return true;
					}
				}
			}
		}
		return false;
	}
	
}

