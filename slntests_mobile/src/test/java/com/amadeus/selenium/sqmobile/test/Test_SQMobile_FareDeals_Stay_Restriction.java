package com.amadeus.selenium.sqmobile.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.calendar.CalendarPage;
import com.amadeus.selenium.sqmobile.page.farecondition.FareConditionPage;
import com.amadeus.selenium.sqmobile.page.faredeal.SQMobileDealPage;
import com.amadeus.selenium.sqmobile.page.home.customers.SQMobileHomePage;
import com.amadeus.selenium.sqmobile.page.welcome.SQMobileWelcomePage;

public class Test_SQMobile_FareDeals_Stay_Restriction extends SeleniumSQTest {

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
    calendarpage.VerifyOriginDestinationDisplayed();

    String failedSteps = this.getFailedSteps();
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }
  }
}
