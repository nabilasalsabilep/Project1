package selenium_page_factory.object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class ProductDisplayObject extends BaseObject {
    @FindBy(xpath = "//button[text()='Add to Cart']")
    public WebElement buttonAddtoCart;

    @FindBy(xpath = "//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]")
    public WebElement buttonOpenCart;

    @FindBy(xpath = "//button/label")
    public WebElement cartNumber;

    public WebElement dynamicTitleProduct(String productName){
        return webDriver.findElement(By.xpath("//h2[contains(text(), '" + productName + "')]"));
    }

    public WebElement dynamicProductPrice(String productPrice) {
        return webDriver.findElement(By.xpath("//h3[contains(text(), '" + productPrice + "')]"));
    }

    public ProductDisplayObject(WebDriver webDriver) {
        super(webDriver);
        
        PageFactory.initElements(webDriver, this);
    }
    
}
