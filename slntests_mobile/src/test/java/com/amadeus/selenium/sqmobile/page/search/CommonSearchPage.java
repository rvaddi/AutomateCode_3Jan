package com.amadeus.selenium.sqmobile.page.search;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Search Page
 * @author rshivaswamy
 *
 */

public class CommonSearchPage extends SqMobileCommonPage{


	/*
	 * Web Element Identifiers
	 *
	 */
	protected static By LOC_IN_SEARCHPAGE_FROM = By.id("B_LOCATION_1");
	protected static By LOC_IN_SEARCHPAGE_TO = By.id("E_LOCATION_1");
	protected static By LOC_RB_SEARCHPAGE_RT = By.id("roundTrip");
	protected static By LOC_RB_SEARCHPAGE_OW = By.id("oneWay");
	protected static By LOC_LS_SEARCHPAGE_DEPARTURE_DATE = By.id("day1");
	protected static By LOC_LS_SEARCHPAGE_DEPARTURE_MONTH = By.id("month1");
	protected static By LOC_LS_SEARCHPAGE_RETURN_DATE = By.id("day2");
	protected static By LOC_LS_SEARCHPAGE_RETURN_MONTH = By.id("month2");
	protected static By LOC_CB_SEARCHPAGE_FLEXIDATES = By.id("DATE_RANGE_VALUE_1");
	protected static By LOC_CB_SEARCHPAGE_FLEXIDATES_VALUE = By.id("DATE_RANGE_VALUE_2");
	protected static By LOC_LS_SEARCHPAGE_CABINCLASS = By.id("CABIN_CLASS");
	protected static By LOC_LS_SEARCHPAGE_ADULT_COUNT = By.id("FIELD_ADT_NUMBER");
	protected static By LOC_LS_SEARCHPAGE_CHILD_COUNT = By.id("FIELD_CHD_NUMBER");
	protected static By LOC_LS_SEARCHPAGE_INFANT_COUNT = By.id("FIELD_INFANTS_NUMBER");
	protected static By LOC_CB_SEARCHPAGE_FLEXIDATES_CHECKBOX = By.name("DATE_RANGE_VALUE_FLEX");
	protected static By LOC_PB_SEARCHPAGE_BACK = By.className("back");
	protected static By LOC_BT_SEARCHPAGE_SEARCHBUTTON = By.cssSelector(".validation");

	// added for calendar UI
	protected static By LOC_CALENDAR_DATE= By.cssSelector(".ui-state-default");
	protected static By LOC_CALENDAR_MONTH= By.className("ui-datepicker-month");
	protected static By LOC_CALENDAR_YEAR= By.className("ui-datepicker-year");
	protected static By LOC_LS_SEARCHPAGE_DEPARTURE_DATE_CALENDAR = By.id("depdate");
	protected static By LOC_LS_SEARCHPAGE_RETURN_DATE_CALENDAR = By.id("retdate");

	protected static By LOC_CE_DEP_NEW_DATE_PICKER = By.id("depdate");
	protected static By LOC_CE_RET_NEW_DATE_PICKER = By.id("retdate");

	protected static By LOC_NEW_CALENDAR_MONTH = By.className("ui-datepicker-month");
	protected static By LOC_NEW_CALENDAR_YEAR = By.className("ui-datepicker-year");
	protected static By LOC_CAL_POP_REF = By.className("ui-datepicker-div");
	protected static By LOC_EG_SEARCHPAGE_CABINCLASS = By.id("COMMERCIAL_FARE_FAMILY_1");

	protected static By LOC_RB_SEARCHPAGE_WARN_MSG = By.cssSelector(".msg.warning>ul");


	String pageName = "Common Search Page";

	public CommonSearchPage(SeleniumSEPTest test) throws Exception {
		super(test);
	}

	//-----------------------------------------------------------------------------


	/**
	 * Fill Search Page
	 * @throws Exception
	 */

