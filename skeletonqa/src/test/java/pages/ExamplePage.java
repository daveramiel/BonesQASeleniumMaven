package pages;

import ar.globallogic.qa.elements.MyElement;
import org.openqa.selenium.By;

public class ExamplePage {

    private MyElement header;

    public ExamplePage() {
        header = new MyElement(By.xpath("//div[@id='app']//header"));
    }

    public MyElement getHeader() {
        return header;
    }
}
