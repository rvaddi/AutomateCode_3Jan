package com.amadeus.selenium.sqmobile.page.search.customers;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.search.SearchPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Search Page
 * @author bsingh
 *
 */

public class IcelandAirSearchPage extends SearchPage{

  /*
   * Web Element Identifiers
   *
   */
  protected static By LOC_LS_SEARCHPAGE_CABINCLASS = By.id("COMMERCIAL_FARE_FAMILY_1");

  String pageName = "IceLandSearch Page";


  public IcelandAirSearchPage(SeleniumSEPTest test) throws Exception {
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
}
