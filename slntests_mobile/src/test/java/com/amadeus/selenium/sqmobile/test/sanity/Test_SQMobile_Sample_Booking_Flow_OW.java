package com.amadeus.selenium.sqmobile.test.sanity;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.SQMobilePaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.PaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.SearchPage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;
import com.amadeus.selenium.sqmobile.test.SeleniumSQTest;


public class Test_SQMobile_Sample_Booking_Flow_OW extends SeleniumSQTest{

  //Local Setup -- Required only during maintenance
  @Override
  public void localSetUp() throws Exception {
    //comment this once coding is done
    setDriverClass(FirefoxDriver.class);
    setBaseUrl("http://nceetvqanlb22.dev.amadeus.net");
    setDebugMode(false);
  }


  @Before
  public void resizeBrowser(){
    getDriverInstance().manage().window().setSize(new Dimension(600,800));
  }


  @Override
  public FirefoxProfile localGetFirefoxProfile() throws Exception {
    //    final String firebugPath = "c:\\firebug-1.9.2.xpi";
    FirefoxProfile profile = new FirefoxProfile();
    //    profile.addExtension(new File(firebugPath));
    profile.setAcceptUntrustedCertificates(true);
    //    profile.setPreference("extensions.firebug.currentVersion", "1.9.2");
    // Add more if needed
    return profile;

  }

  @Test
  public void scenario() throws Exception {

    WelcomePage welcomepage = HelperWelcome.openWelcomePage();

    HomePage homePage = PageFactory.getPageObject(HomePage.class);
    SearchPage searchPage = homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.SQMOBILE);
    searchPage.validateSearchPage();
    searchPage.fillSearchDetailsForCalendarUI();
    searchPage.clickSearchButton();

    OutBoundAvailPage outboundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
    outboundAvailPage.validatePage();
    outboundAvailPage.selectDirectFlight();

    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
    fareReviewPage.validateFareReviewPage();
    SQMobilePaxInfoPage paxInfoPage = (SQMobilePaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.SQMOBILE);
    paxInfoPage.fillPaxInfo();
    paxInfoPage.fillContactInfo("Mobile","123","1234567890","sqmobile@abc.com","+65", "");

    PaymentPage paymentPage = (PaymentPage)paxInfoPage.continuePayment(Customer.SQMOBILE);
    paymentPage.fillPaymentDetail("ADDRESS LINE ONE", "ADDRESS LINE TWO", "City", "A1", "12345", "Singapore");

    ConfirmationPage confirmationPage = paymentPage.clickContinue();
    confirmationPage.validateConfirmationPage();


  }
}
