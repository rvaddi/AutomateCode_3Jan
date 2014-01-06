package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.AirchinaPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.AirchinaPaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.AirChinaSearchPage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;

public class Test_AirChina_Sample_Booking_Flow_OW extends SeleniumSQTest{

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

	    WelcomePage welcomepage = HelperWelcome.openWelcomePage();
	    HomePage homePage =PageFactory.getPageObject(HomePage.class);
	   // HomePage homePage = welcomepage.changeLocalPreferences("USA", "GB" , false);

	    AirChinaSearchPage searchPage= (AirChinaSearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.AIRCHINA);
	    searchPage.fillSearchPage();
	    searchPage.clickSearchButton();

	    OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
	    outBoundAvailPage.selectDirectFlight();

	    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
	    fareReviewPage.validateFareReviewPage();

	    AirchinaPaxInfoPage paxInfoPage = (AirchinaPaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.AIRCHINA);
	    paxInfoPage.fillPaxInfo();
	    paxInfoPage.fillContactInfo("Mobile", "", "55644122454", "emaIil@email.com", "+65", "5245413345");


	    AirchinaPaymentPage paymentPage =  (AirchinaPaymentPage)paxInfoPage.continuePayment(Customer.AIRCHINA);
	   // paymentPage.fillPaymentDetail("AddressLine-1 ", "Address Line 2", "City", "sa", "123454", "Singapore");
	    paymentPage.clickContinue();

	    ConfirmationPage confirmationPage = PageFactory.getPageObject(ConfirmationPage.class);
	    confirmationPage.validateConfirmationPage();

    String failedSteps = this.getFailedSteps();
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }

	  }
}
