package com.amadeus.selenium.sqmobile.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;

public class CommonUtils extends SqMobileCommonPage{

  public CommonUtils (SeleniumSEPTest test) throws Exception {
    super(test);
  }

  //****************************************************************************


  //FUNCTION NAME   : AddDate
  //INPUT PARAM     : Number of Days to be added/subtracted
  //OUTPUT PARAM    : RETURNS updated date
  //DESCRIPTION     : Add/subtract number of days to the current  date

  public String addDate(String dateFormat, String field, int NumofDays){
    GregorianCalendar calender = new GregorianCalendar();
    SimpleDateFormat Sdf = new SimpleDateFormat(dateFormat);
    if (field.equals("Date")) {
      calender.add(Calendar.DAY_OF_YEAR, NumofDays);
    }
    else if (field.equals("Year")) {
      calender.add(Calendar.YEAR, NumofDays);
    }
    String UpdatedDate = Sdf.format(calender.getTime());
    return UpdatedDate;
  }
  //****************************************************************************

//****************************************************************************
  //FUNCTION NAME   : AddDate
  //INPUT PARAM     : Number of Days to be added/subtracted
  //OUTPUT PARAM    : RETURNS updated date
  //DESCRIPTION     : Add/subtract number of days to the current  date

  public String addDate(String dateFormat, int NumofDays){
    GregorianCalendar calender = new GregorianCalendar();
    SimpleDateFormat Sdf = new SimpleDateFormat(dateFormat);
      calender.add(Calendar.DAY_OF_YEAR, NumofDays);
    String UpdatedDate = Sdf.format(calender.getTime());
    return UpdatedDate;
  }
  //****************************************************************************


    /**
     *  Selects the date after the given number of days in the arguments
     *  @param noOfDays days after which date is to be selected
     *  @param itinerary DEPARTURE / RETURN
     *  @param LOC_CALENDAR_DATE date reference
     *  @param LOC_CALENDAR_MONTH month reference
     *  @param LOC_CALENDAR_YEAR year reference
     */
  public String fillDateUsingCalendar(int noOfDays ,By LOC_CALENDAR_DATE , By LOC_CALENDAR_MONTH , By LOC_CALENDAR_YEAR)  {

      String dateAfterGap =  addDate("dd MMMM yyyy", new Integer(noOfDays));
      String date = (dateAfterGap.split(" "))[0];
      String month = (dateAfterGap.split(" "))[1] ;
      String year =(dateAfterGap.split(" "))[2];

      FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_YEAR, year, "Year in the calendar couln't be filled properly");
      FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_MONTH, month, "Month in the calendar couln't be filled properly");

      List<WebElement> dateList = CheckUtils.getElements(getTest(), LOC_CALENDAR_DATE);
      boolean daySelected = false ;
      for(WebElement dateElt : dateList){
        if(Integer.parseInt(dateElt.getText().trim())== Integer.parseInt(date.trim())){
          ClickUtils.click(getTest(), dateElt);
          reporter.reportPassed("Calendar : ", "Date selected successfully in calendar");
          daySelected = true ;
          break;
        }
      }
      if(!daySelected) {
        reporter.reportFailed("Calendar : ", "Date Couldn't be selected");
      }

      return dateAfterGap ;

  }

  /**
   *  Selects the date after the given number of days in the arguments
   *  @param noOfDays days after which date is to be selected
   *  @param itinerary DEPARTURE / RETURN
   *  @param LOC_CALENDAR_DATE date reference
   *  @param LOC_CALENDAR_MONTH month reference
   *  @param LOC_CALENDAR_YEAR year reference
   */
  public HashMap<String, Object> VerifyDateDisabledInCalendar(int noOfDays, By LOC_CALENDAR_DATE,
      By LOC_CALENDAR_MONTH, By LOC_CALENDAR_YEAR) throws ParseException {

    boolean IsDateDisable = false;
    boolean FutureDate = true;
    HashMap<String, Object> CalendarDetail = new HashMap<String, Object>();

    String dateAfterGap = addDate("dd MMMM yyyy", new Integer(noOfDays));
    String date = (dateAfterGap.split(" "))[0];
    String month = (dateAfterGap.split(" "))[1];
    String year = (dateAfterGap.split(" "))[2];

    String currentdateAfterGap = addDate("dd MMMM yyyy", new Integer(0));

    Calendar DatetoSelect = new GregorianCalendar();
    DatetoSelect.setTime(new SimpleDateFormat("dd MMMM yyyy").parse(dateAfterGap));
    Calendar currentdate = new GregorianCalendar();
    currentdate.setTime(new SimpleDateFormat("dd MMMM yyyy").parse(currentdateAfterGap));

    if (DatetoSelect.compareTo(currentdate) >= 0) {
      FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_YEAR, year, "Year in the calendar couln't be filled properly");
      FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_MONTH, month, "Month in the calendar couln't be filled properly");
    }
    else {
      if (DatetoSelect.get(DatetoSelect.YEAR) >= currentdate.get(currentdate.YEAR)) {
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_YEAR, year, "Year in the calendar couln't be filled properly");

        if (DatetoSelect.get(DatetoSelect.MONTH) >= currentdate.get(currentdate.MONTH)) {
          FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_YEAR, year, "Year in the calendar couln't be filled properly");
        }
        else {
          FutureDate = false;
        }
      }
      else {
        FutureDate = false;
      }
    }

    if (FutureDate) {
      List<WebElement> dateList = CheckUtils.getElements(getTest(), LOC_CALENDAR_DATE);

      for (WebElement dateElt : dateList) {
        if (Integer.parseInt(dateElt.getText().trim()) == Integer.parseInt(date.trim())) {
          if (dateElt.getTagName().trim().equalsIgnoreCase("span")) {
            IsDateDisable = true;
            break;
          }
        }
      }
    }
    else {
      IsDateDisable = true;
      reporter.reportPassed("Calender", "Past Date is not displayed in the Calendar. (Date: " + dateAfterGap + ")");
    }

    CalendarDetail.put("IsDateDisable", IsDateDisable);
    CalendarDetail.put("Date", dateAfterGap);

    return CalendarDetail;
  }

}
