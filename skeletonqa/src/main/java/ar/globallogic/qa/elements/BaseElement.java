package ar.globallogic.qa.elements;

import ar.globallogic.qa.webdriver.ConfigHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseElement {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BaseElement.class));
    private final WebDriver driver;
    private final WebDriverWait driverWait;

    protected BaseElement() {
        driver = ar.globallogic.qa.webdriver.WebDriver.getInstance();
        driverWait = new WebDriverWait(driver, ConfigHelper.getAppDefaultWait());
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getDriverWait() {
        return driverWait;
    }

    /**
     * Indicates if an element exists and can be found on screen. {@link NoSuchElementException}s are caught in order to return <code>true</code> or <code>false</code> accordingly.
     *
     * @param elementBy identifier of the element that has to be found
     * @return <code>true</code> or <code>false</code> depending on the results
     */
    protected boolean exists(By elementBy) {
        return exists(elementBy, null);
    }

    /**
     * Indicates if an element exists and is displayed. Depending on the value of parameter <b>withWait</b> it will wait for the element or not.
     *
     * @param elementBy identifier of the element that has to be found
     * @param withWait  <code>true</code> if the driver should use {@link Wait}
     * @return <code>true</code> or <code>false</code> depending whether the element exists or not
     */
    protected boolean exists(By elementBy, boolean withWait) {
        return withWait ? exists(elementBy, getDriverWait()) : exists(elementBy, null);
    }

    /**
     * Indicates if an element exists and is displayed using a specific Wait. {@link NoSuchElementException}s are caught in order to return <code>true</code> or <code>false</code> accordingly.
     *
     * @param elementBy identifier of the element that has to be found
     * @param wait      typically {@link WebDriverWait} or {@link FluentWait}, <code>null</code> if no wait should be applied
     * @return <code>true</code> or <code>false</code> depending on the results
     */
    protected boolean exists(By elementBy, Wait wait) {
        boolean exists = false;
        try {
            if (wait == null) {
                exists = get(elementBy, false) != null;
            } else {
                exists = (boolean) wait.until(
                        ExpectedConditions.and(
                            ExpectedConditions.presenceOfElementLocated(elementBy),
                            ExpectedConditions.visibilityOfElementLocated(elementBy)
                        )
                );
            }
        } catch (NoSuchElementException e) {
            LOGGER.log(Level.INFO, "No such Element: " + elementBy);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return exists;
    }

    /**
     * indicates if an element satisfies the expected conditions as parameters
     * @param wait  {@link WebDriverWait}
     * @param expectedCondition  One or more expected conditions {@link ExpectedCondition}
     *        example: ExpectedConditions.elementToBeClickable(by)
     * @return True or false depending on the expected conditions
     */

    protected boolean waitUntil( WebDriverWait wait, ExpectedCondition<?> expectedCondition ) {
        boolean condition = false;

        try { condition = wait.until(ExpectedConditions.and(expectedCondition));
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
        return condition;
    }

    /**
     * Finds an element of this page object. It does not wait for the element.
     *
     * @param elementBy identifier of the element that has to be found
     * @return the found element
     */
    protected WebElement get(By elementBy) {
        return get(elementBy, false, null);
    }

    /**
     * Finds an element of this page object using {@link WebDriverWait}.
     * With option to wait or not for the element.
     *
     * @param elementBy identifier of the element that has to be found
     * @param withWait  set to <code>true</code> if the driver should wait until the element is displayed. <code>false</code> if the driver should not wait.
     * @return the found element
     */
    protected WebElement get(By elementBy, boolean withWait) {
        return withWait ? get(elementBy, true, getDriverWait()) : get(elementBy, false, null);
    }

    /**
     * Finds an element of this page object using a Wait.
     *
     * @param elementBy identifier of the element that has to be found
     * @param wait      typically {@link WebDriverWait}
     * @return the found element.
     */
    protected WebElement get(By elementBy, Wait wait) {
        return get(elementBy, true, wait);
    }

    /**
     * Internal get method. Use {@link #get(By)}, {@link #get(By, boolean)} or {@link #get(By, Wait)}.
     *
     * @param elementBy
     * @param withWait
     * @param wait
     * @return
     */
    private WebElement get(By elementBy, boolean withWait, Wait wait) {
        int tryCount = 0;
        int maxTries = 6;
        while (tryCount <= maxTries) {
            try {
                if (withWait == false) {
                    return getDriver().findElement(elementBy);
                } else {
                    return (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
                }
            } catch (StaleElementReferenceException staleEx) {
                staleEx.printStackTrace();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tryCount++;
        }
        return null;
    }

    /**
     * Finds an element of this page object but doesn't check if it is visible.
     *
     * @param elementBy identifier of the element that has to be found
     * @param wait      typically {@link WebDriverWait}
     * @return the found element.
     */

    protected boolean present(By elementBy, Wait wait) {
        boolean exists = false;
        try {
            if (wait == null) {
                exists = get(elementBy, false) != null;
            } else {
                exists = wait.until(ExpectedConditions.presenceOfElementLocated(elementBy)) != null;
            }
        } catch (NoSuchElementException e) {
            LOGGER.log(Level.INFO, "No such Element: " + elementBy);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return exists;
    }
}
