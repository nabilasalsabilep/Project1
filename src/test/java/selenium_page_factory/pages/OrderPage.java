package selenium_page_factory.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.OrderObject;

public class OrderPage extends BasePage {
    public OrderObject orderObject;

    public OrderPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.orderObject = new OrderObject(webDriver);
    }

    public void verifyOrderCreated(String productName, String productPrice) throws InterruptedException{
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        Boolean elementIsPresentProductName = wait.until(d -> orderObject.dynamicProductName(productName).isDisplayed());
        Assert.assertTrue(elementIsPresentProductName, "element title product not present");

        Boolean elementIsPresentProductPrice = wait.until(d -> orderObject.dynamicProductPrice(productPrice).isDisplayed());
        Assert.assertTrue(elementIsPresentProductPrice, "element product price not present");

        wait.until(d -> orderObject.successPlacingTheOrderTitle.isDisplayed());
        Assert.assertEquals(orderObject.successPlacingTheOrderTitle.getText(), "THANKYOU FOR THE ORDER.", "Success message is wrong");
    }
    
}
