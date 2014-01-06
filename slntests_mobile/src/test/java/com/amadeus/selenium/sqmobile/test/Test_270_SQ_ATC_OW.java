package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.internal.seleniumemulation.GetValue;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.ATCAvailablityPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ATCConfirmationPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.home.customers.SQMobileHomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.SQMobilePaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.ATCPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.PaymentPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyFlightsPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyTripsPage;
import com.amadeus.selenium.sqmobile.page.retrieval.RetrieveTripListPage;
import com.amadeus.selenium.sqmobile.page.review.ATCFareReviewPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.ATCSearchPage;
import com.amadeus.selenium.sqmobile.page.search.SearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.SQMobileSearchPage;
import com.amadeus.selenium.sqmobile.page.welcome.SQMobileWelcomePage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;

public class Test_270_SQ_ATC_OW  extends SeleniumSQTest{

	  @Override
	  public void localSetUp() throws Exception {
	    //comment this once coding is done
	    setDriverClass(FirefoxDriver.class);
	    setBaseUrl("http://test89.dev.amadeus.net");
	    setDebugMode(false);
	  }


	  @Before
	  public void resizeBrowser(){
	    getDriverInstance().manage().window().setSize(new Dimension(600,800));
	  }


	  @Override
	  public FirefoxProfile localGetFirefoxProfile() throws Exception {
	    FirefoxProfile profile = new FirefoxProfile();
	    profile.setAcceptUntrustedCertificates(true);
	    return profile;

	  }

	  @Test
	  public void scenario() throws Exception {

	    SQMobileWelcomePage welcomepage = (SQMobileWelcomePage)HelperWelcome.openCustomerSpecificWelcomePage(com.amadeus.selenium.sqmobile.helper.HelperWelcome.Customer.SQMOBILE);

	    SQMobileHomePage homePage = (SQMobileHomePage)welcomepage.changeLocalPreferences("Singapore", "GB", true);

	    SQMobileSearchPage searchPage = (SQMobileSearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.SQMOBILE);
	    searchPage.validateSearchPage();
	    searchPage.fillSearchPage();
	    searchPage.clickSearchButton();

	    OutBoundAvailPage outboundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
	    outboundAvailPage.selectDirectFlight();

	    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
	    fareReviewPage.validateFareReviewPage();
	    SQMobilePaxInfoPage alpiPage = ( SQMobilePaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.SQMOBILE);
	    alpiPage.fillPaxInfo();
	    alpiPage.fillContactInfo("Mobile","123","1234567890","sqmobile@abc.com","+65", "");

	    PaymentPage paymentPage = (PaymentPage)alpiPage.continuePayment(Customer.SQMOBILE);
	    paymentPage.fillPaymentDetail("ADDRESS LINE ONE", "ADDRESS LINE TWO", "City", "A1", "12345", "Singapore");

	    ConfirmationPage confirmationPage = paymentPage.clickContinue();
	    confirmationPage.validateConfirmationPage();


	    welcomepage = (SQMobileWelcomePage)HelperWelcome.openCustomerSpecificWelcomePage(com.amadeus.selenium.sqmobile.helper.HelperWelcome.Customer.SQMOBILE);
	    homePage = PageFactory.getPageObject(SQMobileHomePage.class);
	    homePage.sqMobileMenu.clickMyTrips();
	    RetrieveTripListPage retrieveTripList = PageFactory.getPageObject(RetrieveTripListPage.class);
	    retrieveTripList.selectTrip(retrieveTripList.getValue("PNR"));

	    MyFlightsPage myFlightsPage = PageFactory.getPageObject(MyFlightsPage.class);
	    ATCSearchPage atcSearchPage = myFlightsPage.actionChangeTrip();

	    atcSearchPage.changeDepartureDate();
	    ATCAvailablityPage atcAvailPage =    atcSearchPage.clickContinue();
	    atcAvailPage.selectFirstFlight();

	    ATCFareReviewPage atcFareReviewPage = PageFactory.getPageObject(ATCFareReviewPage.class);
	    atcFareReviewPage.clickContinue();

	    ATCPaymentPage atcPaymentPage = PageFactory.getPageObject(ATCPaymentPage.class);
	    atcPaymentPage.fillPaymentInfo();
	    atcPaymentPage.fillPaymentDetail("ADDRESS LINE ONE", "ADDRESS LINE TWO", "City", "A1", "12345", "Singapore");

	    ConfirmationPage confirmation = atcPaymentPage.clickContinue();
	  }
}
