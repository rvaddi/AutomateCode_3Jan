package com.amadeus.selenium.sqmobile.page.search.customers;

import java.io.IOException;

import org.openqa.selenium.By;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.search.SearchPage;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Search Page
 * @author rshivaswamy
 *
 */

public class EVASearchPage extends SearchPage{

  /*
   * Web Element Identifiers
   *
   */
  protected static By LOC_LS_SEARCHPAGE_CABINCLASS = By.id("COMMERCIAL_FARE_FAMILY_1");

  String pageName = "EVASearch Page";


  public EVASearchPage(SeleniumSEPTest test) throws Exception {
    super(test);
    readTestData();
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
