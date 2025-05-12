package testng;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.Product;

public class AssertTest {

    @Test
    public void testMethod(){
        // System.out.println("This is a simple test method.");
        Assert.assertEquals("hello", "hello");
        Product actual = new Product("Laptop", 1000);
        Product expectation = new Product("Laptop", 1000);
        Assert.assertEquals(actual, expectation);

        List<String> actualList = new ArrayList<String>();
        actualList.add("hello");
        actualList.add("hi");
        actualList.add("hola");

        Predicate<String> expectedList = new Predicate<String>() {
            @Override
            public boolean test(String s){
                return s.equals("hello");
            }
            
        };

        Assert.assertListContains(actualList, expectedList, "data list are not equal.");

    }

}
