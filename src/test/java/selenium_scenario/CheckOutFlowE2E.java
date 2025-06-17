package selenium_scenario;

import java.time.Duration;
import java.util.UUID;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

class CheckOutFlowE2E{
    public WebDriver webDriver;
    public Wait<WebDriver> wait;

    public String email;
    public String password;
    public String productName;
    public String productPrice;
    public String cvvCode;
    public String nameCard;
    public String country;

    @BeforeSuite
    @Parameters("browser")
    public void openBrowser(String browser){
        if (browser.equalsIgnoreCase("Chrome")) {
            System.out.println("Browser Start...");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            webDriver = new ChromeDriver(options);
        }
        
        webDriver.get("https://rahulshettyacademy.com/client");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        String loginPage = webDriver.findElement(By.xpath("//div[@class='banner']//h3")).getText();

        Assert.assertEquals(loginPage, "We Make Your Shopping Simple", "Login page text doesn't exist");

        // email = "testerA@gmail.com";
        // password = "P@ssword!1";
        productName = "IPHONE 13 PRO";
    }

    @Test
    public void successfulRegister() {
        System.out.println("Valid registering data is running");

        WebElement buttonRegisterHere = webDriver.findElement(By.xpath("//section//descendant::p[@class='login-wrapper-footer-text']"));
    
        wait.until(d -> buttonRegisterHere.isDisplayed());
        buttonRegisterHere.click();

        WebElement registerPageTitle = webDriver.findElement(By.xpath("//h1[@class='login-title']"));
        WebElement inputFirstName = webDriver.findElement(By.id("firstName"));
        WebElement inputLastName = webDriver.findElement(By.id("lastName"));
        WebElement inputEmail = webDriver.findElement(By.id("userEmail"));
        WebElement inputPhoneNumber = webDriver.findElement(By.id("userMobile"));
        WebElement selectOccupation = webDriver.findElement(By.xpath("//select[@formcontrolname='occupation']"));
        WebElement occupationWithDoctor = webDriver.findElement(By.xpath("//option[text()='Doctor']"));
        WebElement occupationWithStudent = webDriver.findElement(By.xpath("//option[text()='Student']"));
        WebElement occupationWithEngineer = webDriver.findElement(By.xpath("//option[text()='Engineer']"));
        WebElement occupationWithScientist = webDriver.findElement(By.xpath("//option[text()='Scientist']"));
        WebElement genderMale = webDriver.findElement(By.xpath("//input[@value='Male']"));
        WebElement genderFemale = webDriver.findElement(By.xpath("//input[@value='Male']"));
        WebElement inputPassword = webDriver.findElement(By.id("userPassword"));
        WebElement inputConfirmPassword = webDriver.findElement(By.id("confirmPassword"));
        WebElement checkboxTnC = webDriver.findElement(By.xpath("//input[@type='checkbox']"));
        WebElement buttonRegister = webDriver.findElement(By.id("login"));
       
        wait.until(d -> registerPageTitle.isDisplayed());
        inputFirstName.sendKeys("User");
        inputLastName.sendKeys("Tester");

        String uniqueId = UUID.randomUUID().toString().substring(0, 3);
        email =  "testuser_" + uniqueId + "@example.com";
        inputEmail.sendKeys(email);

        inputPhoneNumber.sendKeys("9191239912");
        selectOccupation.click();
        occupationWithEngineer.click();
        genderFemale.click();

        password = "P@ssword!1";
        inputPassword.sendKeys(password);
        inputConfirmPassword.sendKeys(password);
        checkboxTnC.click();
        buttonRegister.click();

        WebElement successMessage = webDriver.findElement(By.xpath("//h1[@class='headcolor']"));
        WebElement buttonLogin = webDriver.findElement(By.xpath("//button[text()='Login']"));

        Assert.assertEquals(successMessage.getText(), "Account Created Successfully", "The message doesn't match");

        buttonLogin.click();

        String loginPage = webDriver.findElement(By.xpath("//div[@class='banner']//h3")).getText();

        Assert.assertEquals(loginPage, "We Make Your Shopping Simple", "Login page text doesn't exist");
    }

    @Test(dependsOnMethods = "successfulRegister")
    public void successfulLogin(){
        System.out.println("Valid credentials test is running.");

        WebElement inputEmail = webDriver.findElement(By.id("userEmail"));
        WebElement inputPassword = webDriver.findElement(By.id("userPassword"));
        WebElement buttonLogin = webDriver.findElement(By.id("login"));

        wait.until(d -> inputEmail.isDisplayed());
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonLogin.click();

        WebElement homePage = webDriver.findElement(By.xpath("//div[@class='left mt-1']/p"));

        wait.until(d -> homePage.isDisplayed());

        Assert.assertEquals(homePage.getText(), "Automation Practice", "Home page text does not match!");
    }

    @Test(dependsOnMethods = "successfulLogin")
    public void searchAndViewProduct() throws Exception{
        WebElement inputSearchBox = webDriver.findElement(By.xpath("//section[@id='sidebar']//descendant::input[@name='search']"));
        
        wait.until(d -> inputSearchBox.isDisplayed());

        inputSearchBox.sendKeys(productName);
        inputSearchBox.sendKeys(Keys.ENTER);

        Thread.sleep(Duration.ofSeconds(3).toMillis());

        WebElement productNameonList = webDriver.findElement(By.xpath("//div[@class='card']/div/h5/b"));
        WebElement productPriceonList = webDriver.findElement(By.xpath("//div[@class='text-muted']"));
        WebElement buttonView = webDriver.findElement(By.xpath("//section[@id='products']//descendant::button[contains(text(), 'View')]"));

        Assert.assertEquals(productNameonList.getText(), productName, "Product name on search result doesn't match");

        productPrice = productPriceonList.getText();

        buttonView.click();
    }

