package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.InBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.customers.SQMobileHomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.SQMobilePaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.PaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.SQMobileSearchPage;
import com.amadeus.selenium.sqmobile.page.welcome.SQMobileWelcomePage;

/**
 * A sample test for Booking Flow
 * @author bsingh
 *
 */
public class Test_SQMobile_Sample_Booking_Flow_RT extends SeleniumSQTest{

  //Local Setup -- Required only during maintenance
  @Override
  public void localSetUp() throws Exception {
    //comment this once coding is done
    setDriverClass(FirefoxDriver.class);
    setBaseUrl("http://test89.dev.amadeus.net/");
    setDebugMode(false);
  }

  @Before
  public void resizeBrowser(){
    getDriverInstance().manage().window().setSize(new Dimension(600,800));
  }


  @Test
  public void scenario() throws Exception {

    SQMobileWelcomePage welcomepage = (SQMobileWelcomePage)HelperWelcome.openCustomerSpecificWelcomePage(com.amadeus.selenium.sqmobile.helper.HelperWelcome.Customer.SQMOBILE);

    SQMobileHomePage homePage = (SQMobileHomePage)welcomepage.changeLocalPreferences("Singapore", "GB", true);

    SQMobileSearchPage searchPage = (SQMobileSearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.SQMOBILE);
    searchPage.validateSearchPage();

    searchPage.fillSearchDetailsForCalendarUI();
    searchPage.clickSearchButton();

    OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
    outBoundAvailPage.selectDirectFlight();

    InBoundAvailPage inBoundAvailPage = PageFactory.getPageObject(InBoundAvailPage.class);
    inBoundAvailPage.selectDirectFlight();

    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
    fareReviewPage.validateFareReviewPage();

    SQMobilePaxInfoPage paxInfoPage = (SQMobilePaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.SQMOBILE);
    paxInfoPage.fillPaxInfo();
    paxInfoPage.fillContactInfo("Mobile","123","1234567890","sqmobile@abc.com","+65", "");

    PaymentPage paymentPage = (PaymentPage)paxInfoPage.continuePayment(Customer.SQMOBILE);
    paymentPage.fillPaymentDetail("AddressLine-1 ", "Address Line 2", "City", "sa", "123454", "Singapore");
    paymentPage.clickContinue();

    ConfirmationPage confirmationPage = PageFactory.getPageObject(ConfirmationPage.class);
    confirmationPage.validateConfirmationPage();

    String failedSteps = this.getFailedSteps();
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }
  }
}