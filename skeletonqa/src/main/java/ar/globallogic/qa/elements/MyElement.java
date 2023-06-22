package ar.globallogic.qa.elements;

import ar.globallogic.qa.webdriver.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyElement extends BaseElement implements WebElement {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(MyElement.class));
    private final By elementBy;
    private WebDriverWait wait;
    private ar.globallogic.qa.webdriver.WebDriver webDriver;

    public MyElement(By locator) {
        this.elementBy = locator;
        this.wait = getDriverWait();
    }

    public MyElement withWait(WebDriverWait webDriverWait) {
        this.wait = webDriverWait;
        return this;
    }


    @Override
    public void click() {
        get(elementBy).click();
    }

    @Override
    public void submit() {
        get(elementBy).submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        get(elementBy).sendKeys(keysToSend);

    }

    @Override
    public void clear() {
        get(elementBy).clear();
    }

    @Override
    public String getTagName() {
        return get(elementBy).getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return get(elementBy).getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return get(elementBy).isSelected();
    }

    @Override
    public boolean isEnabled() {
        return get(elementBy).isEnabled();
    }

    @Override
    public String getText() {
        return get(elementBy).getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return get(elementBy).findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return get(elementBy).findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return exists(elementBy, wait);
    }

    @Override
    public Point getLocation() {
        return get(elementBy).getLocation();
    }

    @Override
    public Dimension getSize() {
        return get(elementBy).getSize();
    }

    @Override
    public Rectangle getRect() {
        return get(elementBy).getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return get(elementBy).getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return get(elementBy).getScreenshotAs(target);
    }

    public void selectOption(String option) {
        new Select(get(elementBy)).selectByValue(option);
    }

    public boolean isPresent(){
        return this.present(elementBy, wait);
    }

    public By getElementBy(){
        return elementBy;
    }

    public void scrollToElement(){
        WebElement element = WebDriver.getInstance().findElement(elementBy);
        Actions builder = new Actions(WebDriver.getInstance());
        builder.moveToElement(element);
        builder.build().perform();
    }

    /**
     * indicates if an element satisfies the expected conditions as parameters
     * @param expectedCondition  One or more expected conditions {@link ExpectedCondition}
     *        example: ExpectedConditions.elementToBeClickable(by)
     * @return True or false depending on the expected conditions
     */
    public boolean waitUntil(ExpectedCondition expectedCondition){
        return waitUntil(wait,expectedCondition);
    }

    /**
     * This method wait element disapper
     * @param limitCount Determines the loop executions limit
     * @return void, wait for element disappear
     * Example = pagina.getSpinnerElement().waitForElementDisappear(10)
     */
    public void waitForElementDisappear(int limitCount) {
        List<By> byList = new ArrayList<>();
        byList.add(elementBy);
        int count = 0;
        while(byList.size() != 0 && count <= limitCount) {
            try {
                Thread.sleep(1000);
                LOGGER.log(Level.INFO, "Spinner de carga visible, esperando por elemento...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}
