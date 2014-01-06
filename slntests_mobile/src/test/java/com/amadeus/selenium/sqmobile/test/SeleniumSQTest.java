package com.amadeus.selenium.sqmobile.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.firefox.FirefoxProfile;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.xml.PublishXMLReport;

public class SeleniumSQTest extends SeleniumSEPTest {
 // protected JenkinsSeleniumParameters parameterJenkins;
  public String errMsg = " ";
  public String jsessionId = null;

  public SeleniumSQTest() {
    super();
    // Param Jenkin initialization
   // this.parameterJenkins = new JenkinsSeleniumParameters(this.parameters);
  }

  @Override
  public FirefoxProfile localGetFirefoxProfile() throws Exception{
    File SQProfileDir = new File("\\\\nceetvdev30\\qad\\SeleniumTests\\Profiles\\SQMobile");
    FirefoxProfile profile = new FirefoxProfile(SQProfileDir);
    //profile.setPreference("general.useragent.override", "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
    reporter.report("User Agent Switcher", "User Agent switched to iPhone");
    return profile;
  }

  @Override
  public void localTearDown() throws Exception {
    if (parameters.isScreenShotAutomaticCapture()) {
      // automatic screen shot capture
      String captureName = this.getClass().getSimpleName();
      reporter.captureScreenShot(captureName + "_ERROR");
    }

    /*
     * String failedSteps = this.getFailedSteps(); if (failedSteps != null && failedSteps.length() > 0) {
     * reporter.fail("Validation(s) failed"); }
     */

    if (errMsg.equals(" ")) {
      errMsg = "";
    }

    this.jsessionId = this.getJSessionID();

    /*Calendar cal = Calendar.getInstance();
    System.out.println("Before Xml: " + cal.getTime());
    reporter.reportPassed("Before Xml: ", new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss:SSS aa").format(cal.getTime()));
    new PublishXMLReport(this).ConvertLogToXml();
    Calendar cal2 = Calendar.getInstance();
    System.out.println("After Xml: " + cal2.getTime());
    reporter.reportPassed("After Xml: ", new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss:SSS aa").format(cal2.getTime()));*/
  }
 }
