package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class LoginObject extends BaseObject {
    @FindBy(xpath = "//div[@class='banner']//h3")
    public WebElement loginPage;

    @FindBy(xpath = "//section//descendant::p[@class='login-wrapper-footer-text']")
    public WebElement buttonRegister;
    
    @FindBy(id = "userEmail")
    public WebElement inputEmail;

    @FindBy(id = "userPassword")
    public WebElement inputPassword;

    @FindBy(id = "login")
    public WebElement buttonLogin;

    public LoginObject(WebDriver webDriver) {
        super(webDriver);
        
        PageFactory.initElements(webDriver, this);
    }
    
}
