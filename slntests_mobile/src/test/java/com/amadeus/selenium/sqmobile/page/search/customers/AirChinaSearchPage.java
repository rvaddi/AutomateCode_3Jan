package com.amadeus.selenium.sqmobile.page.search.customers;

import org.openqa.selenium.By;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.search.NonFlexiSearchPage;
import com.amadeus.selenium.utils.WaitUtils;


/**
 * SearchPage for AirChina
 * @author rshivaswamy
 *
 */


public class AirChinaSearchPage extends NonFlexiSearchPage{

	  /*
	   * Web Element Identifiers
	   *
	   */
	  protected static By LOC_LS_SEARCHPAGE_CABINCLASS = By.id("COMMERCIAL_FARE_FAMILY_1");
	  protected static By LOC_CSS_SEARCHPAGE_WARNMESSAGE = By.cssSelector(".msg.info");

	  String pageName = "AirChina Page";


	  public AirChinaSearchPage(SeleniumSEPTest test) throws Exception {
	    super(test);
	    validateWarnMessage();
	  }

	  private void validateWarnMessage() {

		    boolean isWarnMessageDisplayed =  WaitUtils.waitForElementPresent(getTest(), LOC_CSS_SEARCHPAGE_WARNMESSAGE, 30);
		    if (!isWarnMessageDisplayed ){
		      reporter.fail("Search Page warning message is NOT displayed  : check for DISP_MKTMNG_WARN_MSG param");
		    }
		    else{
		    	reporter.reportPassed("AirChina Page", "Search Page ");
		    }
		  }

	/**
	   * Filling Search Page
	   * @throws Exception
	   */

	  public void fillSearchPage() throws Exception{
		  super.fillSearchPage();
	  }
}
