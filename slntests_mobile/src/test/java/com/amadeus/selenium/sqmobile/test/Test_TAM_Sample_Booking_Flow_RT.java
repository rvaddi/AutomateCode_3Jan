package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.InBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.TAMPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.TAMSearchPage;

/**
 * A sample test for Booking Flow
 * @author bsingh
 *
 */
public class Test_TAM_Sample_Booking_Flow_RT extends SeleniumSEPTestAdvanced{

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
    // HomePage homePage = welcomepage.changeLocalPreferences("Iceland", "GB" , false);

    TAMSearchPage searchPage= (TAMSearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.TAM);

    searchPage.fillSearchPage();
    searchPage.clickSearchButton();

    OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
    outBoundAvailPage.selectDirectFlight();

    InBoundAvailPage inBoundAvailPage = PageFactory.getPageObject(InBoundAvailPage.class);
    inBoundAvailPage.selectDirectFlight();

    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
    fareReviewPage.validateFareReviewPage();

    TAMPaxInfoPage paxInfoPage = (TAMPaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.TAM);
    paxInfoPage.fillPaxInfo();
    paxInfoPage.fillContactInfo("Mobile", "", "55644122454", "emaIil@email.com", "+65", "5245413345");


    TAMPaymentPage paymentPage =  (TAMPaymentPage)paxInfoPage.continuePayment(Customer.TAM);
    paymentPage.fillPaymentDetail("AddressLine-1 ", "Address Line 2", "City", "sa", "123454", "Singapore");
    paymentPage.fillSocialSecurityCardInfo();
    paymentPage.clickContinue();

    ConfirmationPage confirmationPage = PageFactory.getPageObject(ConfirmationPage.class);
    confirmationPage.validateConfirmationPage();


    String failedSteps = this.getFailedSteps();
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }
  }
}