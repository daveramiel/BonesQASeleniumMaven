package steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.ExamplePage;

public class ExampleStep {

    private static final String PAGE_NAME = "Example Step: ";
    private ExamplePage examplePage;

    public ExampleStep() {
        examplePage = new ExamplePage();
    }

    @Step(PAGE_NAME + "Validar header")
    public void validarHeader() {
        Assertions.assertTrue(examplePage.getHeader().isDisplayed(), "El header no se encuentra visible");
    }
}
