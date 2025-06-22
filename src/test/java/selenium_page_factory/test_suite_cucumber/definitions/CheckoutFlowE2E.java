package selenium_page_factory.test_suite_cucumber.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium_page_factory.pages.CartPage;
import selenium_page_factory.pages.CheckoutPage;
import selenium_page_factory.pages.DashboardPage;
import selenium_page_factory.pages.LoginPage;
import selenium_page_factory.pages.OrderPage;
import selenium_page_factory.pages.ProductDisplayPage;
import selenium_page_factory.pages.RegisterPage;

public class CheckoutFlowE2E {
    public RegisterPage registerPage;
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public ProductDisplayPage productDisplayPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public OrderPage orderPage;

    public String productName;
    public String productPrice;
    public String email;

    @Then("Init all pages for run automation for checkout flow")
    public void initAllPages() {
        this.registerPage = new RegisterPage(Hook.webDriver, Hook.wait);
        this.loginPage = new LoginPage(Hook.webDriver, Hook.wait);
        this.dashboardPage = new DashboardPage(Hook.webDriver, Hook.wait);
        this.productDisplayPage = new ProductDisplayPage(Hook.webDriver, Hook.wait);
        this.cartPage = new CartPage(Hook.webDriver, Hook.wait);
        this.checkoutPage = new CheckoutPage(Hook.webDriver, Hook.wait);
        this.orderPage = new OrderPage(Hook.webDriver, Hook.wait);
    }

    @When("Click button Register on Login page")
    public void clickButtonRegisterfromLoginPage(){
        loginPage.clickButtonRegister();
    }

    @And("Input first name, last name, phone number, select occupation with engineer, select female gender")
    public void fillInRequiredData(){
        registerPage.enterFirstName();
        registerPage.enterLastName();
        registerPage.enterEmail();
        email = registerPage.setEmail();
        registerPage.enterPhoneNumber();
        registerPage.clickSelectOccupation();
        registerPage.clickOccupationEngineer();
        registerPage.clickGenderFemale();
    }

    @And("Input password & confirm password {string}")
    public void fillInPasswordandConfrimPassword(String password){
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(password);
    }

    @And("Click TnC checkbox on Register page")
    public void clickTnCCheckbox(){
        registerPage.clickTnCCheckbox();
    }

    @And("Click button Register for register new account")
    public void clickButtonRegister(){
        registerPage.clickButtonRegister();
    }

    @And("Click button Login to open the Login page")
    public void clickButtonLoginAfterRegister(){
        registerPage.verifySuccessRegisterMessage();
        registerPage.clickButtonLogin();
    }

    @Then("Input email {string} and password {string} in login page for checkout flow")
    public void inputEmailAndPassword(String userEmail, String password){
        userEmail.replace("{email}", email);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @Then("Click login button for checkout flow")
    public void clickButtonLogin(){
        loginPage.clickButtonLogin();
    }

    @Then("Search product with keyword {string} for checkout flow")
    public void searchProduct(String value){
        dashboardPage.doSearch(value);
        productName = value;
    }

    @Then("Verify the product name contains inputted keyword")
    public void verifySearchResult(){
        dashboardPage.verifySearchResult(productName);
        productName = dashboardPage.getProductName();
        productPrice = dashboardPage.getProductPrice();
    }

    @Then("Click view product for checkout flow")
    public void clickViewProduct(){
        dashboardPage.clickButtonView();
    }

    @And("Verify product name, to make sure the product name contains inputted keyword is shown for checkout flow")
    public void verifyProductDisplayTitle() throws InterruptedException{
        productDisplayPage.verifyProductName(productName);
    }

    @And("Verify product price is the same as the price on the dashboard")
    public void verifyProductDisplayPrice(){
        productDisplayPage.verifyProductPrice(productPrice);
    }

    @Then("Click Add to Cart button for checkout flow")
    public void clickButtonAddToCartfromProductDisplay() throws InterruptedException{
        productDisplayPage.clickButtonAddToCart();
    }

    @Then("Verify product name, product price on Cart page are the same as the data from product display")
    public void verifyCartPage() throws InterruptedException{
        cartPage.verifyProducts(productName, productPrice);
    }

    @Then("Click checkout button for checkout flow")
    public void clickButtonCheckout(){
        cartPage.clickButtonCheckout();
    }

    @Then("Verify credit card number is match with {string}")
    public void verifyCreditCardNumber(String creditCardNumber){
        checkoutPage.verifyCreditCardNumber(creditCardNumber);
    }

    @Then("Verify email is match with {string}")
    public void verifyEmailonChekcoutPage(String userEmail){
        userEmail = email;
        checkoutPage.verifyEmail(userEmail);
    }

    @Then("Enter the details payment for checkout flow")
    public void enterPaymentDetails(){
        checkoutPage.entercvvCode();
        checkoutPage.enterNameonCard();
        checkoutPage.enterCountry();
        checkoutPage.clickRecommendedCountry();
    }

    @Then("Click place order button for checkout flow")
    public void clickButtonPlaceOrder(){
        checkoutPage.clickPlaceOrder();
    }

    @And("Verify order, to make sure product name contains inputted keyword with product price that is same as the product price from the checkout page successfully ordered")
    public void verifyOrder() throws InterruptedException{
        orderPage.verifyOrderCreated(productName, productPrice);
    }

}
