package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.InBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.confirmation.customers.ELALConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.ELALPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.ELALPaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.ELALSearchPage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;

/**
 * A sample test for Booking Flow
 * @author bsingh
 *
 */
public class Test_ELAL_Sample_Booking_Flow_RT extends SeleniumSQTest{

  //Local Setup -- Required only during maintenance
  @Override
  public void localSetUp() throws Exception {
    //comment this once coding is done
    setDriverClass(FirefoxDriver.class);
    setBaseUrl("http://nceetvqanlb24.dev.amadeus.net");
    setDebugMode(false);
  }

  @Before
  public void resizeBrowser(){
    getDriverInstance().manage().window().setSize(new Dimension(600,800));
  }


  @Test
  public void scenario() throws Exception {

    WelcomePage welcomepage = HelperWelcome.openWelcomePage();
    HomePage homePage = PageFactory.getPageObject(HomePage.class);

   // welcomepage.changeLocalPreferences("",welcomepage.getValue("Language"));
    ELALSearchPage searchPage= (ELALSearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.ELAL);

    // searchPage.fillSearchPage();
    searchPage.fillSearchPageforAjaxList();
    searchPage.clickSearchButton();

    OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
    outBoundAvailPage.selectDirectFlight();

    InBoundAvailPage inBoundAvailPage = PageFactory.getPageObject(InBoundAvailPage.class);
    inBoundAvailPage.selectDirectFlight();

    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
    fareReviewPage.validateFareReviewPage();

    ELALPaxInfoPage paxInfoPage = (ELALPaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.ELAL);
    paxInfoPage.fillContactInfo("Mobile", "", "55644122454", "emaIil@email.com", "+65");
    paxInfoPage.fillPaxInfo();


    ELALPaymentPage paymentPage = (ELALPaymentPage)paxInfoPage.continuePayment("Visa");
    //paymentPage.fillPaymentDetail("AddressLine-1 ", "Address Line 2", "City", "sa", "123454", "Singapore");
    paymentPage.fillPaymentInfo();

    ELALConfirmationPage confirmationPage = (ELALConfirmationPage)paymentPage.implementationExternalPayment();
    confirmationPage.validateConfirmationPage();

    String failedSteps = this.getFailedSteps();
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }
  }
}