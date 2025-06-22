package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.testng.program.selenium_page_factory.base.BaseObject;

public class RegisterObject extends BaseObject {
    @FindBy(xpath = "//h1[@class='login-title']")
    public WebElement registerPageTitle;

    @FindBy(id = "firstName")
    public WebElement inputFirstName;

    @FindBy(id = "lastName")
    public WebElement inputLastName;

    @FindBy(id = "userEmail")
    public WebElement inputEmail;

    @FindBy(id = "userMobile")
    public WebElement inputPhoneNumber;

    @FindBy(xpath = "//select[@formcontrolname='occupation']")
    public WebElement selectOccupation;

    @FindBy(xpath = "//option[text()='Doctor']")
    public WebElement occupationDoctor;

    @FindBy(xpath = "//option[text()='Student']")
    public WebElement occupationStudent;

    @FindBy(xpath = "//option[text()='Engineer']")
    public WebElement occupationEngineer;

    @FindBy(xpath = "//option[text()='Scientist']")
    public WebElement occupationScientist;

    @FindBy(xpath = "//input[@value='Male']")
    public WebElement genderMale;

    @FindBy(xpath = "//input[@value='Female']")
    public WebElement genderFemale;

    @FindBy(id = "userPassword")
    public WebElement inputPassword;

    @FindBy(id = "confirmPassword")
    public WebElement inputConfirmPassword;

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement checkboxTnC;

    @FindBy(xpath = "//h1[@class='headcolor']")
    public WebElement successRegisterMessage;

    @FindBy(xpath = "//button[text()='Login']")
    public WebElement buttonLogin;

    @FindBy(id = "login")
    public WebElement buttonRegister;

    public RegisterObject(WebDriver webDriver) {
        super(webDriver);
        
        PageFactory.initElements(webDriver, this);
    }
    
}
