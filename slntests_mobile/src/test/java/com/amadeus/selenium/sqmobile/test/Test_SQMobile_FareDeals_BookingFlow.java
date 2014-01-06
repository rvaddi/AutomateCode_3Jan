package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.InBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.calendar.CalendarPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.farecondition.FareConditionPage;
import com.amadeus.selenium.sqmobile.page.farecondition.FareConditionTabPage;
import com.amadeus.selenium.sqmobile.page.faredeal.SQMobileDealPage;
import com.amadeus.selenium.sqmobile.page.home.customers.SQMobileHomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.SQMobilePaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.PaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.welcome.SQMobileWelcomePage;

public class Test_SQMobile_FareDeals_BookingFlow extends SeleniumSQTest {

  @Override
  public void localSetUp() throws Exception {
    // comment this once coding is done
    setDriverClass(FirefoxDriver.class);
    setBaseUrl("http://test89.dev.amadeus.net/");
    setDebugMode(false);
  }

  @Before
  public void resizeBrowser() {
    getDriverInstance().manage().window().setSize(new Dimension(600, 800));
  }

  @Test
  public void scenario() throws Exception {

    SQMobileWelcomePage welcomepage = (SQMobileWelcomePage)HelperWelcome.openCustomerSpecificWelcomePage(com.amadeus.selenium.sqmobile.helper.HelperWelcome.Customer.SQMOBILE);

    SQMobileHomePage homePage = (SQMobileHomePage)welcomepage.changeLocalPreferences("Singapore", "GB", true);

    SQMobileDealPage dealpage = homePage.sqMobileMenu.actionFareDeals();
    dealpage.validateDealpage();
    dealpage.FillFareDealpage();
    dealpage.selectDeal();

    FareConditionPage fareconditonpage = PageFactory.getPageObject(FareConditionPage.class);
    fareconditonpage.ClickFareConditionsTab();

    FareConditionTabPage fareconditiontabpage = PageFactory.getPageObject(FareConditionTabPage.class);
    fareconditiontabpage.ValidateFareConditionsTabPage();
    fareconditiontabpage.ClickBackButton();

    fareconditonpage.Fillfareconditionpage();
    fareconditonpage.FillMinStay();
    fareconditonpage.clickSearchButton();
    fareconditonpage.ValidateMinStayRestriction();
    /*fareconditonpage.FillMaxStay();
    fareconditonpage.clickSearchButton();
    fareconditonpage.ValidateMaxStayRestriction();*/
    fareconditonpage.Fillfareconditionpage();
    fareconditonpage.clickSearchButton();

    CalendarPage calendarpage = PageFactory.getPageObject(CalendarPage.class);
    calendarpage.ValidateMatrixCalendarPage();
    calendarpage.SelectRegularFlightfromMatrixCalendar();
    // calendarpage.SelectDealFlightfromMatrixCalendar();

    OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
    // outBoundAvailPage.SelectFareDealFlight();
    outBoundAvailPage.SelectRegularFlight();

    InBoundAvailPage inBoundAvailPage = PageFactory.getPageObject(InBoundAvailPage.class);
    inBoundAvailPage.ValidateSelectedDepartureFlight();
    inBoundAvailPage.SelectRegularFlight();
    // inBoundAvailPage.SelectFareDealFlight();

    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
    fareReviewPage.validateFareReviewPage();

    SQMobilePaxInfoPage paxinfopage = (SQMobilePaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.SQMOBILE);
    paxinfopage.fillPaxInfo();
    paxinfopage.fillContactInfo("Mobile", "123", "1234567890", "sqmobile@abc.com", "+65", "");

    PaymentPage paymentPage = (PaymentPage)paxinfopage.continuePayment(Customer.SQMOBILE);
    paymentPage.fillPaymentDetail("ADDRESS LINE ONE", "ADDRESS LINE TWO", "City", "A1", "12345", "Singapore");

    ConfirmationPage confirmationPage = paymentPage.clickContinue();
    confirmationPage.validateConfirmationPage();

    String failedSteps = this.getFailedSteps();
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }
  }
}
