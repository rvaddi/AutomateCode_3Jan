package com.amadeus.selenium.sqmobile.page.farecondition;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class FareConditionTabPage extends SqMobileCommonPage {

  public FareConditionTabPage(SeleniumSEPTest test) {
    super(test);
    // TODO Auto-generated constructor stub
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_FareConditions_Tabs, 30);
    if (elementPresent) {
      reporter.reportPassed(pageName, "Fare Condition Tab Page loaded");
    }
    else {
      reporter.fail("Fare Condition Tab Page NOT loaded ");
    }
  }

  String pageName = "Fare Conditions Tab ";

  protected static By LOC_FareConditions_Tabs = By.cssSelector("*.accordion");
  protected static By LOC_FareConditions_Tabs_List = By.cssSelector(".accordion>h2");
  // protected static By LOC_FareConditions_Each_tab = By.cssSelector("*.toggleOuterCustom");
  protected static By LOC_FareConditions_Back_Button = By.className("back");


  public void ValidateFareConditionsTabPage() {

    VerifyFareConditionsTab();
    VerifyEachTabSection();
  }


  public void ClickBackButton() {

    ClickUtils.clickButtonOrFail(getTest(), LOC_FareConditions_Back_Button, "Back button is NOT clicked","Back Button is clicked successfully");
  }

  public void VerifyFareConditionsTab() {

    String TabName;
    ArrayList<String> TabNameList = new ArrayList<String>();

    boolean IsTabsDisplayed = true;

    List<WebElement> TabList = CheckUtils.getElements(getTest(), LOC_FareConditions_Tabs_List);

    TabNameList.add("Minimum passengers");
    TabNameList.add("Cabin Class");
    TabNameList.add("Minimum stay");
    TabNameList.add("Maximum stay");
    TabNameList.add("Advance purchase");
    TabNameList.add("Travel Completion Date");
    TabNameList.add("Outbound travel period");
    TabNameList.add("Special conditions");
    TabNameList.add("Other conditions");

    if (!(TabList == null)) {
      for (WebElement Tabs : TabList) {
        TabName = Tabs.getText().trim();
        if (!TabNameList.contains(TabName)) {
          IsTabsDisplayed = false;
          reporter.reportFailed(pageName, TabName + " Tab Name is NOT displayed in the Fare Condition Page");
        }
      }
    }
    else {
      IsTabsDisplayed = false;
    }
    if (IsTabsDisplayed) {
      reporter.reportPassed(pageName, "Tab Names are properly displayed in the Fare Condition Page");
    }
    else {
      reporter.reportFailed(pageName, "Tab Names are NOT properly displayed in the Fare Condition Page");
    }
  }

  public void VerifyEachTabSection() {

    String SectionIdName;
    String GetSectionText;
    WebElement Section;

    boolean IsRelConditionDisplayed = true;

    List<WebElement> EachTabList = CheckUtils.getElements(getTest(), LOC_FareConditions_Tabs_List);

    if (!(EachTabList == null)) {
      for (WebElement eachtab : EachTabList) {
        if (!eachtab.getAttribute("aria-expanded").trim().equalsIgnoreCase("true")) {
          ClickUtils.click(getTest(), eachtab);
        }

        SectionIdName = eachtab.getAttribute("data-aria-controls");
        Section = CheckUtils.getElement(getTest(), By.id(SectionIdName));

        if (Section != null && Section.isDisplayed()) {
          GetSectionText = Section.getText().trim();
          if (GetSectionText.isEmpty()) {
            IsRelConditionDisplayed = false;
            reporter.reportFailed(pageName, eachtab.getText().trim() +
                " Tab NOT expanded and displayed the Relavant Condition in the Fare Condition Page");
          }
        }
        else {
          IsRelConditionDisplayed = false;
          reporter.reportFailed(pageName, eachtab.getText().trim() +
              " Tab NOT expanded and displayed the Relavant Condition in the Fare Condition Page");
        }
      }
    }
    else {
      IsRelConditionDisplayed = false;
    }

    if (IsRelConditionDisplayed) {
      reporter.reportPassed(pageName, "Tabs expanded and displayed the Relavant Condition in the Fare Condition Page");
    }
    reporter.reportFailed(pageName, "Tabs NOT expanded and displayed the Relavant Condition in the Fare Condition Page");

  }


  /*public void VerifyEachTabs() {

    String TabName="";

    List<WebElement> EachTabList = CheckUtils.getElements(getTest(), LOC_FareConditions_Each_tab);

    for (WebElement eachtab : EachTabList) {

      WebElement expand = eachtab.findElement(By.cssSelector("*.accordionOff"));

      if (expand == null) {
        ClickUtils.click(getTest(), By.cssSelector("*.accordionOn"));
      }

      WebElement relavantcondition = eachtab.findElement(By.cssSelector("*.lHeighttranslate"));

      if (!(relavantcondition == null)) {

        TabName = eachtab.findElement(LOC_FareConditions_Tabs_List).getText().trim();
        reporter.reportFailed(pageName, "Tab NOT displayed the relavant conditions as defined in DWM. (Tab Name: " +
            TabName + ")");

      }
      else {
        String relavanttext = relavantcondition.getText().trim();

        if (relavanttext.isEmpty()) {
          reporter.reportFailed(pageName, "Tab NOT displayed the relavant conditions as defined in DWM. (Tab Name: " +
              TabName + ")");
        }
      }
    }
  }*/
}
