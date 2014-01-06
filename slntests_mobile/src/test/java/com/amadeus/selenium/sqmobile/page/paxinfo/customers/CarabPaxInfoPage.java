package com.amadeus.selenium.sqmobile.page.paxinfo.customers;

import org.openqa.selenium.By;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage;
import com.amadeus.selenium.sqmobile.page.seat.SeatMapPage;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Carabbien Pax Info page
 * @author vbalasubramanian
 *
 */
public class CarabPaxInfoPage extends PaxInfoPage{

  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME = By.id("FIRST_NAME_1");
  protected static final By LOC_IN_PAX_INFO_HOME_PHONE = By.name("CONTACT_POINT_HOME_PHONE");


  public CarabPaxInfoPage(SeleniumSEPTest test) throws Exception {
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME, 30)){
      reporter.fail("This is not CarabPaxInfo Page");
    }else{
      reporter.reportPassed("CarabPaxInfo Page", "In CarabPaxInfo Page");
    }
  }


  /**
   * Validate Total PRice
   * -History : Created by Vigneshwaran Balasubramanian
   */
  @Override
  public void validateTotalPrice() throws Exception {
    super.validateTotalPrice();
  }

  /**
   * Select Seat
   * -History : Created by Vigneshwaran Balasubramanian
   */

  @Override
  public SeatMapPage clickSelectSeat() throws Exception {
    return super.clickSelectSeat();
  }

  /**
   * Fillpax Info
   */
  @Override
  public void fillPaxInfo() throws Exception {
    // TODO Auto-generated method stub
    super.fillPaxInfo();
  }

  /**
   * Fill contact Info
   */
  @Override
  public void fillContactInfo(String contacType, String areaCode, String contactNumber, String email,
      String countryCode, String smsNotificationNumber) {
    fillHomePhone();
    // TODO Auto-generated method stub
    super.fillContactInfo(contacType, areaCode, contactNumber, email, countryCode, smsNotificationNumber);
  }

  /**
   * Validate Pax Info page
   * -History : Created by Vigneshwaran Balasubramanian
   */
  @Override
  public void validatePaxInfoPage() throws Exception {
    super.validatePaxInfoPage();
  }


  /**
   * Fills Home Phone Number
   */
  public void fillHomePhone(){
    FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_HOME_PHONE, "5342586892", "Home Phone couldn't be filled successfully");
  }
}
