package selenium_page_factory.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.CartObject;

public class CartPage extends BasePage {
    public CartObject cartObject;

    public CartPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        
        this.cartObject = new CartObject(webDriver);
    }

    public void verifyProducts(String name, String price) throws InterruptedException{
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        wait.until(d -> cartObject.productNameonCart.isDisplayed());
        Assert.assertEquals(cartObject.productNameonCart.getText(), name, "Product name on cart doesn't match");
        Assert.assertEquals(cartObject.productPriceonCart.getText(), price, "Product price on detail doesn't match");
    }

    public void clickButtonCheckout(){
        wait.until(d -> cartObject.buttonCheckout.isDisplayed());
        cartObject.buttonCheckout.click();
    }
    
}
