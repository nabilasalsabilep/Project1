package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class DashboardObject extends BaseObject{
    @FindBy(xpath = "//div[@class='left mt-1']/p")
    public WebElement homePage;

    @FindBy(xpath = "//section[@id='sidebar']//descendant::input[@name='search']")
    public WebElement inputSearch;

    @FindBy(xpath = "//div[@class='card']/div/h5/b")
    public WebElement productNameonList;

    @FindBy(xpath = "//div[@class='text-muted']")
    public WebElement productPriceonList;

    @FindBy(xpath = "//section[@id='products']//descendant::button[contains(text(), 'View')]")
    public WebElement buttonView;

    public DashboardObject(WebDriver webDriver) {
        super(webDriver);
        
        PageFactory.initElements(webDriver, this);
    }
    
}
