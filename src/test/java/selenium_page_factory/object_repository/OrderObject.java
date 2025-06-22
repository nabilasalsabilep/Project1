package selenium_page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class OrderObject extends BaseObject {
    @FindBy(xpath = "//*[contains(text(), 'Thankyou for the order')]")
    public WebElement successPlacingTheOrderTitle;

    public WebElement dynamicProductName(String productName) {
        return webDriver.findElement(By.xpath("//*[contains(text(), '" + productName + "')]"));
    }

    public WebElement dynamicProductPrice(String productPrice) {
        return webDriver.findElement(By.xpath("//*[contains(text(), '" + productPrice + "')]"));
    }

    public OrderObject(WebDriver webDriver) {
        super(webDriver);
        
        PageFactory.initElements(webDriver, this);
    }
    
}
