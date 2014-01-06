package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.checkin.CheckInFlightSelection;
import com.amadeus.selenium.sqmobile.page.checkin.CheckInFlightSelection.Itinerary;
import com.amadeus.selenium.sqmobile.page.checkin.CheckinMissingInfoPage;
import com.amadeus.selenium.sqmobile.page.checkin.CheckinSendFlightInfo;
import com.amadeus.selenium.sqmobile.page.confirmation.CheckinConfirmationPage;
import com.amadeus.selenium.sqmobile.page.review.CheckInSummaryPage;
import com.amadeus.selenium.sqmobile.page.seat.CheckInSeatMapPage;


public class Test_SQMobile_Checkin_Connecting_Flight_OW extends SeleniumSQTest {

  //Local Setup -- Required only during maintenance
  @Override
  public void localSetUp() throws Exception {
    //comment this once coding is done
    setDriverClass(FirefoxDriver.class);
    setBaseUrl("http://test17.dev.amadeus.net/");
    setDebugMode(false);
  }


  @Before
  public void resizeBrowser(){
    getDriverInstance().manage().window().setSize(new Dimension(600,800));
  }


  @Override
  public FirefoxProfile localGetFirefoxProfile() throws Exception {
    // final String firebugPath = "c:\\firebug-1.9.2.xpi";
    FirefoxProfile profile = new FirefoxProfile();
    // profile.addExtension(new File(firebugPath));
    profile.setAcceptUntrustedCertificates(true);
    // profile.setPreference("extensions.firebug.currentVersion", "1.9.2");
    // Add more if needed
    return profile;

  }

  @Test
  public void scenario() throws Exception {

    HelperWelcome.openCustomerSpecificWelcomePage(com.amadeus.selenium.sqmobile.helper.HelperWelcome.Customer.SQMOBILE);

    /* Checkin Procedure starts */
    CheckinSendFlightInfo flightinfo = PageFactory.getPageObject(CheckinSendFlightInfo.class);
    flightinfo.fillIdentificationInfoForGuestFromExcel();
    flightinfo.clickContinueForGuest();
    flightinfo.handleMultiPaxPage();

    CheckInFlightSelection checkInFlightSelection = PageFactory.getPageObject(CheckInFlightSelection.class);
    checkInFlightSelection.ValidateCheckInFlightSelectionPage();
    checkInFlightSelection.selectFlightsForCheckin(Itinerary.DEPARTURE);
    checkInFlightSelection.clickContinue();

    CheckinMissingInfoPage missinginfopage = PageFactory.getPageObject(CheckinMissingInfoPage.class);
    missinginfopage.fillCheckInMissingInfoPage();
    missinginfopage.clickContinueButton();

    CheckInSummaryPage checkInSummaryPage = PageFactory.getPageObject(CheckInSummaryPage.class);
    checkInSummaryPage.validateCheckinSummary();
    checkInSummaryPage.clickContinue();

    CheckinConfirmationPage checkinConfirmationPage = PageFactory.getPageObject(CheckinConfirmationPage.class);
    checkinConfirmationPage.validateCheckInConfirmaitonPage();
    checkinConfirmationPage.validateMessage("checked-in");
    checkinConfirmationPage.clickSeatButton();


    CheckInSeatMapPage checkInSeatMapPage = PageFactory.getPageObject(CheckInSeatMapPage.class);
    checkInSeatMapPage.selectSeatForAllPax();
    checkInSeatMapPage.clickProceed();
    checkInSeatMapPage.validateMessage("seats have been successfully changed");

    // Receiving BoardingPass via email
    // checkinConfirmationPage.receiveBoardingPassByMail("email@email.com");
    checkinConfirmationPage.clickExitCheckin();

    flightinfo.fillIdentificationInfoForGuest();
    flightinfo.clickContinueForGuest();

    checkInSummaryPage.validateCheckinSummary();
    checkInSummaryPage.clickManageCheckIn();

    // Now , Validation After the Boarding Pass has been received
    // checkinConfirmationPage.validateAfterBoardingPassGeneration();


    String failedSteps = this.getFailedSteps();
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }
  }
}
