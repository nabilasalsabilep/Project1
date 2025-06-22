package selenium_page_factory.pages;

import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.RegisterObject;

public class RegisterPage extends BasePage {
    public static String email;
    public RegisterObject registerObject;

    public RegisterPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.registerObject = new RegisterObject(webDriver);
    }

    public void enterFirstName(){
        wait.until(d -> registerObject.inputFirstName.isDisplayed());
        registerObject.inputFirstName.sendKeys("User");
    }

    public void enterLastName(){
        wait.until(d -> registerObject.inputLastName.isDisplayed());
        registerObject.inputLastName.sendKeys("Test");
    }

    public void enterEmail(){
        String uniqueId = UUID.randomUUID().toString().substring(0, 3);
        RegisterPage.email =  "testuser_" + uniqueId + "@example.com";
        registerObject.inputEmail.sendKeys(RegisterPage.email);
    }

    public String setEmail(){
        return RegisterPage.email;
    }

    public void enterPhoneNumber(){
        wait.until(d -> registerObject.inputPhoneNumber.isDisplayed());
        registerObject.inputPhoneNumber.sendKeys("9191239912");
    }

    public void clickSelectOccupation(){
        wait.until(d -> registerObject.selectOccupation.isDisplayed());
        registerObject.selectOccupation.click();
    }

    public void clickOccupationEngineer(){
        wait.until(d -> registerObject.occupationEngineer.isDisplayed());
        registerObject.occupationEngineer.click();
    }

    public void clickGenderFemale(){
        wait.until(d -> registerObject.genderFemale.isDisplayed());
        registerObject.genderFemale.click();
    }

    public void enterPassword(String password){
        wait.until(d -> registerObject.inputPassword.isDisplayed());
        registerObject.inputPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String password){
        wait.until(d -> registerObject.inputConfirmPassword.isDisplayed());
        registerObject.inputConfirmPassword.sendKeys(password);
    }

    public void clickTnCCheckbox(){
        wait.until(d -> registerObject.checkboxTnC.isDisplayed());
        registerObject.checkboxTnC.click();
    }

    public void clickButtonRegister(){
        wait.until(d -> registerObject.buttonRegister.isDisplayed());
        registerObject.buttonRegister.click();
    }

    public void verifySuccessRegisterMessage(){
        wait.until(d -> registerObject.successRegisterMessage.isDisplayed());
        Assert.assertEquals(registerObject.successRegisterMessage.getText(), "Account Created Successfully", "The message doesn't match");
    }

    public void clickButtonLogin(){
        wait.until(d -> registerObject.buttonLogin.isDisplayed());
        registerObject.buttonLogin.click();
    }
    
}