	public void fillSearchPage() throws Exception{

		WaitUtils.waitForElementVisible(getTest(), LOC_IN_SEARCHPAGE_FROM, 60);
		WebElement from = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_FROM);
		WebElement to = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_TO);
		WebElement roundTrip = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_RT);
		WebElement oneWay = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_OW);
		CommonUtils utils = new CommonUtils(getTest());
		String[] tripType = new String[]{"OW","RT"};

		//Enter the values in the fields

		fillDestinationFields(from, getValue("From").trim(), "FROM filed could not be entered");
		fillDestinationFields(to, getValue("To").trim(), "TO filed could not be entered");

		selectDates(roundTrip, oneWay, utils, tripType);

		String DepDate = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_DATE);
		String DepMonth = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_MONTH);
		String RetDate = getStringDate(LOC_LS_SEARCHPAGE_RETURN_DATE);
		String RetMonth = getStringDate(LOC_LS_SEARCHPAGE_RETURN_MONTH);

		//Save flight dates
		updateValue("DEPARTURE DATE", DepDate + " " + DepMonth);
		updateValue("RETURN DATE", RetDate + " " + RetMonth);

		handleFlexiDates();
		selectPAXCountAndCabin();
		this.automaticScreenShot();
	}


	/**
	 * Selecting PAX Count and Cabin Class
	 * @throws IOException
	 */
	public void selectPAXCountAndCabin() throws IOException {

		FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_CABINCLASS, getValue("Cabin Class").trim());
		FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_ADULT_COUNT, getValue("Nb Adult").trim());
		FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_CHILD_COUNT, getValue("Nb Child").trim());
	}


	/**
	 * Handling Flexi Dates operation
	 * @throws IOException
	 */
	public void handleFlexiDates() throws IOException {

		if (getValue("FLEXIBLE DATES").trim().equalsIgnoreCase("TRUE")) {
			// if the flexi dates are not selected then select
			if(!CheckUtils.getElement(getTest(), LOC_CB_SEARCHPAGE_FLEXIDATES_CHECKBOX).isSelected()){
				ClickUtils.clickButtonOrFail(getTest(), LOC_CB_SEARCHPAGE_FLEXIDATES_CHECKBOX, "Flexi Dates could not be selected");
			}

		}
		if (getValue("FLEXIBLE DATES").trim().equalsIgnoreCase("FALSE")) {
			// if flexi dates are selected uncheck it.
			if(CheckUtils.getElement(getTest(), LOC_CB_SEARCHPAGE_FLEXIDATES_CHECKBOX).isSelected()){
				ClickUtils.clickButtonOrFail(getTest(), LOC_CB_SEARCHPAGE_FLEXIDATES_CHECKBOX, "Flexi Dates could not be selected");
			}
		}
	}


	/**
	 * Select Dates
	 * @param roundTrip : WebElement for roundTrip radio button
	 * @param oneWay : WebElement for OneWay radio button
	 * @param utils : Utils for handling date operation
	 * @param tripType : Trip type to be selected RT or OW
	 * @throws IOException
	 */
	public void selectDates(WebElement roundTrip, WebElement oneWay, CommonUtils utils, String[] tripType)
	throws IOException {
		if (getValue("Trip Type").trim().equals(tripType[0])){

			ClickUtils.click(getTest(), oneWay);
			String Dep = utils.addDate("d MMM yyyy", "Date", new Integer (getValue("Dep Gap").trim()));
			String DepDate = (Dep.split(" "))[0];
			String DepMonth = (Dep.split(" "))[1]+" "+(Dep.split(" "))[2];
			FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_DATE, DepDate);
			FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_MONTH, DepMonth);
		}
		else{

			ClickUtils.click(getTest(), roundTrip);
			String Dep = utils.addDate("d MMM yyyy", "Date", new Integer (getValue("Dep Gap").trim()));
			String DepDate = (Dep.split(" "))[0];
			String DepMonth = (Dep.split(" "))[1]+" "+(Dep.split(" "))[2];

			FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_DATE, DepDate);
			FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_MONTH, DepMonth);

			String Ret = utils.addDate("d MMM yyyy", "Date", new Integer (getValue("Ret Gap").trim()));
			String RetDate = (Ret.split(" "))[0];
			String RetMonth = (Ret.split(" "))[1]+" "+(Ret.split(" "))[2];

			FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_RETURN_DATE, RetDate);
			FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_RETURN_MONTH, RetMonth);
		}
	}



	/**
	 * Click Search Button
	 */

	public void clickSearchButton(){

		List<WebElement> submitButtons = CheckUtils.getElements(getTest(), LOC_BT_SEARCHPAGE_SEARCHBUTTON);
		for(WebElement tempButoon : submitButtons){
			if(tempButoon.getText().trim().toLowerCase().contains("search")){
				ClickUtils.clickButtonOrFail(getTest(), tempButoon, "Search Button could not clicked");
				reporter.report(  pageName , "Search Button has been clicked on Search Page");
				waitForOverlayLoading(120);
				break;
			}
		}
	}



	/**
	 * Validate PAX drop-down
	 */
	public void validatePAXCount() {

		/*
		 * Cabin class, PAX count validation and list capturing
		 */

		WebElement nbAdult = CheckUtils.getElement(getTest(), LOC_LS_SEARCHPAGE_ADULT_COUNT);
		WebElement nbChild = CheckUtils.getElement(getTest(), LOC_LS_SEARCHPAGE_CHILD_COUNT);
		WebElement nbInfant = CheckUtils.getElement(getTest(), LOC_LS_SEARCHPAGE_INFANT_COUNT);
		boolean chkPaxCount = nbAdult.getText().contains("1") && (nbChild.getText().contains("0") || nbChild.getText().equals("")) ;/* && nbInfant.getText().contains("0")*/;
		reporter.reportPassed(pageName, "Below is the list of Cabin class displayed");
		if(!reportDropDownList(LOC_LS_SEARCHPAGE_CABINCLASS) && chkPaxCount ){
			reporter.reportPassed(pageName, "Cabin class and number of passengers is displayed correctly");
		}else{
			reporter.reportFailed(pageName, "Cabin class and number of passengers is not displayed correctly");
		}
	}


	public boolean reportDropDownList(By elmntLocator ) {

		boolean isListEmpty = true; //to keep count on number of list items
		WebElement cabinClass = CheckUtils.getElement(getTest(), elmntLocator);
		List<WebElement> cabinClassList = cabinClass.findElements(By.tagName("option"));
		for( Iterator<WebElement> i = cabinClassList.iterator(); i.hasNext();){
			reporter.report(" [] ",i.next().getText());
			isListEmpty = false;
		}

		return isListEmpty;
	}


	/**
	 * Validate Flexi Dates check box
	 */
	public  void validateFlexi() {

		WebElement flexDate = CheckUtils.getElement(getTest(), LOC_CB_SEARCHPAGE_FLEXIDATES);

		boolean isFlexiDatesDisplayed = CheckUtils.checkElementPresent(getTest(), LOC_CB_SEARCHPAGE_FLEXIDATES, "FlexiDates");
		if (isFlexiDatesDisplayed && !(flexDate.isSelected()) ){
			reporter.reportPassed(pageName, "Flexible dates check box is unchecked by default");
		}
		else{
			reporter.reportFailed(pageName, "Flexible dates check box is not displayed or not unchecked by default");
		}
	}

	/**
	 *Validate Date drop down
	 * @throws Exception
	 */

	public void validateDates() throws Exception {

		CommonUtils utils = new CommonUtils(getTest());
		String depDate = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_DATE);
		String depMonth = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_MONTH);
		String retDate = getStringDate(LOC_LS_SEARCHPAGE_RETURN_DATE);
		String retMonth = getStringDate(LOC_LS_SEARCHPAGE_RETURN_MONTH);


		String expDep = utils.addDate("dd MMM yyyy", "Date", 7);
		String expDepDate = (expDep.split(" "))[0];
		String expDepMonth = (expDep.split(" "))[1]+" "+(expDep.split(" "))[2];

		String expRet = utils.addDate("dd MMM yyyy", "Date", 21);
		String expRetDate = (expRet.split(" "))[0];
		String expRetMonth = (expRet.split(" "))[1]+" "+(expRet.split(" "))[2];

		if(expDepDate.contains(depDate) && depMonth.equals(expDepMonth) && expRetDate.contains(retDate) && retMonth.equals(expRetMonth)){
			reporter.reportPassed(pageName, "All the dates are displayed correctly");
		}
		else{
			reporter.reportFailed(pageName, "The departure or return dates are not displayed correctly");
		}
	}


	/**
	 * Get String Date
	 * @param elmtLocator : Element locator of Date drop down
	 * @return String value of the selected date
	 * @throws Exception
	 */
	public String getStringDate(By elmtLocator) throws Exception{

		String stringDate = (new Select(CheckUtils.getElement(getTest(), elmtLocator))).getFirstSelectedOption().getText();
		return stringDate;
	}


	/**
	 * Validate RoundTrip and OneWay Radio
	 */
	public void validateRTandOWRadio() {
		WebElement roundTrip = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_RT);

		boolean isRoundTrip = CheckUtils.checkElementPresent(getTest(), LOC_RB_SEARCHPAGE_RT, "roundTrip Radio Button");
		boolean isOneWayTrip = CheckUtils.checkElementPresent(getTest(), LOC_RB_SEARCHPAGE_OW, "oneWayTrip Radio Button");
		if(isRoundTrip && isOneWayTrip
				&& roundTrip.getAttribute("checked").equalsIgnoreCase("true")){
			reporter.reportPassed(pageName, "Round Trip radio button is selected by default");

		}else{
			reporter.reportFailed(pageName, "Radio buttons for trip type are not displayed properly");
		}
	}


	/**
	 * Validate FROM and TO fields
	 */
	public void validateFromNToFields() {
		boolean isFromDisplayed = CheckUtils.checkElementPresent(getTest(), LOC_IN_SEARCHPAGE_FROM, "FROM field");
		boolean isToDisplayed = CheckUtils.checkElementPresent(getTest(), LOC_IN_SEARCHPAGE_TO, "TO Field");

		if(isFromDisplayed && isToDisplayed){
			reporter.reportPassed(pageName, "From and To fields are displayed correctly");
		}else{
			reporter.reportFailed(pageName, "From and To fields are NOT displayed correctly");
		}
	}


	/**
	 * Fill destination fields
	 * @param dest : Airport / City code which is used to enter into From or TO
	 * @param destValue : Value of the field
	 * @param failMessage : Message which holds to be displayed incase of failure
	 * @throws IOException
	 */
	public void fillDestinationFields(WebElement dest, String destValue, String failMessage) throws IOException {
		FillUtils.fillInputOrFail(getTest(), dest, destValue, failMessage);
		WaitUtils.wait(2);
		List<WebElement> autoComplete1 = CheckUtils.getElements(getTest(), By.className("ui-corner-all"));
		for(WebElement opt : autoComplete1){
			if (opt.isDisplayed() && opt.getTagName().equalsIgnoreCase("a") && opt.getText().contains(destValue)) {
				opt.click();
				break;
			}
		}
	}


	/**
	 * Fill destination fields and Select the values from the ajax list based on the values entered
	 * @param dest : Airport / City code which is used to enter into From or TO
	 * @param destValue : Value of the field
	 * @param failMessage : Message which holds to be displayed incase of failure
	 * @throws IOException
	 */
	public void fillDestinationFieldsbyText(WebElement dest, String destValue, String failMessage) throws IOException {
		FillUtils.fillInputOrFail(getTest(), dest, destValue, failMessage);
		WaitUtils.wait(5);
		// WaitUtils.waitForElementVisible(getTest(), By.className("ui-corner-all"), 10);
		List<WebElement> autoComplete1 = CheckUtils.getElements(getTest(), By.className("ui-corner-all"));
		for(WebElement opt : autoComplete1){
			//  System.out.println("Ajax "+destValue+" value: "+ opt.getText()+" Tag Name: "+ opt.getTagName());
			if (opt.isDisplayed() && opt.getTagName().equalsIgnoreCase("a")) {
				if(opt.getText().contains("("+destValue+")")){
					//   System.out.println("(Inside if loop) Ajax "+destValue+" value: "+opt.getText()+" Tag Name: "+opt.getTagName());
					opt.click();
					break;
				}
			}
		}
	}



	/**
	 * Fills the departure date using calendar
	 * @throws Exception
	 * @author bsingh
	 */
	public String fillDeptDateUsingCalendar(int noOfDays) throws Exception{
		String date= null;
		WebElement  deptDateElt = CheckUtils.getElement(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_DATE_CALENDAR);
		if(deptDateElt!=null){
			ClickUtils.click(getTest(), deptDateElt);
			CommonUtils utils = new CommonUtils(getTest());
			date =  utils.fillDateUsingCalendar(noOfDays, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
		}else{
			reporter.reportFailed(pageName, "Departure Date couldn't be filled successfully using calendar ");
		}
		return date;
	}

	/**
	 * Fills the Return date using calendar
	 * @throws Exception
	 * @author bsingh
	 */
	public String fillReturnDateUsingCalendar(int noOfDays) throws Exception{
		String date = null;
		WebElement  retDateElt = CheckUtils.getElement(getTest(), LOC_LS_SEARCHPAGE_RETURN_DATE_CALENDAR);
		if(retDateElt!=null){
			ClickUtils.click(getTest(), retDateElt);
			CommonUtils utils = new CommonUtils(getTest());
			date = utils.fillDateUsingCalendar(noOfDays, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
		}else{
			reporter.reportFailed(pageName, "Return Date couldn't be filled successfully using calendar ");
		}
		return date;
	}


	/**
	 * Fills Details in the SearchPage having calendar in UI for Departure and Return Fields
	 * @throws Exception
	 * @author bsingh
	 */
	public void fillSearchDetailsForCalendarUI() throws Exception {


		WaitUtils.waitForElementVisible(getTest(), LOC_IN_SEARCHPAGE_FROM, 60);
		WebElement from = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_FROM);
		WebElement to = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_TO);

		selectTripType();


		//Enter the values in the fields - Using already designed methods
		fillDestinationFields(from, getValue("From").trim(), "FROM filed could not be entered");
		fillDestinationFields(to, getValue("To").trim(), "TO filed could not be entered");
		handleFlexiDates();
		String depDate = fillDeptDateUsingCalendar(Integer.parseInt(getValue("Dep Gap").trim()));
		addValue("DEPARTURE DATE", depDate);
		if(getValue("Trip Type").trim().equalsIgnoreCase("RT")){
			String retDate = fillReturnDateUsingCalendar(Integer.parseInt(getValue("Ret Gap").trim()));
			addValue("RETURN DATE", retDate);
		}
		selectPAXCountAndCabin();
		this.automaticScreenShot();
	}


	/**
	 * Selects TripType based on the value in properties file
	 * @throws Exception
	 * @author bsingh
	 */
	public void selectTripType() throws Exception {
		if(getValue("Trip Type").equalsIgnoreCase("RT")){
			ClickUtils.clickButtonOrFail(getTest(), LOC_RB_SEARCHPAGE_RT, "Trip Type - RT couldn't be selected", "Trip Type - RT selected");
		}else{
			ClickUtils.clickButtonOrFail(getTest(), LOC_RB_SEARCHPAGE_OW, "Trip Type - OW couldn't be selected", "Trip Type - OW selected");
		}
	}



	/**
	 * Select New Date Picker
	 * @param roundTrip : WebElement for roundTrip radio button
	 * @param oneWay : WebElement for OneWay radio button
	 * @param utils : Utils for handling date operation
	 * @param tripType : Trip type to be selected RT or OW
	 * @throws IOException
	 */
	public void selectNewDatePicker(WebElement roundTrip, WebElement oneWay,
			CommonUtils utils, String[] tripType) throws IOException{

		if (getValue("Trip Type").trim().equals(tripType[0])){

			ClickUtils.click(getTest(), oneWay);
			String Dep = utils.addDate("d MMMMM yyyy", "Date", new Integer (getValue("Dep Gap").trim()));
			String DepDate = (Dep.split(" "))[0];
			String DepMonth = (Dep.split(" "))[1];
			String DepYear = (Dep.split(" "))[2];

			FillUtils.selectByVisibleText(getTest(), LOC_NEW_CALENDAR_YEAR, DepYear);
			FillUtils.selectByVisibleText(getTest(), LOC_NEW_CALENDAR_MONTH, DepMonth);
			ClickUtils.click(getTest(), By.partialLinkText(DepDate));
		}
		else{

			ClickUtils.click(getTest(), roundTrip);
			String Dep = utils.addDate("d MMMMM yyyy", "Date", new Integer (getValue("Dep Gap").trim()));
			String DepDate = (Dep.split(" "))[0];
			String DepMonth = (Dep.split(" "))[1];
			String DepYear = (Dep.split(" "))[2];

			ClickUtils.click(getTest(), LOC_CE_DEP_NEW_DATE_PICKER);
			// select year
			FillUtils.selectByVisibleText(getTest(), LOC_NEW_CALENDAR_YEAR, DepYear);
			// select month
			FillUtils.selectByVisibleText(getTest(), LOC_NEW_CALENDAR_MONTH, DepMonth);
			// select date
			ClickUtils.click(getTest(), By.partialLinkText(DepDate));
			// the date picker pop-up will be closed

			ClickUtils.click(getTest(), LOC_CE_RET_NEW_DATE_PICKER);

			String Ret = utils.addDate("d MMMMM yyyy", "Date", new Integer (getValue("Ret Gap").trim()));
			String RetDate = (Ret.split(" "))[0];
			String RetMonth = (Ret.split(" "))[1];
			String RetYear = (Ret.split(" "))[2];

			FillUtils.selectByVisibleText(getTest(), LOC_NEW_CALENDAR_YEAR, RetYear);
			FillUtils.selectByVisibleText(getTest(), LOC_NEW_CALENDAR_MONTH, RetMonth);
			ClickUtils.click(getTest(), By.partialLinkText(RetDate));

		}
	}
	/**
	 * Fills Details in the SearchPage having calendar in UI for Departure and Return Fields for Egypt
	 * @throws Exception
	 * @author rvaddi TestId :: 5457
	 */
	public void fillEgyptSearchDetailsForCalendarUI() throws Exception {


		WaitUtils.waitForElementVisible(getTest(), LOC_IN_SEARCHPAGE_FROM, 60);
		WebElement from = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_FROM);
		WaitUtils.waitForElementVisible(getTest(), LOC_IN_SEARCHPAGE_TO, 60);
		WebElement to = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_TO);
		selectTripType();
		//Enter the values in the fields - Using already designed methods
		fillDestinationFields(from, getValue("From").trim(), "FROM filed could not be entered");
		fillDestinationFields(to, getValue("To").trim(), "TO filed could not be entered");
		handleFlexiDates();
		String depDate = fillDeptDateUsingCalendar(Integer.parseInt(getValue("Dep Gap").trim()));
		addValue("DEPARTURE DATE", depDate);
		if(getValue("Trip Type").trim().equalsIgnoreCase("RT")){
			String retDate = fillReturnDateUsingCalendar(Integer.parseInt(getValue("Ret Gap").trim()));
			addValue("RETURN DATE", retDate);
		}
		selectPAXCountAndCabin();
		this.automaticScreenShot();
	}

	public void validatePAXCounts() {
		/* For Egypt */
		WebElement nbAdult = CheckUtils.getElement(getTest(), LOC_LS_SEARCHPAGE_ADULT_COUNT);
		WebElement nbChild = CheckUtils.getElement(getTest(), LOC_LS_SEARCHPAGE_CHILD_COUNT);
		boolean chkPaxCount = nbAdult.getText().contains("1") && (nbChild.getText().contains("0") || nbChild.getText().equals("")) ;/* && nbInfant.getText().contains("0")*/;
		reporter.reportPassed(pageName, "Below is the list of Cabin class displayed");
		if(!reportDropDownList(LOC_EG_SEARCHPAGE_CABINCLASS) && chkPaxCount ){
			reporter.reportPassed(pageName, "Cabin class and number of passengers is displayed correctly");
		}else{
			reporter.reportFailed(pageName, "Cabin class and number of passengers is not displayed correctly");
		}
	}




	/**
	 * Selecting PAX Count and Cabin Class -- Egypt
	 * @throws IOException
	 */
	public void selectPAXCountAndCabins() throws IOException {

		FillUtils.selectByVisibleText(getTest(), LOC_EG_SEARCHPAGE_CABINCLASS, getValue("Cabin Class").trim());
		FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_ADULT_COUNT, getValue("Nb Adult").trim());
		FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_CHILD_COUNT, getValue("Nb Child").trim());
	}

	public void verifyWarningMessage()
	{

		boolean isFromDisplayed = CheckUtils.checkElementPresent(getTest(), LOC_RB_SEARCHPAGE_WARN_MSG, "Warning Message");

		if(isFromDisplayed ){
			reporter.reportPassed(pageName, "Warning Message displayed correctly");

		}else{
			reporter.reportFailed(pageName, "From and To fields are NOT displayed correctly");
		}
	}

	public void modfiyDate() throws Exception
	{

		CommonUtils utils = new CommonUtils(getTest());

		int  tempRet= Integer.parseInt(getValue("Dep Gap").trim());
		tempRet=tempRet+5;
		String Ret = utils.addDate("d MMM yyyy", "Date", tempRet);
		String RetDate = (Ret.split(" "))[0];
		String RetMonth = (Ret.split(" "))[1]+" "+(Ret.split(" "))[2];

		FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_RETURN_DATE, RetDate);
		FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_RETURN_MONTH, RetMonth);
	}
}
