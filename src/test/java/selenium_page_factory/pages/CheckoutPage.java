package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.CheckoutObject;

public class CheckoutPage extends BasePage {
    public CheckoutObject checkoutObject;

    public CheckoutPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        
        this.checkoutObject = new CheckoutObject(webDriver);
    }

    public void entercvvCode(){
        wait.until(d -> checkoutObject.cvvCodeInput.isDisplayed());
        checkoutObject.cvvCodeInput.sendKeys("9081");
    }

    public void verifyCreditCardNumber(String creditCardNumber){
        wait.until(d -> checkoutObject.creditCardNumber.isDisplayed());
        Assert.assertEquals(checkoutObject.creditCardNumber.getAttribute("value"), creditCardNumber, "Credit Card number doesn't match");
    }

    public void verifyEmail(String email){
        wait.until(d -> checkoutObject.userEmail.isDisplayed());
        Assert.assertEquals(checkoutObject.userEmail.getAttribute("value"), email, "Email doesn't match");
    }

    public void enterNameonCard(){
        wait.until(d -> checkoutObject.inputNameOnCardInput.isDisplayed());
        checkoutObject.inputNameOnCardInput.sendKeys("Tester A");
    }

    public void enterCountry(){
        wait.until(d -> checkoutObject.inputUserCountry.isDisplayed());
        checkoutObject.inputUserCountry.sendKeys("indonesia");
    }

    public void clickRecommendedCountry(){
        wait.until(d -> checkoutObject.recommendedCountry.isDisplayed());
        checkoutObject.recommendedCountry.click();
    }

    public void clickPlaceOrder(){
        wait.until(d -> checkoutObject.buttonPlaceOrder.isDisplayed());
        checkoutObject.buttonPlaceOrder.click();
    }
    
}
