package testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SimpleTest {

    public static int x = 1;

    @BeforeSuite
    public void setUp(){
        System.out.println("Setting up the test suite...");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Running before each test...");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("Running after each test...");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Running before the class...");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("Running after the class...");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Running before each test method...");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Running after each test method...");
    }

    @AfterSuite
    public void tearDown(){
        System.out.println("Tearing down the test suite...");
    }

    @Test 
    public void testMethod() throws InterruptedException{
        System.out.println("This is a simple test method.");
        System.out.println("Thread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
        x = 1;
        Thread.sleep(1000);
        Assert.assertTrue(x == 1, "x should be equal to 1.");
    }

    @Test(dependsOnMethods = {"testMethod"})
    public void testMethod2()throws InterruptedException {
        System.out.println("This is a simple test method 2.");
        System.out.println("Thread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
        x = 2;
        Thread.sleep(500);
        Assert.assertTrue(x == 2, "x should be equal to 2.");
    }

    @Test (dependsOnMethods = {"testMethod2"})
    public void testMethod3() throws InterruptedException {
        System.out.println("This is a simple test method 3.");
        System.out.println("Thread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
        x = 3;
        Thread.sleep(500);
        Assert.assertTrue(x == 3, "x should be equal to 3.");
    }
}
