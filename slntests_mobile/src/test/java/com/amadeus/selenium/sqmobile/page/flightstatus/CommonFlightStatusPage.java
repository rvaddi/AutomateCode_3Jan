package com.amadeus.selenium.sqmobile.page.flightstatus;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CommonFlightStatusPage extends SqMobileCommonPage {

	protected static final By LOC_FLIGHT_DETAILS = By.cssSelector(".details>dl");
	protected static final By LOC_TEXT_FLIGHT_NUMBER = By.id("fcode_num");
	protected static final By LOC_BUTTON_SUBMIT = By.cssSelector("button[class=validation]");
	protected static final By LOC_BACK_BUTTON = By.cssSelector("button[class*=cancel]");
	protected static final By LOC_SELECT_DAY = By.id("dd");
	protected static final By LOC_SELECT_MONTH = By.id("MMM");
	protected static final By LOC_SELECT_YEAR = By.id("YYYY");

	private String pageName = "Flight Status Page" ;

	public CommonFlightStatusPage(SeleniumSEPTest test) throws Exception {
		super(test);
		boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_SUBMIT, 30);
		if(elementPresent){
			reporter.reportPassed("CommonFlightStatus Page", "In CommonFlightStatus Page");
		}else{
			reporter.fail("CommonFlightStatus Page not loaded ");
		}
	}


	/**
	 * Fills FlightNumber and Number of Days
	 * @param flightNumber
	 * @param numOfDays
	 * @throws Exception
	 * @author bsingh
	 */
	public void fillFlightStatusInfo(String flightNumber , int numOfDays) throws Exception{
		fillFlightNumber(flightNumber);
		fillDate(numOfDays);
	}

	/**
	 * Fills Flight Number in the text field
	 * @param flightNumber
	 * @author bsingh
	 * @throws IOException
	 */
	public void fillFlightNumber(String flightNumber) throws IOException{
		FillUtils.fillInputOrFail(getTest(), LOC_TEXT_FLIGHT_NUMBER, flightNumber, "Flight Number couldn't be filled successfully");
		addValue("FLIGHT NUMBER", flightNumber);
		reporter.reportPassed("Flight Number : ", flightNumber);
	}


	/**
	 * Fills Date as per the number of days passed in argument
	 * @param numOfDays Number of Days from the current date for which date is to fbe filled
	 * @throws Exception
	 * @author bsingh
	 */
	public void fillDate(int numOfDays) throws Exception{
		CommonUtils utils = PageFactory.getPageObject(CommonUtils.class);
		String[] dateReturned = utils.addDate("dd MMM yyyy", numOfDays).split(" ");
		String day = dateReturned[0];
		//adding
		char dayChar1=day.charAt(0);
		char dayChar2=day.charAt(1);
		String temp=Character.toString(dayChar1);
		String dayTrim=Character.toString(dayChar2);

		String month = dateReturned[1].toUpperCase();
		String year = dateReturned[2];

		if("0".equalsIgnoreCase(temp))
		{
			FillUtils.fillSelectByValueOrFail(getTest(), LOC_SELECT_DAY, dayTrim, "Day Couldn't be filled successfully");
			reporter.reportPassed("Date : ", dayTrim);
		}
		else
		{
			FillUtils.fillSelectByValueOrFail(getTest(), LOC_SELECT_DAY, day.trim(), "Day Couldn't be filled successfully");
			reporter.reportPassed("Date : ", day);
		}
		FillUtils.fillSelectByValueOrFail(getTest(), LOC_SELECT_MONTH, month.trim() , "Month Couldn't be filled successfully");
		reporter.reportPassed("Month : ", month);
		FillUtils.fillSelectByValueOrFail(getTest(), LOC_SELECT_YEAR, year.trim() , "Year Couldn't be filled successfully");
		reporter.reportPassed("Year : ", year);
	}


	/**
	 * Clicks FlightInfo Button
	 * @author bsingh
	 */
	public void actionFlightInfo(){
		ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SUBMIT, "GetFlightInfo Button could not clicked");
		reporter.reportPassed( pageName , "GetFlightInfo Button has been clicked on Search Page");
		waitForOverlayLoading(120);

	}



	/**
	 * Clicks Back Button
	 * @author bsingh
	 */
	public void clickBack(){
		waitForOverlayLoading(120);
		waitForOverlayLoading(120);
		ClickUtils.clickButtonOrFail(getTest(), LOC_BACK_BUTTON, "Back Button could not clicked");
		reporter.reportPassed( pageName , "Back Button has been clicked on Search Page");
	}



}