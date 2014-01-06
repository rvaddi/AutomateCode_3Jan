package com.amadeus.selenium.sqmobile.test.sanity;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.paxinfo.IPaxInfo;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.TAMPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;
import com.amadeus.selenium.sqmobile.test.SeleniumSQTest;

public class Test_Sample_Booking_Flow_OWA_TAM extends SeleniumSQTest{
  @Override
  public void localSetUp() throws Exception {
    //comment this once coding is done
    setDriverClass(FirefoxDriver.class);
    setBaseUrl("http://test17.dev.amadeus.net");
    setDebugMode(false);
  }

  @Before
  public void resizeBrowser(){
    getDriverInstance().manage().window().setSize(new Dimension(600,800));
  }


  @Test
  public void scenario() throws Exception {

   WelcomePage welcomepage = HelperWelcome.openWelcomePage();
  /*  HomePage homePage =welcomepage.changeLocalPreferences("Australia",welcomepage.getValue("Language"));

    SearchPage searchPage = homePage.sqMobileMenu.actionSearch();
    searchPage.validateSearchPage();

    searchPage.fillSearchDetailsForCalendarUI();
    searchPage.clickSearchButton();

    OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
    outBoundAvailPage.selectDirectFlight();

    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
    fareReviewPage.validateFareReviewPage();*/

    IPaxInfo tAMIPaxInfo = PageFactory.getPageObject(TAMPaxInfoPage.class);
    tAMIPaxInfo.fillPaxInfo();
    tAMIPaxInfo.fillContactInfo("Mobile", null, "55644122454", "email@email.com", "+966","123456");
    TAMPaymentPage paymentPage = (TAMPaymentPage)tAMIPaxInfo.continuePayment(Customer.TAM);

    paymentPage.fillPaymentDetail("asdad", "adsad", "asdasd", "äsdasd", "123456", "Brazil");
    paymentPage.clickContinue();
  }

}
