package selenium_page_factory.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.demo.testng.program.selenium_page_factory.base.BasePage;

import selenium_page_factory.object_repository.DashboardObject;

public class DashboardPage extends BasePage {
    public static String productName;
    public static String productPrice;
    public DashboardObject dashboardObject;

    public DashboardPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.dashboardObject = new DashboardObject(webDriver);
    }

    public void doSearch(String value){
        wait.until(d -> dashboardObject.inputSearch.isDisplayed());
        dashboardObject.inputSearch.sendKeys(value);
        dashboardObject.inputSearch.sendKeys(Keys.ENTER);
    }

    public void verifySearchResult(String value){
        wait.until(d -> dashboardObject.productNameonList.isDisplayed());
        dashboardObject.productNameonList.getText().contains(value);
    }

    public String getProductName(){
        wait.until(d -> dashboardObject.productNameonList.isDisplayed());
        DashboardPage.productName = dashboardObject.productNameonList.getText();
        return DashboardPage.productName;
    }

    public String getProductPrice(){
        wait.until(d -> dashboardObject.productPriceonList.isDisplayed());
        DashboardPage.productPrice = dashboardObject.productPriceonList.getText();
        return DashboardPage.productPrice;
    }

    public void clickButtonView(){
        wait.until(d -> dashboardObject.buttonView.isDisplayed());
        dashboardObject.buttonView.click();
    }
    
}
