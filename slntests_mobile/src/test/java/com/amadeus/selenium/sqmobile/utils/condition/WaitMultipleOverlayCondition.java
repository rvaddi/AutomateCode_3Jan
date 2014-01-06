package com.amadeus.selenium.sqmobile.utils.condition;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Condition waiting for the invisibility of all overlay loading
 */
public class WaitMultipleOverlayCondition implements ExpectedCondition<Boolean> {

  protected final static By LOC_DIV_OVERLAY_LOADING = By.cssSelector(".msk.loading");

  /**
   * Constructor
   */
  public WaitMultipleOverlayCondition() {
    super();
  }

  public Boolean apply(WebDriver webDriver)
  {
      boolean validCondition = true;

      List<WebElement> eltList = webDriver.findElements(LOC_DIV_OVERLAY_LOADING);
      for(WebElement elt: eltList) {

        // elt exists
        try {
          if (elt != null && elt.isDisplayed()) {
            validCondition = false;
            break;
          }
        } catch (StaleElementReferenceException e) {
          // element no more into the cache, so not displayed
          continue;
        }catch(WebDriverException e){
          break;
        }
      }
      return Boolean.valueOf(validCondition);
  }
}
