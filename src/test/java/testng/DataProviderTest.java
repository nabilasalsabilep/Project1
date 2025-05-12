package testng;

import org.testng.annotations.Test;

import data_provider.DataProviderSample;

public class DataProviderTest {

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProviderSample.class)
    public void testMethod(int productid, String status){
        System.out.println("Test method executed " + productid + " with status: " + status);
    }
}