package selenium_page_factory.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.ProductDisplayObject;

public class ProductDisplayPage extends BasePage {
    public ProductDisplayObject productDisplayObject;

    public ProductDisplayPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        
        this.productDisplayObject = new ProductDisplayObject(webDriver);
    }

    public void verifyProductName(String productName) throws InterruptedException{
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        wait.until(d -> productDisplayObject.dynamicTitleProduct(productName).isDisplayed());
        Assert.assertEquals(productDisplayObject.dynamicTitleProduct(productName).getText(), productName);
    }

    public void verifyProductPrice(String productPrice){
        wait.until(d -> productDisplayObject.dynamicProductPrice(productPrice).isDisplayed());
        Assert.assertEquals(productDisplayObject.dynamicProductPrice(productPrice).getText(), productPrice);
    }

    public void clickButtonAddToCart() throws InterruptedException{
        wait.until(d -> productDisplayObject.buttonAddtoCart.isDisplayed());
        productDisplayObject.buttonAddtoCart.click();

        Thread.sleep(Duration.ofSeconds(2).toMillis());
        wait.until(d -> productDisplayObject.cartNumber.isDisplayed());
        if (productDisplayObject.cartNumber.getText().equals("1")) {
            wait.until(d -> productDisplayObject.buttonOpenCart.isDisplayed());
            productDisplayObject.buttonOpenCart.click();
        } else {
            System.out.println("cartNumber => " + productDisplayObject.cartNumber);
            Assert.assertTrue(false, "cart number not increment");
        }
    }
    
}
