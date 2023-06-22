package tests;

import ar.globallogic.qa.webdriver.WebDriver;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import tests.utils.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StepReporterEventListener implements StepLifecycleListener {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(StepReporterEventListener.class));

    @Override
    public void beforeStepStart(StepResult result) {
        LOGGER.info("Step to be Completed: " + result.getName());
    }

    @Override
    public void afterStepUpdate(StepResult result) {
        LOGGER.info("Step Completed: " + result.getName());
        if (!(result.getName().contains(TestBase.CERRAR))) {
            this.takeScreenShot();
        }

        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN){
            try {
                this.getNetworkLog();
                this.getPageSource();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment("Captura de pantalla")
    public byte[] takeScreenShot() {
        return ((TakesScreenshot)  WebDriver.getInstance()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment("Page Source")
    public String getPageSource(){
        return WebDriver.getInstance().getPageSource();
    }

    @Attachment("Network log")
    public String getNetworkLog(){
        List<String> list = new ArrayList<>();
        for (LogEntry entry : WebDriver.getInstance().manage().logs().get(LogType.PERFORMANCE)){
            list.add(String.format("{Timestamp: %s} {Message: %s} {Level: %s}",entry.getTimestamp(),entry.getMessage(),entry.getLevel()));
            LOGGER.info(entry.toString());
        }
        return String.join("\n",list);
    }
}
