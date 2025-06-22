package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class CheckoutObject extends BaseObject{
    @FindBy(xpath = "//div[contains(text(), 'CVV Code')]//following-sibling::input")
    public WebElement cvvCodeInput;

    @FindBy(xpath = "//div[contains(text(), 'Credit Card Number')]//following-sibling::input")
    public WebElement creditCardNumber;

    @FindBy(xpath = "//div[@class='user__name mt-5']/input")
    public WebElement userEmail;

    @FindBy(xpath = "//div[@class='user__address']/div/input")
    public WebElement inputUserCountry;

    @FindBy(xpath = "//input[contains(@placeholder, 'Select Country')]//following-sibling::section")
    public WebElement recommendedCountry;

    @FindBy(xpath = "//div[contains(text(), 'Name on Card')]//following-sibling::input")
    public WebElement inputNameOnCardInput;

    @FindBy(xpath = "//div[contains(text(), 'Apply Coupon')]//following-sibling::input")
    public WebElement inputCoupon;

    @FindBy(xpath = "//button[text()='Apply Coupon")
    public WebElement buttonApplyCoupon;

    @FindBy(xpath = "//a[contains(text(), 'Place Order')]")
    public WebElement buttonPlaceOrder;

    public CheckoutObject(WebDriver webDriver){
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
