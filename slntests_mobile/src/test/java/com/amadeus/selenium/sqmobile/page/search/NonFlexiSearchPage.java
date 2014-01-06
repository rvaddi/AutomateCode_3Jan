package com.amadeus.selenium.sqmobile.page.search;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class NonFlexiSearchPage extends SearchPage{

	  /*
	   * Web Element Identifiers
	   *
	   */
	  protected static By LOC_LS_SEARCHPAGE_CABINCLASS = By.id("COMMERCIAL_FARE_FAMILY_1");


	  public NonFlexiSearchPage(SeleniumSEPTest test) throws Exception {
	    super(test);
	  }

	  /**
	   * Selecting PAX Count and Cabin Class
	   * @throws IOException
	   */
	  public void selectPAXCountAndCabin() throws IOException {

	    FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_CABINCLASS, getValue("Cabin Class").trim());
	    FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_ADULT_COUNT, getValue("Nb Adult").trim());
	    FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_CHILD_COUNT, getValue("Nb Child").trim());
	  }
	  
	  
	  /**
	   * Filling Search Page
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

		    selectPAXCountAndCabin();
		    this.automaticScreenShot();
		  }
}
