package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import tests.utils.TestBase;
import org.junit.jupiter.api.Test;
import steps.ExampleStep;

@DisplayName("Example Test Suite")
public class ExampleTest extends TestBase {

    @Test
    @DisplayName("First Test Example")
    @Description("La descripcion de un test case")
    @Link("http://El_link_de_la_tarea_en_Jira/asd-1111")
    @Feature("asd-1111: El Nombre y el ID del caso")
    @Story("A que historia pertenece 'Example' por ejemplo")
    @Severity(SeverityLevel.NORMAL)
    public void FirstTestExample() {
        ExampleStep exampleStep = new ExampleStep();
        exampleStep.validarHeader();
    }
}