    @Test(dependsOnMethods = "searchAndViewProduct")
    public void verifyDataProductInProductViewPage() throws InterruptedException {
        WebElement titleProduct = webDriver.findElement(By.xpath("//h2[contains(text(), '" + productName + "')]"));
        WebElement productPriceonDetail = webDriver.findElement(By.xpath("//h3[contains(text(), '" + productPrice + "')]"));
        
        wait.until(d -> titleProduct.isDisplayed());

        Assert.assertEquals(titleProduct.getText(), productName, "Product name on detail doesn't match");
        Assert.assertEquals(productPriceonDetail.getText(), productPrice, "Product price on detail doesn't match");
    }

    @Test(dependsOnMethods = "verifyDataProductInProductViewPage")
    public void doATCAndOpenCartPage() throws InterruptedException{
        WebElement buttonAddtoCart = webDriver.findElement(By.xpath("//button[text()='Add to Cart']"));
        WebElement buttonOpenCart = webDriver.findElement(By.xpath("//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]"));

        buttonAddtoCart.click();

        WebElement cartNumber = webDriver.findElement(By.xpath("//button/label"));

        // wait.until(d -> cartNumber.isDisplayed());
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        Assert.assertEquals(cartNumber.getText(), "1", "Cart number not increment");

        buttonOpenCart.click();

        Thread.sleep(Duration.ofSeconds(5).toMillis());

        WebElement productNameonCart = webDriver.findElement(By.xpath("//div[@class='cartSection']/h3"));
        WebElement productPriceonCart = webDriver.findElement(By.xpath("//div[@class='prodTotal cartSection']/p"));

        Assert.assertEquals(productNameonCart.getText(), productName, "Product name doesn't match");
        Assert.assertEquals(productPriceonCart.getText(), productPrice, "Product price doesn't match");
    }

    @Test(dependsOnMethods = "doATCAndOpenCartPage")
    public void doCheckout() throws InterruptedException{
        WebElement buttonCheckout = webDriver.findElement(By.xpath("//button[text() = 'Checkout']"));

        buttonCheckout.click();

        // Thread.sleep(Duration.ofSeconds(3).toMillis());

        WebElement productNameonCheckout = webDriver.findElement(By.xpath("//div[@class='item__title']"));
        WebElement productPriceonCheckout = webDriver.findElement(By.xpath("//div[@class='item__price']"));

        Assert.assertEquals(productNameonCheckout.getText(), productName, "Product name doesn't match");
        Assert.assertEquals(productPriceonCheckout.getText(), productPrice, "Product price doesn't match");
    }

    @Test(dependsOnMethods = "doCheckout")
    public void doPlaceOrder(){
        WebElement cvvCodeInput = webDriver.findElement(By.xpath("//div[contains(text(), 'CVV Code')]//following-sibling::input"));
        WebElement creditCardNumber = webDriver.findElement(By.xpath("//div[contains(text(), 'Credit Card Number')]//following-sibling::input"));
        WebElement userEmail = webDriver.findElement(By.xpath("//div[@class='user__name mt-5']/input"));
        WebElement inputUserCountry = webDriver.findElement(By.xpath("//div[@class='user__address']/div/input"));
        WebElement inputNameonCard = webDriver.findElement(By.xpath("//div[contains(text(), 'Name on Card')]//following-sibling::input"));
        WebElement inputCoupon = webDriver.findElement(By.xpath("//div[contains(text(), 'Apply Coupon')]//following-sibling::input"));
        WebElement buttonApplyCoupon = webDriver.findElement(By.xpath("//button[text()='Apply Coupon']"));
        WebElement buttonPlaceOrder = webDriver.findElement(By.xpath("//a[contains(text(), 'Place Order')]"));

        wait.until(d -> cvvCodeInput.isDisplayed());

        Assert.assertEquals(creditCardNumber.getAttribute("value"), "4542 9931 9292 2293", "Credit card number doesn't match");
        Assert.assertEquals(userEmail.getAttribute("value"), email, "Email doesn't match");
        
        cvvCodeInput.sendKeys("9128");
        inputUserCountry.sendKeys("Indonesia");

        WebElement recommendationCountry = webDriver.findElement(By.xpath("//input[contains(@placeholder, 'Select Country')]//following-sibling::section"));

        recommendationCountry.click();
        inputNameonCard.sendKeys("Tester A");

        buttonPlaceOrder.click();
    }

    @Test(dependsOnMethods = "doPlaceOrder")
    public void verifyOrderCreated() throws InterruptedException{
        Thread.sleep(Duration.ofSeconds(3).toMillis());
        WebElement successMessage = webDriver.findElement(By.xpath("//*[contains(text(), 'Thankyou for the order')]"));
        WebElement productNameonOrderCreated = webDriver.findElement(By.xpath("//*[contains(text(), '" + productName + "')]"));
        WebElement productPriceonOrderCreated = webDriver.findElement(By.xpath("//*[contains(text(), '" + productPrice + "')]"));

        Assert.assertEquals(successMessage.getText(), "THANKYOU FOR THE ORDER.", "Success message is wrong");
        Assert.assertEquals(productNameonOrderCreated.getText(), productName, "Product name doesn't match");
        Assert.assertEquals(productPriceonOrderCreated.getText(), productPrice, "Product price doesn't match");
    }

    @AfterSuite
    public void tearDown(){
        webDriver.quit();
    }

}