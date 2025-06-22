package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class CartObject extends BaseObject{
    @FindBy(xpath = "//div[@class='cartSection']/h3")
    public WebElement productNameonCart;

    @FindBy(xpath = "//div[@class='prodTotal cartSection']/p")
    public WebElement productPriceonCart;

    @FindBy(xpath = "//button[text() = 'Checkout']")
    public WebElement buttonCheckout;

    public CartObject(WebDriver webDriver){
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
