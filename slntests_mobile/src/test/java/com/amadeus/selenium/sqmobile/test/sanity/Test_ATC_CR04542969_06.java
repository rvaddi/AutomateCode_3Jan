package com.amadeus.selenium.sqmobile.test.sanity;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.page.availabililty.TimetableResultPage;
import com.amadeus.selenium.sqmobile.page.availabililty.TimetableResultPage.Itinerary;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.search.TimetableSearchPage;
import com.amadeus.selenium.sqmobile.page.search.TimetableSearchPage.TripType;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;
import com.amadeus.selenium.sqmobile.test.SeleniumSQTest;


public class Test_ATC_CR04542969_06 extends SeleniumSQTest{

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


  @Test
  public void scenario() throws Exception {

    //***INSTANTIATION of HomePage to use methods under that class***
    WelcomePage welcomePage = PageFactory.getPageObject(WelcomePage.class);

    HomePage homePage = PageFactory.getPageObject(HomePage.class);
    homePage.validateHomepage();
    homePage.sqMobileMenu.actionTimetable();

    TimetableSearchPage timetableSearchPage = PageFactory.getPageObject(TimetableSearchPage.class);
    timetableSearchPage.validateDefaultDeptDate(0);
    timetableSearchPage.validateDefaultReturnDate(14);
    timetableSearchPage.actionSearchTimetable(TripType.RT);

    TimetableResultPage timetableResultPage = PageFactory.getPageObject(TimetableResultPage.class);
    timetableResultPage.validateTimetableSearchPage();
    timetableResultPage.selectSearchOption(Itinerary.Departure);
    timetableResultPage.validateFlights(com.amadeus.selenium.sqmobile.page.availabililty.TimetableResultPage.TripType.RT);



  }
}