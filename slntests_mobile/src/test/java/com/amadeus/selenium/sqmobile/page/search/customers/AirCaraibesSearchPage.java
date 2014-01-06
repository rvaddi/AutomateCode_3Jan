package com.amadeus.selenium.sqmobile.page.search.customers;

import org.openqa.selenium.By;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.search.NonFlexiSearchPage;
/**
 * Search Page
 * @author rshivaswamy
 *
 */

public class AirCaraibesSearchPage extends NonFlexiSearchPage{

	  /*
	   * Web Element Identifiers
	   *
	   */
	  protected static By LOC_LS_SEARCHPAGE_CABINCLASS = By.id("COMMERCIAL_FARE_FAMILY_1");

	  String pageName = "AirCaraibes Page";


	  public AirCaraibesSearchPage(SeleniumSEPTest test) throws Exception {
	    super(test);
	  }

	  /**
	   * Filling Search Page
	   * @throws Exception
	   */
	  
	  public void fillSearchPage() throws Exception{
		  super.fillSearchPage();
	  }
}
