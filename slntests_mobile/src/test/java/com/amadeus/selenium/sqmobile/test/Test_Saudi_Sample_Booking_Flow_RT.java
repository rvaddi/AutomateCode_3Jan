package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.InBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.confirmation.customers.SaudiConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.SaudiPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.SaudiPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.SaudiPaymentPasswordPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.SaudiSearchPage;

/**
 * A sample test for Booking Flow
 * @author bsingh
 *
 */
public class Test_Saudi_Sample_Booking_Flow_RT extends SeleniumSQTest{

  //Local Setup -- Required only during maintenance
  @Override
  public void localSetUp() throws Exception {
    //comment this once coding is done
    setDriverClass(FirefoxDriver.class);
    setBaseUrl("http://nceetvqanlb24.dev.amadeus.net/");
    setDebugMode(false);
  }

  @Before
  public void resizeBrowser(){
    getDriverInstance().manage().window().setSize(new Dimension(600,800));
  }


  @Test
  public void scenario() throws Exception {

    HelperWelcome.openWelcomePage();
    HomePage homePage = PageFactory.getPageObject(HomePage.class);

    SaudiSearchPage searchPage= (SaudiSearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.SAUDI);
    // searchPage.fillSearchPage();
    searchPage.fillSearchPageforAjaxList();
    searchPage.clickSearchButton();

    OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
    outBoundAvailPage.selectDirectFlight();

    InBoundAvailPage inBoundAvailPage = PageFactory.getPageObject(InBoundAvailPage.class);
    inBoundAvailPage.selectDirectFlight();

    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
    fareReviewPage.validateFareReviewPage();

    SaudiPaxInfoPage paxInfoPage = (SaudiPaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.SAUDI);
    paxInfoPage.fillPaxInfo();
    paxInfoPage.fillContactInfo("Mobile", "", "895445855214", "email@email.com", "+65", "535562552455");

    SaudiPaymentPage paymentPage = (SaudiPaymentPage)paxInfoPage.continuePayment(Customer.SAUDI);
    paymentPage.fillPaymentDetail("AddressLine-1", "Address Line 2", "City", "sa", "123454", "Singapore");

    SaudiPaymentPasswordPage saudiPaymentPasswordPage = paymentPage.clickContinueToPasswordPage();
    saudiPaymentPasswordPage.passwordVerification("123");

    SaudiConfirmationPage confirmationPage = PageFactory.getPageObject(SaudiConfirmationPage.class);
    confirmationPage.validateConfirmationPage();

    String failedSteps = this.getFailedSteps();
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }
  }
}